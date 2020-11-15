package com.example.uvotematter.votelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uvotematter.models.User
import com.example.uvotematter.models.Vote
import com.google.firebase.database.*

class VoteListViewModel(val nickname: String) : ViewModel() {

    private var refVotes: DatabaseReference = FirebaseDatabase.getInstance().getReference("votes")
    private var refUsers: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    private val _listOfUsers = MutableLiveData<List<User>>()

    private val _listOfVotes = MutableLiveData<MutableList<Vote>>()
    val listOfVotes: LiveData<MutableList<Vote>>
        get() = _listOfVotes

    init {
        _listOfVotes.value = mutableListOf()
        _listOfUsers.value = listOf()

        getAllVotes()
        getAllUsers()
    }

    private fun getAllUsers() {
        refUsers.addListenerForSingleValueEvent(object : ValueEventListener {
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

    private fun getAllVotes() {
        refVotes.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(votes: DataSnapshot) {
                if (votes.exists()) {
                    val tempArray = ArrayList<Vote>()
                    for (vote in votes.children) {
                        val v = vote.getValue(Vote::class.java)
                        v?.let {
                            tempArray.add(it)
                        }
                    }
                    _listOfVotes.value = tempArray
                    checkIfVoteFinish()
                } else {
                    _listOfVotes.value = mutableListOf()
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                //TODO("Not yet implemented")
            }
        })
    }

    private fun checkIfVoteFinish() {
        if (_listOfVotes.value?.size == _listOfUsers.value?.size) {
            var totalVotes = 0
            for (vote in _listOfVotes.value!!) {
                totalVotes += vote.points.toInt()
            }
            _listOfVotes.value?.add(
                Vote("", "Total votes", "$totalVotes")
            )
        }
    }

    fun saveVote(votePints: String) {
        val voteId = refVotes.push().key

        voteId?.let { id ->
            val vote = Vote(voteId, nickname, votePints)
            refVotes.child(id).setValue(vote)
        }
    }

    fun deleteVotes() {
        refVotes.setValue(null)
    }

    class Factory(val nickname: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VoteListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VoteListViewModel(nickname) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}