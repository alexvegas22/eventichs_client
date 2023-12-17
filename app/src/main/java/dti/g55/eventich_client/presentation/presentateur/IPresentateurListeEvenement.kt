package dti.g55.eventich_client.presentation.presentateur

import android.widget.DatePicker
import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

interface IPresentateurListeEvenement : IPresentateur {
    fun setupListeEvenements();
    fun getListeEvenementsEntreDatesFiltrer(filtre: String);
    fun setDatesInitial();
    fun formaterDateVersAnneeMoisJour(date: Date): String;
    fun traiterChangementRecherche(recherche: String);
    fun traiterOuvertureDatePickerDialog(tag: BoutonDateTag);
    fun traiterChangementDate(datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int);
    fun toDateStart(date: Date): Date;
    fun toDateEnd(date: Date): Date;
    fun traiterClickEvenement(evenement: Evenement);
}