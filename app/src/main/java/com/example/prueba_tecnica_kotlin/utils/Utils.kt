package com.example.prueba_tecnica_kotlin.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    companion object{

        /**
         * Método que devuelve las 3 primeras iniciales un texto
         * @author Oscar Argaez
         * @param string
         * @return String
         */
        fun getInitals(string: String): String? {
            var myInitials = ""
            val initials = string.split(" ")
            for (initial in initials) myInitials += initial.substring(0, 1)
            return if (myInitials.length < 4) myInitials else myInitials.substring(0, 3)
        }

        /**
         * Método que comprueba si hay conexión a internet
         * @author Oscar Argaez
         * @param context
         * @return Boolean
         */
        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) {
                return networkInfo.isConnected
            }
            return false
        }

    }

}