package com.example.android.boomplacer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.boomplacer.MainViewModel
import com.example.android.boomplacer.databinding.FragmentUiBinding
import com.example.android.boomplacer.ui.interfaces.IndicatorsButtons

class UiFragment : Fragment(), IndicatorsButtons {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainMenuFrame: ViewGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUiBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        bindControllers(binding)
        mainMenuFrame = binding.mainMenu.menuMainFrame
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.game.gameEvents.bombsChanged.observe(viewLifecycleOwner, Observer {
            Log.d("Game", "onCreateView: $it")
        })
    }

    override fun pressMainMenu() {
        viewModel.game.pauseGame()
        mainMenuFrame.visibility = View.VISIBLE
    }

    private fun bindControllers(binding: FragmentUiBinding) {
        MenuController.bind(binding, viewModel)
        LevelSelectorController.bind(binding, viewModel)
        WinLoseDialogController.bind(binding, viewModel)

        with(binding.indicators) {
            this.viewModel = this@UiFragment.viewModel
            this.controller = this@UiFragment
        }
    }
}