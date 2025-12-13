package com.example.pract12

import androidx.annotation.DrawableRes

data class ListItem(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageResId: Int
)