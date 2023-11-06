package dti.g55.eventich_client.presentation.vues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.EvenementPresentateur

class fragment_afficher_evenement : Fragment() {
    lateinit var meteoLayout: LinearLayout
    lateinit var backButtonImage: ImageView
    lateinit var nomEvenement : TextView
    lateinit var organisationEvenement : TextView
    lateinit var adresseEvenement: TextView
    lateinit var dateLongue: TextView
    lateinit var dateCourte: TextView
    lateinit var imageEvenement : ImageView
    private var presentateur : EvenementPresentateur = EvenementPresentateur(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_afficher_evenement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meteoLayout = view.findViewById(R.id.lytConteneurInfosMeteo)
        backButtonImage = view.findViewById(R.id.imgBoutonArrière)
        nomEvenement = view.findViewById(R.id.txtNomCompletÉvénement)
        organisationEvenement = view.findViewById(R.id.txtOrganisateur)
        adresseEvenement = view.findViewById(R.id.txtAdresse)
        dateCourte = view.findViewById(R.id.txtDateCourte)
        dateLongue = view.findViewById(R.id.txtDateLongue)
        imageEvenement = view.findViewById(R.id.imgÉvénement)
        meteoLayout.setOnClickListener { voirMeteo() }
        backButtonImage.setOnClickListener { retour() }
        presentateur.traiterDemarrage()
    }

    fun voirMeteo(){
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_meteo2)
    }

    fun retour() {
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_liste_evenements)
    }

    fun setEvenementInfo(evenement: Evenement){
        nomEvenement.setText(evenement.nom)
        organisationEvenement.setText(evenement.organisation)
        adresseEvenement.setText(evenement.location)
        dateCourte.setText(evenement.date.toString())
        dateLongue.setText(evenement.date.toString())

    }

}