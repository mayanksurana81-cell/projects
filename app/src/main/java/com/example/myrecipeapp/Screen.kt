package com.example.myrecipeapp

import okhttp3.Route

sealed class Screen(val route: String) {

    object recipeScreen:Screen("recipescreen")
    object detailScreen: Screen("detailscreen")

}