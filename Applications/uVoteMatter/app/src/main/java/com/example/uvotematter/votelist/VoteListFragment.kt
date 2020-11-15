package com.example.uvotematter.votelist

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.uvotematter.R
import com.example.uvotematter.databinding.FragmentVoteListBinding
import com.example.uvotematter.login.LoginFragment
import com.example.uvotematter.models.User
import com.example.uvotematter.models.Vote
import com.google.firebase.database.*

class VoteListFragment : Fragment() {

    private lateinit var binding: FragmentVoteListBinding
    private lateinit var factory: VoteListViewModel.Factory
    private lateinit var viewModel: VoteListViewModel
    private lateinit var adapter: VoteResultAdapter

    private lateinit var nickname: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVoteListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)


        nickname = VoteListFragmentArgs.fromBundle(requireArguments()).nickname

        factory = VoteListViewModel.Factory(nickname)
        viewModel = ViewModelProvider(this, factory).get(VoteListViewModel::class.java)

        adapter = VoteResultAdapter()
        binding.voteResultRecycler.adapter = adapter

        viewModel.listOfVotes.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
            if (it.isEmpty()) {
                binding.voteRecyclerview.visibility = View.VISIBLE
            }
        })

        binding.voteRecyclerview.adapter = VotesAdapter(OnClickListener {
            viewModel.saveVote(it)
            binding.voteRecyclerview.visibility = View.GONE
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                viewModel.deleteVotes()
                binding.voteRecyclerview.visibility = View.VISIBLE
                true
            }
            else -> true
        }
    }

}