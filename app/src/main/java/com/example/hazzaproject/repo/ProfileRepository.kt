package com.example.hazzaproject.repo

import com.example.hazzaproject.models.Profile


//do all of the decision making regarding app’s data.

class ProfileRepository constructor(private val dao : FakeDao){

    fun addProfile(profile: Profile){
        dao.addProfile(profile)
    }

    fun getProfile()= dao.getProfile()

    companion object{
        @Volatile private var instance : ProfileRepository? = null
            fun getInstance(profileDao : FakeDao) =
                instance ?: synchronized(this){
                    instance ?: ProfileRepository(profileDao).also{ instance = it}
                }
    }



}