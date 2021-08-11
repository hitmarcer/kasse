package huette.kasse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.data.entities.User
import java.util.*
import kotlin.collections.ArrayList

class NamesAdapter constructor() : RecyclerView.Adapter<NamesAdapter.NamesViewHolder>(),
    Filterable {
    //lateinit var alUserOlds: ArrayList<UserOld>
    private var userList = emptyList<User>()
    private var userFilterList = emptyList<User>()
    lateinit var context: Context
    lateinit var listener: OnItemClickListener

    init {
        userFilterList = userList
    }

    constructor(ct: Context, listener: OnItemClickListener) : this() {
        this.context = ct
        this.listener = listener
    }

    inner class NamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val tfContent: TextView = itemView.findViewById(R.id.tvContent)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.OnItemClick(position, userFilterList)
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int, users: List<User>)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NamesAdapter.NamesViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.names_drinks, parent, false)
        return NamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        val name: String =
            "${userFilterList[position].firstName}\n${userFilterList[position].lastName}"
        holder.tfContent.text = name
    }

    override fun getItemCount(): Int {
        return userFilterList.size
    }

    fun setData(users: List<User>) {
        this.userList = users
        this.userFilterList = users
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    userFilterList = userList
                } else {
                    val resultList = ArrayList<User>()
                    for (row in userList) {
                        if (row.firstName.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT)) || row.lastName.lowercase(
                                Locale.ROOT
                            ).contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    userFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userFilterList = results?.values as List<User>
                notifyDataSetChanged()
            }
        }
    }

}
