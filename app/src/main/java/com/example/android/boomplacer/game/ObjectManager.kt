package com.example.android.boomplacer.game

import com.example.android.boomplacer.model.gameobjects.base.Blast
import com.example.android.boomplacer.model.gameobjects.base.Bomb
import com.example.android.boomplacer.model.gameobjects.base.Target
import java.util.*

class ObjectManager {
    var score: Int = 0

    val placedTargets = mutableListOf<Target>()
    val placedBombs = mutableListOf<Bomb>()
    val placedBlasts = mutableListOf<Blast>()

    val destroyedTargets = mutableListOf<Target>()

    val pendingTargets = LinkedList<Target>()
    val inventoryBombs = LinkedList<Bomb>()

    val noBombsOrBlastsOnScreen
        get() = placedBlasts.isEmpty() && placedBombs.isEmpty()
    val noTargetsOnScreen
        get() = placedTargets.isEmpty()

    val targetsCount
        get() = pendingTargets.size + placedTargets.size
    val inventoryBombsCount
        get() = inventoryBombs.size

    fun clearScreen() {
        placedTargets.clear()
        placedBombs.clear()
        placedBlasts.clear()
    }

    fun resetInventory() {
        inventoryBombs.clear()
    }

    fun resetPendingTargets() {
        pendingTargets.clear()
    }

    fun resetScore() {
        score = 0
    }

    fun reset() {
        resetPendingTargets()
        resetInventory()
        resetScore()
        clearScreen()
    }

    /**
     * @return true - if target has been placed, false - otherwise
     * */
    fun placeTarget(): Boolean {
        return if (pendingTargets.isNotEmpty()) {
            placedTargets.add(pendingTargets.pop())
            true
        } else {
            false
        }
    }

    fun placeBlast(bomb: Bomb) {
        val blast = bomb.blast
        blast.positionPx = bomb.positionPx
        placedBlasts.add(blast)
    }

    /**
     * @return true - if bomb has been placed, false - otherwise
     * */
    fun placeBomb(x: Float, y: Float): Boolean {
        return if (inventoryBombs.isNotEmpty()) {
            val bomb = inventoryBombs.pop()
            bomb.positionPx.x = x
            bomb.positionPx.y = y
            placedBombs.add(bomb)
            true
        } else {
            false
        }
    }

    fun addPendingTargets(targets: Iterable<Target>) {
        pendingTargets.addAll(targets)
    }

    fun addInventoryBombs(bombs: Iterable<Bomb>) {
        inventoryBombs.addAll(bombs)
    }

    fun calculateScore() {
        destroyedTargets.forEach {
            score += it.calculateScore()
        }
        destroyedTargets.clear()
    }

    fun calculateFinalScore() {
        inventoryBombs.forEach { bomb ->
            score += bomb.calculateScore()
        }
    }
}