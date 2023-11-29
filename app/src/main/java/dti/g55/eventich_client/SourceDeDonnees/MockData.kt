package dti.g55.eventich_client.SourceDeDonnees
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.HeureMeteo
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat
import java.util.Date

object MockData: ISourceDonnee {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy")

    val evenements = mutableListOf(
        Evenement(
            0,
            R.drawable.ic_search,
            "Événement de Test",
            9999,
            Date(),
            Date(),
            "Repentigny, QC J5Y 2C2",
            "Alpha Group",
            "Divers",
            "Objet factice créé à des fins de tests"
        ),
        Evenement(
            1,
            R.drawable.ic_search,
            "Conférence sur les mathématiques ésotériques",
            124,
            Date(),
            Date(),
            "6400 16e Avenue, Montréal, QC H1X 2S9",
            "Cégep de Rosemont",
            "Éducation",
            "Venez en apprendre plus sur les mathématiques ésotériques de second niveau"
        ),
        Evenement(
            2,
            R.drawable.ic_search,
            "Assemblée générale des lutins",
            50,
            Date(),
            Date(),
            "x-5678 y-120 z-33 Atelier secret",
            "Ho Ho Ho",
            "Loisirs",
            "Assemblée très importante, humains interdits"
        ),
        Evenement(
            3,
            R.drawable.ic_search,
            "Festival du hamburger",
            546,
            Date(),
            Date(),
            "3451 Rue Fleury E, Montréal-Nord, Quebec H1H 5R2",
            "B Burger",
            "Commerce",
            "L'opportunité de gouter à nos nouveaux burgers pour seulement 10$ l'entrée. À vos marques, pret, régalez-vous!"
        ),
        Evenement(
            4,
            R.drawable.ic_search,
            "R A Izmash Forum",
            1128,
            Date(),
            Date(),
            "Proyezd Deryabina, 3/435, Izhevsk, Udmurt Republic, Russia, 426008",
            "Izmash",
            "Commerce",
            "Крупнейший российский производитель боевого автоматического и снайперского оружия, управляемых артиллерийских снарядов, а также широкого спектра высокоточного оружия."
        ),
        Evenement(
            5, R.drawable.ic_search, "V O I D", 0, Date(), Date(), "...", "null", "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            6,
            R.drawable.ic_search,
            "Événement vide",
            2,
            Date(),
            Date(),
            "362 Rue du Domaine #350, Sainte-Sophie, QC J5J 1K9",
            "Clairview",
            "Loisirs",
            "Un autre objet factice créé à des fins de tests"
        ), Evenement(
            24,
            R.drawable.ic_search,
            "Spectacle de musique",
            52,
            dateFormat.parse("26-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Lutins Inc",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            11,
            R.drawable.ic_search,
            "Événement 1",
            23,
            dateFormat.parse("22-10-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            12,
            R.drawable.ic_search,
            "Un autre événement",
            53,
            dateFormat.parse("22-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            13,
            R.drawable.ic_search,
            "Festival de quelque chose",
            66,
            dateFormat.parse("23-10-2023"),
            Date(),
            "8765 rue Principale, Chambly, QC",
            "Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            14,
            R.drawable.ic_search,
            "Événement 4",
            24,
            dateFormat.parse("23-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            15,
            R.drawable.ic_search,
            "Événement 5",
            53,
            dateFormat.parse("31-10-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            16,
            R.drawable.ic_search,
            "Spectacle de musique",
            534,
            dateFormat.parse("26-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Rosemont",
            "Divers",
            "Spectacle de musique de tout genre; Jazz, Rock, Metal et plus."
        ),
        Evenement(
            17,
            R.drawable.ic_search,
            "Festival du hamburger",
            55,
            dateFormat.parse("28-10-2023"),
            Date(),
            "8765 rue Principale, Chambly, QC",
            "Maisonneuve",
            "Spectacle",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            18,
            R.drawable.ic_search,
            "Danse en ligne",
            5,
            dateFormat.parse("4-11-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Monster Inc",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            25,
            R.drawable.ic_search,
            "Festival du hamburger",
            78,
            dateFormat.parse("28-10-2023"),
            Date(),
            "8765 rue Principale, Chambly, QC",
            "Lutins Inc",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            26,
            R.drawable.ic_search,
            "Danse en ligne",
            96,
            dateFormat.parse("4-11-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Lutins Inc",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            27,
            R.drawable.ic_search,
            "Événement 1",
            95,
            dateFormat.parse("22-10-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Monster Inc.",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            28,
            R.drawable.ic_search,
            "Un autre événement",
            6,
            dateFormat.parse("22-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Monster Inc.",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            29,
            R.drawable.ic_search,
            "Festival de quelque chose",
            4,
            dateFormat.parse("23-10-2023"),
            Date(),
            "8765 rue Principale, Chambly, QC",
            "Monster Inc.",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            30,
            R.drawable.ic_search,
            "Grève étudiante",
            153,
            dateFormat.parse("10-11-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Collège Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            31,
            R.drawable.ic_search,
            "Grève des profs",
            532,
            dateFormat.parse("06-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Collège Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            32,
            R.drawable.ic_search,
            "Grève de la coop",
            5,
            dateFormat.parse("23-11-2023"),
            Date(),
            "8765 rue Principale, Chambly, QC",
            "Collège Rosemont",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        )
    )

    val profilUtilisateur: ProfilUtilisateur = ProfilUtilisateur(
        R.drawable.alistaire_cockburn,
        "Cockburn",
        "Alistaire",
        "5142224444",
        "agileKing@alliance.com",
        "6400 16e Avenue, Montreal, Quebec"
    )

    val evenementsInscrits = arrayListOf(
        Evenement(
            19,
            R.drawable.ic_search,
            "Marriage Turc",
            4,
            dateFormat.parse("22-10-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Turkish Airlines",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            20,
            R.drawable.ic_search,
            "Conférence sur les mathématiques ésotériques",
            23,
            dateFormat.parse("22-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Monster Inc.",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            21,
            R.drawable.ic_search,
            "Festival des grilles d'égouts",
            44,
            dateFormat.parse("23-10-2023"),
            Date(),
            "8765 rue Principale, Chambly, QC",
            "Sainte-Julie Org.",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            22,
            R.drawable.ic_search,
            "Assemblée générale des lutins",
            52,
            dateFormat.parse("23-10-2023"),
            Date(),
            "1928 rue Rue, Montréal, QC",
            "Lutins Inc.",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
        Evenement(
            23,
            R.drawable.ic_search,
            "Fin du monde",
            51,
            dateFormat.parse("31-10-2023"),
            Date(),
            "123 rue Chemin, Montréal, QC",
            "Mayas et Co.",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------"
        ),
    )
    val listeOrganisations = arrayListOf(
        "Collège Rosemont"
    )

    val listeLogins: Array<Array<String>> = arrayOf(
        arrayOf("agileking@alliance.com", "burn"),
        arrayOf("alex@email.com", "root"),
        arrayOf("user@email.com", "root")
    )

    val météo: ConditionMeterologique = créerMétéoJournée()
    fun créerMétéoJournée(): ConditionMeterologique {
        val météo = ConditionMeterologique("Partiellement nuagueux", 17.0, 13, arrayListOf())
        for (heure in 0..23) {
            val testMétéo =
                HeureMeteo(heure, R.drawable.day_cloudy_icon, "Partiellement nuageux", 17.0, 13)
            météo.listeHeures.add(testMétéo)
        }
        return météo
    }

    override suspend fun obtenirListeEvenements(): ArrayList<Evenement> {
        return ArrayList(
            mutableListOf(
                Evenement(
                    0,
                    R.drawable.whiteworth_chalet_front,
                    "Événement de Test",
                    9999,
                    dateFormat.parse("14-11-2023"),
                    dateFormat.parse("14-11-2023"),
                    "Repentigny, QC J5Y 2C2",
                    "Alpha Group",
                    "Divers",
                    "Objet factice créé à des fins de tests"
                ),
                Evenement(
                    1,
                    R.drawable.thermometer,
                    "Conférence sur les mathématiques ésotériques",
                    124,
                    dateFormat.parse("16-11-2023"),
                    dateFormat.parse("16-11-2023"),
                    "6400 16e Avenue, Montréal, QC H1X 2S9",
                    "Cégep de Rosemont",
                    "Éducation",
                    "Venez en apprendre plus sur les mathématiques ésotériques de second niveau"
                ),
                Evenement(
                    2,
                    R.drawable.day_cloudy_icon,
                    "Assemblée générale des lutins",
                    50,
                    dateFormat.parse("18-11-2023"),
                    dateFormat.parse("18-11-2023"),
                    "x-5678 y-120 z-33 Atelier secret",
                    "Ho Ho Ho",
                    "Loisirs",
                    "Assemblée très importante, humains interdits"
                ),
                Evenement(
                    3,
                    R.drawable.alistaire_cockburn,
                    "Festival du hamburger",
                    546,
                    dateFormat.parse("22-11-2023"),
                    dateFormat.parse("22-11-2023"),
                    "3451 Rue Fleury E, Montréal-Nord2",
                    "B Burger",
                    "Commerce",
                    "L'opportunité de gouter à nos nouveaux burgers pour seulement 10$ l'entrée. À vos marques, pret, régalez-vous!"
                ),
                Evenement(
                    4,
                    R.drawable.day_sunny_icon,
                    "R A Izmash Forum",
                    1128,
                    dateFormat.parse("25-11-2023"),
                    dateFormat.parse("25-11-2023"),
                    "Proyezd Deryabina, 3/435, Izhevsk",
                    "Izmash",
                    "Commerce",
                    "Крупнейший российский производитель боевого автоматического и снайперского оружия, управляемых артиллерийских снарядов, а также широкого спектра высокоточного оружия."
                ),
                Evenement(
                    5,
                    R.drawable.confirmed,
                    "V O I D",
                    0,
                    dateFormat.parse("26-11-2023"),
                    dateFormat.parse("27-11-2023"),
                    "...",
                    "Collège Rosemont",
                    "Divers",
                    "----_________-----__-_-----------___________----------____---____-----------"
                ),
                Evenement(
                    6,
                    R.drawable.people,
                    "Événement vide",
                    2,
                    dateFormat.parse("20-12-2023"),
                    dateFormat.parse("21-12-2023"),
                    "362 Rue du Domaine, Sainte-Sophie",
                    "Test organ",
                    "Loisirs",
                    "Un autre objet factice créé à des fins de tests"
                ),
                Evenement(30,R.drawable.ic_search, "Grève étudiante",153, dateFormat.parse("10-11-2023"), dateFormat.parse("10-11-2023"), "123 rue Chemin, Montréal, QC","Collège Rosemont","Divers",
                    "----__-----------------__------------------------"),
                Evenement(31,R.drawable.ic_search, "Grève des profs",532, dateFormat.parse("06-10-2023"), dateFormat.parse("06-10-2023"), "1928 rue Rue, Montréal, QC","Collège Rosemont","Divers",
                    "----__-----------------__------------------------"),
                Evenement(32,R.drawable.ic_search, "Grève de la coop", 5, dateFormat.parse("23-11-2023"), dateFormat.parse("23-11-2023"), "8765 rue Principale, Chambly, QC","Collège Rosemont","Divers",
                    "----__-----------------__------------------------")
            )
        )
    }

    override suspend fun obtenirOrganisations(): ArrayList<String> {
        return listeOrganisations
    }

    override suspend fun obtenirConditionMeteorologique(): ConditionMeterologique {
        TODO("Not yet implemented")
    }
}