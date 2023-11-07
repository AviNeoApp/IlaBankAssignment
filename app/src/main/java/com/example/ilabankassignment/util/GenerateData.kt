package com.example.ilabankassignment.util

import com.example.ilabankassignment.R
import com.example.ilabankassignment.model.CarouselAndListItemModel
import com.example.ilabankassignment.model.ListItemModel

fun getLocalDataArrayList() : ArrayList<CarouselAndListItemModel>{
    return arrayListOf(
        CarouselAndListItemModel(
            R.drawable.ic_tractor_carousel, arrayListOf(
                ListItemModel("Tractor 1", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 2", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 3", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 11", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 21", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 31", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 12", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 22", R.drawable.ic_tractor_list_item),
                ListItemModel("Tractor 32", R.drawable.ic_tractor_list_item),
            )
        ),
        CarouselAndListItemModel(
            R.drawable.ic_airplane_carousel, arrayListOf(
                ListItemModel("Airplane 1", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 2", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 3", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 11", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 21", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 31", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 12", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 22", R.drawable.ic_airplane_list_item),
                ListItemModel("Airplane 32", R.drawable.ic_airplane_list_item),
            )
        ),
        CarouselAndListItemModel(
            R.drawable.ic_boat_carousel, arrayListOf(
                ListItemModel("Boat 1", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 2", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 3", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 11", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 21", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 31", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 12", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 22", R.drawable.ic_boat_list_item),
                ListItemModel("Boat 32", R.drawable.ic_boat_list_item),
            )
        )
    )
}