package com.ht117.yukute.settings

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 *  Shared Preferences Settings
 *
 *  This class manages shared preferences globally, for the app and any other module that's introduced
 *
 *  @param settings
 *  @constructor creates an instance of the Settings class
 */
class Settings(
    val settings: SharedPreferences
) {

    /**
     * Fetches the saved bearer token
     *
     * @return the bearer token, as a nullable string
     */
    fun getBearerToken(): String? = settings.getString(BEARER_TOKEN_KEY, "")

    /**
     * Saves the bearer token
     *
     * @param token
     */
    fun setBearerToken(token: String) {
        settings.edit {
            putString(BEARER_TOKEN_KEY, token)
        }
    }

    /**
     * Responsible for clearing the shared preferences file
     */
    fun clearData() {
        settings.edit { clear() }
    }

    companion object SettingsConstants {
        const val YAKUTE_SETTINGS_NAME = "yakute_settings"
        const val BEARER_TOKEN_KEY = "bearer_token"
    }
}