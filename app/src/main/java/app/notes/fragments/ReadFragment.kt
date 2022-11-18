package app.notes.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import app.notes.R
import app.notes.databinding.FragmentHomeBinding
import app.notes.databinding.FragmentReadBinding
import app.notes.viewmodels.NotesViewModel


class ReadFragment : Fragment() {

    private lateinit var binding: FragmentReadBinding
    private val viewModel: NotesViewModel by activityViewModels()
    private val args: ReadFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReadBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_scr_read_to_scr_list)
        }

        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete note ?")
                .setMessage("This action is not reversible")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.delete(args.note)
                    Navigation.findNavController(view).navigate(R.id.action_scr_read_to_scr_list)
                }
                .setNegativeButton("No") { _, _ ->

                }.show()
        }

        binding.btnEdit.setOnClickListener {
            val action = ReadFragmentDirections.actionScrReadToScrEdit().setNote(args.note)
            Navigation.findNavController(view).navigate(action)
        }

        binding.txtTitle.text = args.note.title
        binding.txtContent.text = args.note.content

        return view
    }
}