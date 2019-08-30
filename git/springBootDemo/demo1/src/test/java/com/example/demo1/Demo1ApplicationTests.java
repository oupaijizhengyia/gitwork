package com.example.demo1;

import com.example.demo1.controller.EmployeeController;
import com.example.demo1.po.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

	@Autowired
	EmployeeController employeeController;

	@Test
	public void contextLoads() {
		Date date = new Date(System.currentTimeMillis());
		Employee employee = new Employee();
		/*employee.setCreateTime(date);
		employee.setModTime(date);
		employee.setEmployeeCode("001");
		employee.setEmployeeName("ss");*/
		employeeController.listEmployee(employee);

	}

}
