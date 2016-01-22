package com.lapeevvd.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Not Empty для валидации
 */
@MappedSuperclass
public class NamedEntity extends AbstractEntity{
    @NotEmpty
    @Column(name = "name", nullable = false)
    protected String name;

    public NamedEntity() {
        super();
    }

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
