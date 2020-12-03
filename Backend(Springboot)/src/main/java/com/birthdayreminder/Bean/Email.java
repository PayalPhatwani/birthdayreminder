package com.birthdayreminder.Bean;

public class Email {
	int personid;
	String subject;
	String emailBody;

	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Email(int personid, String subject, String emailBody) {
		super();
		this.personid = personid;
		this.subject = subject;
		this.emailBody = emailBody;
	}

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	@Override
	public String toString() {
		return "Email [personid=" + personid + ", subject=" + subject + ", emailBody=" + emailBody + "]";
	}

}
