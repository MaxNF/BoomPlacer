package com.example.android.boomplacer.binding

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:showWhenEvent")
fun bindGameDialog(view: View, unit: Unit?) {
    if (unit != null) {
        view.visibility = View.VISIBLE
    }
}