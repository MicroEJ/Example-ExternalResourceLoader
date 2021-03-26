/*
 * C
 *
 * Copyright 2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

#ifndef EXTERNAL_IMAGES_H
#define EXTERNAL_IMAGES_H
#include <stdint.h>

#define LOGO_IMAGE_SIZE 1537 // image size in bytes
#define ROBOT_IMAGE_SIZE 4285 // image size in bytes

extern uint8_t logo_png_image[LOGO_IMAGE_SIZE];
extern uint8_t robot_png_image[ROBOT_IMAGE_SIZE];

#endif
