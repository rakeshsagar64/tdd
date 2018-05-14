package com.src.model;

public class Skill {
	private int id;
	private String technology;
	private Employee employee;
	private String certification;
	private String githubProject;
	private int experience;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getGithubProject() {
		return githubProject;
	}
	public void setGithubProject(String githubProject) {
		this.githubProject = githubProject;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
}
