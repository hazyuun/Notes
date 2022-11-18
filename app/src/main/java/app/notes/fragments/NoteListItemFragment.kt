package app.notes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.notes.databinding.FragmentNoteListItemBinding

class NoteListItemFragment : Fragment() {
    private lateinit var binding: FragmentNoteListItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}