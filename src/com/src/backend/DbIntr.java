package com.src.backend;

import java.sql.SQLException;
import java.util.List;

import com.src.model.Address;
import com.src.model.Contact;
import com.src.model.Employee;
import com.src.model.Skill;

public interface DbIntr {
	
	public int addEmployee(Employee e) throws SQLException;
	public int addSkill(Skill skill,int employeeId) throws SQLException;
	public List<Skill> fetchEmployeesWithSkill(String skill) throws SQLException;
	public int updateContact(Contact contact) throws SQLException;
	public int updateAddress(Address address) throws SQLException ;
	
}
