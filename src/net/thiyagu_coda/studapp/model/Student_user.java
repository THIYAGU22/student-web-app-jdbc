package net.thiyagu_coda.studapp.model;

public class Student_user {
	protected int stud_id;
	protected String name;
	protected String email;
	protected String dept;
	protected String country;
	protected int phoneNum;
	
	public Student_user(int stud_id, String name, String email, String dept, String country, int phoneNum) {
		super();
		this.stud_id = stud_id;
		this.name = name;
		this.email = email;
		this.dept = dept;
		this.country = country;
		this.phoneNum = phoneNum;
	}

	public Student_user(String name, String email, String dept, String country, int phoneNum) {
		super();
		this.name = name;
		this.email = email;
		this.dept = dept;
		this.country = country;
		this.phoneNum = phoneNum;
	}


	public int getStud_id() {
		return stud_id;
	}

	public void setStud_id(int stud_id) {
		this.stud_id = stud_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}