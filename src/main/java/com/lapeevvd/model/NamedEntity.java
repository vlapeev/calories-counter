package com.lapeevvd.model;

public class NamedEntity extends AbstractEntity{
    protected String name;

    public NamedEntity() {}

    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
