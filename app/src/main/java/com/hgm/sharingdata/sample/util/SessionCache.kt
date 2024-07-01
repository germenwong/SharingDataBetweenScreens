package com.hgm.sharingdata.sample.util

interface SessionCache {

    fun saveSession(session: Session)

    fun getActiveSession(): Session?

    fun clearSession()
}