package app.notes.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
abstract class NoteDao {
    @Query("SELECT * FROM note ORDER BY modified_at DESC")
    abstract fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    abstract fun getOne(id: Int): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(note: Note): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun update(note: Note): Int

    @Delete
    abstract suspend fun delete(note: Note)

    @Transaction
    open suspend fun upsert(note: Note) {
        val id = insert(note)
        if (id == -1L) {
            update(note)
        }
    }

}