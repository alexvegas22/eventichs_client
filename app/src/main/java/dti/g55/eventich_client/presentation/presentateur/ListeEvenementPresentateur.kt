package dti.g55.eventich_client.presentation.presentateur

import android.widget.DatePicker
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.EvenementModele
import dti.g55.eventich_client.presentation.modeles.ListeEvenementModele
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.ListeEvenementVue
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import java.util.Calendar
import java.util.Date

enum class BoutonDateTag {
    DEBUT,
    FIN
}

class ListeEvenementPresentateur(
    val vue: ListeEvenementVue,
    val listeEvenementsModele: ListeEvenementModele = ModeleFactory.listeEvenements,
    val evenementsModele: EvenementModele = ModeleFactory.evenements
): IPresentateur {
    override fun init() {
        vue.disposerVueChargement()
        setDatesInitial()
        setupListeEvenements()
        getListeEvenementsEntreDatesFiltrer(listeEvenementsModele.filtre)
    }

    private fun setupListeEvenements() {
        vue.setupListeEvenements(listeEvenementsModele.listeEvenements)
    }

    private fun getListeEvenementsEntreDatesFiltrer(filtre: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Thread.sleep(1_000) //simulation - À enlever
            listeEvenementsModele.listeEvenements = listeEvenementsModele.getListeEvenementsEntreDates(listeEvenementsModele.dateDebut, listeEvenementsModele.dateFin)

            CoroutineScope(Dispatchers.Main).launch {
                var nouvelleListe = listeEvenementsModele.getListeFiltrer(listeEvenementsModele.listeEvenements, filtre)
                vue.rafraichirListeEvenements(nouvelleListe)
                vue.disposerVueChargementTerminé()
            }
        }
    }

    private fun setDatesInitial() {
        var startDate = toDateStart(Date())
        var calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, 5)
        var endDate = toDateEnd(calendar.time)

        listeEvenementsModele.dateDebut = startDate
        listeEvenementsModele.dateFin = endDate

        val formattedStartDate = formaterDateVersAnneeMoisJour(listeEvenementsModele.dateDebut)
        val formattedEndDate = formaterDateVersAnneeMoisJour(listeEvenementsModele.dateFin)

        vue.changerTexteBoutonDateDebut(formattedStartDate)
        vue.changerTexteBoutonDateFin(formattedEndDate)

        vue.setupDatePickerDialog(listeEvenementsModele.dateDebut, BoutonDateTag.DEBUT)
        vue.setupDatePickerDialog(listeEvenementsModele.dateFin, BoutonDateTag.FIN)
    }

    private fun formaterDateVersAnneeMoisJour(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return String.format("%04d-%02d-%02d", year, month, day)
    }

    fun traiterChangementRecherche(recherche: String) {
        listeEvenementsModele.filtre = recherche
        var listeFiltrer = listeEvenementsModele.getListeFiltrer(listeEvenementsModele.listeEvenements, listeEvenementsModele.filtre)
        vue.rafraichirListeEvenements(listeFiltrer)
    }

    fun traiterOuvertureDatePickerDialog(tag: BoutonDateTag){
        when (tag) {
            BoutonDateTag.DEBUT -> vue.ouvrirSelecteurDateDebut()
            BoutonDateTag.FIN -> vue.ouvrirSelecteurDateFin()
        }
    }

    fun traiterChangementDate(datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendrier = Calendar.getInstance()
        calendrier.set(year, monthOfYear, dayOfMonth)
        val date = calendrier.time
        val formattedDate = formaterDateVersAnneeMoisJour(date)

        when (datePicker.tag) {
            BoutonDateTag.DEBUT -> {
                if (date.after(listeEvenementsModele.dateFin)){
                    vue.afficherErreurDateDebutInvalide()
                    return
                }

                listeEvenementsModele.dateDebut = toDateStart(date)
                vue.changerTexteBoutonDateDebut(formattedDate)
            }
            BoutonDateTag.FIN -> {
                if (date.before(listeEvenementsModele.dateDebut)){
                    vue.afficherErreurDateFinInvalide()
                    return
                }

                listeEvenementsModele.dateFin = toDateEnd(date)
                vue.changerTexteBoutonDateFin(formattedDate)
            }
            else -> return
        }

        getListeEvenementsEntreDatesFiltrer(listeEvenementsModele.filtre)
    }

    fun toDateStart(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.time
    }
    fun toDateEnd(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)

        return calendar.time
    }

    fun traiterClickEvenement(evenement: Evenement){
        evenementsModele.evenementCourant = evenement
        vue.allerVersEvenement()
    }
}