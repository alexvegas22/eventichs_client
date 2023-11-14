package dti.g55.eventich_client.domaine.entite

class ConditionMeterologique( val meteoDescription: String, val températureMoyenne: Int, val pourcentageHumidité: Int, val listeHeures: ArrayList<HeureMeteo> ) {
}