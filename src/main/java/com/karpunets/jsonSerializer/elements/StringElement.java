package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class StringElement extends JsonElement<String> {
    public StringElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(String obj, JsonWriter writer) throws IOException {
        writer.writeString(obj);
    }
}
