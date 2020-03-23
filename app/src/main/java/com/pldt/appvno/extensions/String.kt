package com.pldt.appvno.extensions

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


fun String.addCountryCode(postNumber: String) : String {

    return "+$postNumber $this"
}

fun String.formatNumber() : String {

    var formatedNumber = ""
    for ((index, value) in this.withIndex()){
        if (index == 2 || index == 5)
            formatedNumber += "$value "
        else
            formatedNumber += value
    }
    return formatedNumber
}

fun String.isJSON(): Boolean {
    try {
        JSONObject(this)
    } catch (e: JSONException) {
        try {
            JSONArray(this)
        } catch (e: JSONException) {
            return false
        }
    }

    return true
}

fun String.toJSON(): JSONObject = JSONObject(this)
