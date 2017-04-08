package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class BooleanElement extends JsonElement<Boolean> {
    public BooleanElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(Boolean obj, JsonWriter writer) throws IOException {
        writer.writeBoolean(obj);
    }
}
