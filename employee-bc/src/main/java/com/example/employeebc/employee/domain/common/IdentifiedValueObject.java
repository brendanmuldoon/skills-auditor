package com.example.employeebc.employee.domain.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class IdentifiedValueObject extends AssertionConcern{
    private long id = -1; //surrogate id for ORM

    protected long id(){
        return id;
    }
}
