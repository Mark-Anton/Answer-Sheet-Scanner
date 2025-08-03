package com.example.answersheetscanner

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import androidx.core.graphics.get

object AnswerSheetAnalyzer {
    fun analyze(bitmap: Bitmap): AnswerSheet {
        val answers = mutableListOf<Char>()

        // Loop through questions and check darkness at each bubble location
        val bubbleAreas = getBubbleRects()

        for (options in bubbleAreas) {
            var darkest = 0f
            var selected = 'A'

            options.forEachIndexed { i, rect ->
                val darkness = getDarknessInRegion(bitmap, rect)
                if (darkness > darkest) {
                    darkest = darkness.toFloat()
                    selected = 'A' + i
                }
            }

            answers.add(selected)
        }

        return AnswerSheet(answers)
    }

    private fun getBubbleRects(): List<List<Rect>> {
        // Return a list of 5 Rects (A-E) per question (assuming 20 questions)
        return List(20) {
            listOf(
                Rect(100, 100 + it * 50, 120, 120 + it * 50), // A
                Rect(130, 100 + it * 50, 150, 120 + it * 50), // B
                Rect(160, 100 + it * 50, 180, 120 + it * 50), // C
                Rect(190, 100 + it * 50, 210, 120 + it * 50), // D
                Rect(220, 100 + it * 50, 240, 120 + it * 50), // E
            )
        }
    }

    private fun getDarknessInRegion(bitmap: Bitmap, rect: Rect): Long {
        var total = 0L
        for (x in rect.left until rect.right) {
            for (y in rect.top until rect.bottom) {
                val pixel = bitmap[x, y]
                val gray = (Color.red(pixel) + Color.green(pixel) + Color.blue(pixel)) / 3
                total += gray
            }
        }
        val area = (rect.width() * rect.height()).coerceAtLeast(1)
        return 255 - (total / area)  // Darkness score
    }
}
