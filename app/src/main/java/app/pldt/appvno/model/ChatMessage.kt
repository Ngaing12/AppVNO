package app.pldt.appvno.model

data class ChatMessage(val id: String , val message: String, val fromId: String, val toId: String, val timestamp: Long, val messageType : Int){
    constructor() : this  ("","", "", "", -1, 0)
}
