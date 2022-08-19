package com.example.employeebc.employee.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@NoArgsConstructor
public abstract class IdentifiedValueObject extends AssertionConcern{
    private long id = -1; //surrogate id for ORM

    protected long id(){
        return id;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }
}
