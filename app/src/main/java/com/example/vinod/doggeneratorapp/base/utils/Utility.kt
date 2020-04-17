package com.example.vinod.doggeneratorapp.base.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.vinod.doggeneratorapp.R

const val NETWORK_TIMEOUT_IN_SECONDS = 60L
const val NETWORK_CACHE_SIZE = 10 * 1024 * 1024L

fun View.showView() {
  this.visibility = View.VISIBLE
}

fun View.hideView() {
  this.visibility = View.GONE
}

fun Int?.orDefaultInt() = this ?: 0

fun String?.orDefault(default: String): String {
  return if (!this.isNullOrEmpty()) this else default
}

fun setImageLoader(imageView: ImageView, url: String) {
  Glide.with(imageView.context).load(url).apply(RequestOptions.placeholderOf(R.color.colorPrimary))
    .into(imageView)
}

fun convertDpToPx(context: Context, dp: Float): Float {
  return dp * context.resources.displayMetrics.density
}

fun Boolean?.orFalse(): Boolean = this ?: false

fun showAlertDialog(context: Activity) {
  AlertDialog.Builder(context).setTitle("Oops")
    .setMessage("Something went wrong, please try again after some time")
    // Specifying a listener allows you to take an action before dismissing the dialog.
    // The dialog is automatically dismissed when a dialog button is clicked.
    .setPositiveButton(android.R.string.yes) { dialog, _ ->
      dialog.cancel()
    }

    // A null listener allows the button to dismiss the dialog and take no further action.
    .setNegativeButton(android.R.string.no) { dialog, _ ->
      dialog.cancel()
      context.finish()
    }.setIcon(android.R.drawable.ic_dialog_alert).show()
}