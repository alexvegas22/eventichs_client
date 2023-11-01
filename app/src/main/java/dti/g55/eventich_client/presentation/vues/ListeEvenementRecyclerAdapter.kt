package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.EvenementListeItem

class ListeEvenementRecyclerAdapter(var listeEvenements: List<EvenementListeItem>, var context: Context): RecyclerView.Adapter<ListeEvenementRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item: LinearLayout
        val image: ImageView
        val nom: TextView
        val date: TextView
        val location: TextView
        val button: ImageButton

        init {
            item = itemView.findViewById(R.id.evenement_liste_item)
            image = itemView.findViewById(R.id.evenement_liste_item_image)
            nom = itemView.findViewById(R.id.evenement_liste_item_nom)
            date = itemView.findViewById(R.id.evenement_liste_item_date)
            location = itemView.findViewById(R.id.evenement_liste_item_location)
            button = itemView.findViewById(R.id.evenement_liste_item_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_evenement_liste_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = listeEvenements[position].imageId
        val nom = listeEvenements[position].nom
        val date = listeEvenements[position].date
        val location = listeEvenements[position].location

        holder.image.setImageResource(image)
        holder.nom.text = nom
        holder.date.text = date.toString()
        holder.location.text = location

        holder.item.setOnClickListener { goToEvent(holder.item) }
    }

    override fun getItemCount(): Int {
        return listeEvenements.size
    }

    fun goToEvent(item: LinearLayout) {
        item.findNavController().navigate(R.id.action_liste_evenements_to_fragment_afficher_evenement)
    }
}