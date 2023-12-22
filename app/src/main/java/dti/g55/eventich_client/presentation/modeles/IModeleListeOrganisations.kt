package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.domaine.entite.Organisation

interface IModeleListeOrganisations {
    var listeOrganisations : ArrayList<Organisation>
    var listeOrganisationsInscrites : ArrayList<Organisation>
    suspend fun retournerOrganisationsPubliques(): ArrayList<Organisation>
    suspend fun retournerOrganisationsInscrites(): ArrayList<Organisation>
}