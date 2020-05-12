package app.pldt.appvno.model

data class UserCallStatus(
    val callState : String,
    val uid :String
) {
   constructor() : this ("", "")
}