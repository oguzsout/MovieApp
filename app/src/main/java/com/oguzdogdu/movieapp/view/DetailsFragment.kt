package com.oguzdogdu.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.oguzdogdu.movieapp.databinding.FragmentDetailsBinding
import com.oguzdogdu.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    val viewModel: MovieViewModel by viewModels()

    private val supportFragmentManager : FragmentManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val manager = supportFragmentManager
        try {
            // get last entry (you can get another if needed)
            val entry = manager?.getBackStackEntryAt(manager.backStackEntryCount - 1)
            // you can pop it by name
            if (entry != null) {
                manager.popBackStack(entry.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            // or pop by id
            if (entry != null) {
                manager.popBackStack(entry.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        viewModel.getMovieDetails(args.imdbId!!)

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            binding.movieDetails = it.data
        }
    }
}
