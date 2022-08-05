package com.example.employeebc.employee.domain.common;

import lombok.ToString;

import static com.example.employeebc.ApplicationConstants.*;

@ToString
public class Address extends ValueObject {

    private String houseNumber;
    private String streetName;
    private String postcode;

    public Address(String houseNumber, String streetName, String postcode){
        setHouseNumber(houseNumber);
        setStreetName(streetName);
        setPostcode(postcode);
    }

    private void setPostcode(String postcode) {
        assertArgumentNotEmpty(postcode, POSTCODE_ERROR_MSG_EMPTY);
        this.postcode = postcode;
    }

    private void setStreetName(String streetName) {
        assertArgumentNotEmpty(streetName, STREET_NAME_ERROR_MSG_EMPTY);
        this.streetName = streetName;
    }

    private void setHouseNumber(String houseNumber) {
        assertArgumentNotEmpty(houseNumber, HOUSE_NUMBER_ERROR_MSG_EMPTY);
        this.houseNumber = houseNumber;
    }

    public String houseNumber() {
        return houseNumber;
    }

    public String streetName() {
        return streetName;
    }

    public String postcode() {
        return postcode;
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        Address anotherAddress = (Address) o;

        return anotherAddress.houseNumber.equals(this.houseNumber) &&
                anotherAddress.streetName.equals(this.streetName) &&
                anotherAddress.postcode.equals(this.postcode);
    }
}
