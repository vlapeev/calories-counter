package com.lapeevvd.model;

public class AbstractEntity {
    protected Integer id;

    public AbstractEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Проверка существования объекта
    public boolean isNew() {
        return this.id == null;
    }

    // переопределение equals и hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;
        if (this.id == null || that.id == null){
            // TODO: 22.12.2015 ошибка на несовпадение id
        }
        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
