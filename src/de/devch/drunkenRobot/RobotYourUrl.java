package de.devch.drunkenRobot;

import java.util.Timer;

public class RobotYourUrl {

	public static Timer t = new Timer();

	public static void main(String[] args) {

		// Set URL to check with http or https.
		// UrlResponseChecker.URL = "http://www.google.de";
		UrlResponseChecker.URL = "https://www.google.de";

		t.schedule(new UrlResponseChecker(), 0, 1000);
	}
}
