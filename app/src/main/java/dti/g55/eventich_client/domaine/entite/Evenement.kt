package dti.g55.eventich_client.domaine.entite

import java.util.Date

class Evenement(val id: Int, val nom: String, val adresse : String, val dateDebut: Date, val dateFin: Date, val type: String, val categorie: String, val description: String, val image: String, val organisation: String)  {
}
