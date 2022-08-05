package com.example.employeebc.employee.domain.common;

import lombok.ToString;

@ToString
public class Identity extends ValueObject{
    protected String id;

    public Identity(String id){
       setID(id);
    }

    private void setID(String id){
        this.assertArgumentNotEmpty(id,"id cannot be empty");
        this.id = id;
    }

    public String id(){
        return id;
    }
}
