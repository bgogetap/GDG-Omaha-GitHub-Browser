package com.gdgomaha.githubbrowser.extensions

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

infix fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun View.getColor(colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}