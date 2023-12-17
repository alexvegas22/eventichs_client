package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.domaine.entite.Evenement
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import java.util.Date

class ListeEvenementModeleTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread");

    @Before
    fun setUp(){
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `Étant donné un modèle liste événement nouvellement initialisé, lorsqu'on retourne la liste d'événements, on obtient une liste d'événements vide`() {
        val modèle = ListeEvenementModele()

        val résultat_obtenu = modèle.listeEvenements

        assertEquals(arrayListOf<Evenement>(), résultat_obtenu)
    }

    @Test
    fun `Étant donné un modèle liste événement nouvellement initialisé, lorsqu'on retourne la date de début, on obtient la date d'aujourd'hui`() {
        val modèle = ListeEvenementModele()

        val résultat_obtenu = modèle.dateDebut

        assertEquals(Date(), résultat_obtenu)
    }

    @Test
    fun `Étant donné un modèle liste événement nouvellement initialisé, lorsqu'on retourne le filtre, on obtient une chaine vide`() {
        val modèle = ListeEvenementModele()

        val résultat_obtenu = modèle.filtre

        assertEquals("", résultat_obtenu)
    }

    @Test
    fun `Étant donné un modèle liste événement nouvellement initialisé, lorsqu'on appelle la fonction retournerListeÉvénements, on obtient la liste d'événements de la source de donnée`() = runTest {
        val source = Mockito.mock(ISourceDonnee::class.java)
        val listeEvenements = arrayListOf(Evenement(
            1,
            "Evenement",
            "123 rue Du Chemin",
            Date(),
            Date(),
            "public",
            "party",
            "Description du party",
            "lien_image",
            "Rosemont"))

        Mockito.`when`(source.obtenirListeEvenements()).thenReturn(listeEvenements)

        val modèle = ListeEvenementModele(source)

        val résultat_obtenu = modèle.retournerListeÉvénements()

        assertEquals(listeEvenements, résultat_obtenu)
    }

    @Test
    fun `Étant donné un ListeEvenementModele nouvellement instantié, lorsqu'on appelle la fonction getListFiltrer avec une liste d'événements et le filtre 'even', alors on obtient une liste avec seulement les événements contenant 'even' dans le nom ou l'adresse`() {
        val evenement1 = Evenement(
            1,
            "Titre",
            "123 rue Du Chemin",
            Date(),
            Date(),
            "public",
            "party",
            "Description du party",
            "lien_image",
            "Rosemont")
        val evenement2 = Evenement(
            1,
            "Evenement #1",
            "123 rue Du Chemin",
            Date(),
            Date(),
            "public",
            "party",
            "Description du party",
            "lien_image",
            "Rosemont")
        val evenement3 = Evenement(
            1,
            "Party!!",
            "123 rue De l'evenement",
            Date(),
            Date(),
            "public",
            "party",
            "Description du party",
            "lien_image",
            "Rosemont")

        val listeEvenements = arrayListOf(evenement1, evenement2, evenement3)
        val modèle = ListeEvenementModele()

        val résultat_attendu = arrayListOf(evenement2, evenement3)
        val résultat_obtenu = modèle.getListeFiltrer(listeEvenements, "even")

        assertEquals(résultat_attendu, résultat_obtenu)
    }

    @Test
    fun `Étant donné un ListeEvenementModele nouvellement instantié, lorsqu'on appelle la fonction getListeFiltrer avec une liste d'événements et le filtre 'EVeN', alors on obtient une liste avec les événements contenant 'even' dans le nom ou l'adresse peut importe la capitalization`() {
        val evenement1 = Evenement(
            1,
            "evENement 1",
            "123 rue Du Chemin",
            Date(),
            Date(),
            "public",
            "party",
            "Description du party",
            "lien_image",
            "Rosemont")
        val evenement2 = Evenement(
            1,
            "Party 1",
            "L'adresse de l'eVeNement",
            Date(),
            Date(),
            "public",
            "party",
            "Description du party",
            "lien_image",
            "Rosemont")

        val listeEvenements = arrayListOf(evenement1, evenement2)
        val modèle = ListeEvenementModele()

        val résultat_attendu = arrayListOf(evenement1, evenement2)
        val résultat_obtenu = modèle.getListeFiltrer(listeEvenements, "even")

        assertEquals(résultat_attendu, résultat_obtenu)
    }
}