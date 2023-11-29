package dti.g55.eventich_client.presentation.vues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.presentateur.MeteoPresentateur
import dti.g55.eventich_client.utilitaire.CustomRecyclerAdapter


class MeteoVue : Fragment() {
    lateinit var btnRetourImage: ImageView
    lateinit var rvMétéo: RecyclerView
    lateinit var tvDate: TextView
    lateinit var tvTemp: TextView
    private val presentateur = MeteoPresentateur(this)
    private val modeleEvenement = ModeleFactory.evenements
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meteo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMétéo = view.findViewById(R.id.rvMétéo)

        btnRetourImage = view.findViewById(R.id.btnRetour)

        btnRetourImage.setOnClickListener { presentateur.traiterClickBoutonRetour() }

        tvDate = view.findViewById(R.id.tvDate)
        tvTemp = view.findViewById(R.id.tvTemp)

        presentateur.init()
    }


    fun setupListeHeure(condition: ConditionMeterologique){
        var adapter = CustomRecyclerAdapter(condition.listeHeures, R.layout.fragment_condition_meteo, ::ListeMeteoViewHolder, presentateur)
        var layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        tvDate.text = modeleEvenement.evenementCourant.dateDebut.toString()
        tvTemp.text = condition.températureMoyenne.toString()
        rvMétéo.layoutManager = layoutManager
        rvMétéo.itemAnimator = DefaultItemAnimator()
        rvMétéo.adapter = adapter
    }

    fun rafraichirListeHeure(condition: ConditionMeterologique){
        rvMétéo.adapter = CustomRecyclerAdapter(condition.listeHeures, R.layout.fragment_condition_meteo, ::ListeMeteoViewHolder, presentateur)
        tvDate.text = modeleEvenement.evenementCourant.dateDebut.toString()
        tvTemp.text = condition.températureMoyenne.toString()
    }

    fun retour() {
        findNavController().navigate(R.id.action_meteo2_to_fragment_afficher_evenement)
    }

}