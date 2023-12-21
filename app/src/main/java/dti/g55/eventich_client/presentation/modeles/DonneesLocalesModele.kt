package dti.g55.eventich_client.presentation.modeles

import android.content.Context
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesSQL
import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

class DonneesLocalesModele(var context : Context){
    val source = SourceDeDonneesSQL(context)


    var profilModele = ModeleFactory.profil

}