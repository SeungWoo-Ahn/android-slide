package io.softeer.slideapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object GlobalCoroutineScope {
    val mainCoroutineScope = CoroutineScope(Dispatchers.Main)
    val iOCoroutineScope = CoroutineScope(Dispatchers.IO)
}