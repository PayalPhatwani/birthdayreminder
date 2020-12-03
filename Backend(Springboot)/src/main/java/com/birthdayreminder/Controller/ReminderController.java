package com.birthdayreminder.Controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birthdayreminder.Bean.Email;
import com.birthdayreminder.Bean.Person;
import com.birthdayreminder.Service.ReminderService;

@RequestMapping("/people")
@CrossOrigin(origins = "*")
@RestController
public class ReminderController {

	@Autowired
	private ReminderService service;

	@RequestMapping("/")
	public String home() {
		return "Welcom to Birthday Reminder";
	}

	@PostMapping("/sendemail/{id}")
	public String sendEmail(@PathVariable int id, @RequestBody Email email)
			throws AddressException, MessagingException, IOException {
		service.sendmail(id, email);
		return "Email sent successfully";
	}

	@GetMapping("/getList")
	public List<Person> getPeople() {
		return service.getTodayBirthdayList();
	}
}
