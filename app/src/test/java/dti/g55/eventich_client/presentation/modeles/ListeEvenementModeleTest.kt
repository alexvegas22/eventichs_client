package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import dti.g55.eventich_client.R
import java.util.Date

class ListeEvenementModeleTest {
    @Test
    fun `Étant donné un modèle liste événement nouvellement initialisé, lorsqu'on retourne la liste d'événements, on obtient la liste d'événements de la source de donnée`() {
        val source = Mockito.mock(MockData::class.java)
        val résultat_attendu = arrayListOf(Evenement(
            1,
            R.drawable.ic_search,
            "Evenement",
            2, Date(),
            "Location",
            "Organ",
            "Cat",
            "Desc"))
        Mockito.`when`(source.obtenirListeEvenements()).thenReturn(résultat_attendu)
        val modèle = ListeEvenementModele(source)

        val résultat_obtenu = modèle.retournerListeÉvénements()

        assertEquals(résultat_attendu, résultat_obtenu)
    }

    @Test
    fun `Étant donné un ListeEvenementModele nouvellement instantié, lorsqu'on appelle la fonction getListFiltrer avec une liste d'événements et le filtre 'even', alors on obtient une liste avec seulement les événements contenant 'even' dans le nom ou la location`() {
        val evenement1 = Evenement(1, R.drawable.ic_search, "Evenement", 2, Date(),
            "Location", "Organ", "Cat", "Desc")
        val evenement2 = Evenement(1, R.drawable.ic_search, "Test", 2, Date(),
            "Location evEn", "Organ", "Cat", "Desc d'evenement")
        val evenement3 = Evenement(1, R.drawable.ic_search, "Aucun match", 2, Date(),
            "Location", "Organ", "Cat", "Aucune description")

        val listeEvenements = arrayListOf(evenement1, evenement2, evenement3)
        val modèle = ListeEvenementModele()

        val résultat_attendu = arrayListOf(evenement1, evenement2)
        val résultat_obtenu = modèle.getListeFiltrer(listeEvenements, "even")

        assertEquals(résultat_attendu, résultat_obtenu)
    }
}