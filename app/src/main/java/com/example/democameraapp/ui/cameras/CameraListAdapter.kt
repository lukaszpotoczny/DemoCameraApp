package com.example.democameraapp.ui.cameras

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.democameraapp.data.model.Camera
import com.example.democameraapp.databinding.ItemCameraBinding

class CameraListAdapter(
    private val cameraList: List<Camera>,
    private val onCameraClicked: (Camera) -> Unit
) : RecyclerView.Adapter<CameraListAdapter.CameraItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraItemViewHolder {
        val binding: ItemCameraBinding = ItemCameraBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CameraItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CameraItemViewHolder, position: Int) {
        holder.bind(cameraList[position])
    }

    override fun getItemCount() = cameraList.size

    inner class CameraItemViewHolder(
        private val binding: ItemCameraBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(camera: Camera) {
            binding.apply {
                cameraName.text = camera.name
                cameraId.text = camera.id

                root.setOnClickListener {
                    onCameraClicked.invoke(camera)
                }
            }

        }
    }
}