package com.andry.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.andry.moviesapp.databinding.FragmentHomeBinding
import com.andry.moviesapp.ui.adapter.MoviesAdapter
import com.andry.moviesapp.ui.viewmodel.MoviesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesAdapter: MoviesAdapter

    private val viewModel: MoviesViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupRecyclerView()
        setupSearch()
        lifecycleScope.launch { viewModel.getMoviesFromDb() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter()
        binding.movieRecycler.adapter = moviesAdapter
    }

    private fun setupSearch() {
        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch { viewModel.filterMovies(text.toString()) }
        }
    }

    private fun setupObserver() {
        viewModel.movieEvent.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
        }
    }
}