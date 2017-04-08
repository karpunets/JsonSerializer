package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * @author Karpunets
 * @since 14.12.2016
 */
public class NumberElementTest {

    private static StringWriter writer;
    private static JsonWriter jsonWriter;
    private static final Integer value = 1234;


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
        NumberElement numberElement =  new NumberElement(new JsonSerializer());
        numberElement.write(value, jsonWriter);
        System.out.println(writer.toString());
    }

}