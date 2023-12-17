package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface IPresentateurMeteo : IPresentateur{
    fun traiterClickBoutonRetour()

    fun setupListeHeure()

    fun obtenir_Meteo()
}