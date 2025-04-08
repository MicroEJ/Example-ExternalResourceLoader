/*
 * Java
 *
 * Copyright 2015-2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.externaltranslations;

import com.microej.example.externaltranslations.generated.HelloWorldMessages;
import com.microej.example.externaltranslations.generated.HelloWorldMessagesDefault;

import ej.nls.NLS;

/**
 * This example application will display the same message in several languages,
 * using all the available locales.
 *
 */
public class Main {

	/*
	 * Note: static declared here to run in simulation, see DefaultNLS class for
	 * more information.
	 */
	static {
		if (HelloWorldMessagesDefault.KeysCRC32 != HelloWorldMessages.KeysCRC32) {
			throw new RuntimeException(
					"CRC check fail between translations, make sure PO files are consistent.");
		}
	}

	/**
	 * Entry point of the program.
	 *
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {

		// Create new NLS for the header "HelloWorldMessages"
		NLS nls = HelloWorldMessages.NLS;


		// List all the available locales
		String[] locales = nls.getAvailableLocales();

		System.out.println("Available locales:");
		for (int i = 0; i < locales.length; i++) {
			System.out.println("- " + locales[i]);
		}

		// Print the messages for each locale
		System.out.println("Saying:");
		for (int i = 0; i < locales.length; i++) {
			nls.setCurrentLocale(locales[i]);

			System.out.println(nls.getDisplayName(nls.getCurrentLocale()) + " (" + locales[i] + ")");

			System.out.println(
					"- " + nls.getMessage(HelloWorldMessages.Hello) +
					" " + nls.getMessage(HelloWorldMessages.Everyone));

			System.out.println("- " + nls.getMessage(HelloWorldMessages.HowAreYou));
		}
	}

}
