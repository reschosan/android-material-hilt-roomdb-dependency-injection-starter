package com.example.myapplication.ui.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.db.helpers.ScreenInfoDBHelper
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.RxBus
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ChangeScreenInfoDialog : DialogFragment() {

    @Inject
    lateinit var screenInfoDBHelper: ScreenInfoDBHelper

    private lateinit var etChangeHome: TextInputEditText
    private lateinit var etChangeDashboard: TextInputEditText
    private lateinit var etChangeNotifications: TextInputEditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it)
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.dialog_change_screen_info, null) as View
            builder.setView(view)

            etChangeHome = view.findViewById(R.id.et_change_screen_info_home)
            etChangeDashboard = view.findViewById(R.id.et_change_screen_info_dashboard)
            etChangeNotifications = view.findViewById(R.id.et_change_screen_info_notifications)

            var id: Int? = null
            lifecycleScope.launch(Dispatchers.IO) {
                val screenInfo = screenInfoDBHelper.getCurrentScreenInfo()
                id = screenInfo.uid
                val home = screenInfo.home
                val dashboard = screenInfo.dashboard
                val notifications = screenInfo.notifications

                withContext(Dispatchers.Main) {

                    etChangeHome.text = SpannableStringBuilder(home)
                    etChangeDashboard.text = SpannableStringBuilder(dashboard)
                    etChangeNotifications.text = SpannableStringBuilder(notifications)
                }
            }
            builder.setPositiveButton(R.string.label_ok) { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {

                    screenInfoDBHelper.updateScreenInfo(
                        id!!,
                        et(etChangeHome),
                        et(etChangeDashboard),
                        et(etChangeNotifications)
                    )
                }

            }
            builder.setNegativeButton(R.string.label_cancel) { dialog, _ ->
                dialog?.cancel()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        RxBus.subscribe(Constants.messageChangeScreenInfoDialogFinished)
    }

    private fun et(editText: TextInputEditText): String {
        return editText.text.toString()
    }
}