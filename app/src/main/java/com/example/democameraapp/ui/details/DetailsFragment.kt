package com.example.democameraapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.democameraapp.data.AppConst.BUNDLE_CAMERA_KEY
import com.example.democameraapp.data.model.Camera
import com.example.democameraapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (arguments?.getSerializable(BUNDLE_CAMERA_KEY) as? Camera)?.let {
            setText(it)
        }
    }

    private fun setText(camera: Camera){
        binding.apply {
            cameraTitle.text = camera.name
            cameraId.text = camera.id
            cameraName.text = camera.name
            cameraType.text = camera.type
            cameraSerialNumber.text = camera.serial_number
            cameraIp.text = camera.ip_address
        }
    }
}