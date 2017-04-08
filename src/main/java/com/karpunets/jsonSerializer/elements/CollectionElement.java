package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class CollectionElement extends JsonElement<Collection> {
    public CollectionElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(Collection collection, JsonWriter writer) throws IOException {
        writer.writeArrayBegin();
        for (Object obj: collection) {
            writeObject(obj, writer);
            writer.writeSeparator();
        }
        writer.writeArrayEnd();
    }
}
