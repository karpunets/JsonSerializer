package com.karpunets.example.company;

import com.karpunets.jsonSerializer.annotations.JsonIgnore;
import com.karpunets.jsonSerializer.annotations.JsonProperty;

/**
 * @author Karpunets
 * @since 15.12.2016
 */
public class Worker {

    public String name;
    public String surname;
    public int age;

    @JsonIgnore
    public double salary;

    @JsonProperty
    private boolean married;

    public Worker(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

}
