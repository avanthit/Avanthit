package com.ss.assignment.model;

import java.util.Set;

public class RequestObject {
    private String name;
    private Set<String> names;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }
}
