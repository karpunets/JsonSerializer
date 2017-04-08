package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class PrimitiveArrayElement extends JsonElement {

    public PrimitiveArrayElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(Object array, JsonWriter writer) throws IOException {
        JsonElement jsonElement = jsonSerializer.getElement(Array.get(array, 0).getClass());

        writer.writeArrayBegin();
        for (int i = 0; i < Array.getLength(array); i++) {
            jsonElement.write(Array.get(array, i), writer);
            writer.writeSeparator();
        }
        writer.writeArrayEnd();
    }

}
