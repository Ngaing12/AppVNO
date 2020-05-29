package app.pldt.appvno.firebase

import java.util.*

abstract class FirebaseCallback {
      abstract fun <T> onSuccess(data : T)
      abstract fun <T> onError(data : T)
}