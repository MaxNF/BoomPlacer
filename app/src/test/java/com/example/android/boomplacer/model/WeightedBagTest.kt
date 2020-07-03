package com.example.android.boomplacer.model

import org.junit.Test
import com.google.common.truth.Truth.*

class WeightedBagTest {

    @Test
    fun addStringToBag_receiveItBack() {
        val weightedBag: WeightedBag<String> = WeightedBag()
        val s = "test"
        weightedBag.add(s, 1)
        val result = weightedBag.getRandom()
        assertThat(result).isEqualTo(s)
    }
}