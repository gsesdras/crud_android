package com.gois.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gois.mysubscribers.R
import com.gois.mysubscribers.data.db.AppDatabase
import com.gois.mysubscribers.data.db.dao.SubscriberDAO
import com.gois.mysubscribers.data.db.entity.SubscriberEntity
import com.gois.mysubscribers.extensions.navigateWithAnimations
import com.gois.mysubscribers.repository.DatabaseDataSource
import com.gois.mysubscribers.repository.SubscriberRepository
import com.gois.mysubscribers.ui.subscriber.SubscriberViewModel
import kotlinx.android.synthetic.main.subscriber_list_fragment.*

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModelEvents()
        configureViewListeners()
    }

    private fun observerViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) { allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers)
            recycler_subscribers.run {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

    private fun configureViewListeners(){
        fabAddSubscriber.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.subscriberFragment)
        }
    }
}