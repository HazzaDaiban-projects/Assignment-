package com.example.hazzaproject.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hazzaproject.R
import com.example.hazzaproject.models.Profile
import com.example.hazzaproject.utilities.InjectorUtils
import com.example.hazzaproject.utilities.RecyclerViewAdopter
import com.example.hazzaproject.viewmodels.ProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val listOfProfiles = ArrayList<Profile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val feed = findViewById<ImageButton>(R.id.feed)
        feed.setOnClickListener ({
            showFragmentProfile()
        })



        val profile = findViewById<ImageButton>(R.id.profiles)
        profile.setOnClickListener ({
            showFragmentList()
        })

    }

    fun showFragmentProfile() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentOne()
        transaction.replace(R.id.FragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showFragmentList() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentTwo()
        transaction.replace(R.id.FragmentHolder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun initializeUi(){

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fab.isShown()) {
                    fab.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show()
                }

                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        val factory = InjectorUtils.provideProfileViewModelFactory()
        val viewModel = ViewModelProviders.of(this,factory)
            .get(ProfileViewModel :: class.java)
        viewModel.getProfiles()?.observe(this , Observer { profiles ->
            profiles.forEach{profiles ->
                listOfProfiles.add(profiles)
            }
        })


        val adopter = RecyclerViewAdopter(listOfProfiles, this)
        recyclerView.setAdapter(adopter)
        recyclerView.setLayoutManager(LinearLayoutManager(this))


    }



}
