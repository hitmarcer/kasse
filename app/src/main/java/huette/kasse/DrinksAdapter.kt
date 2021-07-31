package huette.kasse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DrinksAdapter constructor() : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {
    lateinit var alDrinks: ArrayList<Drink>
    lateinit var context: Context
    lateinit var listener: OnItemClickListener

    constructor(
        ct: Context,
        alDrinks: ArrayList<Drink>,
        listener: OnItemClickListener
    ) : this() {
        this.context = ct
        this.alDrinks = alDrinks
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
                listener.OnItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int)
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
            "${Variables.alDrinks.get(position).drinkName}\n${Variables.alDrinks.get(position).price} €"
        holder.tfContent.setText(drinkName)
    }

    override fun getItemCount(): Int {
        val i: Int = Variables.alDrinks.size
        return i
    }
}
