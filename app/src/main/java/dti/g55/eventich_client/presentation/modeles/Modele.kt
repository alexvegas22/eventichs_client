package dti.g55.eventich_client.presentation.modeles

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat

class Modele : ViewModel() {


    private var evenements = MockData.evenements
    private var profilConnecte = MockData.profilUtilisateur
    private var organisations = MockData.listeOrganisations

    @SuppressLint("SimpleDateFormat")
    fun listeEvenementsInscrits(/* code utilisateur*/): List<Evenement>{
        // À FAIRE
        return MockData.evenementsInscrits
    }

    fun getProfil(): ProfilUtilisateur {
        return profilConnecte
    }
    fun isConnected() : Boolean{

        return true
    }
    fun getEvenementSelectionne(idEvenement : Int): Evenement{

        return evenements.filter {
            it.id == idEvenement
        }[0]
    }
    fun retournerListeÉvénements(): MutableList<Evenement> {
        return evenements
    }

    fun filtrerOrganisation() : List<Evenement>? {
        var resultatOrganisation : List<Evenement>? = null

            for(organisation in organisations){
                if (resultatOrganisation == null){
                    resultatOrganisation = evenements.filter {
                        it.organisation == organisation
                    }.toList()
                }
                else {
                    resultatOrganisation += evenements.filter {
                        it.organisation == organisation
                    }.toList()
                }
            }

        return resultatOrganisation

    }
}