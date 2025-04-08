/*
 * Kotlin
 *
 * Copyright 2025 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

plugins {
    id("com.microej.gradle.application") version "1.1.0"
}

group="com.mycompany"
version="3.1.0"

microej {
    applicationEntryPoint = "com.microej.externalresourceloader.ExternalImages"
    skippedCheckers = "nullanalysis"
    // Uncomment to use "prod" architecture when using a VEE Port (defaults to "eval")
    // architectureUsage = "prod"
}

dependencies {
    implementation(libs.api.edc)
    implementation(libs.api.microui)

    //Uncomment the microejVee dependency to set the VEE Port or Kernel to use
    //microejVee("com.nxp.vee.mimxrt1170:vee-port:3.0.0")
}

/*
* Check if the External Resources Loader is enabled in the VEE Port.
*/
tasks.loadVee{
    doLast{
        var externalResourcesLoaderScriptPath =
            layout.buildDirectory.file("vee/scripts/init-external-resource-loader/external-resource-loader-init.xml").get().toString()
        if(!File(externalResourcesLoaderScriptPath).exists()){
            error("The External Resources Loader is not enabled in your VEE Port. Enable it before building the application.")
        }
    }
}