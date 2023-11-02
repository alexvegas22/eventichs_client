package dti.g55.eventich_client.presentation.modeles

import androidx.lifecycle.ViewModel
import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur

class ProfilViewModel : ViewModel() {

    private var profilConnecte = MockData.profilUtilisateur

    fun getProfil(): ProfilUtilisateur {
        return profilConnecte
    }


    fun isConnected() : Boolean{

        return true
    }
}