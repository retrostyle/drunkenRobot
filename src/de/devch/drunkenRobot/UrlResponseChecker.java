package de.devch.drunkenRobot;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class UrlResponseChecker extends TimerTask {

	public static String URL;
	public static int responseCode;

	public void run() {
		UrlResponseChecker.checkUrlWithResponsCode(URL);
	}

	public static boolean checkUrlWithResponsCode(String urlToCheck) {

		// Install Manager and Verifier
		TrustAllManager.trustAllCertificates();

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(TrustAllManager.allHostsValid);

		try {

			if (urlToCheck.startsWith("https")) {
				HttpsURLConnection connection = (HttpsURLConnection) new URL(urlToCheck).openConnection();
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("HEAD");
				responseCode = connection.getResponseCode();
			} else if (urlToCheck.startsWith("http")) {
				HttpURLConnection connection = (HttpURLConnection) new URL(urlToCheck).openConnection();
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("HEAD");
				responseCode = connection.getResponseCode();
			} else {
				System.err.println("You cant check this URL " + URL);
				System.err.println("Please use a valid protocol (http/https).");
				RobotYourUrl.t.cancel();
			}

			if (200 <= responseCode && responseCode <= 399) {
				System.out.println("Code : " + responseCode);
				System.out.println("The url is reachable: " + URL);
				return true;
			} else {
				System.out.println("Code : " + responseCode);
				System.out.println("The url is not reachable: " + URL);
				return false;
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}