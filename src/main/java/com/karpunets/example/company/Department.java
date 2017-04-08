package com.karpunets.example.company;

import com.karpunets.jsonSerializer.annotations.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Karpunets
 * @since 15.12.2016
 */
public class Department {

    @JsonProperty(name = "WORKERS")
    private Set<Worker> workers = new HashSet<Worker>();
    private int tasksLength;
    private int vacancy;
    private static final int MAX_TASKS = 3;
    public final String name;
    public Task[] tasks = new Task[MAX_TASKS];

    public Department(String name) {
        this.name = name;
    }

    public int getVacancie() {
        return vacancy;
    }

    public void inctementVacancie() {
        vacancy++;
    }

    public void addWorkers(String name, String surname, int age) {
        if (vacancy > 0) {
            workers.add(new Worker(name, surname, age));
        }
    }

    public Task addTask(String description, long deadline) {
        tasks[tasksLength++ % MAX_TASKS] = new Task(description, deadline);
        return tasks[tasksLength];
    }

    public Task addTask() {
        tasks[tasksLength++ % MAX_TASKS] = new Task();
        return tasks[tasksLength];
    }

}
