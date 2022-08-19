package com.example.employeebc;

import com.example.employeebc.employee.domain.common.AddressTests;
import com.example.employeebc.employee.domain.common.EmployeeTests;
import com.example.employeebc.employee.domain.common.FullNameTests;
import com.example.employeebc.employee.domain.common.IdentityTests;
import com.example.employeebc.employee.domain.manager.ManagerTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
        {
                AddressTests.class,
                EmployeeTests.class,
                FullNameTests.class,
                IdentityTests.class,
                ManagerTests.class
        }
)
public class TestSuite {
}
