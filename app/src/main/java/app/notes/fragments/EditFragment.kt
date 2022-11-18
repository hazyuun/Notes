package app.notes.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import app.notes.R
import app.notes.databinding.FragmentEditBinding
import app.notes.viewmodels.EditFragmentViewModel

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding

    private lateinit var viewModel: EditFragmentViewModel

    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        if (args.note != null) {
            binding.inputTitle.setText(args.note?.title)
            binding.inputContent.setText(args.note?.content)
        }

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_scr_edit_to_scr_list)
        }

        binding.btnSave.setOnClickListener {
            viewModel.updateTitle(binding.inputTitle.text.toString())
            viewModel.updateContent(binding.inputContent.text.toString())

            val success = viewModel.save()
            val message = if (success) "Note saved" else "Title and content can not be empty"

            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

            if (success) Navigation.findNavController(view)
                .navigate(R.id.action_scr_edit_to_scr_list)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[EditFragmentViewModel::class.java]
        viewModel.setNote(args.note)
    }
}