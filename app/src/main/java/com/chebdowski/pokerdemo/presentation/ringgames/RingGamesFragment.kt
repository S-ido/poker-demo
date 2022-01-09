package com.chebdowski.pokerdemo.presentation.ringgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chebdowski.pokerdemo.R
import com.chebdowski.pokerdemo.databinding.FragmentRingGamesBinding
import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.Result
import com.chebdowski.pokerdemo.domain.Ring
import com.chebdowski.pokerdemo.presentation.BaseFragment
import com.chebdowski.pokerdemo.presentation.ErrorMessageViewModel
import com.chebdowski.pokerdemo.presentation.tabledetails.TableDetails
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RingGamesFragment : BaseFragment() {

    private var _binding: FragmentRingGamesBinding? = null
    private val binding get() = _binding!!

    private val ringGamesViewModel: RingGamesViewModel by viewModel()
    private val errorMessageViewModel: ErrorMessageViewModel by viewModel()

    private lateinit var ringGamesAdapter: RingGamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRingGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.ring_games)
        setupRingGamesList()
        setupDataCollectors()
    }

    private fun setupRingGamesList() {
        binding.ringGamesList.layoutManager = LinearLayoutManager(context)
        ringGamesAdapter = RingGamesAdapter(::handleRingClickCallback)
        binding.ringGamesList.adapter = ringGamesAdapter
        binding.ringGamesList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun setupDataCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(STARTED) {
                ringGamesViewModel.ringGames.collect { result ->
                    when (result) {
                        is Result.Success -> handleRingGames(result.data)
                        is Result.Error -> handleError(result.failure)
                        is Result.Loading -> handleLoading()
                    }
                }
            }
        }
    }

    private fun handleRingClickCallback(ring: Ring) {
        val tableDetails = ringToTableDetails(ring)
        val directions = RingGamesFragmentDirections.navigateToTableDetailsFragment(tableDetails)
        findNavController().navigate(directions)
    }

    private fun ringToTableDetails(ring: Ring) = TableDetails(ring.name, ring.gameType, ring.minBuyIn, ring.maxBuyIn)

    private fun handleRingGames(ringGames: List<Ring>) {
        binding.progressBar.visibility = View.GONE
        binding.ringGamesList.visibility = View.VISIBLE

        ringGamesAdapter.submitList(ringGames)
    }

    private fun handleError(failure: Failure) {
        binding.progressBar.visibility = View.GONE

        val errorResourceId = errorMessageViewModel.getMessage(failure)
        Snackbar.make(binding.root, errorResourceId, Snackbar.LENGTH_LONG).show()
    }

    private fun handleLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.ringGamesList.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}