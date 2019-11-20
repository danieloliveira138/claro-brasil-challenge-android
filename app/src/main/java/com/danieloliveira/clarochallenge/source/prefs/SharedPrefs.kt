package com.danieloliveira.clarochallenge.source.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.danieloliveira.clarochallenge.enums.IntegerConstants
import com.danieloliveira.clarochallenge.enums.Languages
import com.danieloliveira.clarochallenge.enums.SharedContants
import com.danieloliveira.clarochallenge.enums.StringContants

class SharedPrefs(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SharedContants.PREF_NAME.name,
        IntegerConstants.PREF_MODE.const)

    fun setLanguage(language: Languages) =
        sharedPreferences.edit {
            putString(SharedContants.LANGUAGE_KEY.name, language.lang)
        }

    fun getLanguage(): String =
        sharedPreferences.getString(
            SharedContants.LANGUAGE_KEY.name,
            Languages.ENGLISH.lang
        ) ?: Languages.ENGLISH.lang


    fun setAdultContent(enable: Boolean) =
        sharedPreferences.edit {
            putBoolean(SharedContants.ADULT_CONTENT_KEY.name, enable)
        }

    fun getAdultContent(): Boolean =
        sharedPreferences.getBoolean(SharedContants.ADULT_CONTENT_KEY.name, false)

    fun setTypeSearch(typeSearch: String) =
        sharedPreferences.edit {
            putString(SharedContants.TYPE_SEARCH.name, typeSearch)
        }

    fun getTypeSearch(): String =
        sharedPreferences.getString(
            SharedContants.TYPE_SEARCH.name,
            StringContants.POPULAR.const
        ) ?: StringContants.POPULAR.const
}