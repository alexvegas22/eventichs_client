package dti.g55.eventich_client.domaine.entite

import java.util.Date

class Evenement(val imageId: Int, val nomComplet: String, val nomAbbrevié: String, val nbParticipant: Int,  val date: Date, val location: String, val organisation : String,
    val catégorie: String, val organisateur: String, val description: String, val meteo: ConditionMeterologique)  {
}
