package dti.g55.eventich_client.domaine.entite

class ConditionMeterologique(val meteoDescription: String, val tempMoyenne: Double, val tempJour: Double, val tempNuit: Double, val tempSoir: Double, val tempMatin: Double, val tempMax: Double, val tempMin: Double,
                             val tempRessentieJour: Double, val tempRessentieNuit: Double, val tempRessentieSoir: Double, val tempRessentieMatin: Double, val pourcentageHumidité: Int ) {
}