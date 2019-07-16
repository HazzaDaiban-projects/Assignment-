package com.example.hazzaproject.UI

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hazzaproject.R
import com.example.hazzaproject.models.Profile
import com.example.hazzaproject.repo.FakeDao
import com.example.hazzaproject.repo.ProfileRepository
import com.example.hazzaproject.utilities.InjectorUtils
import com.example.hazzaproject.utilities.RecyclerViewAdopter
import com.example.hazzaproject.viewmodels.ProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.*
import java.io.IOException
import java.util.ArrayList


class FragmentTwo : Fragment(){
    private lateinit var viewModel: ProfileViewModel
    private val listOfProfiles = ArrayList<Profile>()





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.repo = ProfileRepository(FakeDao())
        return inflater!!.inflate(R.layout.recyclelist,container,false)
    }

    override fun onStart() {
        super.onStart()
        initializeUi()


        }



    private fun initializeUi(){

        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView)
        val swipeContainer = view!!.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        load(recyclerView , swipeContainer )

        swipeContainer.setOnRefreshListener{
            val updateHandler = Handler()

            val runnable = Runnable {
                fake()
                load(recyclerView , swipeContainer )
                Toast.makeText(activity,"List Loaded",Toast.LENGTH_SHORT).show()
            }

            updateHandler.postDelayed(runnable, 5000)

        }
    }


private fun fake(){

    val extra1 = Profile("Ali Ahmad" , "person2","Tester" , "Ali@mail.com")
    val extra2 = Profile("Saleh Rashid" , "person3","Cleaner" , "saleh@mail.com")
    val extra3 = Profile("Mohammed Jaber" , "person1","Nothing" , " Nohammed@mail.com")

    viewModel.addProfile(extra1)
    viewModel.addProfile(extra2)
    viewModel.addProfile(extra3)

}

    private fun load(recyclerView: RecyclerView , swipeContainer :SwipeRefreshLayout){


        val factory = InjectorUtils.provideProfileViewModelFactory()
        listOfProfiles.clear();
        viewModel.getProfiles()?.observe(this , Observer{ profiles ->
            profiles.forEach{profiles ->
                listOfProfiles.add(profiles)
            }
        })
        val adopter = RecyclerViewAdopter(listOfProfiles, activity)
        recyclerView.setAdapter(adopter)
        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        swipeContainer.isRefreshing = false

    }




}