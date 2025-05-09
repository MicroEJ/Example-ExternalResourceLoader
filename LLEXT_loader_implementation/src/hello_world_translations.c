/*
 * C
 *
 * Copyright 2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

#include "external_resources.h"

/*
* Make sure to align this array on 32-bit to prevent memory access issues.
*/
const uint8_t hello_world_translations[HELLOWORLD_TRANSLATIONS_SIZE] __attribute__((aligned(4))) =
{
  0x4D, 0x45, 0x4A, 0x5F, 0x4E, 0x4C, 0x53, 0x01, 0x04, 0x78, 0x1D, 0x8A, 0x13, 0x10, 0x20, 0x30, 0x14, 0x40, 0x4D, 0x56, 0x5C, 0x00, 0x00, 0x00, 0x45, 0x65, 0x6E, 0x5F, 0x55, 0x53, 0x00, 0x00, 0x14, 0x69, 0x72, 0x7A, 0x7F, 0x00, 0x00, 0x00,
  0x45, 0x65, 0x73, 0x5F, 0x45, 0x53, 0x00, 0x00, 0x14, 0x8D, 0x97, 0xA5, 0xAD, 0x00, 0x00, 0x00, 0x45, 0x66, 0x72, 0x5F, 0x46, 0x52, 0x00, 0x00, 0x4C, 0x45, 0x6E, 0x67, 0x6C, 0x69, 0x73, 0x68, 0x20, 0x28, 0x55, 0x53, 0x29, 0x48, 0x65, 0x76,
  0x65, 0x72, 0x79, 0x6F, 0x6E, 0x65, 0x45, 0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x4C, 0x48, 0x6F, 0x77, 0x20, 0x61, 0x72, 0x65, 0x20, 0x79, 0x6F, 0x75, 0x3F, 0xC7, 0x45, 0x73, 0x70, 0x61, 0xC3, 0xB1, 0x6F, 0x6C, 0x47, 0x61, 0x20, 0x74, 0x6F, 0x64,
  0x6F, 0x73, 0x44, 0x48, 0x6F, 0x6C, 0x61, 0xCB, 0xC2, 0xBF, 0x20, 0x51, 0x75, 0xC3, 0xA9, 0x20, 0x74, 0x61, 0x6C, 0x20, 0x3F, 0xC8, 0x46, 0x72, 0x61, 0x6E, 0xC3, 0xA7, 0x61, 0x69, 0x73, 0x4D, 0x74, 0x6F, 0x75, 0x74, 0x20, 0x6C, 0x65, 0x20,
  0x6D, 0x6F, 0x6E, 0x64, 0x65, 0x47, 0x42, 0x6F, 0x6E, 0x6A, 0x6F, 0x75, 0x72, 0xCF, 0x43, 0x6F, 0x6D, 0x6D, 0x65, 0x6E, 0x74, 0x20, 0xC3, 0xA7, 0x61, 0x20, 0x76, 0x61, 0x20, 0x3F, 0x00
};