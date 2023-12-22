package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.domaine.entite.Organisation

class ListeOrganisationsModele(val source: ISourceDonnee = SourceDeDonneesHTTP("http://v34l.com:8080")) : IModeleListeOrganisations {

    override lateinit var listeOrganisationsInscrites : ArrayList<Organisation>
    override lateinit var listeOrganisations : ArrayList<Organisation>
    var profilModele = ProfilModele()

    override suspend fun retournerOrganisationsPubliques(): ArrayList<Organisation> {
        listeOrganisations =  source.obtenirOrganisationsPubliques()
        return listeOrganisations
    }

    override suspend fun retournerOrganisationsInscrites(): ArrayList<Organisation> {
        listeOrganisationsInscrites = source.obtenirOrganisations()
        return listeOrganisationsInscrites
    }

}