/*
 * C
 *
 * Copyright 2021-2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

#ifndef EXTERNAL_RESOURCES_H
#define EXTERNAL_RESOURCES_H
#include <stdint.h>

#define LOGO_IMAGE_SIZE 1537 // image size in bytes
#define ROBOT_IMAGE_SIZE 4285 // image size in bytes
#define HELLOWORLD_TRANSLATIONS_SIZE 191 // NLS resource size in bytes

extern const uint8_t logo_png_image[LOGO_IMAGE_SIZE];
extern const uint8_t robot_png_image[ROBOT_IMAGE_SIZE];
extern const uint8_t hello_world_translations[HELLOWORLD_TRANSLATIONS_SIZE];

#endif
