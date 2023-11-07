package com.example.ilabankassignment.util

interface ICommonMethods {
    //For performing initial setup
    fun initialSetUp()

    //For calling data fetching services
    fun callDataService()

    //set up observers
    fun setObserver()

    //set up OnClick actions
    fun setOnClickListeners()
}