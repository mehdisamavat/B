package com.example.providerB.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.providerA.ui.dialog.OnSubmitDialogClick
import com.example.providerB.R
import com.example.providerB.databinding.AddDialogBinding


class AddDialog(context: Context, private val onSubmitDialogClick: OnSubmitDialogClick) :
    Dialog(context, R.style.AddDialogStyle) {
    private lateinit var addDialogBinding: AddDialogBinding
    private val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDialogBinding = DataBindingUtil.inflate<AddDialogBinding?>(
            layoutInflater,
            R.layout.add_dialog,
            null,
            false
        ).apply {
            dialogHandler = onSubmitDialogClick
            lifecycleOwner = lifecycleOwner
            setContentView(root)
        }
        window?.apply {
            setGravity(Gravity.CENTER)
            attributes = window?.attributes?.apply {
                flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
            }
        }
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        addDialogBinding.userNameEditText.setText("")
        addDialogBinding.checkedCheckBox.isChecked = false
    }


}