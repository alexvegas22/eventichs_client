package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.presentation.modeles.ModelDonnéesFactices
import dti.g55.eventich_client.presentation.vues.AfficherEvenementFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PrésentateurAfficherÉvénement(val vueAfficherEvenementFragment : AfficherEvenementFragment) {

    //variables
    private var job: Job? = null
    private var modèle = ModelDonnéesFactices()

    //fun
    fun initialiser_fragment(){
        charger_données()
    }

    private fun charger_données() {
        job = CoroutineScope( Dispatchers.IO ).launch {
            //récuperer données
            CoroutineScope( Dispatchers.Main ).launch {
                //affichage
                vueAfficherEvenementFragment.afficher_données( modèle.retournerUnÉvénement() )
            }
        }
    }

    fun verifier_etat_pipeline(){

    }
}