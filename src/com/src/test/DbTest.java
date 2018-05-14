package com.src.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.src.backend.DbFactory;
import com.src.backend.DbIntr;
import com.src.model.Address;
import com.src.model.Contact;
import com.src.model.Employee;
import com.src.model.Skill;

public class DbTest {
	
	private DbIntr di;
	@Before
	public void setUp() {
		di=DbFactory.getInstance();
		
		
	}
	
	
	@Ignore
	@Test
	public void testAddingEmployee() {
		
		//employee object
		Employee e=new Employee();
		e.setFirstName("name");
		e.setLastName("last name");
		e.setCurrentRole("java developer");
		
		
		Address a=new Address();
		Contact c=new Contact();
		
		
		a.setCity("city");
		a.setState("state");
		a.setCountry("country");
		e.setAddress(a);
		c.setEmail("email");
		c.setLinkedinLink("link");
		e.setContact(c);
		
		
		
		try {
			assertEquals(1,di.addEmployee(e));
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Ignore
	@Test
	public void testAddressUpdate() {
		Address address=new Address();
		address.setId(1);
		address.setCity("belgaum");
		address.setState("Karnataka");
		address.setCountry("India");
		try {
			assertEquals(1,di.updateAddress(address));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testInsert() {
		Contact c=new Contact();
		c.setId(1);
		c.setEmail("some email");
		c.setLinkedinLink("www.link.com");
		try {
			assertEquals(1, di.updateContact(c));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@Ignore
	@Test
	public void testSkillAdding() {
		
		Skill s=new Skill();
		s.setTechnology("java");
		s.setCertification("www.onlinecertificatelink.com");
		s.setGithubProject("www.github.com");
		s.setExperience(2);
		
		try {
		assertEquals(1, di.addSkill(s,1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
