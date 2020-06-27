package com.example.android.boomplacer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.boomplacer.R
import com.example.android.boomplacer.game.GameView
import com.example.android.boomplacer.model.Event

class MenuFragment(private val game: GameView) : Fragment() {
    private val _resumeButtonClicked = MutableLiveData<Event<Unit>>()
    val resumeButtonClicked: LiveData<Event<Unit>> = _resumeButtonClicked

    private val _newGameButtonClicked = MutableLiveData<Event<Unit>>()
    val newGameButtonClicked: LiveData<Event<Unit>> = _newGameButtonClicked

    private val _exitButtonClicked = MutableLiveData<Event<Unit>>()
    val exitButtonClicked: LiveData<Event<Unit>> = _exitButtonClicked

    private val _bestResultsButtonClicked = MutableLiveData<Event<Unit>>()
    val bestResultsButtonClicked: LiveData<Event<Unit>> = _bestResultsButtonClicked


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button_new_game).setOnClickListener {
            _newGameButtonClicked.postValue(Event(Unit))
        }

        view.findViewById<Button>(R.id.button_exit).setOnClickListener {
            _exitButtonClicked.postValue(Event(Unit))
        }

        view.findViewById<Button>(R.id.button_resume).setOnClickListener {
            _resumeButtonClicked.postValue(Event(Unit))
        }

        view.findViewById<Button>(R.id.button_best_results).setOnClickListener {
            _bestResultsButtonClicked.postValue(Event(Unit))
        }
    }
}
