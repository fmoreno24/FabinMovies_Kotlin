package com.fmoreno.fabinmovies_kt.utils

import android.content.Context
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class Utils {


    fun getCurrentYear(): String? {
        return getCurrentime("yyyy")
    }
    /**
     * get Current Time with format parameter
     *
     * @param format
     * @return string time
     */
    fun getCurrentime(format: String?): String? {
        val cal = Calendar.getInstance()
        cal.time
        val sdf = SimpleDateFormat(format)
        return sdf.format(cal.time)
    }

    /**
     * this method get resource from any object who need it.
     *
     * @param idResource
     */
    fun getStringFromResource(idResource: Int, _context: Context): String? {
        // TODO Auto-generated method stub
        return try {
            _context.resources.getString(idResource)
        } catch (e: NullPointerException) {
            // TODO: handle exception
            ""
        }
    }
}