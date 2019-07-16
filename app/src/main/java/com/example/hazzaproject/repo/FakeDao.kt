package com.example.hazzaproject.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hazzaproject.models.Profile
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class FakeDao {
    private val profileList = mutableListOf<Profile>()
    private val profiles = MutableLiveData<List<Profile>>()

    init {
        profiles.value = profileList

        addProfile(Profile("Abbi Hurst", "person1", "CEO", "a@hotmail.com"))
        addProfile(Profile("Denise Brown", "person2", "Vice President", "VP@mail.com"))
        addProfile(Profile("Saima Dillo", "person3", "Team leader", "TL@mail.com"))
        addProfile(Profile("Aamna Farro", "person1", "Developer", "dev@mail.com"))

    }

    fun addProfile(profile: Profile) {
        profileList.add(profile)
        profiles.value = profileList
    }


/*
    private fun fetchJson() {

        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                var body = response?.body?.string()
                println(body)
                //body = "{\"user\":{\"name\":\"Brian Voong\",\"username\":\"brianvoong\"},\"profiles\":[{\"name\":\"Ali KoKo\",\"imageName\":\"person2\",\"jobTitle\":\"System analyst\",\"email\":\"Ali@msil.com\"},{\"name\":\"Saleh Alkatosh\",\"imageName\":\"person3\",\"jobTitle\":\"Tester\",\"email\":\"saleh@msil.com\"}]}"
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
               // profiles.profilesx.forEach({profile -> addProfile(profile)})
                //println(profileList)
            }

            override fun onFailure(call: Call, e: IOException) {


            }

        })
    }
*/

    fun getProfile(): LiveData<List<Profile>> {
        return profiles
    }


}