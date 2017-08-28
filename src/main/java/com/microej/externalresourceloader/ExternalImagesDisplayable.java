/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */

package com.microej.externalresourceloader;

import java.io.IOException;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;

/**
 * This displayable displays some images supposed to be in the external resources. At each press on the screen the image
 * displayed changes. If the image is not found, a message is displayed instead.
 */
public class ExternalImagesDisplayable extends Displayable implements EventHandler {

	private static final String[] EXTERNAL_IMAGES = new String[] { "/images/is2t_logo.png",
			"/images/microej_logo.png" };
	private static final String IMAGE_NOT_FOUND = "Image not found.";
	private static final int BACKGROUND_COLOR = Colors.WHITE;
	private static final int TEXT_COLOR = Colors.BLACK;
	private static final int IMAGE_PATH_MARGIN = 10;

	private final int displayWidth;
	private final int displayHeight;
	private int imageIndex;
	private Image image;

	public ExternalImagesDisplayable(Display display) {
		super(display);
		this.displayWidth = display.getWidth();
		this.displayHeight = display.getHeight();
		// to begin with the first image.
		this.imageIndex = -1;
		changeImage();
	}

	private void changeImage() {
		this.imageIndex++;

		if (this.imageIndex >= EXTERNAL_IMAGES.length) {
			this.imageIndex = 0;
		}
		try {
			this.image = Image.createImage(EXTERNAL_IMAGES[this.imageIndex]);
		} catch (IOException e) {
			this.image = null;
		}
		repaint();
	}

	@Override
	public void paint(GraphicsContext g) {
		// Draws the background.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, this.displayWidth, this.displayHeight);

		// Draws image path.
		g.setColor(TEXT_COLOR);

		int halfDisplayWidth = this.displayWidth / 2;
		int halfDisplayHeight = this.displayHeight / 2;
		g.drawString(EXTERNAL_IMAGES[this.imageIndex], halfDisplayWidth, IMAGE_PATH_MARGIN,
				GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// Draws the image if it found otherwise a message with a warning.
		if (this.image != null) {
			g.drawImage(this.image, halfDisplayWidth, halfDisplayHeight,
					GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		} else {
			g.drawString(IMAGE_NOT_FOUND, halfDisplayWidth, halfDisplayHeight,
					GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		}
	}

	@Override
	public EventHandler getController() {
		return this;
	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Event.POINTER && Pointer.isPressed(event)) {
			changeImage();
		}
		return true;
	}
}
