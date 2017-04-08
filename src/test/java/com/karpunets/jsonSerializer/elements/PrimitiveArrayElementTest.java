package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Karpunets
 * @since 14.12.2016
 */
public class PrimitiveArrayElementTest {

    private static StringWriter writer;
    private static JsonWriter jsonWriter;
    private static final int[] values = {1, 6, 32, 97, 13, 54, 12, 57};


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
        PrimitiveArrayElement primitiveArrayElement =  new PrimitiveArrayElement(new JsonSerializer());
        primitiveArrayElement.write(values, jsonWriter);
        System.out.println(writer.toString());
    }

}