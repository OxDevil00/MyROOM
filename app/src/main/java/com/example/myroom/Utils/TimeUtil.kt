package com.example.myroom.Utils

class TimeUtil  {
    companion object{

        private const val SECOND_MILLIS =  1000
        private const val MINUTE_MILLIS =  60* SECOND_MILLIS
        private const val HOUR_MILLIS =  60* MINUTE_MILLIS
        private const val DAY_MILLIS =  24 * HOUR_MILLIS

        fun getTimeAgo(time : Long) : String?{
            val now = System.currentTimeMillis()
            if (time > now){
                return null
            }
            val diff = now - time
            return if(diff < MINUTE_MILLIS){
                "just now"
            }else if (diff < 2 * MINUTE_MILLIS){
                "a minute ago"
            }else if(diff < 59 * MINUTE_MILLIS){
                (diff/ MINUTE_MILLIS).toString() + " minutes ago"
            }else if (diff < 119 * MINUTE_MILLIS){
                "an hour ago"
            }else if (diff < DAY_MILLIS ){
                (diff/ HOUR_MILLIS).toString() + " hours ago"
            }else if (diff < 2 * DAY_MILLIS){
                "yesterday"
            }else{
                (diff/ DAY_MILLIS).toString() + " days ago"
            }

        }


    }
}