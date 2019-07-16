package com.example.hazzaproject.repo

class Database private constructor() {
    var profileDao = FakeDao()
        private set

    companion object {
        @Volatile private var instance: Database? = null
         fun getInstance()=
            instance ?: synchronized(this){
            instance ?: Database().also{ instance = it}
        }
    }

}