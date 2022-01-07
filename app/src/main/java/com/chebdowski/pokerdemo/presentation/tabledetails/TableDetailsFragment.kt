package com.chebdowski.pokerdemo.presentation.tabledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.chebdowski.pokerdemo.R
import com.chebdowski.pokerdemo.databinding.FragmentTableDetailsBinding
import com.chebdowski.pokerdemo.presentation.BaseFragment

class TableDetailsFragment : BaseFragment() {

    private val args by navArgs<TableDetailsFragmentArgs>()

    private var _binding: FragmentTableDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tableDetails = args.tableDetails
        handleTableDetails(tableDetails)
    }

    private fun handleTableDetails(tableDetails: TableDetails) {
        setTitle(tableDetails.name)
        binding.gameType.text = tableDetails.gameType
        binding.buyInRange.text = getString(R.string.buy_in_range, tableDetails.minBuyIn, tableDetails.maxBuyIn)
    }
}