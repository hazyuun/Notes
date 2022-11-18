package app.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.notes.R
import app.notes.data.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesRecyclerViewAdapter(private val onItemClick: ((Note) -> Unit)) :
    RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder>() {
    private var notes = emptyList<Note>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val content: TextView
        val date: TextView

        init {
            title = itemView.findViewById(R.id.txt_note_title)
            content = itemView.findViewById(R.id.txt_note_content)
            date = itemView.findViewById(R.id.txt_date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_note_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            onItemClick.invoke(notes[position])
        }

        holder.title.text = notes[position].title
        holder.content.text = notes[position].content
        if (notes[position].modifiedAt == null) {
            holder.date.text = "-"
            return
        }
        val fmt = SimpleDateFormat("dd-MM-yyyy hh:mm")
        val date = Date(notes[position].modifiedAt!!)
        holder.date.text = fmt.format(date)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(n: List<Note>) {
        notes = n
        notifyDataSetChanged()
    }
}