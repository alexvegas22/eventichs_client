package dti.g55.eventich_client.presentation.modeles

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat

class Modele : ViewModel() {

    private var profilConnecte = MockData.profilUtilisateur

    @SuppressLint("SimpleDateFormat")
    fun listeEvenementsInscrits(/* code utilisateur*/): List<Evenement>{

        // À FAIRE

        return MockData.evenements
    }
    fun getProfil(): ProfilUtilisateur {
        return profilConnecte
    }
    fun isConnected() : Boolean{

        return true
    }
    fun obtenirEvenementCourant(index: Int): Evenement{

        return MockData.evenements[index]
    }

    fun retournerListeÉvénements(): MutableList<Evenement> {
        return MockData.evenements
    }

}