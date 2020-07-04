package com.example.android.boomplacer.game

import com.example.android.boomplacer.model.gameobjects.blasts.Blast
import com.example.android.boomplacer.model.gameobjects.bombs.Bomb
import com.example.android.boomplacer.model.gameobjects.targets.Target
import java.util.*

class ObjectManager {
    var score: Int = 0

    val placedTargets = mutableListOf<Target>()
    val placedAntiTargets = mutableListOf<Target>()
    val placedBombs = mutableListOf<Bomb>()
    val placedBlasts = mutableListOf<Blast>()

    val destroyedTargets = mutableListOf<Target>()
    val destroyedAntiTargets = mutableListOf<Target>()
    val expiredBombs = mutableListOf<Bomb>()

    val pendingTargets = LinkedList<Target>()
    val pendingAntiTargets = LinkedList<Target>()
    val inventoryBombs = LinkedList<Bomb>()

    val noBlastsOnScreen
        get() = placedBlasts.isEmpty()
    val noBombsOnScreen
        get() = placedBombs.isEmpty()
    val noTargetsOnScreen
        get() = placedTargets.isEmpty()
    val isAntiTargetsDestroyed
        get() = destroyedAntiTargets.isNotEmpty()
    val noBombsInInventory
        get() = inventoryBombs.isEmpty()

    val targetsCount
        get() = pendingTargets.size + placedTargets.size
    val inventoryBombsCount
        get() = inventoryBombs.size

    fun reset() {
        placedTargets.clear()
        placedAntiTargets.clear()
        placedBombs.clear()
        placedBlasts.clear()

        destroyedTargets.clear()
        destroyedAntiTargets.clear()

        expiredBombs.clear()

        pendingTargets.clear()
        pendingAntiTargets.clear()
        inventoryBombs.clear()
        score = 0
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

    fun placeAllAntiTargets() {
        while (pendingAntiTargets.isNotEmpty()) {
            placedAntiTargets.add(pendingAntiTargets.pop())
        }
    }

    fun placeBlastsFromExpiredBombs() {
        expiredBombs.forEach { bomb ->
            val blast = bomb.blast
            blast.positionPx = bomb.positionPx
            placedBlasts.add(blast)
        }
        expiredBombs.clear()
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

    fun addPendingAntiTargets(antiTargets: Iterable<Target>) {
        pendingAntiTargets.addAll(antiTargets)
    }

    fun addInventoryBombs(bombs: Iterable<Bomb>) {
        inventoryBombs.addAll(bombs)
    }

    fun calculateScore() {
        destroyedTargets.forEach {
            score += it.score
        }
        destroyedTargets.clear()
    }

    fun calculateFinalScore() {
        inventoryBombs.forEach { bomb ->
            score += bomb.score
        }
    }
}