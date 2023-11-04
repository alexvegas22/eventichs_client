package dti.g55.eventich_client.utilitaire

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CustomViewHolder<T: Any>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bindItem(item: T)
}