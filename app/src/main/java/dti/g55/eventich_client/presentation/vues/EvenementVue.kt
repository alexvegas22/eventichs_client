package dti.g55.eventich_client.presentation.vues

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.EvenementPresentateur

class EvenementVue : Fragment() {
    lateinit var meteoLayout: LinearLayout
    lateinit var backButtonImage: ImageView
    lateinit var imageEvenement : ImageView
    lateinit var nomEvenement : TextView
    lateinit var nbparticipant: TextView
    lateinit var dateLongue: TextView
    lateinit var dateCourte: TextView
    lateinit var temperature: TextView
    lateinit var humidite: TextView
    lateinit var meteo: TextView
    lateinit var categorie: TextView
    lateinit var adresseEvenement: TextView
    lateinit var organisationEvenement : TextView
    lateinit var description: TextView
    lateinit var afficheur_temperature: TextView
    lateinit var afficheur_humidite: TextView
    lateinit var afficheur_meteo: TextView
    lateinit var afficheur_categorie: TextView
    lateinit var afficheur_adresseEvenement: TextView
    lateinit var afficheur_organisationEvenement : TextView
    lateinit var afficheur_description: TextView
    private var presentateur = EvenementPresentateur(this)
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

        val args: EvenementVueArgs by navArgs()
        val evenementId = args.evenementSelectionne
        Toast.makeText(context, "Evenement: $evenementId", Toast.LENGTH_LONG).show()

        //assignation
        backButtonImage = view.findViewById(R.id.imgBoutonArrière)
        nomEvenement = view.findViewById(R.id.txtNomCompletÉvénement)
        nbparticipant = view.findViewById(R.id.txtNbParticipants)
        imageEvenement = view.findViewById(R.id.imgÉvénement)
        dateCourte = view.findViewById(R.id.txtDateCourte)
        dateLongue = view.findViewById(R.id.txtDateLongue)
        meteoLayout = view.findViewById(R.id.lytConteneurInfosMeteo)
        afficheur_temperature = view.findViewById(R.id.txtaffichageT)
        afficheur_humidite = view.findViewById(R.id.txtaffichageH)
        afficheur_meteo = view.findViewById(R.id.txtaffichageM)
        temperature = view.findViewById(R.id.txtTempérature)
        humidite = view.findViewById(R.id.txtHumidité)
        meteo = view.findViewById(R.id.txtMeteo)
        afficheur_categorie = view.findViewById(R.id.txtAffichageCatégorie)
        categorie = view.findViewById(R.id.txtCatégorie)
        afficheur_adresseEvenement = view.findViewById(R.id.txtAffichageAdresse)
        adresseEvenement = view.findViewById(R.id.txtAdresse)
        afficheur_organisationEvenement = view.findViewById(R.id.txtAffichageOrganisateur)
        organisationEvenement = view.findViewById(R.id.txtOrganisateur)
        afficheur_description = view.findViewById(R.id.txtAffichageDescription)
        description = view.findViewById(R.id.txtDescription)

        //setlisteners
        meteoLayout.setOnClickListener { voirMeteo() }
        backButtonImage.setOnClickListener { retour() }

        //initialisation
        presentateur.initialiser_fragment()
    }

    fun voirMeteo(){
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_meteo2)
    }

    fun changerCouleursTextInitiales(){
        nomEvenement.setTextColor(Color.parseColor("#E91E63"))
        nbparticipant.setTextColor(Color.parseColor("#E91E63"))
        dateCourte.setTextColor(Color.parseColor("#E91E63"))
        dateLongue.setTextColor(Color.parseColor("#E91E63"))
        afficheur_temperature.setTextColor(Color.parseColor("#FF333333"))
        afficheur_humidite.setTextColor(Color.parseColor("#FF333333"))
        afficheur_meteo.setTextColor(Color.parseColor("#FF333333"))
        temperature.setTextColor(Color.parseColor("#E91E63"))
        humidite.setTextColor(Color.parseColor("#E91E63"))
        meteo.setTextColor(Color.parseColor("#E91E63"))
        afficheur_categorie.setTextColor(Color.parseColor("#FF333333"))
        categorie.setTextColor(Color.parseColor("#E91E63"))
        afficheur_adresseEvenement.setTextColor(Color.parseColor("#FF333333"))
        adresseEvenement.setTextColor(Color.parseColor("#E91E63"))
        afficheur_organisationEvenement.setTextColor(Color.parseColor("#FF333333"))
        organisationEvenement.setTextColor(Color.parseColor("#E91E63"))
        afficheur_description.setTextColor(Color.parseColor("#FF333333"))
        description.setTextColor(Color.parseColor("#E91E63"))
    }

    fun changerCouleursTextFinales(){
        nomEvenement.setTextColor(Color.parseColor("#FF333333"))
        nbparticipant.setTextColor(Color.parseColor("#FF333333"))
        dateCourte.setTextColor(Color.parseColor("#FF333333"))
        dateLongue.setTextColor(Color.parseColor("#FF333333"))
        afficheur_temperature.setTextColor(Color.parseColor("#FF807F7F"))
        afficheur_humidite.setTextColor(Color.parseColor("#FF807F7F"))
        afficheur_meteo.setTextColor(Color.parseColor("#FF807F7F"))
        temperature.setTextColor(Color.parseColor("#FF333333"))
        humidite.setTextColor(Color.parseColor("#FF333333"))
        meteo.setTextColor(Color.parseColor("#FF333333"))
        afficheur_categorie.setTextColor(Color.parseColor("#FF807F7F"))
        categorie.setTextColor(Color.parseColor("#FF333333"))
        afficheur_adresseEvenement.setTextColor(Color.parseColor("#FF807F7F"))
        adresseEvenement.setTextColor(Color.parseColor("#FF333333"))
        afficheur_organisationEvenement.setTextColor(Color.parseColor("#FF807F7F"))
        organisationEvenement.setTextColor(Color.parseColor("#FF333333"))
        afficheur_description.setTextColor(Color.parseColor("#FF807F7F"))
        description.setTextColor(Color.parseColor("#FF333333"))
    }

    fun retour() {
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_liste_evenements)
    }

    fun afficher_données(evenement: Evenement){
        nomEvenement.setText(evenement.nomComplet)
        nbparticipant.setText(evenement.nbParticipant.toString())
        dateCourte.setText(evenement.date.toString())
        dateLongue.setText(evenement.date.toString())
        temperature.setText("25") // À changer
        humidite.setText("13") // À changer
        meteo.setText("Ciel dégagé") // À changer
        categorie.setText(evenement.catégorie)
        adresseEvenement.setText(evenement.location)
        organisationEvenement.setText(evenement.organisation)
        description.setText(evenement.description)
    }

}