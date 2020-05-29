package app.pldt.appvno.common

import android.content.Context
import android.content.SharedPreferences

data class Session(val token: String, val isLoggedIn: Boolean)

object SessionManager {

    private val PREF_NAME = "SessionManager.PREF_NAME"
    private val KEY_TOKEN = "SessionManager.KEY_TOKEN"
    private val KEY_IS_LOGGED_IN = "SessionManager.KEY_IS_LOGGED_IN"
    private val KEY_FIRST_OPEN = "key_first_open"

    private var sharedPref: SharedPreferences? = null

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, 0)
    }

    fun isShowOnBoarding() : Boolean {
        return sharedPref?.getBoolean(KEY_FIRST_OPEN, false) ?: false
    }

    fun setIsShownOnBoarding(isShownOnBoarding : Boolean) {
        val editor = sharedPref?.edit()
        if (isShownOnBoarding) {
            editor?.putBoolean(KEY_FIRST_OPEN, true)
        } else
            editor?.clear()
        editor?.apply()
    }

//    fun save(session: Session) {
//        sharedPref?.let { pref ->
//            val editor = pref.edit()
//            editor.putString(KEY_TOKEN, session.token)
//            editor.putBoolean(KEY_IS_LOGGED_IN, session.isLoggedIn)
//            editor.apply()
//        }
//    }
//
//    fun getSession(): Session {
//        sharedPref?.let { pref ->
//            val token = pref.getString(KEY_TOKEN, "")
//            val isLoggedIn = pref.getBoolean(KEY_IS_LOGGED_IN, false)
//            return Session(token, isLoggedIn)
//        }
//
//        return Session("", false)
//    }
//
//    fun hasSession(): Boolean {
//        sharedPref?.let { pref ->
//            return pref.getBoolean(KEY_IS_LOGGED_IN, false)
//        }
//
//        return false
//    }
//
//    fun clearSession() {
//        save(Session("", false))
//    }
}
