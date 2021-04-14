# External Images Example

The following project illustrates the use of [MicroEJ External Resources Loader](https://docs.microej.com/en/latest/PlatformDeveloperGuide/externalResourceLoader.html) by displaying
images supposed to be in the external resources (used for resources stored on non-byte-addressable memories).

## Requirements

- MicroEJ SDK 5.3.0 or higher
- MicroEJ UI Pack 13.0.4 or higher
- A MicroEJ Platform with (at least):
	- EDC-1.3.3
	- MICROUI-3.0.3
	- Containing at least the module `External Resources Loader`

This example has been tested on:

- STM32F7508-DK board
- MicroEJ SDK 5.3.0
- STM32CubeIDE 1.5.0

## Project structure

- `src/main/java`: Java sources.

- `ExternalImages/externalResources/images`: the images are supposed to be in the external resources.

- `src/main/c`: C sources.

## Enable the External Resources Loader Module in the Platform

The External Resources Loader module needs to be activated in the STM32F7508-DK platform before using it.
In the **stm32f7508_freertos-configuration** project:

* Open the **STM32F7508.platform** file
* In the **Platform Content** section, click on **Modules**
* Check the **External Resources Loader** checkbox
* Go back to the **Overview** tab
* Click **Build Platform**

## Configuring the Images Heap Size

Depending on the size of the image resources used, the MicroUI images heap needs to be increased or decreased.
In this example, the images heap is already configured to work properly. It has been set to 128Kb.
The images heap needs to be configured for the simulator and the embedded target as well.

To modify the Images heap size:

* Select **Run > Run Configurations...** menu item
* Select your launch configuration
* Go to the **Configuration** tab
  * In **MicroUI** section, the **Images heap size** field allows to configure the size of the Images heap.

## Running the Application on Simulator

Using MicroEJ, you may deploy and run your application on an embedded target (if the hardware and related BSP are available) 
or you may run your application on a Java simulator mimicking the behavior of your embedded target.

* Select **Run > Run Configurations...** menu item
* Select **MicroEJ Application** group
* Create a new configuration:
    * In **Project** field, Click the **Browse** button and select **Example-ExternalResourceLoader**
	* In **Execution** tab
		* In **Target** frame
			* Click the **Browse** button next to the Platform Field and select your platform
		* In **Execution** frame
			* Notice that "Execute on Simulator" radio button option is checked
	* In **Configuration** tab
		* In **External Resources Loader** section
			* Click the **Browse** button next to the field and select the **ExternalImages/externalResources** folder
			which contains the **images/** folder with the image resources.
	* Click on "Run"
	

The simulator should start and display the loaded images. Click on the screen to switch between images.
	
## Running the Application on Device

### Building for the Device

* Select **Run > Run Configurations...** menu item
* Select **MicroEJ Application** group
* Create a new configuration:
    * In **Project** field, Click the **Browse** button and select **Example-ExternalResourceLoader**
	* In **Execution** tab
		* In **Target** frame
			* Click the **Browse** button next to the Platform Field and select your platform
		* In **Execution** frame
			* Notice that "Execute on Device" radio button option is checked
	* Click on "Run"

### Importing the BSP Project

1. Open STM32CubeIDE in an empty workspace
2. Select **File > Import...**
3. Select **General > Existing Projects into Workspace**
4. Press **Next >**
5. Next to the **Select root directory** field, press **Browse...**
6. Navigate to the **stm32f7508_freertos-bsp/projects/microej/SW4STM32** folder
7. Press **OK**
8. Press **Finish**

### Adding C Sources to the BSP Project

Some C sources need to be added to the BSP project in order to use the external resources module.
Those sources are located in the [src/main/c](src/main/c) folder of the repository.

The [LLEXT_static_array.c](src/main/c/src/LLEXT_static_array.c) file is the low level driver for the external resources module. 

The images located in **ExternalImages/externalResources/images** are stored in C arrays in hexadecimal format.
They are respectively stored in [robot_image.c](src/main/c/src/robot_image.c) and [logo_image.c](src/main/c/src/logo_image.c).

### Building the BSP Project

1. In STM32CubeIDE, right-click on the **application** project
2. Press **Build Project**
3. Wait for the end of the build

### Flashing the Application on the Board

1. Plug-in your STM32F7508-DK board
2. In STM32CubeIDE, select **Run > Run Configurations...**
3. Under **STM32 Cortex-M C/C++ Application**, select the **application_debug** run configuration
4. Press **Run**

## Loading a Resource from a Non Byte-addressable Memory

Depending on the resource size and on the use case, the resource can be stored either in a byte addressable
memory or in a non-byte-addressable memory. 

- When the resource is stored in a byte addressable memory, it is directly loaded from its location.
- When the resource is stored in a non-byte addressable memory, it is first sought in that memory and then 
  copied and loaded from RAM.

By default, the [LLEXT_static_array.c](src/main/c/src/LLEXT_static_array.c) driver loads the images from a byte addressable
memory. To simulate a loading from a non-byte addressable memory, uncomment the `#define NON_BYTE_ADDRESSABLE` line in the [LLEXT_static_array.c](src/main/c/src/LLEXT_static_array.c) file.

## License

See the license file [LICENSE.txt](LICENSE.txt) located at the root of this repository.

---
_Markdown_   
_Copyright 2015-2021 MicroEJ Corp. All rights reserved._  
_Use of this source code is governed by a BSD-style license that can be found with this software._  