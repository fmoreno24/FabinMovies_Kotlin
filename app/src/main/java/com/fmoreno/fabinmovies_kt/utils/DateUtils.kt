package com.fmoreno.fabinmovies_kt.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val DATE_FORMAT_YEAR = SimpleDateFormat("yyyy", Locale.US)

    @Throws(ParseException::class)
    private fun toDate(date: String): Date? {
        return DATE_FORMAT.parse(date)
    }

    fun getYear(date: String?): String? {
        return if (date != null && !date.isEmpty()) {
            try {
                DATE_FORMAT_YEAR.format(
                    toDate(
                        date
                    )
                )
            } catch (e: ParseException) {
                e.printStackTrace()
                ""
            }
        } else {
            ""
        }
    }
}