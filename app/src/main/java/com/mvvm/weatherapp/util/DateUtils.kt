package com.mvvm.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object {

        fun getFormattedTimeText(timeStamp: Int): String {
            val time = Date(timeStamp.toLong() * 1000)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time.time

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)


            if (isNow(time.time))
                return "Now"
            else
                return toFormattedTime(
                    hour,
                    minute
                )
        }

        fun getFormattedDayText(timeStamp: Int): String {
            val time = Date(timeStamp.toLong() * 1000)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time.time

            val day = calendar.get(Calendar.DAY_OF_MONTH)

            return "" + dayName(
                calendar.timeInMillis
            ) + ", " + day

        }


        private fun isNow(timeStamp: Long): Boolean {
            val calendar1 = Calendar.getInstance()
            calendar1.timeInMillis = timeStamp
            val day1 = calendar1[Calendar.DATE]
            val month1 = calendar1[Calendar.MONTH]
            val year1 = calendar1[Calendar.YEAR]
            val hour1 = calendar1.get(Calendar.HOUR_OF_DAY)

            val calendar2 = Calendar.getInstance()
            val daySelected = calendar2[Calendar.DATE]
            val monthSelected = calendar2[Calendar.MONTH]
            val yearSelected = calendar2[Calendar.YEAR]
            val hour2 = calendar2.get(Calendar.HOUR_OF_DAY)

            return yearSelected == year1 && monthSelected == month1 && daySelected == day1 && hour1 == hour2
        }

        private fun toFormattedTime(hour: Int, minute: Int): String {
            var welFormat = ""
            welFormat += if (hour < 10) "0$hour" else "" + hour
            welFormat += ":"
            welFormat += if (minute < 10) "0$minute" else "" + minute
            return welFormat
        }


        private fun dayName(timeStamp: Long): String? {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeStamp
            val date = calendar.timeInMillis
            return SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
        }


    }

}