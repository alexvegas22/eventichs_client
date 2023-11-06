package dti.g55.eventich_client.utilitaire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter<T: Any ,VHT: CustomViewHolder<T>>(var items: List<T>, @LayoutRes var layout: Int, var viewHolder: (View) -> VHT)
    : RecyclerView.Adapter<VHT>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHT {
        val itemView: View = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VHT, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}