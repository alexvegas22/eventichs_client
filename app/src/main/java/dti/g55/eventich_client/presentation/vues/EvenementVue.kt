package dti.g55.eventich_client.presentation.vues

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.squareup.picasso.Picasso
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.EvenementPresentateur

class EvenementVue : Fragment() {
    lateinit var meteoLayout: LinearLayout
    lateinit var backButtonImage: ImageView
    lateinit var imageEvenement : ImageView
    lateinit var animationChargement : LottieAnimationView
    lateinit var nomEvenement : TextView
    lateinit var nbparticipant: TextView
    lateinit var calendrier: ImageView
    lateinit var dateDebut: TextView
    lateinit var dateFin: TextView
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
    lateinit var btnRejoindre: Button
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

        //assignation
        backButtonImage = view.findViewById(R.id.imgBoutonArrière)
        nomEvenement = view.findViewById(R.id.txtNomCompletÉvénement)
        nbparticipant = view.findViewById(R.id.txtNbParticipants)
        imageEvenement = view.findViewById(R.id.imgÉvénement)
        animationChargement = view.findViewById(R.id.lottieChargement)
        calendrier = view.findViewById(R.id.imgIconeCalendrier)
        dateFin = view.findViewById(R.id.txtDateCourte)
        dateDebut = view.findViewById(R.id.txtDateLongue)
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
        btnRejoindre = view.findViewById(R.id.btnRejoindre)

        //setlisteners
        meteoLayout.setOnClickListener { voirMeteo() }
        backButtonImage.setOnClickListener { presentateur.traiterRetour() }
        calendrier.setOnClickListener { cliquerCalendrier() }
        btnRejoindre.setOnClickListener { presentateur.traiterRejoindre() }

        //initialisation
        presentateur.init()
    }

    fun voirMeteo(){
        findNavController().navigate(R.id.action_fragment_afficher_evenement_to_meteo2)
    }

    fun afficherAnimationChargement(){
        animationChargement.visibility = View.VISIBLE
        imageEvenement.visibility = View.INVISIBLE
    }

    private fun masquerAnimationChargement() {
        animationChargement.visibility = View.INVISIBLE
        imageEvenement.visibility = View.VISIBLE
    }

    fun changerCouleursTextInitiales(){
        nomEvenement.setTextColor(Color.parseColor("#E91E63"))
        nbparticipant.setTextColor(Color.parseColor("#E91E63"))
        dateDebut.setTextColor(Color.parseColor("#E91E63"))
        dateFin.setTextColor(Color.parseColor("#E91E63"))
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
        dateDebut.setTextColor(Color.parseColor("#FF333333"))
        dateFin.setTextColor(Color.parseColor("#FF333333"))
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

    fun afficher_données(evenement: Evenement, conditionMeterologique: ConditionMeterologique, nbParticipants : String){
        masquerAnimationChargement()

        val imageParam = evenement.image
        Log.e("image link",imageParam)
        imageEvenement.setImageResource(R.drawable.whiteworth_chalet_front)
       try {
            // Use Picasso to load the image from the URL
            Picasso.get().load(evenement.image).into(imageEvenement)
        } catch (e : Exception) {

        }

        nomEvenement.setText(evenement.nom)
        nbparticipant.setText(nbParticipants)
        dateDebut.setText(evenement.dateDebut.toString())
        dateFin.setText(evenement.dateFin.toString())
        temperature.text = conditionMeterologique.températureMoyenne.toString()
        humidite.text = conditionMeterologique.pourcentageHumidité.toString()
        meteo.text = conditionMeterologique.meteoDescription
        categorie.setText(evenement.description)
        adresseEvenement.setText(evenement.adresse)
        organisationEvenement.setText(evenement.organisation)
        description.setText(evenement.categorie)
    }

    fun cliquerCalendrier(){
        presentateur.ajouterAuCalendrier()
    }
    fun utiliserCalendrier(intent: Intent){
        startActivity(intent)
    }

    fun afficherParticipation(){
        btnRejoindre.text = "J'y vais!"
        btnRejoindre.setBackgroundColor(resources.getColor(androidx.constraintlayout.widget.R.color.material_grey_300))
    }

    fun afficherErreur(){
        Toast.makeText(context, "Un problème est survenu, veuillez réessayer.", Toast.LENGTH_SHORT).show()
    }
}