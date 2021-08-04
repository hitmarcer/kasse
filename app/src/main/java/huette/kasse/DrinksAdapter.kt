package huette.kasse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.data.entities.Drink

class DrinksAdapter constructor() : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {
    private var drinksList = emptyList<Drink>()
    lateinit var context: Context
    lateinit var listener: OnItemClickListener

    constructor(
        ct: Context,
        listener: OnItemClickListener
    ) : this() {
        this.context = ct
        this.listener = listener
    }

    inner class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val tfContent: TextView = itemView.findViewById(R.id.tvContent)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.OnItemClick(position, drinksList)
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int, drinks: List<Drink>)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DrinksAdapter.DrinksViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.names_drinks, parent, false)
        return DrinksViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinksAdapter.DrinksViewHolder, position: Int) {
        val drinkName: String =
            "${drinksList[position].drinkName}\n${String.format("%.2f", drinksList[position].price)} €"
        holder.tfContent.setText(drinkName)
    }

    override fun getItemCount(): Int {
        val i: Int = drinksList.size
        return i
    }

    fun setData(drinks: List<Drink>) {
        this.drinksList = drinks
        notifyDataSetChanged()
    }
}
