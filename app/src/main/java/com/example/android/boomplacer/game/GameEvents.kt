package com.example.android.boomplacer.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GameEvents {
    private var bombsCount: Int = 0
        set(value) {
            if (value != field) {
                _bombsChanged.postValue(value)
                field = value
            }
        }

    private var targetsLeft: Int = 0
        set(value) {
            if (value != field) {
                _targetsChanged.postValue(value)
                field = value
            }
        }

    private var difficultyOffset: Int = 1
        set(value) {
            if (value != field) {
                _levelChanged.postValue(getLevelNumber(value))
                field = value
            }
        }

    private var score: Int = 0
        set(value) {
            if (value != field) {
                _scoreChanged.postValue(value)
                field = value
            }
        }

    private val _bombsChanged = MutableLiveData<Int>()
    val bombsChanged: LiveData<Int> = _bombsChanged

    private val _targetsChanged = MutableLiveData<Int>()
    val targetsChanged: LiveData<Int> = _targetsChanged

    private val _levelChanged = MutableLiveData<Int>()
    val levelChanged: LiveData<Int> = _levelChanged

    private val _scoreChanged = MutableLiveData<Int>()
    val scoreChanged: LiveData<Int> = _scoreChanged

    private val _levelWon = MutableLiveData<Int>()
    val levelWon: LiveData<Int> = _levelWon

    private val _categoryWon = MutableLiveData<Int>()
    val categoryWon: LiveData<Int> = _categoryWon

    private val _levelLost = MutableLiveData<Unit>()
    val levelLost: LiveData<Unit> = _levelLost

    fun updateValues(bombs: Int, targets: Int, difficultyOffset: Int, score: Int) {
        bombsCount = bombs
        targetsLeft = targets
        this.difficultyOffset = difficultyOffset
        this.score = score
    }

    fun resetValues() {
        bombsCount = 0
        targetsLeft = 0
        difficultyOffset = 0
        score = 0
    }

    fun notifyLevelWon(finalScore: Int) {
        _levelWon.postValue(finalScore)
    }

    fun notifyCategoryWon(finalScore: Int) {
        _categoryWon.postValue(finalScore)
    }

    fun notifyLevelLost() {
        _levelLost.postValue(Unit)
    }

    private fun getLevelNumber(difficultyOffset: Int) =
        difficultyOffset + 1 // level number and difficulty offset are both the same, but level number should start from 1, not 0
}