package dti.g55.eventich_client.presentation.vues

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.AccueilPresentateur
import dti.g55.eventich_client.utilitaire.CustomRecyclerAdapter


class AccueilVue : Fragment() {

    lateinit var listeEvenements: List<Evenement>
    var listeEvenementsOrganisation: List<Evenement>? = null
    private lateinit var recyclerEvenementsProchains: RecyclerView
    private lateinit var recyclerEvenementsOrganisations: RecyclerView
    private lateinit var nomUtilisateur : TextView
    private lateinit var context: Context
    private var presentateur =  AccueilPresentateur(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        context = container!!.context

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentateur.traiterDemarrage()
        recyclerEvenementsProchains = view.findViewById(R.id.SubscribedEventsRecyclerView)
        recyclerEvenementsOrganisations = view.findViewById(R.id.OrganisationEventsRecyclerView)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        var adapter = CustomRecyclerAdapter(
            listeEvenements,
            R.layout.home_featured_event,
            ::AccueilEvenementViewHolder
        )
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerEvenementsProchains.layoutManager = layoutManager
        recyclerEvenementsProchains.itemAnimator = DefaultItemAnimator()
        recyclerEvenementsProchains.adapter = adapter

        if (listeEvenementsOrganisation != null) {
            var adapterOrganisation = CustomRecyclerAdapter(
                listeEvenementsOrganisation!!,
                R.layout.home_featured_event,
                ::AccueilEvenementViewHolder
            )
            var layoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager2.reverseLayout = true
            layoutManager2.stackFromEnd = true
            recyclerEvenementsOrganisations.layoutManager = layoutManager2
            recyclerEvenementsOrganisations.itemAnimator = DefaultItemAnimator()
            recyclerEvenementsOrganisations.adapter = adapterOrganisation
        }
    }
}