package pl.elpassion.elmascarar

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class PreferencesWrapper(val prefs: SharedPreferences) {

    companion object {
        private val prefKey = "pl.elpassion.elmascarar"
        private val playerIdKey = "pl.elpassion.elmascarar.playerId"
        fun create(activity: Activity): PreferencesWrapper {
            val sharedPreferences = activity.getSharedPreferences(prefKey, Context.MODE_PRIVATE)
            return PreferencesWrapper(sharedPreferences)
        }
    }

    fun getPlayerId() : Int? {
        if (prefs.contains(playerIdKey))
            return prefs.getInt(playerIdKey,0)
        return null
    }

}