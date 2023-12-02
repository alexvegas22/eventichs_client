package dti.g55.eventich_client.presentation.presentateur

import android.annotation.SuppressLint
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

    @SuppressLint("UseRequireInsteadOfGet")
    fun ajouterAuCalendrier()/*:Intent*/ {

        val event = modèle.evenementCourant

        val startMillis: Long = Calendar.getInstance().run {
            set(2023, 12, 2, 7, 30)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            set(2023, 12, 2, 12, 45)
            timeInMillis
        }

        job = CoroutineScope( Dispatchers.IO ).launch {
            //charger données
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.CALENDAR_ID, 3)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
                .putExtra(CalendarContract.Events.TITLE, "Yoga")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")
            CoroutineScope( Dispatchers.Main ).launch {
                //afficher données
                vueAfficherEvenementFragment.startActivity(intent)
            }
        }

        /*
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            .putExtra(CalendarContract.Events.TITLE, "Yoga")
            .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
            .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
            .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")
        vueAfficherEvenementFragment.startActivity(intent)
         */

        /*
        val calendarId: Long = 3
        val startMillis: Long = Calendar.getInstance().run {
            set(2023, 12, 2, 7, 30)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            set(2023, 12, 2, 12, 45)
            timeInMillis
        }

        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, startMillis)
            put(CalendarContract.Events.DTEND, endMillis)
            put(CalendarContract.Events.TITLE, "Jazzercise")
            put(CalendarContract.Events.DESCRIPTION, "Group workout")
            put(CalendarContract.Events.CALENDAR_ID, calendarId)
            put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles")
        }
        // Find out what to do
        val uri: Uri? = vueAfficherEvenementFragment.context?.contentResolver?.insert(CalendarContract.Events.CONTENT_URI, values)
        */
    }
}