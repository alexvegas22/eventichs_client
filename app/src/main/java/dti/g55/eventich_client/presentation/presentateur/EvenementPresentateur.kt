package dti.g55.eventich_client.presentation.presentateur

import android.content.Intent
import android.provider.CalendarContract
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.EvenementVue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
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

    fun ajouterAuCalendrier() {

        // À modifier

        val event = modèle.evenementCourant

        val formatter = SimpleDateFormat("dd/MM/yyyy")

        val startMillis: Long = Calendar.getInstance().run {
            set(2023, 12, 2, 7, 30)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            set(2023, 12, 2, 12, 45)
            timeInMillis
        }

            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.CALENDAR_ID, 3)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.dateDebut.time)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.dateFin.time)
                .putExtra(CalendarContract.Events.TITLE, event.nom)
                .putExtra(CalendarContract.Events.DESCRIPTION, event.description)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, event.adresse)
                //.putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")

            vueAfficherEvenementFragment.utiliserCalendrier(intent)
    }
}