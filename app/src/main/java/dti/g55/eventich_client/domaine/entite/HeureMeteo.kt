package dti.g55.eventich_client.domaine.entite

import androidx.annotation.DrawableRes
import java.util.Date

class HeureMeteo (val heure: Int, @DrawableRes val image: Int, val description: String, val température: Int, val humidité: Int) {
}