package dti.g55.eventich_client.presentation.modeles

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat

class Modele : ViewModel() {

    private var evenements = MockData.evenements
    private var evenementSelectionne = MockData.testÉvénement1
    private var profilConnecte = MockData.profilUtilisateur

    @SuppressLint("SimpleDateFormat")
    fun listeEvenementsInscrits(/* code utilisateur*/): List<Evenement>{

        // À FAIRE

        return evenements
    }
    fun getProfil(): ProfilUtilisateur {
        return profilConnecte
    }
    fun isConnected() : Boolean{

        return true
    }
    fun getEvenementSelectionne(): Evenement{

        return evenementSelectionne
    }
    fun setEvenementSelectionnne(event : Evenement){
        evenementSelectionne = event

    }

    fun retournerListeÉvénements(): MutableList<Evenement> {
        return evenements
    }

}