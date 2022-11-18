package app.notes.viewmodels

import android.app.Application
import androidx.lifecycle.*
import app.notes.data.Note
import app.notes.data.NotesDB
import app.notes.data.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: NotesRepository
    var allNotes: LiveData<List<Note>>

    init {
        val noteDao = NotesDB.getInstance(application.applicationContext).noteDao()
        repository = NotesRepository(noteDao)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }

}