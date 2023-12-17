package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.SourceDeDonnees.ISourceDonnee
import kotlin.test.*
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import kotlinx.coroutines.test.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesHTTP
import dti.g55.eventich_client.SourceDeDonnees.SourceDeDonneesException
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique


@RunWith(MockitoJUnitRunner::class)
class MeteoModeleTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `étant donné un Modèle nouvellement instancié lorsqu'on appelle la fonction obtenirMétéo, on obtient la météo de la journée donnée par la source de données`() = runTest {
        val source = Mockito.mock(ISourceDonnee::class.java)
        val conditionMeteo = ConditionMeterologique( "Partiellement nuageux", 20.0, 20, arrayListOf() )
        Mockito.`when`( source.obtenirConditionMeteorologique() ).thenReturn( conditionMeteo )
        val modèle = MeteoModele( source )

        val météo = modèle.obtenirMétéo()

        assertEquals( conditionMeteo, météo )
    }

}