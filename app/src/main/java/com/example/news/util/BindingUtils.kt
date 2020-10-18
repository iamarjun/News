package com.example.news.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.text.TextUtils
import timber.log.Timber
import java.io.IOException
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object BindingUtils {
    private val timeZone: TimeZone = TimeZone.getTimeZone("UTC")

    /**
     * @param utcTimeString Time in UTC:+00 - Example: 2018-05-10T10:13:00Z
     * @return Formatted String of time elapsed by now in min/hrs/days
     */
    fun getElapsedTime(utcTimeString: Long): String {
        var timeElapsedInSeconds =
            (System.currentTimeMillis() - utcTimeString) / 1000
        return when {
            timeElapsedInSeconds < 60 -> {
                "less than 1m"
            }
            timeElapsedInSeconds < 3600 -> {
                timeElapsedInSeconds /= 60
                timeElapsedInSeconds.toString() + "m"
            }
            timeElapsedInSeconds < 86400 -> {
                timeElapsedInSeconds /= 3600
                timeElapsedInSeconds.toString() + "h"
            }
            else -> {
                timeElapsedInSeconds /= 86400
                timeElapsedInSeconds.toString() + "d"
            }
        }
    }

    fun stringToDate(date: String?): Date? {
        val format =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        try {
            return format.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * Utility method for fetching formatted News Source and Time
     *
     * @param sourceName Article source name
     * @param date       Publish date of article
     * @return Formatted outputted Example: **CNN • 7h**
     */
    fun getSourceAndTime(sourceName: String, date: Timestamp): String {
        return sourceName + " • " + getElapsedTime(date.time)
    }

    /**
     * Utility method for Image url If image url is valid then it is parsed else
     * Article url provides url to website and icon finder utility is used to find icon
     *
     * @param imageView  Default view passed for displaying image
     * @param url        Url of the image
     * @param articleUrl URL to the article
     */
//    fun loadThumbnailImage(
//        imageView: ImageView,
//        url: String?,
//        articleUrl: String?
//    ) {
//        var url = url
//        val context: Context = imageView.context
//        if (url == null) {
//            val iconUrl =
//                "https://besticon-demo.herokuapp.com/icon?url=%s&size=80..120..200"
//            url = java.lang.String.format(iconUrl, Uri.parse(articleUrl).authority)
//        }
//        GlideApp.with(imageView)
//            .load(url)
//            .apply(GlideModule.roundedCornerImage(RequestOptions(), imageView.context, 4))
//            .placeholder(context.resources.getDrawable(R.color.cardBackground))
//            .into(imageView)
//    }

    /**
     * Utility method for Image url If image url is valid then it is parsed else
     * Article url provides url to website and icon finder utility is used to find icon
     * This puts a radius 0 to image
     *
     * @param imageView  Default view passed for displaying image
     * @param url        Url of the image
     * @param articleUrl URL to the article
     */
//    fun loadImage(
//        imageView: ImageView,
//        url: String?,
//        articleUrl: String?
//    ) {
//        var url = url
//        val context: Context = imageView.context
//        if (url == null) {
//            val iconUrl =
//                "https://besticon-demo.herokuapp.com/icon?url=%s&size=80..120..200"
//            url = java.lang.String.format(iconUrl, Uri.parse(articleUrl).authority)
//        }
//        GlideApp.with(imageView)
//            .load(url)
//            .apply(GlideModule.roundedCornerImage(RequestOptions(), imageView.context, 0))
//            .placeholder(context.resources.getDrawable(R.color.cardBackground))
//            .into(imageView)
//    }

    /**
     * Truncate extra characters at the end of each content
     * Remove string at end similar to [18040+ chars]
     *
     * @param content Unformatted content
     * @return Formatted contented
     */
    fun truncateExtra(content: String?): String {
        return content?.replace("(\\[\\+\\d+ chars])".toRegex(), "") ?: ""
    }

    /**
     * Format date and time for details activity
     *
     * @param date Timestamp for current date
     * @return Formatted date of format **01 Oct 2018 | 02:45PM**
     */
    fun formatDateForDetails(date: Timestamp): String {
        val format =
            SimpleDateFormat("dd MMM yyyy | hh:mm aaa", Locale.getDefault())
        return format.format(Date(date.getTime()))
    }
//
//    fun loadSourceImage(imageView: ImageView, sourceUrl: String?) {
//        var sourceUrl = sourceUrl
//        val context: Context = imageView.context
//        val iconUrl =
//            "https://besticon-demo.herokuapp.com/icon?url=%s&size=80..120..200"
//        sourceUrl = java.lang.String.format(iconUrl, Uri.parse(sourceUrl).authority)
//        GlideApp.with(imageView)
//            .load(sourceUrl)
//            .apply(GlideModule.roundedCornerImage(RequestOptions(), context, 4))
//            .placeholder(context.resources.getDrawable(R.color.cardBackground))
//            .into(imageView)
//    }

    /**
     * Utility method for fetching formatted News Source Category and country
     *
     * @param category News category
     * @param country  News Source
     * @return Formatted as **Category  •  Source**
     */
    fun getSourceName(category: String, country: String?): String {
        val builder = StringBuilder()
        builder.append(capitalise(category))
        if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(country)) {
            builder.append(" • ")
        }
        val locale = Locale("en", country)
        builder.append(locale.displayCountry)
        return builder.toString()
    }

    private fun capitalise(s: String): String {
        return if (TextUtils.isEmpty(s)) s else s.substring(0, 1)
            .toUpperCase(Locale.ROOT) + s.substring(1)
    }

    fun getCountryName(context: Context, location: Location): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>
        try {
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            return if (addresses != null && addresses.isNotEmpty()) {
                addresses[0].locale.isO3Country.substring(0,2).toLowerCase(Locale.ROOT)
            } else null
        } catch (ignored: IOException) {
            //do something
            Timber.d(ignored)
        }

        return null
    }
}