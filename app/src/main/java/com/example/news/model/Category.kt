package com.example.news.model

import java.util.*


enum class Category(val title: String) {
    
    business("Business"),
    entertainment("Entertainment"),
    general("General"),
    health("Health"),
    science("Science"),
    sports("Sports"),
    technology("Technology");
    
    companion object {
        private val VALUES: List<Category> = values().toList()
        private val SIZE = VALUES.size
        private val RANDOM: Random = Random()

        fun randomCategory(): Category {
            return VALUES[RANDOM.nextInt(SIZE)]
        }

    }
}