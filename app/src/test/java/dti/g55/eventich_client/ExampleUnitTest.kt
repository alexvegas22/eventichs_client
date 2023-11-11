package dti.g55.eventich_client

import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.presentation.modeles.EvenementModele
import dti.g55.eventich_client.presentation.modeles.ModeleFactory
import dti.g55.eventich_client.presentation.presentateur.EvenementPresentateur
import dti.g55.eventich_client.presentation.vues.EvenementVue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
//import org.junit.Test
import org.junit.Assert.*
import kotlin.test.*
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.After
import kotlinx.coroutines.test.*
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.times
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

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
    fun `étant donné un Modèle instancié, lorsque le présentateur appelle la fonction init(), Il appelle la fonction changerCouleursTextInitiales() de la vue et et charger_données()`() = runTest {

        // mock
        val vue : EvenementVue = Mockito.mock( EvenementVue::class.java )
        // sujet de test - mock pour pouvoir spy avec mockito
        val presentateur = EvenementPresentateur(vue)
        // spy
        val spy = Mockito.spy(presentateur)

        spy.init()
        Mockito.verify(vue).changerCouleursTextInitiales()
        Mockito.verify(spy).charger_données()
    }

    //@Test
    //fun `étant donné un Modèle instancié, lorsque le présentateur appelle la fonction getEvenementSelectionne, on obtient l'événement au bon index passé en parametre à la fonction`() = runTest {

        // mock
        //val modele : EvenementModele = Mockito.mock( ModeleFactory.evenements::class.java )
        //val vue : EvenementVue = Mockito.mock( EvenementVue::class.java )
        // sujet de test - mock pour pouvoir spy avec mockito
        //val presentateur = EvenementPresentateur(vue)
        // spy
        //val spy = Mockito.spy(presentateur)

        //Mockito.`when`( modele.evenementCourant ).thenReturn( Evenement(0,0,"",0, Date(),"","","","") )

        //spy.charger_données()
        //Mockito.verify ------------------- whatever à verifier
    //}
}