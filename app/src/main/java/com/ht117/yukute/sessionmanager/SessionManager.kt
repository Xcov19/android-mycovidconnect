package com.ht117.yukute.sessionmanager

class SessionManager : SessionManagerInterface {

    /**
     * check if the user's session is valid.
     * Change of location -> invalidates session
     * Change of duration  -> invalidates session
     *
     */

    override fun isValidSession(): Boolean {
        return true
    }

}