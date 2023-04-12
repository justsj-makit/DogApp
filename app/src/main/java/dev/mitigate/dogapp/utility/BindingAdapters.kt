package dev.mitigate.dogapp.utility

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageLink")
fun setImageLink(view: View, link: String) {
    Glide.with(view.context)
        .load(link)
        .into(view as ImageView)
}