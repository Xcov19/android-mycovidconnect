package com.ht117.yukute.sessionmanager

import android.content.SharedPreferences
import com.ht117.yukute.utils.Constants
import java.util.*

class SessionManager(private val sharedPreferences: SharedPreferences) : SessionManagerInterface {

    /**
     * check if the user's session is valid.
     * Change of location -> invalidates session
     * Change of duration  -> invalidates session
     */
    override fun isValidSession(): Boolean {
        if (getElapsedTime() <= Constants.VALID_SESSION_TIME) {
            return true
        }
        return false
    }

    override fun setLastAccess() {
        val currentTime = getCurrentTime().toString()
        sharedPreferences.edit().putString(Constants.LAST_ACCESS_TIME, currentTime).apply()
    }

    private fun getCurrentTime(): Long {
        val calendar = Calendar.getInstance()
        return calendar.time.time
    }

    /**
     * Get the elapsed time, the default time is 0 if no last time has been stored
     * Find the difference of current time and the store time
     */

    private fun getElapsedTime(): Long {
        val timeString =
            sharedPreferences.getString(Constants.LAST_ACCESS_TIME, Constants.EMPTY_STRING)
        if (timeString.isNullOrEmpty()) {
            return 0
        }
        val currentTime = getCurrentTime()
        val lastAccessTime = timeString.toLong()
        return currentTime - lastAccessTime
    }
}