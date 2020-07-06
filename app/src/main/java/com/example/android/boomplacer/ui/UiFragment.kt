package com.example.android.boomplacer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        MenuController.bind(binding, viewModel)
        LevelSelectorController.bind(binding, viewModel)
        WinLoseDialogController.bind(binding, viewModel)

        with(binding.indicators) {
            this.viewModel = viewModel
            this.controller = this@UiFragment
        }

        mainMenuFrame = binding.mainMenu.menuMainFrame
        return binding.root
    }

    override fun pressMainMenu() {
        viewModel.game.pauseGame()
        mainMenuFrame.visibility = View.VISIBLE
    }
}