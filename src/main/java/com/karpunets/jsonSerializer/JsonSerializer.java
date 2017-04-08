package com.karpunets.jsonSerializer;

import com.karpunets.jsonSerializer.elements.*;
import com.karpunets.jsonSerializer.stream.IndentedJsonWriter;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class JsonSerializer {

    private final Lock lock = new ReentrantLock();

    private static final Set<Class> PRIMITIVE_ARRAY_CLASSES = new HashSet<>();
    private static final Set<Class> PRIMITIVE_CLASSES = new HashSet<>();
    private static Map<Class, JsonElement> elementCache = new HashMap<>();
    private boolean indent;
    private int indentSize = 4;
    private JsonWriter jsonWriter;
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    static {
        Collections.addAll(PRIMITIVE_ARRAY_CLASSES, new Class[]{byte[].class, short[].class, int[].class, long[].class, float[].class, double[].class, char[].class, boolean[].class});
        Collections.addAll(PRIMITIVE_CLASSES, new Class[]{Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class});
    }

    public void setIndent(boolean indent) {
        this.indent = indent;
    }

    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }

    public String serialize(Object obj) {
        StringWriter stringWriter = new StringWriter();
        serialize(obj, stringWriter);
        return stringWriter.toString();
    }

    public void serialize(Object obj, OutputStream stream) {
        serialize(obj, stream, DEFAULT_CHARSET);
    }

    public void serialize(Object obj, OutputStream stream, Charset charset) {
        serialize(obj, new OutputStreamWriter(stream, charset));
    }

    public void serialize(Object obj, Writer writer) {
        if (indent) {
            jsonWriter = new IndentedJsonWriter(writer);
            ((IndentedJsonWriter) jsonWriter).setIndentSize(indentSize);
        } else {
            jsonWriter = new JsonWriter(writer);
        }
        try {
            serialize(obj, jsonWriter);
            jsonWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void serialize(Object obj, JsonWriter writer) throws IOException {
        JsonElement jsonElement = getElement(obj.getClass());
        jsonElement.write(obj, writer);
    }

    public JsonElement getElement(Class elementClass) {

        if (elementCache.containsKey(elementClass)) {
            return elementCache.get(elementClass);
        }

        if (elementCache.isEmpty()) {
            lock.lock();
            elementCache.put(Boolean.class, new BooleanElement(this));
            elementCache.put(Collection.class, new CollectionElement(this));
            elementCache.put(Map.class, new MapElement(this));
            elementCache.put(Number.class, new NumberElement(this));
            elementCache.put(Object[].class, new ObjectArrayElement(this));
            elementCache.put(String.class, new StringElement(this));
            elementCache.put(Array.class, new PrimitiveArrayElement(this));
            elementCache.put(Object.class, new ObjectElement(this));
            lock.unlock();
        }

        elementClass = getParentClass(elementClass);
        if (!elementCache.containsKey(elementClass)) {
            ObjectElement objectElement = new ObjectElement(this);
            return objectElement;
        }
        JsonElement jsonElement = elementCache.get(elementClass);

        return jsonElement;
    }

    private Class getParentClass(Class childClass) {
        if (Collection.class.isAssignableFrom(childClass)) {
            return Collection.class;
        } else if (Map.class.isAssignableFrom(childClass)) {
            return Map.class;
        } else if (isNumber(childClass)) {
            return Number.class;
        } else if (Object[].class.isAssignableFrom(childClass)) {
            return Object[].class;
        } else if (isPrimitiveArray(childClass)) {
            return Array.class;
        }
        return childClass;
    }

    private boolean isNumber(Class elementClass) {
        for (Class primitiveClass : PRIMITIVE_CLASSES) {
            if (elementClass.isAssignableFrom(primitiveClass)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPrimitiveArray(Class elementClass) {
        for (Class primitiveClass : PRIMITIVE_ARRAY_CLASSES) {
            if (primitiveClass.isAssignableFrom(elementClass)) {
                return true;
            }
        }
        return false;
    }

}