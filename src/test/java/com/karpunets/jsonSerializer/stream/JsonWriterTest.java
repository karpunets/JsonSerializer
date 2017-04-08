package com.karpunets.jsonSerializer.stream;

import com.karpunets.example.company.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Karpunets
 * @since 13.12.2016
 */
public class JsonWriterTest {

    private static StringWriter writer;
    private static JsonWriter jsonWriter;


    @Before
    public void setUp() throws Exception {
        writer = new StringWriter();
        jsonWriter = new JsonWriter(writer);
    }

    @Test
    public void writeObjectBegin() throws Exception {
        jsonWriter.writeObjectBegin();
        assertEquals(writer.toString(), "{");
    }

    @Test
    public void writeObjectEnd() throws Exception {
        jsonWriter.writeObjectEnd();
        assertEquals(writer.toString(), "}");
    }

    @Test
    public void writeArrayBegin() throws Exception {
        jsonWriter.writeArrayBegin();
        assertEquals(writer.toString(), "[");
    }

    @Test
    public void writeArrayEnd() throws Exception {
        jsonWriter.writeArrayEnd();
        assertEquals(writer.toString(), "]");
    }

    @Test
    public void writeString() throws Exception {
        String test = "test";
        jsonWriter.writeString(test);
        assertEquals(writer.toString(), "\"" + test + "\"");
    }

    @Test
    public void writeNumber() throws Exception {
        Number test = 5;
        jsonWriter.writeNumber(test);
        assertEquals(writer.toString(), test.toString());
    }

    @Test
    public void writePropertySeparator() throws Exception {
        jsonWriter.writePropertySeparator();
        assertEquals(writer.toString(), ":");
    }

    @Test
    public void writeBoolean() throws Exception {
        boolean test = true;
        jsonWriter.writeBoolean(test);
        assertEquals(writer.toString(), test ? "true" : "false");
    }

    @Test
    public void writeNull() throws Exception {
        jsonWriter.writeNull();
        assertEquals(writer.toString(), "null");
    }

    @After
    public void tearDown() throws Exception {
        writer.close();
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerException() throws Exception {
        jsonWriter = new JsonWriter(null);
    }

}