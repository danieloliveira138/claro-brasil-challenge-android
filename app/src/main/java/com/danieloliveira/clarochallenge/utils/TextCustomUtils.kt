package com.danieloliveira.clarochallenge.utils

import android.text.SpannableString
import android.text.style.StyleSpan
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class TextCustomUtils {
    companion object {
        fun getYear(dateString: String?): String {
            return try {
                DateTime(dateString).year.toString()
            } catch (e: Exception) {
                dateString ?: "N/A"
            }
        }

        fun spannableText(span: String, text: String?, mode: Int): SpannableString {
            var spanText = SpannableString("")
            text?.let {
                spanText = SpannableString(span + text)
                spanText.setSpan(StyleSpan(mode), 0, span.length, 0)
            }
            return spanText
        }

        fun getFormattedDateText(stringDate: String?): String {
            val result: String
            result = try {
                val date = DateTime(stringDate)
                val parser = DateTimeFormat.forPattern("dd/MM/yyyy")
                parser.print(date)
            } catch (e: Exception) {
                stringDate ?: ""
            }
            return result

        }
    }
}