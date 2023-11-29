package dti.g55.eventich_client.SourceDeDonnees

import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

interface ISourceDonnee {
    suspend fun obtenirListeEvenements(): ArrayList<Evenement>
    suspend fun obtenirOrganisations(): ArrayList<String>
}