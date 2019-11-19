package com.danieloliveira.clarochallenge.enums

import android.graphics.Typeface
import android.text.SpannableString
import com.danieloliveira.clarochallenge.utils.TextCustomUtils

enum class TextStyleSpannable {
    ITALIC {
        override fun spannableText(span: String, text: String?): SpannableString =
            TextCustomUtils.spannableText(span, text, Typeface.ITALIC)
    },
    BOLD {
        override fun spannableText(span: String, text: String?): SpannableString =
            TextCustomUtils.spannableText(span, text, Typeface.BOLD)
    },
    BOLD_ITALIC {
        override fun spannableText(span: String, text: String?): SpannableString =
            TextCustomUtils.spannableText(span, text, Typeface.BOLD_ITALIC)
    };

    abstract fun spannableText(span: String, text: String?) : SpannableString
}