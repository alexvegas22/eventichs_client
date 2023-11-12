package dti.g55.eventich_client.domaine.entite

import java.util.Date

class Evenement(val id: Int, val imageId: Int, val nom: String, val nbParticipant: Int,  val dateDebut: Date, val dateFin : Date, val location: String, val organisation : String,
    val catégorie: String, val description: String)  {
}
