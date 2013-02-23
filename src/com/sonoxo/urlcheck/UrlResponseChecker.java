package com.sonoxo.urlcheck;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class UrlResponseChecker extends TimerTask {

	public static String setURL;
	private static int responseCode;

	public void run() {

		if (UrlResponseChecker.checkUrlWithResponsCode(setURL)) {
			System.out.println("The url is reachable!" + setURL);
		} else {
			System.out.println("The url is not reachable" + setURL);
		}
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
				System.err.println("You cant check this URL");
			}

			if (200 <= responseCode && responseCode <= 399) {
				System.out.println("Code : " + responseCode);
				return true;
			} else {
				System.out.println("Code : " + responseCode);
				return false;
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Please use a valid protocol. (http/https)");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}