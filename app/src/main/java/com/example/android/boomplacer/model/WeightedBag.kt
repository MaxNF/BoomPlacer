package com.example.android.boomplacer.model

import java.util.*
import kotlin.random.Random

class WeightedBag<K> {
    val map = TreeMap<Int, K>()

    fun add(key: K, weight: Int) {
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
        return map.lowerEntry(r)!!.value
    }
}

