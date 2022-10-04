package com.hermosotech.filmjoy.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hermosotech.filmjoy.core.di.RoomModule
import com.hermosotech.filmjoy.databinding.FragmentTvShowDetailBinding
import com.hermosotech.filmjoy.domain.model.ApiConfig
import com.hermosotech.filmjoy.domain.model.TvShow
import com.hermosotech.filmjoy.ui.viewmodel.TvShowDetailViewModel
import com.hermosotech.filmjoy.ui.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TvShowDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TvShowDetailFragment : Fragment() {

    private val tvShowDetailViewModel: TvShowDetailViewModel by viewModels()
    private val args : TvShowDetailFragmentArgs by navArgs()
    private lateinit var apiConfig : ApiConfig
    private lateinit var tvShow : TvShow

    private var _binding: FragmentTvShowDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShowId: Int = args.tvShowId
        val tableName: String = args.tableName

        if (tvShowId >= 0 && tableName.isNotEmpty()) {
            tvShowDetailViewModel.onCreate(tvShowId, tableName)

            tvShowDetailViewModel.apiConfiguration.observe(viewLifecycleOwner){
                apiConfig = it
            }

            tvShowDetailViewModel.tvShow.observe(viewLifecycleOwner) {
                tvShow = it
                Toast.makeText(context, tvShow.name, Toast.LENGTH_SHORT).show()
            }

            tvShowDetailViewModel.isLoading.observe(viewLifecycleOwner) {
                // todo progress bar
            }
        }
    }

}