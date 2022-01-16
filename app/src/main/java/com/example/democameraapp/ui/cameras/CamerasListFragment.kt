package com.example.democameraapp.ui.cameras

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.democameraapp.R
import com.example.democameraapp.data.AppConst.BUNDLE_CAMERA_KEY
import com.example.democameraapp.data.model.Camera
import com.example.democameraapp.databinding.FragmentCamerasListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CamerasListFragment : Fragment() {

    private lateinit var binding: FragmentCamerasListBinding
    private lateinit var cameraAdapter: CameraListAdapter

    private val viewModel: CamerasListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCamerasListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModel()
        viewModel.fetchDeviceList()
    }

    private fun subscribeToViewModel() {
        viewModel.cameraListLiveData.observe(viewLifecycleOwner) { cameras ->
            cameraAdapter = CameraListAdapter(
                cameraList = cameras,
                onCameraClicked = ::navigateToDetails
            )

            binding.apply {
                progressBar.visibility = View.GONE
                deviceRecyclerView.adapter = cameraAdapter
            }
        }
    }

    private fun navigateToDetails(camera: Camera) {
        val bundle = bundleOf(BUNDLE_CAMERA_KEY to camera)
        binding.root.findNavController()
            .navigate(R.id.action_camerasListFragment_to_detailsFragment, bundle)
    }
}