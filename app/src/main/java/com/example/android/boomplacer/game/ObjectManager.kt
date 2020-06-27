package com.example.android.boomplacer.game

import com.example.android.boomplacer.gameobjects.base.Blast
import com.example.android.boomplacer.gameobjects.base.Bomb
import com.example.android.boomplacer.gameobjects.base.Target
import java.util.*

class ObjectManager {
    val placedTargets = mutableListOf<Target>()
    val placedBombs = mutableListOf<Bomb>()
    val placedBlasts = mutableListOf<Blast>()

    val pendingTargets = LinkedList<Target>()
    val inventoryBombs = LinkedList<Bomb>()

    fun clearScreen() {
        placedTargets.clear()
        placedBombs.clear()
        placedBlasts.clear()
    }

    fun clearInventory() {
        inventoryBombs.clear()
    }

    fun clearPending() {
        pendingTargets.clear()
    }

    fun clearAll() {
        clearPending()
        clearInventory()
        clearScreen()
    }

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
}