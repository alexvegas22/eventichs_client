package dti.g55.eventich_client.presentation.presentateur

import android.content.SharedPreferences
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.AccueilVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccueilPresentateur(var vue: AccueilVue): IPresentateur {

    private var listeEvenementModele = ModeleFactory.listeEvenements
    private var evenementModele = ModeleFactory.evenements

    /**
     * Traite le démarrage de la vue Fragment Profil
     */
    override fun init() {
        afficherListeEvenements()
    }

    /**
     * Passe la liste des evenements à la vue
     */
    fun afficherListeEvenements() {
        CoroutineScope(Dispatchers.IO).launch {
            val evenementsInscrits = listeEvenementModele.listeEvenementsInscrits()
            val evenementsOrganisations = listeEvenementModele.filtrerOrganisation()

            CoroutineScope(Dispatchers.Main).launch {
                vue.setupRecyclerView(evenementsInscrits, evenementsOrganisations)
            }
        }
    }

    fun traiterClickEvenement(evenement: Evenement){
        evenementModele.evenementCourant = evenement
        vue.allerVersEvenement()
    }
}