package com.example.news.util

import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.NonNull
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import kotlin.math.roundToInt


@GlideModule
class GlideModule : AppGlideModule() {

    companion object {
        @NonNull
        @GlideOption
        fun roundedCornerImage(
            options: RequestOptions, @NonNull context: Context,
            radius: Int
        ): RequestOptions? {
            if (radius > 0) {
                val px =
                    (radius * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
                return options.transforms(CenterCrop(), RoundedCorners(px))
            }
            return options.transforms(CenterCrop())
        }
    }
}