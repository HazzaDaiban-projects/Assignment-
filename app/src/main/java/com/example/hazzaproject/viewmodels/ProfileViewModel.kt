package com.example.hazzaproject.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hazzaproject.models.Profile
import com.example.hazzaproject.repo.ProfileRepository


class ProfileViewModel : ViewModel() {
    var repo: ProfileRepository? = null
    fun getProfiles() = repo?.getProfile( )
    fun addProfile(profile : Profile) = repo?.addProfile(profile)

}