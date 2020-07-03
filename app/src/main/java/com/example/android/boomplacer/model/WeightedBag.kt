package com.example.android.boomplacer.model

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.random.Random

class WeightedBag<K> {
    private val map = TreeMap<Int, K>()

    fun add(key: K, weight: Int) {
        if (weight <= 0 ) {
            throw IllegalArgumentException("Weight should be greater than zero.")
        }
        val accumulatedWeight = if (map.isEmpty()) {
            0
        } else {
            map.lastKey()
        }
        map.put(accumulatedWeight + weight, key)
    }

    fun getRandom(): K {
        if (map.isEmpty()) {
            throw NoSuchElementException("Weighted bag is empty.")
        }
        val r = Random.nextInt(map.lastKey())
        return map.higherEntry(r)!!.value
    }
}

