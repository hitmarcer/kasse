package huette.kasse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NamesAdapter constructor(): RecyclerView.Adapter<NamesAdapter.NamesViewHolder>() {
    lateinit var alUsers: ArrayList<User>
    lateinit var context: Context

    constructor(ct: Context, alUsers: ArrayList<User>) : this() {
        this.context = ct
        this.alUsers = alUsers
    }

    class NamesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tfContent: TextView = itemView.findViewById(R.id.tvContent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.names_drinks, parent,false)
        return NamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        val name: String = "${Variables.alUsers.get(position).firstName}\n${Variables.alUsers.get(position).lastName}"
        holder.tfContent.setText(name)
    }

    override fun getItemCount(): Int {
        val i: Int = Variables.alUsers.size
        return i
    }
}
