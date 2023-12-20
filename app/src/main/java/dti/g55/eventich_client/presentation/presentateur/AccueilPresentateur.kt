package dti.g55.eventich_client.presentation.presentateur

import android.content.SharedPreferences
import android.util.Log
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesException
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.AccueilVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccueilPresentateur(var vue: AccueilVue): IPresentateur {

    private var listeEvenementModele = ModeleFactory.listeEvenements
    private var evenementModele = ModeleFactory.evenements
    private var organisationModele = ModeleFactory.listeOrganisations
    val coroutineContext: CoroutineContext = Dispatchers.IO
    /**
     * Traite le démarrage de la vue Fragment Profil
     */
    override fun init() {
        afficherListeEvenements()
    }

    /**
     * Passe la liste des evenements, la liste de noms d'organisations et les évènements de ces organisations à la vue
     */
    fun afficherListeEvenements() {
        vue.afficherChargement()
        CoroutineScope(Dispatchers.IO).launch {
            val evenementsInscrits = listeEvenementModele.listeEvenementsInscrits()
            val evenementsOrganisations = listeEvenementModele.filtrerOrganisation()
            val nomsOrganisation = organisationModele.retournerOrganisationsInscrites()
            CoroutineScope(Dispatchers.Main).launch {
                vue.masquerChargement()
                vue.setupRecyclerView(evenementsInscrits, nomsOrganisation, evenementsOrganisations)
            }
        }
    }
    fun getEvenementsParOrganisation(organisation : String){
        CoroutineScope(coroutineContext).launch {
            try {
                listeEvenementModele.listeEvenements = listeEvenementModele.getEvenementParOrganisation(organisation)

            }
            catch(e: SourceDeDonneesException) {
                println(e);
            }
        }
    }

    fun traiterClickEvenement(evenement: Evenement){
        evenementModele.evenementCourant = evenement
        vue.allerVersEvenement()
    }
}