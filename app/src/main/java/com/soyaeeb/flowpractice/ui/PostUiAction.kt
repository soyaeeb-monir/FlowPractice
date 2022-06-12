package com.soyaeeb.flowpractice.ui

sealed class PostUiAction {
    data class ItemClicked(val id: Int) : PostUiAction()
}
