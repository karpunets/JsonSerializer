package com.karpunets.jsonSerializer.elements;

import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.stream.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Karpunets
 * @since 14.12.2016
 */
public class MapElementTest {

    private static StringWriter writer;
    private static JsonWriter jsonWriter;
    private static final Map<String, Integer> value = new HashMap<String, Integer>();


    @Before
    public void setUp() throws Exception {
        writer = new StringWriter();
        jsonWriter = new JsonWriter(writer);

        value.put("1", 1);
        value.put("2", 2);
        value.put("3", 3);
        value.put("4", 4);
        value.put("5", 5);
    }

    @After
    public void tearDown() throws Exception {
        writer.close();
    }

    @Test
    public void write() throws Exception {
        MapElement mapElement =  new MapElement(new JsonSerializer());
        mapElement.write(value, jsonWriter);
        System.out.println(writer.toString());
    }

}