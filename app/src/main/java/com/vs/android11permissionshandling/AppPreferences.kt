package com.vs.android11permissionshandling

import com.chibatching.kotpref.KotprefModel

object AppPreferences : KotprefModel() {


    var cameraPermissionDeniedOnce by booleanPref()
}