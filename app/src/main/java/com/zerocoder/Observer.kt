package com.zerocoder

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class Observer: LifecycleObserver {

    //This one is deprecated from java 8
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.d("MAIN","Observer - ON CREATE")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.d("MAIN","Observer - ON RESUME")
    }
//    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//      Lx`og.d("MAIN","Observer - ON CREATE")
//    }
}