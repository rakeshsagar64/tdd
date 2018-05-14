package com.src.backend;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.src.model.Address;
import com.src.model.Contact;
import com.src.model.Employee;
import com.src.model.Skill;

 class DbClass implements DbIntr {
	 
	 private static Connection con;

		static{
			try {
				//getResourceAsStreams()
				Properties p=new Properties();
				p.load(DbClass.class.getResourceAsStream("db.properties"));
			//Class.forName("com.mysql.jdbc.Driver");
				Class.forName(p.getProperty("driver"));
				//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SkillEngine?useSSL=false","root","rakesh666");
				con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		}
		
	@Override
	public int addEmployee(Employee e) throws SQLException {
		// TODO Auto-generated method stub
		int output=0;
		
		CallableStatement prepareCall = con.prepareCall("call add_employee(?,?,?,?,?,?,?,?,?)");
		prepareCall.setString(1, e.getFirstName());
		prepareCall.setString(2, e.getLastName());
		prepareCall.setString(3, e.getContact().getEmail());
		prepareCall.setString(4, e.getContact().getLinkedinLink());
		prepareCall.setInt(5, e.getYearsOfExperience());
		prepareCall.setString(6, e.getCurrentRole());
		prepareCall.setString(7, e.getAddress().getCity());
		prepareCall.setString(8, e.getAddress().getState());
		prepareCall.setString(9, e.getAddress().getCountry());
		output=prepareCall.executeUpdate();
		
		return output;
	}

	@Override
	public int addSkill(Skill skill,int employeeId) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql="insert into Skill_Set values(null,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,skill.getTechnology());
		ps.setInt(2, employeeId);//gets from session
		ps.setString(3, skill.getCertification());
		ps.setString(4, skill.getGithubProject());
		ps.setInt(5, skill.getExperience());
		return ps.executeUpdate();
		
	}
	
	
	
	public List<Skill> fetchEmployeesWithSkill(String skill) throws SQLException{
		
		String sql="select Skill_Set.technology, Skill_Set.employee_id, Skill_Set.certification,Skill_Set.github_project,Skill_Set.experience, Employee.first_name,Employee.last_name,Employee.current_role,Employee.years_of_experience,Employee.id,Contacts.email,Contacts.linkedin_link from Skill_Set inner join Employee on Skill_Set.employee_id=Employee.id inner join Contacts on Employee.id=Contacts.id where technology=?;";
		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, skill);
		ResultSet rs = ps.executeQuery();
		
		List<Skill> list=new LinkedList<>();
		
		while(rs.next()) {
		
			//adding skill data
			Skill s=new Skill();
			s.setTechnology(rs.getString("technology"));
			s.setCertification(rs.getString("certification"));
			s.setGithubProject(rs.getString("github_project"));
			s.setExperience(rs.getInt("experience"));
			
			//adding employee
				Employee e=new Employee();
				e.setId(rs.getInt("employee_id"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setCurrentRole(rs.getString("current_role"));
				e.setYearsOfExperience(rs.getInt("years_of_experience"));
			
				Contact c=new Contact();
				c.setEmail(rs.getString("email"));
				c.setLinkedinLink(rs.getString("linked_link"));
				
			e.setContact(c);
			s.setEmployee(e);
			
			list.add(s);
			
			
		}
		return list;
	}
	
	public int updateAddress(Address address) throws SQLException {
		
		String sql="update Addresses set city=?, state=?, country=? where id=?;";
		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, address.getCity());
		ps.setString(2, address.getState());
		ps.setString(3, address.getCountry());
		ps.setInt(4, address.getId());
		return ps.executeUpdate();
		
	}
	
	public int updateContact(Contact contact) throws SQLException {
		
		String sql="update Contacts set email=?,linkedin_link=? where id=?;";
		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, contact.getEmail());
		ps.setString(2, contact.getLinkedinLink());
		ps.setInt(3, contact.getId());
		
		return ps.executeUpdate();
	}
	
	
	
	
}
