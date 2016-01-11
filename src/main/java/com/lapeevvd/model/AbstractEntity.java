package com.lapeevvd.model;

public class AbstractEntity {
    public static final int START_SEQ = 100000;

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

    public AbstractEntity() {}

    // Проверка существования объекта
    public boolean isNew() {
        return this.id == null;
    }


    // переопределение equals и hashCode
    // TODO: 11.01.2016 Здесь может быть какой-то критический момент

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
