package app.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], exportSchema = false, version = 1)
abstract class NotesDB : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var THE: NotesDB? = null

        fun getInstance(context: Context): NotesDB {
            val db = THE

            if (db != null) {
                return db
            }

            synchronized(this) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDB::class.java,
                    "note"
                )
                val instance = builder.build()
                THE = instance
                return instance
            }
        }
    }
}