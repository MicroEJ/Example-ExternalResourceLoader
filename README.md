# Overview
The following illustrate the external resource loader feature.

## ExternalImages
This example try to display some images supposed to be in the external resources.

### Requirements
- JRE 7 (or later) x86.
- MicroEJ 3.1 or later.
- Java platform with (at least) EDC-1.2, MICROUI-1.5.0 and containing the module `External Resources Loader`.

### Project structure
- `src/main/java`
  - Java sources
- `externalResources`: Images supposed to be in the external resources
- `launches/`: MicroEJ launches

### Launches
A Simulation and an Embedded launches are available.

## LLEXT implementations

### LLEXT_static_array.c
This implementation simply store the images in a static array.

2 versions of this array are available:
- An array containing the preprocessed images (default)
- An array containing the not preprocesse images.

To switch between these versions, comment or uncomment the line 38.

## Changes
- ExternalImages example and LLEXT_static_array.c.

## License
See the license file `LICENSE.md` located at the root of this repository.