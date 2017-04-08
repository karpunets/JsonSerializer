package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class MapElement extends JsonElement<Map> {
    public MapElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(Map map, JsonWriter writer) throws IOException {
        writer.writeObjectBegin();
        for (Object key : map.keySet()) {
            writer.writeString(key.toString());
            writer.writePropertySeparator();
            Object obj = map.get(key);
            writeObject(obj, writer);
            writer.writeSeparator();
        }
        writer.writeObjectEnd();
    }
}
