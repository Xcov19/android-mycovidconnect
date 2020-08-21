package com.ht117.yukute.ui.screen.base

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {

    open fun initView() {}
    open fun initFirst() {}
    open fun initLogic() {}
    open fun cleanUp() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initFirst()
    }

    override fun onResume() {
        super.onResume()
        initLogic()
    }

    override fun onStop() {
        super.onStop()
        cleanUp()
    }

    fun navigateTo(destination: Int, args: Bundle = bundleOf()) {
        findNavController().navigate(destination, args)
    }
}