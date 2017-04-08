package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class NumberElement extends JsonElement<Number> {
    public NumberElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(Number obj, JsonWriter writer) throws IOException {
        writer.writeNumber(obj);
    }
}
