package com.example.android.boomplacer.model

import com.example.android.boomplacer.math.Vector2
import org.junit.Test
import com.google.common.truth.Truth.*
import org.junit.Before

class Vector2Test {

    private var speed = 0f
    private var angle = 0f
    private lateinit var vector: Vector2

    @Before
    fun createVector() {
        speed = -1f
        angle = 1f
        vector = Vector2.create(angle, speed)
    }

    @Test
    fun testMagnitudeEqualsSpeed() {
        assertThat(vector.getMagnitude()).isEqualTo(speed)
    }

    @Test
    fun testGetAngleEqualsAngle() {
        assertThat(vector.getAngle()).isEqualTo(angle)
    }
}