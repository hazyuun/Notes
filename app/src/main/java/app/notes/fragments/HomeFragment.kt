package app.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.notes.R
import app.notes.adapters.NotesRecyclerViewAdapter
import app.notes.databinding.FragmentHomeBinding
import app.notes.viewmodels.NotesViewModel

class HomeFragment : Fragment() {

    private val viewModel: NotesViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_scr_list_to_scr_edit)
        }

        binding.rvNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)


        val adapter = NotesRecyclerViewAdapter { note ->
            val action = HomeFragmentDirections.actionScrListToScrRead(note)
            Navigation.findNavController(view).navigate(action)
        }

        binding.rvNotes.adapter = adapter
        viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            adapter.setNotes(notes)
        }

        return view
    }
}