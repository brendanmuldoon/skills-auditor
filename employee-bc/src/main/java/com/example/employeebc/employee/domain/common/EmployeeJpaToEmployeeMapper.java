package com.example.employeebc.employee.domain.common;

public class EmployeeJpaToEmployeeMapper {

    private EmployeeJpaToEmployeeMapper(){}

    public static Employee map(String id,
                        String firstName,
                        String surname,
                        String houseNum,
                        String streetName,
                        String postcode,
                        Role role,
                        String username,
                        String password) {
        Identity identity = new Identity(id);
        FullName fullName = new FullName(firstName, surname);
        Address address = new Address(houseNum, streetName, postcode);
        SecurityCredentials securityCredentials = new SecurityCredentials(username, password);
        return new Employee(identity, fullName, address, role, securityCredentials);


    }


}
