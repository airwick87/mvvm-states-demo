package com.eric.jlp_android.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


/**
 * Ideally this could utilise Glide API for configuration options, We could also use an dedicated
 * view for loading images i.e. NetworkImageView
 */
private const val apiImageBase = "https://"
fun ImageView.load(path: String) {
    Glide
        .with(this)
        .load(apiImageBase + path)
        .centerCrop()
        .transition(
            DrawableTransitionOptions.withCrossFade(200))
        .into(this)
}