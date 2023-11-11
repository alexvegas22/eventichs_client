package dti.g55.eventich_client.presentation.presentateur

import android.widget.DatePicker
import dti.g55.eventich_client.presentation.modeles.ListeEvenementModele
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.vues.ListeEvenementVue
import java.util.Calendar
import java.util.Date

enum class BoutonDateTag {
    DEBUT,
    FIN
}

class ListeEvenementPresentateur(val vue: ListeEvenementVue): IPresentateur {
    private val modele = ModeleFactory.listeEvenements

    override fun init() {
        setDatesInitial()
        modele.listeEvenements = modele.getListeEvenementsEntreDates(modele.dateDebut, modele.dateFin)
        vue.setupListeEvenements(modele.listeEvenements)
    }

    private fun setDatesInitial() {
        var startDate = toDateStart(Date())
        var calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, 5)
        var endDate = toDateEnd(calendar.time)

        modele.dateDebut = startDate
        modele.dateFin = endDate

        val formattedStartDate = formaterDateVersAnneeMoisJour(modele.dateDebut)
        val formattedEndDate = formaterDateVersAnneeMoisJour(modele.dateFin)

        vue.changerTexteBoutonDateDebut(formattedStartDate)
        vue.changerTexteBoutonDateFin(formattedEndDate)

        vue.setupDatePickerDialog(modele.dateDebut, BoutonDateTag.DEBUT)
        vue.setupDatePickerDialog(modele.dateFin, BoutonDateTag.FIN)
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
        modele.filtre = recherche
        var listeFiltrer = modele.getListeFiltrer()
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
                if (date.after(modele.dateFin)){
                    vue.afficherErreurDateDebutInvalide()
                    return
                }

                modele.dateDebut = toDateStart(date)
                vue.changerTexteBoutonDateDebut(formattedDate)
            }
            BoutonDateTag.FIN -> {
                if (date.before(modele.dateDebut)){
                    vue.afficherErreurDateFinInvalide()
                    return
                }

                modele.dateFin = toDateEnd(date)
                vue.changerTexteBoutonDateFin(formattedDate)
            }
            else -> return
        }

        modele.listeEvenements = modele.getListeEvenementsEntreDates(modele.dateDebut, modele.dateFin)
        var nouvelleListe = modele.getListeFiltrer()
        vue.rafraichirListeEvenements(nouvelleListe)
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
}