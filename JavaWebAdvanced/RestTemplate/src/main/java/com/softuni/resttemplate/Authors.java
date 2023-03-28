package com.softuni.resttemplate;

public class Authors {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public Authors setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Authors setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
