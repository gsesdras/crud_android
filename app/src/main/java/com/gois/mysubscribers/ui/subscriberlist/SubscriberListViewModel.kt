package com.gois.mysubscribers.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.gois.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    val allSubscribersEvent = repository.getAllSubscribers()
}