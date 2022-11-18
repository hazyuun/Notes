package app.notes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.notes.data.Note
import app.notes.data.NotesDB
import app.notes.data.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var note: Note

    fun setNote(n: Note?) {
        note = n
            ?: Note(
                null,
                "",
                "",
                null
            )
    }

    private var repository: NotesRepository

    init {
        val noteDao = NotesDB.getInstance(application.applicationContext).noteDao()
        repository = NotesRepository(noteDao)
    }

    fun save(): Boolean {
        if (!validateInput()) {
            return false
        }

        viewModelScope.launch(Dispatchers.IO) {
            repository.upsert(note)
        }

        return true
    }

    fun updateTitle(t: String) {
        note.title = t
    }

    fun updateContent(c: String) {
        note.content = c
    }

    private fun validateInput(): Boolean {
        var valid = true
        valid = valid && note.title.isNotEmpty()
        valid = valid && note.title.isNotBlank()
        valid = valid && note.content.isNotEmpty()
        valid = valid && note.content.isNotBlank()
        return valid
    }
}