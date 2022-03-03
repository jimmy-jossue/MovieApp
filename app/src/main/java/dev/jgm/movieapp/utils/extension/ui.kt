package dev.jgm.movieapp.utils.extension

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import dev.jgm.movieapp.R

fun ImageView.loadImage(
    url: String,
    @DrawableRes idPlaceholder: Int = R.drawable.ic_circular_progress,
) {
    val uri = Uri.parse(url)
    Glide.with(this.context)
        .load(uri)
        //.placeholder(idPlaceholder)
        .centerCrop()
        .into(this)
}