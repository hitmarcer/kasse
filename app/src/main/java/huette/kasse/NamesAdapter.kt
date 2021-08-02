package huette.kasse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NamesAdapter constructor(): RecyclerView.Adapter<NamesAdapter.NamesViewHolder>() {
    lateinit var alUserOlds: ArrayList<UserOld>
    lateinit var context: Context
    lateinit var listener: OnItemClickListener

    constructor(ct: Context, alUserOlds: ArrayList<UserOld>, listener: OnItemClickListener) : this() {
        this.context = ct
        this.alUserOlds = alUserOlds
        this.listener = listener
    }

    inner class NamesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val tfContent: TextView = itemView.findViewById(R.id.tvContent)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.OnItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesAdapter.NamesViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.names_drinks, parent,false)
        return NamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        val name: String = "${Variables.alUserOlds.get(position).firstName}\n${Variables.alUserOlds.get(position).lastName}"
        holder.tfContent.setText(name)
    }

    override fun getItemCount(): Int {
        val i: Int = Variables.alUserOlds.size
        return i
    }
}
