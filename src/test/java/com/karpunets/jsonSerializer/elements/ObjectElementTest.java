package com.karpunets.jsonSerializer.elements;

import com.karpunets.example.company.Department;
import com.karpunets.jsonSerializer.JsonSerializer;
import com.karpunets.jsonSerializer.annotations.JsonIgnore;
import com.karpunets.jsonSerializer.annotations.JsonProperty;
import com.karpunets.jsonSerializer.stream.JsonWriter;
import com.karpunets.example.company.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Karpunets
 * @since 15.12.2016
 */
public class ObjectElementTest {

    private static StringWriter writer;
    private static JsonWriter jsonWriter;


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
        Department value = new Department("MyDepartment");
        value.inctementVacancie();
        value.inctementVacancie();
        value.addWorkers("Ann", "Bin", 20);
        value.addWorkers("Tom", "Rich", 25);
        value.addTask("To be happy", 777);
        value.addTask();


        ObjectElement objectElement = new ObjectElement(new JsonSerializer());
        objectElement.write(value, jsonWriter);
        System.out.println(writer.toString());
    }

    @Test
    public void writeWorker() throws Exception {
        Worker value = new Worker("Ann", "Bin", 20);

        ObjectElement objectElement = new ObjectElement(new JsonSerializer());
        objectElement.write(value, jsonWriter);
        System.out.println(writer.toString());
    }


}