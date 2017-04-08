package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public abstract class JsonElement<T> {

    final JsonSerializer jsonSerializer;

    public JsonElement(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public abstract void write(T obj, JsonWriter writer) throws IOException;

    void writeObject(Object obj, JsonWriter writer) throws IOException {
        if (obj == null) {
            writer.writeNull();
        } else {
            jsonSerializer.getElement(obj.getClass()).write(obj, writer);
        }
    }

}
