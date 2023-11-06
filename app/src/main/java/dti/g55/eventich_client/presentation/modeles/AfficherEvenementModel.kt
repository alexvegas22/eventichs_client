package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

val testEvenement = Evenement(R.drawable.ic_search, "Événement", Date(), "123 rue Chemin, Montréal, QC", "Rosemont")
class AfficherEvenementModel(var événement: Evenement = testEvenement) {

}