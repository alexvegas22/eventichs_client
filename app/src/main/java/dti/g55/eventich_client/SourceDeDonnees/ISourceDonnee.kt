package dti.g55.eventich_client.SourceDeDonnees

import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

interface ISourceDonnee {
    fun obtenirListeEvenements(): ArrayList<Evenement>
    fun obtenirOrganisations(): ArrayList<String>
}