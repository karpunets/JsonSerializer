package com.karpunets.example;

import com.karpunets.example.company.Company;
import com.karpunets.jsonSerializer.JsonSerializer;

/**
 * @author Karpunets
 * @since 15.12.2016
 */
public class CreateJson {

    public static void main(String[] args) {

        Company company = Company.getTestCompany();

        JsonSerializer serializer = new JsonSerializer();
        System.out.println(serializer.serialize(company).toString());

    }
}
