package com.birthdayreminder.Bean;


public class Person {

	private int id;
	private String name;
	private String dob;
	private String pictureUrl;
	private String mailId;

	public Person(int id, String name, String dob, String pictureUrl, String mailId) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.pictureUrl = pictureUrl;
		this.mailId = mailId;

	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", dob=" + dob + ", pictureUrl=" + pictureUrl + "]";
	}

}
