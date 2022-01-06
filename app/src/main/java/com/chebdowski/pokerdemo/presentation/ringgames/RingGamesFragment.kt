package com.chebdowski.pokerdemo.presentation.ringgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chebdowski.pokerdemo.databinding.FragmentRingGamesBinding
import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.Ring
import com.chebdowski.pokerdemo.presentation.ErrorMessageViewModel
import com.chebdowski.pokerdemo.presentation.tabledetails.TableDetails
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class RingGamesFragment : Fragment() {

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

        setupRingGamesList()
        setupObservers()

        ringGamesViewModel.loadRingGames()
    }

    private fun setupRingGamesList() {
        binding.ringGamesList.layoutManager = LinearLayoutManager(context)
        ringGamesAdapter = RingGamesAdapter(::handleRingClickCallback)
        binding.ringGamesList.adapter = ringGamesAdapter
        binding.ringGamesList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun setupObservers() {
        ringGamesViewModel.loading.observe(viewLifecycleOwner, { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        })

        ringGamesViewModel.ringGames.observe(viewLifecycleOwner, { ringGames ->
            ringGamesAdapter.submitList(ringGames)
        })

        ringGamesViewModel.failure.observe(viewLifecycleOwner, { failure -> handleError(failure) })
    }

    private fun handleRingClickCallback(ring: Ring) {
        val tableDetails = ringToTableDetails(ring)
        val directions = RingGamesFragmentDirections.navigateToTableDetailsFragment(tableDetails)
        findNavController().navigate(directions)
    }

    private fun ringToTableDetails(ring: Ring) = TableDetails(ring.name, ring.gameType, ring.minBuyIn, ring.maxBuyIn)

    private fun handleError(failure: Failure) {
        val errorResourceId = errorMessageViewModel.getMessage(failure)
        Snackbar.make(binding.root, errorResourceId, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}