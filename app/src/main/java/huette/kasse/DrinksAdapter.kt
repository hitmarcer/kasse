package huette.kasse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DrinksAdapter constructor() : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {
    lateinit var alDrinkOlds: ArrayList<DrinkOld>
    lateinit var context: Context
    lateinit var listener: OnItemClickListener

    constructor(
        ct: Context,
        alDrinkOlds: ArrayList<DrinkOld>,
        listener: OnItemClickListener
    ) : this() {
        this.context = ct
        this.alDrinkOlds = alDrinkOlds
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
            "${Variables.alDrinkOlds.get(position).drinkName}\n${Variables.alDrinkOlds.get(position).price} €"
        holder.tfContent.setText(drinkName)
    }

    override fun getItemCount(): Int {
        val i: Int = Variables.alDrinkOlds.size
        return i
    }
}
