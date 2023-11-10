package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import java.util.Date

class MeteoModele {
    lateinit var listeHeures: ArrayList<HeureMeteo>

    fun getListeMétéoParHeure(): ArrayList<HeureMeteo> {
        val heures = MockData.listeHeuresMétéo

        return ArrayList(heures)
    }
}