package dti.g55.eventich_client.presentation.presentateur

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import dti.g55.eventich_client.presentation.modeles.MeteoModele
import dti.g55.eventich_client.presentation.vues.IVueMeteo
import kotlin.test.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito

import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MeteoPresentateurTest {
    val conditionMeteo = ConditionMeterologique( "---", 0.0, 0, arrayListOf<HeureMeteo>() )

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    @Test
    fun `Étant donné un présentateur nouvellement instancié, lorsqu'on appelle la fonction setupListeHeure, la fonction setupListeHeure de la vue est appelé avec la liste météo du modèle passé`() = runTest {
        //val mockVue = mock( IVueMeteo::class.java )
        //val présentateur = MeteoPresentateur( mockVue )


       //présentateur.setupListeHeure()

        //verify( mockVue ).setupListeHeure( ConditionMeterologique( "---", 0.0, 0, arrayListOf<HeureMeteo>() ) )
    }

    @Test
    fun `Étant donné un présentateur nouvellement instancié, lorsqu'on appelle la fonction obtenir_Meteo, on obtient alors rafraichirListeHeure de la vue avec le nouvel objet Meteo`() = runTest {
        //val mockVue = mock( IVueMeteo::class.java )
        //val source = mock( ISourceDonnee::class.java )
        //val modele = MeteoModele()

        //val présentateur = MeteoPresentateur( mockVue )

        //présentateur.obtenir_Meteo()
        //verify( mockVue ).rafraichirListeHeure( source.obtenirConditionMeteorologique() )
    }
}