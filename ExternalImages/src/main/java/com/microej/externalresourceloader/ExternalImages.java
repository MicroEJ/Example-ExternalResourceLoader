/*
 * Copyright 2015-2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

package com.microej.externalresourceloader;

import ej.microui.MicroUI;
import ej.microui.display.Display;
import ej.microui.display.Displayable;

public final class ExternalImages {

	// Prevents initialization.
	private ExternalImages() {
	}

	public static void main(String[] args) {
		MicroUI.start();
		Display display = Display.getDisplay();
		Displayable displayable = new ExternalImagesDisplayable(display);
		display.requestShow(displayable);
	}
}
