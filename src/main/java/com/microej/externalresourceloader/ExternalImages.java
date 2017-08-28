/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
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
		Display display = Display.getDefaultDisplay();
		Displayable displayable = new ExternalImagesDisplayable(display);
		displayable.show();
	}
}
