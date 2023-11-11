package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur

class ProfilModele {
    private var profil = MockData.profilUtilisateur

    fun getProfil(): ProfilUtilisateur {
        return profil
    }
    fun isConnected() : Boolean{
        return true
    }
}