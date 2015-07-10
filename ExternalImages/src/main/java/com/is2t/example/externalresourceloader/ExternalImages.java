/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */

package com.is2t.example.externalresourceloader;

import ej.microui.io.Display;
import ej.microui.io.Displayable;

public final class ExternalImages {

	// Prevents initialization.
	private ExternalImages() {
	}

	public static void main(String[] args) {
		Display display = Display.getDefaultDisplay();
		Displayable displayable = new ExternalImagesDisplayable(display);
		displayable.show();
	}
}
