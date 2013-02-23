package com.sonoxo.urlcheck;

import java.net.URL;
import java.util.Timer;

public class CheckingYourUrl {

	public static void main(String[] args) {

		// Set URL to checl
		UrlResponseChecker.setURL = "://www.services.sonoxo.com/snxsqa";

		// Create schedule for Timer
		Timer t = new Timer();
		t.schedule(new UrlResponseChecker(), 0, 1000);

	}

}
