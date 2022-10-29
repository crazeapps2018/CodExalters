package com.mep.it.util

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.mep.it.R

@BindingAdapter("imageFromUrl")
fun RoundedImageView.imageFromUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .placeholder(R.drawable.app_logo)
        .error(R.drawable.app_logo)
        .fallback(R.drawable.app_logo)
        .into(this)
}

@BindingAdapter("setTechnology")
fun AppCompatTextView.isTechnology(t: String) {
    if (t == "1") {
        this.text = "Android Developer"
    } else {
        this.text = "iOS Developer"

    }
}
