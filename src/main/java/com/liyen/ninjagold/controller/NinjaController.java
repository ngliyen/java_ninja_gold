package com.liyen.ninjagold.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NinjaController {
	
	@RequestMapping("/Gold")
	public String index(HttpSession session) {
		if (session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
		}
		if (session.getAttribute("logs") == null) {
			ArrayList<String> logs = new ArrayList<String>();
			session.setAttribute("logs", logs);
		}
		
		return "index.jsp";
	}
	
	@RequestMapping(value="/findgold", method=RequestMethod.POST)
	public String check(HttpSession session, @RequestParam(value="places") String place) {
		if (session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
		}
		if (session.getAttribute("logs") == null) {
			ArrayList<String> logs = new ArrayList<String>();
			session.setAttribute("logs", logs);
		}
		updateGold(place, session);
		int totalGold = (int) session.getAttribute("gold");
		if (totalGold < -50) {
			return "redirect:/prison";			
		} else {
			return "redirect:/Gold";
		}
	}
	
	@RequestMapping(value="/reset", method=RequestMethod.POST)
	public String invalidate(HttpSession session) {
		session.invalidate();
		return "redirect:/Gold";
	}
	
	@RequestMapping("/prison")
	public String prison() {
		return "prison.jsp";
	}
	
	public void updateGold(String place, HttpSession session) {
		Random randMachine = new Random();
		ArrayList<String> currentLog = (ArrayList<String>) session.getAttribute("logs");
		int currentGold = (int) session.getAttribute("gold");
		
		// present date in a specific format
		String pattern = "MMMM d yyyy h:mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		int goldEarned;
		
		// generate a random goldEarned amount based on the place
		if (place.equals("farm")) {
			goldEarned = randMachine.nextInt(11) + 10; 
		} else if (place.equals("cave")) {
			goldEarned = randMachine.nextInt(6) + 5;
		} else if (place.equals("house")) {
			goldEarned = randMachine.nextInt(4) + 2;
		} else { // last place is casino
			goldEarned = randMachine.nextInt(101) - 50;
//			goldEarned = randMachine.nextInt(3) - 1; // this is for testing, easier to hit -1/0/1
		}
		
		// update the total gold
		currentGold += goldEarned;
		
		// update the activities log
		if (goldEarned > 0) {
			currentLog.add("You entered a " + place + " and earned " + goldEarned + " gold (" + date + ")");
		}
		else if (goldEarned < 0) {
			int goldLost = -goldEarned;
			currentLog.add("You entered a " + place + " and lost " + goldLost + " gold (" + date + ")");
		}
		else {
			currentLog.add("You entered a " + place + " and did not win/lose any money. Boring! (" + date + ")");
		}
		
		// update the session values
		session.setAttribute("gold", currentGold);
		session.setAttribute("logs", currentLog);
	}


}

/*
public String check(HttpSession session, @RequestParam(value="places") String place) {
	if (session.getAttribute("gold") == null) {
		session.setAttribute("gold", 0);
	}
	if (session.getAttribute("logs") == null) {
		ArrayList<String> logs = new ArrayList<String>();
		session.setAttribute("logs", logs);
	}
	Random randMachine = new Random();
	ArrayList<String> currentLog = (ArrayList<String>) session.getAttribute("logs");
//	String currentLog = (String) session.getAttribute("logs");
	Integer currentGold = (Integer) session.getAttribute("gold");
	String pattern = "MMMM d yyyy h:mm a";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	Integer goldEarned;
	if (place.equals("farm")) {
		goldEarned = randMachine.nextInt(11) + 10; 
		currentGold += goldEarned;
		currentLog.add("You entered a farm and earned " + goldEarned + " gold (" + date + ")");
		session.setAttribute("logs", currentLog);
	}
	else if (place.equals("cave")) {
		goldEarned = randMachine.nextInt(6) + 5;
		currentGold += goldEarned;
		currentLog.add("You entered a cave and earned " + goldEarned + " gold (" + date + ")");
	}
	else if (place.equals("house")) {
		goldEarned = randMachine.nextInt(4) + 2;
		currentGold += goldEarned;
		currentLog.add("You entered a house and earned " + goldEarned + " gold (" + date + ")");
	}
	else if (place.equals("casino")) {
		Integer win = randMachine.nextInt(2);
		goldEarned = randMachine.nextInt(2);
		if (goldEarned == 0) {
			currentLog.add("You entered a casino and did not win/lose any money. Boring! (" + date + ")");
		}
		else if (win == 1) {
			currentGold += goldEarned;
			currentLog.add("You entered a casino and earned " + goldEarned + " gold. It's your lucky day! (" + date + ")");
			
		}
		else {
			currentGold -= goldEarned;
			currentLog.add("You entered a casino and lost " + goldEarned + " gold.. Ouch. (" + date + ")");
		}	
	}
	session.setAttribute("gold", currentGold);
	return "redirect:/Gold";
}
*/
