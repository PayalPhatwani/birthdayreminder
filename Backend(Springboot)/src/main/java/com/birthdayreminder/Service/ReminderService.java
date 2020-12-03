package com.birthdayreminder.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.birthdayreminder.Bean.Email;
import com.birthdayreminder.Bean.Person;

@Service
public class ReminderService {

	static List<Person> people;

	public ReminderService() {
		people = Arrays.asList(new Person(1, "Sophie Tuner", "26/03/1999",
				"https://images.unsplash.com/photo-1583318605147-8e52610d9c75?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
				"codingnationbypayal@gmail.com"),
				new Person(2, "Selena Armin", "3/12/2000",
						"https://images.unsplash.com/photo-1547944409-0c3ef40a5c48?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
						"codingnationbypayal@gmail.com"),
				new Person(3, "Taylor Tong", "3/12/1999",
						"https://images.unsplash.com/photo-1593579491833-457b2c451e38?ixlib=rb-1.2.1&auto=format&fit=crop&w=1053&q=80",
						"codingnationbypayal@gmail.com"),
				new Person(4, "Sarah-Ann Hamlin", "30/11/1999",
						"https://images.unsplash.com/photo-1517451382354-13bfa3e76529?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
						"codingnationbypayal@gmail.com"),
				new Person(5, "John-Mark Smith", "3/12/1989",
						"https://images.unsplash.com/photo-1530283598907-1f487c5b0cb3?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
						"codingnationbypayal@gmail.com"),
				new Person(6, "Selena Armin", "3/12/2000",
						"https://images.unsplash.com/photo-1547944409-0c3ef40a5c48?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
						"codingnationbypayal@gmail.com"),
				new Person(7, "Sarah-Ann Hamlin", "3/12/1999",
						"https://images.unsplash.com/photo-1517451382354-13bfa3e76529?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80",
						"codingnationbypayal@gmail.com"));
	}

	// checking dob is matching with today's date or not
	public static boolean checkDate(String given_date) {
		String[] tokens = given_date.split("/");
		int given_day = Integer.parseInt(tokens[0]);
		int given_month = Integer.parseInt(tokens[1]);

		LocalDate c = LocalDate.now();
		int now_month = c.getMonthValue();
		int now_day = c.getDayOfMonth();
		if (now_month == given_month && now_day == given_day) {
			return true;
		} else {
			return false;
		}
	}

	public List<Person> getTodayBirthdayList() {
		List<Person> peopleBirthdayList = people.stream().filter((p) -> checkDate(p.getDob()))
				.collect(Collectors.toList());
		return peopleBirthdayList;
	}

	// method for sending mail
	public void sendmail(int personId, Email email) throws AddressException, MessagingException, IOException {
		Person person = people.stream().filter((person1) -> person1.getId() == personId).findFirst().get();
		// necessary configuration to send email
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// enter your gmail id and password here from which you want to send email
				return new PasswordAuthentication("gmailid", "password");
			}
		});
		Message msg = new MimeMessage(session);
		
		msg.setFrom(new InternetAddress("xyz@gmail.com", false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(person.getMailId()));
		msg.setSubject(email.getSubject());
		msg.setContent(email.getEmailBody(), "text/html");
		msg.setSentDate(new Date());
		Transport.send(msg);
	}

}
