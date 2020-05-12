package app.pldt.appvno.model

import java.io.Serializable

data class TempUser (
    val number : String,
    val email : String,
    val password : String,
    val id : String = ""
) : Serializable