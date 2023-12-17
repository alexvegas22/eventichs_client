package dti.g55.eventich_client.presentation.vues

import dti.g55.eventich_client.domaine.entite.ConditionMeterologique

interface IVueMeteo {
    fun rafraichirListeHeure(condition: ConditionMeterologique)
    fun setupListeHeure(condition: ConditionMeterologique)
    fun retour()
}