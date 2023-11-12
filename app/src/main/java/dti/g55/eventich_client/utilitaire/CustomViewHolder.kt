package dti.g55.eventich_client.utilitaire

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.presentation.presentateur.IPresentateur

abstract class CustomViewHolder<T: Any>(itemView: View, presentateur: IPresentateur): RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: T)
}