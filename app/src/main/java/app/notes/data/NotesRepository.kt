package app.notes.data

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAll()

    suspend fun insert(note: Note) {
        noteDao.insert(note.apply {
            modifiedAt = System.currentTimeMillis()
        })
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun upsert(note: Note) {
        noteDao.upsert(note.apply {
            modifiedAt = System.currentTimeMillis()
        })
    }
}