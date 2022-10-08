package com.hermosotech.filmjoy.core

import android.content.Context
import android.os.Build
import java.util.*
import javax.inject.Inject

/**
 * Manages Locale configuration changes.
 */
class LocaleManager @Inject constructor() {

    fun getCurrentLocate(context: Context) : Locale = getLocaleFromConfig(context)

    private fun getLocaleFromConfig(context: Context): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            context.resources.configuration.locale
        }
    }

}