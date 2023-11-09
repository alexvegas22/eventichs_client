package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.presentation.modeles.Modele
import dti.g55.eventich_client.presentation.vues.EvenementVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EvenementPresentateur(val vueAfficherEvenementFragment : EvenementVue) {

    //variables
    private var job: Job? = null
    private var modèle = Modele()

    //fun
    fun initialiser_fragment(){
        vueAfficherEvenementFragment.changerCouleursTextInitiales()
        charger_données()
    }

    private fun charger_données() {
        job = CoroutineScope( Dispatchers.IO ).launch {
            //charger données
            Thread.sleep(2_000) //simulation - À enlever
            val evenement = modèle.getEvenementSelectionne()
            CoroutineScope( Dispatchers.Main ).launch {
                //afficher données
                vueAfficherEvenementFragment.changerCouleursTextFinales()
                vueAfficherEvenementFragment.afficher_données(evenement)
            }
        }
    }

    fun verifier_etat_pipeline(){

    }
}