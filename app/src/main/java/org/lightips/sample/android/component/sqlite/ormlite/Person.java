package org.lightips.sample.android.component.sqlite.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by liuce on 11/5/14.
 */
@DatabaseTable(tableName = "orm_person")
public class Person {
    @DatabaseField(id=true)
    private Integer id;

    @DatabaseField
    private String name;
    @DatabaseField
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
