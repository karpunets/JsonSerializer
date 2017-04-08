package com.karpunets.example.company;

/**
 * @author Karpunets
 * @since 15.12.2016
 */
public class Task {

    public String description;
    public long deadline;

    public Task() {
    }

    public Task(String description, long deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "description: " + description + "; deadline - " + deadline;
    }
}
