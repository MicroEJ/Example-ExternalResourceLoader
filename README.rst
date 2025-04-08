.. image:: https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/sdk_6.0.json
   :alt: sdk_6 badge
.. image:: https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/arch_8.3.json
   :alt: arch_8.3 badge
   :align: left
.. image:: https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/gui_3.json
   :alt: GUI 3 Badge
   :align: left

.. class:: center

⚠️ Those samples are compatible with MICROEJ SDK 6. MICROEJ SDK 5 compatible samples are available `here <https://github.com/MicroEJ/Example-ExternalResourceLoader/tree/SDK-5.x>`_. ⚠️

Overview
========

This project gather examples demonstrating the use of the
`External Resources Loader <https://docs.microej.com/en/latest/PlatformDeveloperGuide/externalResourceLoader.html>`__.

The samples have been tested on the
`NXP i.MX RT1170 VEE Port <https://github.com/MicroEJ/nxp-vee-imxrt1170-evk>`__.

See https://github.com/search?q=org%3AMicroEJ+VEEPort&type=repositories for the list of supported boards using MICROEJ SDK.

Each sample provides a ``README.md`` that contains instructions on how to run it.

Project Structure
=================

- `ExternalImages <./ExternalImages>`__: this example shows how to load images supposed to be in an external memory (like in a FileSystem).
- `ExternalTranslations <./ExternalTranslations>`__ this example shows how to load images supposed to be in an external memory (like in a FileSystem).
- `LLEXT_loader_implementation <./LLEXT_loader_implementation>`__: simple implementation of External Resources Loader Low Level API (LLEXT_RES).

.. ReStructuredText
.. Copyright 2025 MicroEJ Corp. All rights reserved.
.. Use of this source code is governed by a BSD-style license that can be found with this software.