package com.example.mytmdb.core.view_model

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytmdb.core.event.EventLiveData
import com.example.mytmdb.core.event.EventMutableLiveData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


abstract class BaseViewModel : ViewModel() {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    private val _showMessage = EventMutableLiveData<String>()
    val showMessage: EventLiveData<String> = _showMessage

    private val _showMessageRes = EventMutableLiveData<@StringRes Int>()
    val showMessageRes: EventLiveData<Int> = _showMessageRes

    private val _progressEvent = MutableLiveData<ProgressVisibilityEvent>()
    val progressEvent: LiveData<ProgressVisibilityEvent> = _progressEvent


    private var initProgress = true

    protected val errorHandler: (Throwable) -> Unit = {
        Timber.e(it)
    }

    fun deactivateProgress() {
        initProgress = false
    }

    fun activateProgress() {
        initProgress = true
    }

    fun showMessage(@StringRes message: Int) = _showMessageRes.postEvent(message)

    fun showMessage(message: String) = _showMessage.postEvent(message)

    fun showBaseScreenProgress() {
        if (initProgress) _progressEvent.postValue(ProgressVisibilityEvent.Show)

    }

    fun hideBaseScreenProgress() {
        if (initProgress) _progressEvent.postValue(ProgressVisibilityEvent.Hide)
    }

    protected infix operator fun CompositeDisposable.plus(d: Disposable) = this.add(d)


    override fun onCleared() {
        disposables.clear()
    }

    fun <T> Single<T>.await(
        showProgress: Boolean = true,
        hideProgress: Boolean = true,
        onSuccess: (T) -> Unit
    ) {
        disposables + this
            .doOnSubscribe { if (showProgress) showBaseScreenProgress() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { if (hideProgress) hideBaseScreenProgress() }
            .doOnError { hideBaseScreenProgress() }
            .subscribe(onSuccess, errorHandler)
    }

    fun <T> Single<T>.await(
        showProgress: Boolean = true,
        hideProgress: Boolean = true,
        onError: (Throwable) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        disposables + this
            .doOnSubscribe { if (showProgress) showBaseScreenProgress() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { if (hideProgress) hideBaseScreenProgress() }
            .doOnError { hideBaseScreenProgress() }
            .subscribe(onSuccess, onError)
    }

    fun Completable.await(
        showProgress: Boolean = true,
        hideProgress: Boolean = true,
        onSuccess: () -> Unit
    ) {
        disposables + this
            .doOnSubscribe { if (showProgress) showBaseScreenProgress() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { if (hideProgress) hideBaseScreenProgress() }
            .doOnError { hideBaseScreenProgress() }
            .subscribe(onSuccess, errorHandler)
    }

    fun clearDisposables() {
        disposables.clear()
    }
}