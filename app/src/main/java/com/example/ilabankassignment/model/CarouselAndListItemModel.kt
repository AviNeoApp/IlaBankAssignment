package com.example.ilabankassignment.model

import androidx.annotation.DrawableRes

data class CarouselAndListItemModel(
    @DrawableRes val carouselImageId: Int,
    val listItems: List<ListItemModel>
)