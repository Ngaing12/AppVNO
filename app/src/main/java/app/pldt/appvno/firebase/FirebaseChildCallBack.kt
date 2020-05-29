package app.pldt.appvno.firebase

abstract class FirebaseChildCallBack {
    abstract fun <T> onChildAdded(data : T)
    abstract fun <T> onChildChanged(data : T)
    abstract fun <T> onChildRemoved(data : T)
    abstract fun <T> onChildMoved(data : T)
    abstract fun <T> onCancelled(data : T)
}