package com.example.testrelog.common

sealed class UiEvents {
    data class ToastEvent(val message: String):UiEvents()
    data class NavigateEvent(val route: String) : UiEvents()
}