package com.chebdowski.pokerdemo.presentation.ringgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chebdowski.pokerdemo.databinding.FragmentRingGamesBinding
import com.chebdowski.pokerdemo.domain.Failure
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class RingGamesFragment : Fragment() {

    private var _binding: FragmentRingGamesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RingGamesViewModel by viewModel()

    private lateinit var ringGamesAdapter: RingGamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRingGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRingGamesList()
        setupObservers()

        viewModel.loadRingGames()
    }

    private fun setupRingGamesList() {
        binding.ringGamesList.layoutManager = LinearLayoutManager(context)
        ringGamesAdapter = RingGamesAdapter { ring ->
            println(ring.name)
        }
        binding.ringGamesList.adapter = ringGamesAdapter
        binding.ringGamesList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun setupObservers() {
        viewModel.loading.observe(viewLifecycleOwner, { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        })

        viewModel.ringGames.observe(viewLifecycleOwner, { ringGames ->
            ringGamesAdapter.submitList(ringGames)
        })

        viewModel.failure.observe(viewLifecycleOwner, { failure -> handleError(failure) })
    }

    private fun handleError(failure: Failure) {
        "message"?.let {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}