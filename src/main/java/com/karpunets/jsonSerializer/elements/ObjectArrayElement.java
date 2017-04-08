package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class ObjectArrayElement extends JsonElement<Object[]> {
    public ObjectArrayElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(Object[] objects, JsonWriter writer) throws IOException {
        writer.writeArrayBegin();
        for (Object obj : objects) {
            writeObject(obj, writer);
            writer.writeSeparator();
        }
        writer.writeArrayEnd();
    }
}
