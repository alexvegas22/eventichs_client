package dti.g55.eventich_client.presentation.presentateur

import android.content.ContentResolver
import android.content.ContentValues
import android.provider.CalendarContract.Events.*
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.EvenementVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class EvenementPresentateur(val vueAfficherEvenementFragment : EvenementVue) : IPresentateur{

    private var job: Job? = null
    private var modèle = ModeleFactory.evenements
    private var modèleMétéo = ModeleFactory.meteo

    fun charger_données() {
        job = CoroutineScope( Dispatchers.IO ).launch {
            //charger données
            Thread.sleep(1_000) //simulation - À enlever
            val evenement = modèle.evenementCourant
            val météo = modèleMétéo.obtenirMétéo()
            CoroutineScope( Dispatchers.Main ).launch {
                //afficher données
                vueAfficherEvenementFragment.changerCouleursTextFinales()
                vueAfficherEvenementFragment.afficher_données(evenement, météo)
            }
        }
    }

    fun verifier_etat_pipeline(): Boolean{
        return job!=null
    }

    override fun init() {
        vueAfficherEvenementFragment.changerCouleursTextInitiales()
        vueAfficherEvenementFragment.afficherAnimationChargement()
        charger_données()
    }

    fun traiterRetour(){
        vueAfficherEvenementFragment.retour()
    }

    fun ajouterAuCalendrier(){

        // Do something that works

    }
}