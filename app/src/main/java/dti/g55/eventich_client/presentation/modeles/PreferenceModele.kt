package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Preference

class PreferenceModele {
    val dark = R.style.AppThemeDark
    val light = R.style.AppTheme
    val preferencesUtilisateur = Preference("French", dark)
    fun getTheme(): Int {
        return preferencesUtilisateur.modeUI
    }
    fun toggleTheme() {
        preferencesUtilisateur.modeUI = if (isDark()) dark else light
    }
    fun isDark(): Boolean{
        return preferencesUtilisateur.modeUI == R.style.AppThemeDark
    }
}