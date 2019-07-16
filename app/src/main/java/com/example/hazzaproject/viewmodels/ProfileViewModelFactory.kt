package com.example.hazzaproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hazzaproject.repo.ProfileRepository

class ProfileViewModelFactory(private val repo: ProfileRepository ) : ViewModelProvider.NewInstanceFactory() {



}