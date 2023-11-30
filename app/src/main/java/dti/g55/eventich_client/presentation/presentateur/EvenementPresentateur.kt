package dti.g55.eventich_client.presentation.presentateur

import android.content.ActivityNotFoundException
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.widget.Toast
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.EvenementVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Calendar


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
            var nbParticipants = modèle.obtenirBbParticipants(evenement)
            CoroutineScope( Dispatchers.Main ).launch {
                //afficher données
                vueAfficherEvenementFragment.changerCouleursTextFinales()
                vueAfficherEvenementFragment.afficher_données(evenement, météo, nbParticipants)
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
        job?.cancel()
        vueAfficherEvenementFragment.retour()
    }

    fun ajouterAuCalendrier()/*:Intent*/ {

        val event = modèle.evenementCourant
        /*
        print(event.dateDebut)
        val startMillis: Long = Calendar.getInstance().run {
            set(2023, 11, 30, 7, 30)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            set(2023, 12, 1, 8, 45)
            timeInMillis
        }

        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            .putExtra(CalendarContract.Events.TITLE, event.nom.toString())
            .putExtra(CalendarContract.Events.DESCRIPTION, event.description.toString())
            .putExtra(CalendarContract.Events.EVENT_LOCATION, event.adresse.toString())
        return intent*/

        var intent = Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
            val beginTime: Calendar = Calendar.getInstance().apply {
                set(2023, 11, 30, 7, 30)
            }
            val endTime = Calendar.getInstance().apply {
                set(2023, 12, 1, 8, 45)
            }
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
            putExtra(CalendarContract.Events.TITLE, "Ninja class")
            putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo")
        }
        try {
            vueAfficherEvenementFragment.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(vueAfficherEvenementFragment.context, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
}