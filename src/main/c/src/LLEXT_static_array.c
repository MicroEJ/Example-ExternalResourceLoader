/*
 * C
 *
 * Copyright 2015-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

/*
 * Implement these functions to target some external resources.
 * An external resource is a file stored outside the CPU address range.
 * From the JPF configuration file, check the module "External Resource Loader"
 * to use this file.
 *
 * @see LLEXT_RES_impl.h
 */

/* Includes ------------------------------------------------------------------*/

#include <stdio.h>
#include <string.h>
#include "LLEXT_RES_impl.h"
#include "external_images.h"

#define LLEXT_RES_FILE_NOT_FOUND -1
#define LLEXT_RES_BAD_OFFSET -2

/*
 * Uncomment this line to simulate a resource load from a non byte-addressable memory.
 * See the README.md for more information.
 */
//#define NON_BYTE_ADDRESSABLE

typedef struct
{
	char* path;
	uint8_t *content;
	uint32_t size;
	uint32_t offset;
}llext_file;

static llext_file files[] = {{"images/logo.png", logo_png_image, LOGO_IMAGE_SIZE, 0},
							{"images/robot.png", robot_png_image, ROBOT_IMAGE_SIZE, 0}};

#define LLEXT_NB_FILES 2

/* Private functions ----------------------------------------------------------*/

// Checks if the given resourceID corresponds to an available resource.
static int32_t is_resourceID(RES_ID resourceID)
{
	return resourceID >=0 && resourceID < LLEXT_NB_FILES;
}

/* Public functions ----------------------------------------------------------*/

RES_ID LLEXT_RES_open(const char* expected_path)
{
	int32_t index;

	for(index = 0; index < LLEXT_NB_FILES; index++)
	{
		if(strcmp(expected_path, files[index].path) == 0)
		{
			return index;
		}
	}

	return LLEXT_RES_FILE_NOT_FOUND;
}

int32_t LLEXT_RES_close(RES_ID resourceID)
{
	if(is_resourceID(resourceID))
	{
		files[resourceID].offset = 0;
		return LLEXT_RES_OK;
	}
	else
	{
		return LLEXT_RES_FILE_NOT_FOUND;
	}
}

int32_t LLEXT_RES_read(RES_ID resourceID, void* ptr, int32_t* size)
{
	if(is_resourceID(resourceID))
	{
		int32_t available = LLEXT_RES_available(resourceID);

		if(available > 0)
		{
			*size = *size > available ? available : *size;
			memcpy(ptr, files[resourceID].content + files[resourceID].offset, *size);
			files[resourceID].offset += *size;
			return LLEXT_RES_OK;
		}
		else
		{
			return LLEXT_RES_EOF;
		}
	}
	else
	{
		return LLEXT_RES_FILE_NOT_FOUND;
	}
}

int32_t LLEXT_RES_available(RES_ID resourceID)
{
	if(is_resourceID(resourceID))
	{
		return files[resourceID].size - files[resourceID].offset;
	}
	else
	{
		return LLEXT_RES_FILE_NOT_FOUND;
	}
}

int32_t LLEXT_RES_seek(RES_ID resourceID, int64_t offset)
{
	if(is_resourceID(resourceID))
	{
		if(offset >= 0 && offset <= files[resourceID].size)
		{
			files[resourceID].offset = offset;
			return LLEXT_RES_OK;
		}
		else
		{
			return LLEXT_RES_BAD_OFFSET;
		}
	}
	else
	{
		return LLEXT_RES_FILE_NOT_FOUND;
	}
}

int64_t LLEXT_RES_tell(RES_ID resourceID)
{
	if(is_resourceID(resourceID))
	{
			return files[resourceID].offset;
	}
	else
	{
		return LLEXT_RES_FILE_NOT_FOUND;
	}
}

int32_t LLEXT_RES_getBaseAddress(RES_ID resourceID)
{
#ifndef NON_BYTE_ADDRESSABLE
	if(is_resourceID(resourceID))
	{
		return (int32_t) files[resourceID].content;
	}
	else
	{
		return LLEXT_RES_FILE_NOT_FOUND;
	}
#else
	return -1;
#endif
}
