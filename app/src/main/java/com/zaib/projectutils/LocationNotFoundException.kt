package com.zaib.projectutils

class LocationNotFoundError
/**
 * @param location the location that was not found
 */
    (location: String) : Exception("Location $location not found")