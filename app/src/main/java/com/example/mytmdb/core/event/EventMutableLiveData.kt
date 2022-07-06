package com.example.mytmdb.core.event

class EventMutableLiveData<T> : EventLiveData<T> {

    /**
     * Creates a EventMutableLiveData initialized with the given `value`.
     *
     * @param value initial value
     */
    constructor(value: T) : super(value)

    /**
     * Creates a EventMutableLiveData with no value assigned to it.
     */
    constructor() : super()


    fun postEvent(value: T) {
        super.postValue(Event(value))
    }

    fun setEvent(value: T) {
        super.setValue(Event(value))
    }
}