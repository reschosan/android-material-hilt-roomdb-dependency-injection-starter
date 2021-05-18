package com.example.myapplication.ui.fragments.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.db.helpers.ScreenInfoDBHelper
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.RxBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @Inject
    lateinit var screenInfoDBHelper: ScreenInfoDBHelper

    private lateinit var tvDash: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        tvDash = root.findViewById(R.id.text_dashboard)
        setupText(tvDash)
        return root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RxBus.getEvents().subscribe {
            if (it === Constants.messageChangeScreenInfoDialogFinished) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val screenInfo = screenInfoDBHelper.getCurrentScreenInfo()
                    withContext(Dispatchers.Main) {
                        tvDash.text = screenInfo.dashboard
                    }
                }
            }
        }
    }

    private fun setupText(tv: TextView) {
        lifecycleScope.launch(Dispatchers.IO) {
            val screenInfo = screenInfoDBHelper.getCurrentScreenInfo()

            withContext(Dispatchers.Main) {
                tv.text = screenInfo.dashboard
            }
        }
    }
}