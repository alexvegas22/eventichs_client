package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur

class ProfilModele {
    private lateinit var profil : ProfilUtilisateur

    fun getProfil(): ProfilUtilisateur {
        return profil
    }
    fun setProfil(profilUtilisateur: ProfilUtilisateur){
        profil = profilUtilisateur
    }

    fun isConnected() : Boolean{
        return profil != null
    }
}