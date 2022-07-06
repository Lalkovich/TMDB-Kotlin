package com.example.mytmdb.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.mytmdb.core.event.Event
import com.example.mytmdb.core.event.EventMutableLiveData

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

fun <T> LiveData<Event<T>>.eventObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let {
            it.getContentIfNotHandled()?.let(observer)
        }
    })
}

fun EventMutableLiveData<Unit>.call() = postEvent(Unit)