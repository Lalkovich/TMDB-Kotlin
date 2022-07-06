package com.example.mytmdb.core.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.example.mytmdb.R
import com.example.mytmdb.core.eventObserve
import com.example.mytmdb.core.nonNullObserve
import com.example.mytmdb.core.view_model.BaseViewModel


abstract class BaseVMFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes layoutId: Int
) : BaseFragment<Binding>(layoutId) {

    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBaseObservers()
        initObservers()
    }

    private fun initBaseObservers() {
        viewModel.showMessage.eventObserve(viewLifecycleOwner) {
            showMessage(it)
        }

        viewModel.showMessageRes.eventObserve(viewLifecycleOwner) {
            showMessage(it)
        }

        viewModel.progressEvent.nonNullObserve(viewLifecycleOwner) {
            val progress = view?.findViewById<View>(R.id.base_progress)
            progress?.visibility = it.visibility
        }
    }

    open fun initObservers() {}
}