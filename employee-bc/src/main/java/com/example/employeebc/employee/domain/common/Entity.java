package com.example.employeebc.employee.domain.common;


import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity extends AssertionConcern {
   protected final Identity id;
    protected List<ApplicationEvent> listOfEvents = new ArrayList<>();

    protected Entity(Identity id) {
        this.id = id;
    }

    protected void addDomainEvent(ApplicationEvent event){
        listOfEvents.add(event);
    }

    public List<ApplicationEvent> getListOfEvents(){
        return listOfEvents;
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        Entity another = (Entity) o;

        return another.id == this.id;
    }
}
