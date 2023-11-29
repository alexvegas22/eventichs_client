package dti.g55.eventich_client.domaine.entite

import androidx.annotation.DrawableRes

class HeureMeteo (val heure: Int, @DrawableRes val image: Int, val description: String, val température: Double, val humidité: Int) {
}