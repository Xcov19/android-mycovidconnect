package com.ht117.yukute.ui.screen.landing

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.core.content.PermissionChecker
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ht117.yukute.R
import com.ht117.yukute.ui.hasPermission
import com.ht117.yukute.ui.screen.base.*
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

data class LandingState(val isLoading: Boolean = false) : IState
sealed class LandingAction : IAction {
    object CheckAuthorize : LandingAction()
}

class LandingFragment : BaseFragment(R.layout.fragment_landing), IView<LandingState> {

    private val viewModel: LandingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initView() {
        super.initView()

        btnSos.setOnClickListener {
            if (requireContext().hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                navigateTo(R.id.navigate_to_map)
            } else {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOC_REQ)
            }
        }

        btnLogin.setOnClickListener {
            navigateTo(R.id.navigate_to_login)
        }

        btnSignup.setOnClickListener {
            navigateTo(R.id.navigate_to_signup)
        }
    }

    override fun initFirst() {
        super.initFirst()
        viewModel.state.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    override fun initLogic() {
        super.initLogic()
        lifecycleScope.launch {
            viewModel.intents.send(LandingAction.CheckAuthorize)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.landing, menu)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOC_REQ) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                btnSos.performClick()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showRationaleDialog()
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun showRationaleDialog() {
        LocationRationaleDialog().show(childFragmentManager, "rationale")
        //requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOC_REQ)

    }

    companion object {
        const val LOC_REQ = 9981
    }

    override fun render(state: LandingState) {

    }

}