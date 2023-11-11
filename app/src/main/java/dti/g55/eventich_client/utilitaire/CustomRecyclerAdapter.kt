package dti.g55.eventich_client.utilitaire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.presentation.presentateur.IPresentateur

class CustomRecyclerAdapter<T: Any ,VHT: CustomViewHolder<T>, P: IPresentateur>(var items: List<T>, @LayoutRes var layout: Int, var viewHolder: (View, P) -> VHT, var presentateur: P)
    : RecyclerView.Adapter<VHT>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHT {
        val itemView: View = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return viewHolder(itemView, presentateur)
    }

    override fun onBindViewHolder(holder: VHT, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}