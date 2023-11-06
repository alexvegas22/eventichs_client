package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.presentation.modeles.AfficherEvenementModel
import dti.g55.eventich_client.presentation.vues.fragment_afficher_evenement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PrésentateurAfficherÉvénement(val vueAfficherEvenementFragment : fragment_afficher_evenement) {

    //variables
    private var job: Job? = null
    private var modèle = AfficherEvenementModel()

    //fun
    fun initialiser_fragment(){
        charger_données()
    }

    private fun charger_données() {
        job = CoroutineScope( Dispatchers.IO ).launch {
            //récuperer données
            CoroutineScope( Dispatchers.Main ).launch {
                //affichage
                vueAfficherEvenementFragment.setEvenementInfo( modèle.événement )
            }
        }
    }

    fun verifier_etat_pipeline(){

    }
}