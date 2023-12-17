package dti.g55.eventich_client.presentation.vues

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.presentateur.BoutonDateTag
import java.util.Date

interface IVueListeEvenement {
    fun setupListeEvenements(evenements: ArrayList<Evenement>)
    fun setupDatePickerDialog(date: Date, tag: BoutonDateTag)
    fun rafraichirListeEvenements(evenements: ArrayList<Evenement>)
    fun ouvrirSelecteurDateDebut();
    fun ouvrirSelecteurDateFin();
    fun changerTexteBoutonDateDebut(texte: String)
    fun changerTexteBoutonDateFin(texte: String)
    fun afficherErreurDateDebutInvalide()
    fun afficherErreurDateFinInvalide()
    fun allerVersEvenement()
}