package com.mindorks.bootcamp.instagram.ui.splash

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.bootcamp.instagram.utils.common.Event
import com.mindorks.bootcamp.instagram.utils.network.NetworkHelper
import com.mindorks.bootcamp.instagram.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    private val launchDummyLiveData: MutableLiveData<Event<Bundle>> = MutableLiveData()

    fun launchDummyCommand() = launchDummyLiveData

    fun onViewCreated() {
        // Empty Bundle passed to Activity in Event that is needed by the other Activity
        launchDummyLiveData.postValue(Event(Bundle()))
    }
}