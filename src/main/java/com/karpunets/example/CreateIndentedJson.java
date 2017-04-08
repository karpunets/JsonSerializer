package com.karpunets.example;

import com.karpunets.example.company.Company;
import com.karpunets.jsonSerializer.JsonSerializer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Karpunets
 * @since 16.12.2016
 */
public class CreateIndentedJson {

    private static String nameFile = ".\\target\\generated-sources\\company.json";

    public static void main(String[] args) {

        Company company = Company.getTestCompany();

        JsonSerializer serializer = new JsonSerializer();
        serializer.setIndent(true);
        serializer.setIndentSize(3);

        try (FileWriter writer = new FileWriter(nameFile)) {
            serializer.serialize(company, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
