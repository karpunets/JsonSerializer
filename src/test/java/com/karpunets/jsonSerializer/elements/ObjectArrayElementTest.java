package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * @author Karpunets
 * @since 14.12.2016
 */
public class ObjectArrayElementTest {

    private static StringWriter writer;
    private static JsonWriter jsonWriter;
    private static final String[] value = {"a", "ab", "cd", "asd", "sa", "11", "123444"};


    @Before
    public void setUp() throws Exception {
        writer = new StringWriter();
        jsonWriter = new JsonWriter(writer);
    }

    @After
    public void tearDown() throws Exception {
        writer.close();
    }

    @Test
    public void write() throws Exception {
        ObjectArrayElement objectArrayElement =  new ObjectArrayElement(new JsonSerializer());
        objectArrayElement.write(value, jsonWriter);
        System.out.println(writer.toString());
    }

}