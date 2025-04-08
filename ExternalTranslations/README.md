# Overview

Native Language Support (NLS) allows the application to facilitate internationalization.
It provides support to manipulate messages and translate them in different languages

This sample shows how to load a NLS resource using the External Resources Loader.
See the [NLS Documentation](https://docs.microej.com/en/latest/ApplicationDeveloperGuide/nls.html#external-resource)
for more information about this mechanism.

# Requirements

- MICROEJ SDK 6.
- A VEE Port that contains (at least):
	- EDC-1.3
	- MICROUI-3.6
	- With the module [External Resources Loader](https://docs.microej.com/en/latest/VEEPortingGuide/externalResourceLoader.html#installation) enabled

This example has been tested on:

- IntelliJ IDEA with MicroEJ plugin for IntelliJ IDEA ``1.1.0``.
- [NXP i.MX RT1170 VEE Port 3.0.0](https://github.com/MicroEJ/nxp-vee-imxrt1170-evk/tree/NXPVEE-MIMXRT1170-EVK-3.0.0).
  with the ``External Resources Loader`` module enabled.

# Project structure

- `src/main/java`: application sources.
- `ExternalImages/externalResources/images`: the images are supposed to be in the external resources.
- `src/main/c`: simple implementation of the [External Resources Loader Low Level APIs](https://docs.microej.com/en/latest/VEEPortingGuide/appendix/llapi.html#llext-res-external-resources-loader) (LLEXT_RES).

# Usage

No default VEE Port has been configured to run this sample.

It is recommended to start using this sample with the
[NXP i.MX RT1170 VEE Port 3.0.0](https://github.com/MicroEJ/nxp-vee-imxrt1170-evk/tree/NXPVEE-MIMXRT1170-EVK-3.0.0).
Complete the [Getting Started for NXP i.MX RT1170 Evaluation Kit](https://docs.microej.com/en/latest/SDK6UserGuide/gettingStartedIMXRT1170.html)
to make sure your environment is fully setup.

If you are using an other VEE Port, make sure to properly setup the VEE Port environment
before going further. Refer to the dedicated VEE Port README or Getting Started for more information.
Refer to the [Select a VEE Port](https://docs.microej.com/en/latest/SDK6UserGuide/selectVeePort.html) documentation for more information about using a VEE Port in your project.

## Enable the External Resources Loader Module in the VEE Port

Enable the External Resources Loader Module in the VEE Port in order to run this sample.

Refer to the [External Resources Loader](https://docs.microej.com/en/latest/VEEPortingGuide/externalResourceLoader.html#installation)
installation section to enable the module in your VEE Port.

## Run on Simulator

Translations may be wrongly displayed when running the sample in the console IDE,
due to a wrong encoding. Follow the steps below to get the correct result: 

- Open a PowerShell terminal or the terminal of your IDE if it is a PowerShell,
- Go to the ``Example-ExternalResourceLoader/`` folder,
- Run the following commands:
  ````
  [Console]::OutputEncoding = [System.Text.Encoding]::GetEncoding("ISO-8859-1")
  
  ./gradlew :ExternalTranslations:runOnSimulator
  ````
  
The simulator should start and display the translations in the console.

Alternative ways to run in simulation are described in the [Run on Simulator](https://docs.microej.com/en/latest/SDK6UserGuide/runOnSimulator.html) documentation.

### Expected Behavior

The application shows the available locales and prints some messages in several languages:

```
Available locales:
- en_US
- es_ES
- fr_FR
Saying:
English (US) (en_US)
- Hello everyone
- How are you?
Español (es_ES)
- Hola a todos
- ¿ Qué tal ?
Français (fr_FR)
- Bonjour tout le monde
- Comment ça va ?
```

## Run on Device

Make sure to properly setup the VEE Port environment before going further.
Refer to the VEE Port README or Getting Started for more information.

### External Resource Generation

When building the application project for the Device, the NLS resource to embed on the target is generated under
``build/application/object/externalResources/``:
- ``com/microej/example/externaltranslations/generated/HelloWorldMessages.nls``.

Those resources will be loaded using the External Resources Loader.
It is the developer responsibility to make sure that the resources will be available on the target before starting the application.

In this sample, ``HelloWorldMessages.nls`` is already provided in [hello_world_translations.c](../LLEXT_loader_implementation/src/hello_world_translations.c).

If ``HelloWorldMessages.nls`` is not available at application startup (FS failure, ...), the application will use a fallback version of the translations (``HelloWorldMessagesDefault``).
See the section ``Fallback on a Default NLS Resource`` for more information.

### Adding LLEXT C Sources to the BSP Project

Some C sources need to be added to the BSP project in order to run the sample:

- [LLEXT_static_array.c](../LLEXT_loader_implementation/src/LLEXT_static_array.c): provides a simple implementation of the External Resources Module Low Level APIs.
- External Resources (images and translations stored in C arrays in hexadecimal format):
  - [logo_image.c](../LLEXT_loader_implementation/src/logo_image.c)
  - [robot_image.c](../LLEXT_loader_implementation/src/robot_image.c)
  - [hello_world_translations.c](../LLEXT_loader_implementation/src/hello_world_translations.c)
  - [external_resources.h](../LLEXT_loader_implementation/inc/external_resources.h)

Those sources are located in the [LLEXT_loader_implementation](../LLEXT_loader_implementation) folder of the repository.

The procedure below shows an example of how to integrate those sources in the
``NXP i.MX RT1170 VEE Port`` BSP project:

- Copy / Paste the content of the [LLEXT_loader_implementation](../LLEXT_loader_implementation) folder to the ``bsp\vee\src\main`` folder of the VEE Port,
- Declare those new sources in the following file: ``bsp/vee/scripts/armgcc/CMakeLists.txt``:
  ``` C
  add_executable(${MCUX_SDK_PROJECT_NAME}
      "${MicroEjRootDirPath}/src/main/src/LLEXT_static_array.c"
      "${MicroEjRootDirPath}/src/main/src/logo_image.c"
      "${MicroEjRootDirPath}/src/main/src/robot_image.c"
      "${MicroEjRootDirPath}/src/main/src/hello_world_translations.c"
      ...
  target_include_directories(${MCUX_SDK_PROJECT_NAME} PUBLIC
      ${MicroEjRootDirPath}/src/main/inc
      ...
  ```

### Build and Run on Device

Run the following command in your IDE
(or click the ``Play`` button next to the line
below when opening this README in IntelliJ IDEA):

`./gradlew :ExternalTranslations:runOnDevice`

Alternative ways to run on device are described in the [Run on Device](https://docs.microej.com/en/latest/SDK6UserGuide/runOnDevice.html) documentation.

### Expected Behavior

The application shows the available locales and prints some messages in several languages:

```
MicroEJ START
LLEXT_RES_open
Looking for resource: com/microej/example/externaltranslations/generated/HelloWorldMessages.nls
LLEXT_RES_getBaseAddress
is_resourceID
LLEXT_RES_available
is_resourceID
LLEXT_RES_close
is_resourceID
Available locales:
- en_US
- es_ES
- fr_FR
Saying:
English (US) (en_US)
- Hello everyone
- How are you?
Español (es_ES)
- Hola a todos
- ¿ Qué tal ?
Français (fr_FR)
- Bonjour tout le monde
- Comment ça va ?
MicroEJ END (exit code = 0)

```

## Loading a Resource from a Non Byte-addressable Memory

Depending on the resource size and on the use case, the resource can be stored either in a byte addressable
memory or in a non-byte-addressable memory.

- When the resource is stored in a byte addressable memory, it is directly loaded from its location.
- When the resource is stored in a non-byte addressable memory, it is first sought in that memory and then
  copied and loaded from RAM.

By default, the [LLEXT_static_array.c](../LLEXT_loader_implementation/src/LLEXT_static_array.c) driver loads the images from a byte addressable
memory. To simulate a loading from a non-byte addressable memory, uncomment the `#define NON_BYTE_ADDRESSABLE` line in the [LLEXT_static_array.c](../LLEXT_loader_implementation/src/LLEXT_static_array.c) file.

## Fallback on a Default NLS Resource

If the External Resources Loader can't load ``HelloWorldMessages.nls`` at application startup (FS failure, ...), 
the application will fallback on a default NLS resource embedded in the application binary (``HelloWorldMessagesDefault``).

The ``DefaultNLS`` class is a wrapper used to fallback on the default NLS resource.

In this example, the fallback on default NLS resource is already implemented.
The default NLS resource only contains the ``en_US`` locale (``HelloWorldMessagesDefault_en_US.po``).

If you want to implement it in your own project, refer to the [Fallback on Default Resource](https://docs.microej.com/en/latest/ApplicationDeveloperGuide/nls.html#fallback-on-default-resource)
section of the NLS Documentation.

### Testing the Fallback on the Default NLS Resource (on Device only)

Edit ``LLEXT_static_array.c`` to simulate a load issue of the NLS resource:
- Add ``return LLEXT_RES_FILE_NOT_FOUND;`` at the beginning of the ``RES_ID LLEXT_RES_open(const char* expected_path)`` function:

```C
RES_ID LLEXT_RES_open(const char* expected_path)
{
  	return LLEXT_RES_FILE_NOT_FOUND;
  	...
```

#### Expected Behavior

The application fails to load ``HelloWorldMessages.nls`` and loads the default NLS Resource instead:

```
MicroEJ START
NLS-PO:I=6
java.io.IOException: NLS-PO:S=1
	at java.lang.System.@M:0x3001dd90:0x3001dd9a@
	at java.lang.Throwable.@M:0x3001e84c:0x3001e862@
	at java.lang.Throwable.@M:0x3001cf10:0x3001cf22@
	at java.lang.Exception.@M:0x3001d6b0:0x3001d6c2@
	at java.io.IOException.@M:0x3001e080:0x3001e092@
	at com.microej.nls.BinaryNLS.@M:0x3001cbac:0x3001cc2c@
	at com.microej.nls.BinaryNLS.@M:0x3001d36c:0x3001d380@
	at com.microej.nls.BinaryNLS.@M:0x3001dd48:0x3001dd68@
	at com.microej.nls.BinaryNLS.@M:0x3001e824:0x3001e838@
	at com.microej.example.externaltranslations.generated.HelloWorldMessages.@M:0x3001fbc0:0x3001fbdc@
	at java.lang.Thread.@M:0x3001e738:0x3001e748@
	at java.lang.Thread.@M:0x3001f850:0x3001f864@
	at java.lang.Thread.@M:0x3001f82c:0x3001f837@

Available locales:
- en_US
Saying:
English (US) (en_US)
- Hello everyone
- How are you?
MicroEJ END (exit code = 0)
```

### Testing CRC Check

To guarantee the proper application operation, the default translations (``HelloWorldMessagesDefault``) 
must be consistent with the translations embedded in External Memory (``HelloWorldMessages``).

The [Main.java](src/main/java/com/microej/example/externaltranslations/Main.java)
class performs a CRC check at startup to guarantee the consistency of ``msgid``
between ``HelloWorldMessagesDefault`` and ``HelloWorldMessages``.

- Comment the following 2 lines in ``HelloWorldMessagesDefault_en_US.po`` to test the CRC check:
  ```
	#msgid "HELLO"
	#msgstr "Hello"
  ```

Run the application on Simulator or on Device, expected output:

```
Exception in thread "bootstrap" java.lang.ExceptionInInitializerError: java.lang.RuntimeException: CRC check fail between translations, make sure PO files are consistent.
	at java.lang.Throwable.fillInStackTrace(Throwable.java:82)
	at java.lang.Throwable.<init>(Throwable.java:37)
	at java.lang.Exception.<init>(Exception.java:18)
	at java.lang.RuntimeException.<init>(RuntimeException.java:18)
	at com.microej.example.nls.Main.<clinit>(Main.java:27)
Caused by: java.lang.RuntimeException: CRC check fail between default and fallback translations. Make sure PO files are aligned.
	at java.lang.Throwable.fillInStackTrace(Throwable.java:82)
	at java.lang.Throwable.<init>(Throwable.java:37)
	at java.lang.Exception.<init>(Exception.java:18)
	at java.lang.RuntimeException.<init>(RuntimeException.java:18)
	at com.microej.example.nls.Main.<clinit>(Main.java:27)
```

# Dependencies

_All dependencies are retrieved transitively by MicroEJ Module Manager_.

# Source

N/A.

# Restrictions

None.

---
_Markdown_   
_Copyright 2021-2025 MicroEJ Corp. All rights reserved._  
_Use of this source code is governed by a BSD-style license that can be found with this software._ 
