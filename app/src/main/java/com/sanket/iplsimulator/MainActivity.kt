package com.sanket.iplsimulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sanket.iplsimulator.data.datasource.IPLSimulatorLocalDataSourceImpl
import com.sanket.iplsimulator.data.repositoryimpl.IPLSimulatorRepositoryImpl
import com.sanket.iplsimulator.domain.usecases.*
import com.sanket.iplsimulator.presentation.IPLSimulatorViewModel
import com.sanket.iplsimulator.presentation.IPLSimulatorViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: IPLSimulatorViewModel
    private lateinit var factory: IPLSimulatorViewModelFactory
    lateinit var application: SimulatorApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        application = applicationContext as SimulatorApplication
        prepareViewModel()
    }

    private fun prepareViewModel() {
        val dependecies = IPLSimulatorRepositoryImpl(
            IPLSimulatorLocalDataSourceImpl(
                SimulatorApplication.getDB(this).getDAO()
            )
        )
        factory = IPLSimulatorViewModelFactory(
            AddIPLTeamUseCase(dependecies),
            GetIPLTeamsUseCase(dependecies),
            SimulateGameUseCase(dependecies),
            GetTeamPairsUseCase(dependecies),
            GetWinnersUseCase(dependecies)
        )
        viewModel = ViewModelProvider(this, factory)[IPLSimulatorViewModel::class.java]
    }
}