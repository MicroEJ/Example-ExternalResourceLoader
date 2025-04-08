/*
 * Java
 *
 * Copyright 2024-2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.externaltranslations;

import com.microej.example.externaltranslations.generated.HelloWorldMessagesDefault;

import ej.nls.NLS;

/**
 * This class provides a default implementation of the translations, in case the
 * HelloWorldMessages translations can't be loaded from the external memory.
 *
 * A CRC check is performed at VM startup (CLINIT phase). If the check fails,
 * the VM start is aborted. The .PO files of HelloWorldMessagesDefault and
 * HelloWorldMessages must be synchronized in order to pass the CRC check.
 *
 * Note: the code in the static section will only be executed when running the
 * code on the target. Move it to the Main class to execute it in simulation as
 * well.
 */
public class DefaultNLS implements NLS {

	//	static {
	//		if (HelloWorldMessagesDefault.KeysCRC32 != HelloWorldMessages.KeysCRC32) {
	//			throw new RuntimeException(
	//					"CRC check fail between default and fallback translations. Make sure PO files are aligned.");
	//		}
	//	}
	@Override
	public String[] getAvailableLocales() {
		return HelloWorldMessagesDefault.NLS.getAvailableLocales();
	}

	@Override
	public String getDisplayName(String locale) {
		return HelloWorldMessagesDefault.NLS.getDisplayName(locale);
	}

	@Override
	public String getMessage(int messageID, int count) {
		return HelloWorldMessagesDefault.NLS.getMessage(messageID, count);
	}

	@Override
	public String getMessage(int messageID, int count, String locale) {
		return HelloWorldMessagesDefault.NLS.getMessage(messageID, count, locale);
	}

	@Override
	public String getMessage(int messageID) {
		return HelloWorldMessagesDefault.NLS.getMessage(messageID);
	}

	@Override
	public String getMessage(int messageID, String locale) {
		return HelloWorldMessagesDefault.NLS.getMessage(messageID, locale);
	}

	@Override
	public void setCurrentLocale(String locale) {
		HelloWorldMessagesDefault.NLS.setCurrentLocale(locale);
	}

	@Override
	public String getCurrentLocale() {
		return HelloWorldMessagesDefault.NLS.getCurrentLocale();
	}

}
