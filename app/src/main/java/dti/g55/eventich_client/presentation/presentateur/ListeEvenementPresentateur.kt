package dti.g55.eventich_client.presentation.presentateur

import android.widget.DatePicker
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesException
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.EvenementModele
import dti.g55.eventich_client.presentation.modeles.IModeleEvenement
import dti.g55.eventich_client.presentation.modeles.IModeleListeEvenements
import dti.g55.eventich_client.presentation.modeles.ListeEvenementModele
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.IVueListeEvenement
import dti.g55.eventich_client.presentation.vues.ListeEvenementVue
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import java.util.Calendar
import java.util.Date
import kotlin.coroutines.CoroutineContext

enum class BoutonDateTag {
    DEBUT,
    FIN
}

class ListeEvenementPresentateur(
    val vue: IVueListeEvenement,
    val listeEvenementsModele: IModeleListeEvenements = ModeleFactory.listeEvenements,
    val evenementsModele: IModeleEvenement = ModeleFactory.evenements,
    val coroutineContext: CoroutineContext = Dispatchers.IO
): IPresentateurListeEvenement {
    override fun init() {
        vue.disposerVueChargement()
        setDatesInitial()
        setupListeEvenements()
        getListeEvenementsEntreDatesFiltrer(listeEvenementsModele.filtre)
    }

    override fun setupListeEvenements() {
        vue.setupListeEvenements(listeEvenementsModele.listeEvenements)
    }

    override fun getListeEvenementsEntreDatesFiltrer(filtre: String) {
        CoroutineScope(coroutineContext).launch {
            try {
                listeEvenementsModele.listeEvenements = listeEvenementsModele.getListeEvenementsEntreDates(listeEvenementsModele.dateDebut, listeEvenementsModele.dateFin)
                CoroutineScope(Dispatchers.Main).launch {
                    var nouvelleListe = listeEvenementsModele.getListeFiltrer(listeEvenementsModele.listeEvenements, filtre)
                    vue.rafraichirListeEvenements(nouvelleListe)
                    vue.disposerVueChargementTerminé()
                }
            }
            catch(e: SourceDeDonneesException) {
                println(e);
            }
        }
    }

    override fun setDatesInitial() {
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

    override fun formaterDateVersAnneeMoisJour(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return String.format("%04d-%02d-%02d", year, month, day)
    }

    override fun traiterChangementRecherche(recherche: String) {
        listeEvenementsModele.filtre = recherche
        var listeFiltrer = listeEvenementsModele.getListeFiltrer(listeEvenementsModele.listeEvenements, listeEvenementsModele.filtre)
        vue.rafraichirListeEvenements(listeFiltrer)
    }

    override fun traiterOuvertureDatePickerDialog(tag: BoutonDateTag){
        when (tag) {
            BoutonDateTag.DEBUT -> vue.ouvrirSelecteurDateDebut()
            BoutonDateTag.FIN -> vue.ouvrirSelecteurDateFin()
        }
    }

    override fun traiterChangementDate(datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
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

    override fun toDateStart(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.time
    }
    override fun toDateEnd(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)

        return calendar.time
    }

    override fun traiterClickEvenement(evenement: Evenement){
        evenementsModele.evenementCourant = evenement
        vue.allerVersEvenement()
    }
}