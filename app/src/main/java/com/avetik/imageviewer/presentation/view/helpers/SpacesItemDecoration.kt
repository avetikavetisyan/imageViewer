package com.avetik.imageviewer.presentation.view.helpers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration : RecyclerView.ItemDecoration() {
    private var space = 0
    private var spanCount = 0
    private var column = 0

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        if(spanCount == 0) {
            spanCount = getSpanCount(parent.width, parent.height)
            space = getSpace(spanCount, parent.width)
        }

        outRect.top = space
        val position = parent.getChildAdapterPosition(view)
        column = if(position < spanCount) {
            position
        } else {
            position%spanCount
        }

        val spaces = getSpaces(spanCount, space, column)

        outRect.left = spaces.first
        outRect.right = spaces.second
    }

    private fun getSpaces(spanCount: Int, space: Int, column: Int):Pair<Int, Int>{
        val leftSpace: Int
        val rightSpace: Int

        if(spanCount == 2){
            when(column){
                0 -> {
                    leftSpace = space
                    rightSpace = (space * 0.5f).toInt()
                }
                else -> {
                    leftSpace = (space * 0.5f).toInt()
                    rightSpace = space
                }
            }
        } else {
            when(column){
                0 -> {
                    leftSpace = space
                    rightSpace = (space * 0.25f).toInt()
                }
                1 -> {
                    leftSpace = (space * 0.75f).toInt()
                    rightSpace = (space * 0.5f).toInt()
                }
                2 -> {
                    leftSpace = (space * 0.5f).toInt()
                    rightSpace = (space * 0.75f).toInt()
                }
                else -> {
                    leftSpace = (space * 0.25f).toInt()
                    rightSpace = space
                }
            }
        }
        return Pair(leftSpace, rightSpace)
    }

    private fun getSpanCount(screenWidth: Int, screenHeight: Int): Int {
        return if(screenWidth > screenHeight) {
            4
        } else {
            2
        }
    }

    private fun getSpace(spanCount: Int, screenWidth: Int): Int {
        return if(spanCount == 2) {
            (screenWidth.toFloat() * 0.1f).toInt()
        } else {
            (screenWidth.toFloat() * 0.04f).toInt()
        }
    }
}