package com.chebdowski.pokerdemo.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.chebdowski.pokerdemo.R
import com.chebdowski.pokerdemo.databinding.ActivityMainBinding
import com.chebdowski.pokerdemo.presentation.tabledetails.TableDetailsFragmentDirections


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navigateToRingsList()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToRingsList() {
        val directions = TableDetailsFragmentDirections.navigateToRingGamesFragment()
        findNavController(R.id.main_content).navigate(directions)
    }
}