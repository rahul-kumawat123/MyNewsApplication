package com.example.mynewsapplication

import android.content.Context
import android.widget.Toast

/**
 * Extension Function to for displaying Toast
 */
fun Context.showToast(msg : String){
    Toast.makeText(this , msg , Toast.LENGTH_SHORT).show()
}