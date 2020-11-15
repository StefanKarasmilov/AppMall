package com.example.uvotematter.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uvotematter.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginViewModel : ViewModel() {

    private  val ref = FirebaseDatabase.getInstance().getReference("users")

    private val _listOfUsers = MutableLiveData<List<User>>()
    val listOfUsers: LiveData<List<User>>
        get() = _listOfUsers

    init {
        _listOfUsers.value = listOf()

        getAllUsers()
    }

    private fun getAllUsers() {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(users: DataSnapshot) {
                if (users.exists()) {
                    val tempList = ArrayList<User>()
                    for (vote in users.children) {
                        val v = vote.getValue(User::class.java)
                        v?.let {
                            tempList.add(it)
                        }
                    }
                    _listOfUsers.value = tempList
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                //TODO("Not yet implemented")
            }

        })
    }

}