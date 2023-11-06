package dti.g55.eventich_client.presentation.modeles

import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import java.util.Date

class ModelDonnéesFactices {
    
    val testMeteo1 = ConditionMeterologique("pluie légère",0.0,286.98,285.22,287.97,287.29,
        287.97,285.22, 282.61,283.19,284.98,282.68,84)
    val testMeteo2 = ConditionMeterologique("ciel nuageux",0.0,386.98,385.22,387.97,387.29,
        387.97,385.22, 382.61,383.19,384.98,382.68,5)
    val testMeteo3 = ConditionMeterologique("mort iminente",0.0,0.0,0.0,0.0,0.0,
        0.0,0.0, 0.0,0.0,0.0,0.0,84)
    val testMeteo4 = ConditionMeterologique("ciel ensoleillé",0.0,286.98,285.22,287.97,287.29,
        287.97,285.22, 282.61,283.19,284.98,282.68,84)

    val testÉvénement1 = Evenement(0, "Événement de Test", "Evtest", 9999, Date(), "Repentigny, QC J5Y 2C2", "Alpha Group", "Divers",
        "Elies", "Objet factice créé à des fins de tests", testMeteo1)
    val testÉvénement2 = Evenement(1, "Conférence sur les mathématiques ésotériques", "ConfME", 124, Date(), "6400 16e Avenue, Montréal, QC H1X 2S9", "Cégep de Rosemont", "Éducation",
        "M. peu importe", "Venez en apprendre plus sur les mathématiques ésotériques de second niveau", testMeteo2)
    val testÉvénement3 = Evenement(2, "Assemblée générale des lutins", "AGL", 50, Date(), "x-5678 y-120 z-33 Atelier secret", "Ho Ho Ho", "Loisirs",
        "Père Noel", "Assemblée très importante, humains interdits", testMeteo3)
    val testÉvénement4 = Evenement(3, "Festival du hamburger", "HamFest", 546, Date(), "3451 Rue Fleury E, Montréal-Nord, Quebec H1H 5R2", "B Burger", "Commerce",
        "Paul", "L'opportunité de gouter à nos nouveaux burgers pour seulement 10$ l'entrée. À vos marques, pret, régalez-vous!", testMeteo4)
    val testÉvénement5 = Evenement(4, "R A Izmash Forum", "RAIF", 1128, Date(), "Proyezd Deryabina, 3/435, Izhevsk, Udmurt Republic, Russia, 426008", "Izmash", "Commerce",
        "Mikhail", "Крупнейший российский производитель боевого автоматического и снайперского оружия, управляемых артиллерийских снарядов, а также широкого спектра высокоточного оружия.", testMeteo3)
    val testÉvénement6 = Evenement(5, "V O I D", "void", 0, Date(), "...", "null", "Divers",
        "???", "----_________-----__-_-----------___________----------____---____-----------", testMeteo1)
    val testÉvénement7 = Evenement(6, "Événement vide", "Ev0", 2, Date(), "362 Rue du Domaine #350, Sainte-Sophie, QC J5J 1K9", "Clairview", "Loisirs",
        "Henry IX", "Un autre objet factice créé à des fins de tests", testMeteo2)

    val evenements = mutableListOf(testÉvénement1, testÉvénement2, testÉvénement3, testÉvénement4, testÉvénement5, testÉvénement6, testÉvénement7)

    fun retournerUnÉvénement(): Evenement {
        return testÉvénement1
    }

    fun retournerListeÉvénements(): MutableList<Evenement> {
        return evenements
    }

    fun retournerUnÉvénementparNom(nomÉvénement: String): Evenement? {
        return evenements.find { it.nomComplet==nomÉvénement }
    }
}