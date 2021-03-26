/*
 * Copyright 2015-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

package com.microej.externalresourceloader;

import ej.microui.MicroUIException;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.display.ResourceImage;
import ej.microui.event.Event;
import ej.microui.event.EventHandler;
import ej.microui.event.generator.Pointer;

/**
 * This displayable displays some images supposed to be in the external resources. At each press on the screen the image
 * displayed changes. If the image is not found, a message is displayed instead.
 */
public class ExternalImagesDisplayable extends Displayable implements EventHandler {

	private static final String[] EXTERNAL_IMAGES = new String[] { "/images/logo.png", "/images/robot.png" };
	private static final String IMAGE_NOT_FOUND = "Image not found.";
	private static final int BACKGROUND_COLOR = Colors.WHITE;
	private static final int TEXT_COLOR = Colors.BLACK;
	private static final int IMAGE_PATH_MARGIN = 10;

	private final int displayWidth;
	private final int displayHeight;
	private int imageIndex;
	private ResourceImage image;

	public ExternalImagesDisplayable(Display display) {
		super();
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
			this.image = ResourceImage.loadImage(EXTERNAL_IMAGES[this.imageIndex]);
		} catch (MicroUIException e) {
			this.image = null;
		}

		requestRender();
	}

	@Override
	public void render(GraphicsContext g) {
		// Draws the background.
		g.setColor(BACKGROUND_COLOR);
		Painter.fillRectangle(g, 0, 0, this.displayWidth, this.displayHeight);

		// Draws image path.
		g.setColor(TEXT_COLOR);

		int halfDisplayWidth = this.displayWidth / 2;

		int halfDisplayHeight = this.displayHeight / 2;

		Painter.drawString(g, EXTERNAL_IMAGES[this.imageIndex], Font.getDefaultFont(),
				halfDisplayWidth - Font.getDefaultFont().stringWidth(EXTERNAL_IMAGES[this.imageIndex]) / 2,
				IMAGE_PATH_MARGIN - Font.getDefaultFont().getHeight() / 2);

		// Draws the image if it found otherwise a message with a warning.
		if (this.image != null) {
			Painter.drawImage(g, this.image, halfDisplayWidth - this.image.getWidth() / 2,
					halfDisplayHeight - this.image.getHeight() / 2);
		} else {
			Painter.drawString(g, IMAGE_NOT_FOUND, Font.getDefaultFont(),
					halfDisplayWidth - Font.getDefaultFont().stringWidth(IMAGE_NOT_FOUND) / 2,
					halfDisplayHeight - Font.getDefaultFont().getHeight() / 2);
		}
	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Pointer.EVENT_TYPE && Pointer.isPressed(event)) {
			this.image.close();
			changeImage();
		}
		return true;
	}
}
