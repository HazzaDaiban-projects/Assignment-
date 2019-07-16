package com.example.hazzaproject.utilities

import com.example.hazzaproject.viewmodels.ProfileViewModelFactory
import com.example.hazzaproject.repo.Database
import com.example.hazzaproject.repo.ProfileRepository

object InjectorUtils {

    fun provideProfileViewModelFactory() : ProfileViewModelFactory {
        val profileReposirory = ProfileRepository.getInstance(Database.getInstance().profileDao)
        return ProfileViewModelFactory(profileReposirory)
    }
}