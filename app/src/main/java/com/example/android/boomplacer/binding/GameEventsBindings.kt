package com.example.android.boomplacer.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:score")
fun bindWinDialog(textView: TextView, scoreText: String?) {
    if (scoreText != null) {
        textView.rootView.visibility = View.VISIBLE
        textView.text = scoreText
    }
}

@BindingAdapter("app:showWhenEvent")
fun bindLoseDialog(view: View, unit: Unit?) {
    if (unit != null) {
        view.visibility = View.VISIBLE
    }
}