package com.example.democameraapp.data.model

import org.json.JSONObject
import java.io.Serializable

data class Camera(
    val accountId: String?,
    val id: String?,
    val name: String?,
    val type: String?,
    val bridges: List<List<String>>?,
    val service_status: String?,
    val permissions: String?,
    val tags: List<String>?,
    val guid: String?,
    val serial_number: String?,
    val device_status: Int?,
    val timezone: String?,
    val timezone_utc_offset: Int?,
    val is_unsupported: Int?,
    val ip_address: String?,
    val is_shared: Int?,
    val owner_account_name: String?,
    val is_upnp: Boolean?,
    val video_input: String?,
    val video_status: String?,
    val location: List<Any>?,
    val parent_camera_id: String?,
    val child_camera_view: String?,
    val is_hidden: Int?,
    val ignored_inputs: List<String>?,
    val responder_camera: Int?,
    val super_tags: JSONObject?,
    val discovered_state: JSONObject?,
    val flags: JSONObject?,
) : Serializable