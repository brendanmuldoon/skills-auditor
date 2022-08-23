package com.example.employeebc;

import com.example.employeebc.employee.domain.common.AddressTests;
import com.example.employeebc.employee.domain.common.EmployeeTests;
import com.example.employeebc.employee.domain.common.FullNameTests;
import com.example.employeebc.employee.domain.common.IdentityTests;
import com.example.employeebc.employee.domain.manager.ManagerAggregateTests;
import com.example.employeebc.employee.domain.manager.ManagerTeamTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
        {
                AddressTests.class,
                EmployeeTests.class,
                FullNameTests.class,
                IdentityTests.class,
                ManagerAggregateTests.class,
                ManagerTeamTests.class
        }
)
public class TestSuite {
}
