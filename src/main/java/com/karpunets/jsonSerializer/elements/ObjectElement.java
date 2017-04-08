package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.annotations.JsonIgnore;
import com.karpunets.jsonSerializer.annotations.JsonProperty;
import com.karpunets.jsonSerializer.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class ObjectElement extends JsonElement {

    private List<Field> fields = new ArrayList<>();

    public ObjectElement(JsonSerializer jsonSerializer) {
        super(jsonSerializer);
    }

    public void write(Object obj, JsonWriter writer) throws IOException {
        writer.writeObjectBegin();

        if (fields.isEmpty()) {
            fields.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
            deleteDuplicates(fields);
            for (int i = 0; i < fields.size(); i++) {
                if (fields.get(i).getAnnotation(JsonIgnore.class) != null) {
                    fields.remove(i);
                    continue;
                }
            }
        }

        for (Field field : fields) {
            JsonProperty property = field.getAnnotation(JsonProperty.class);
            if (property == null) {
                int modifier = field.getModifiers();
                if (!Modifier.isPublic(modifier) || Modifier.isSynchronized(modifier)) {
                    continue;
                }
                writer.writeString(field.getName());
            } else {
                field.setAccessible(true);
                if (property.name().equals("")) {
                    writer.writeString(field.getName());
                } else {
                    writer.writeString(property.name());
                }
            }
            writer.writePropertySeparator();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            writeObject(value, writer);
            writer.writeSeparator();

        }


        writer.writeObjectEnd();
    }

    private void deleteDuplicates(List<Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            JsonProperty property = field.getAnnotation(JsonProperty.class);
            if (property != null) {
                for (int q = 0; q < fields.size(); q++) {
                    Field fieldDuplicate = fields.get(q);
                    if (field != fieldDuplicate && property.name().equals(fieldDuplicate.getName())) {
                        fields.remove(q);
                    }
                }
            }
        }
    }

}

