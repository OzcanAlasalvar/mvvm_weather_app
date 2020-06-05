package com.mvvm.weatherapp.util

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("setAdapter")
fun bindRecyclerViewAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<*>?
) {
    recyclerView.adapter = adapter
}


@BindingAdapter("setAdapter")
fun bindViewPagerAdapter(
    viewPager: ViewPager,
    adapter: FragmentPagerAdapter
) {
    viewPager.adapter = adapter
}


@BindingAdapter("layoutManager")
fun bindLayoutManager(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
}


fun ImageView.downloadFromUrl(url: String?) {

    if (!TextUtils.isEmpty(url)) {

        Glide.with(context)
            .setDefaultRequestOptions(RequestOptions())
            .load(url)
            .into(this)
    }

}

// http://openweathermap.org/img/wn/10d@2x.png

fun convertIconToUrl(icon: String?): String {
    val start = "https://openweathermap.org/img/wn/"
    val end = "@2x.png"
    return start + icon + end
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, iconCode: String?) {
    if (!TextUtils.isEmpty(iconCode)) {
        val url = convertIconToUrl(iconCode)
        view.downloadFromUrl(url)
    }
}

fun TextView.textCelsius(celsius: Float) {
    this.text = "" + Math.round(celsius) + "°";
}

@BindingAdapter("android:celsiusFormatText")
fun celsiusFormatText(view: TextView, celsius: Float) {
    view.textCelsius(celsius)
}

@BindingAdapter("android:timeFormatText")
fun timeFormatText(view: TextView, dt: Int) {
    view.textTimeFormat(dt)
}

fun TextView.textTimeFormat(dt: Int) {
    this.text = DateUtils.getFormattedTimeText(dt)
}


@BindingAdapter("android:dayFormatText")
fun dayFormatText(view: TextView, dt: Int) {
    view.textDayFormat(dt)
}

fun TextView.textDayFormat(dt: Int) {
    this.text = DateUtils.getFormattedDayText(dt)
}