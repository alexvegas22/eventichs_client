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
            "Événement de Test",
            "3 Rue de la rue",
            Date(),
            Date(),
            "Repentigny, QC J5Y 2C2",
            "Divers",
            "Objet factice créé à des fins de tests",
            "https://www.paperlesspost.com/blog/wp-content/uploads/Blog00_FallParty_PeopleToastInField.png",
            "Alpha Group"
        ),
        Evenement(
            1,
            "Party de sorciers",
            "5 Rue de la rue",
            Date(),
            Date(),
            "Repentigny, QC J5Y 2C2",
            "Divers",
            "Objet factice créé à des fins de tests",
            "https://www.paperlesspost.com/blog/wp-content/uploads/Blog00_FallParty_PeopleToastInField.png",
            "Alpha Group"
        ),
        Evenement(
            2,
            "Assemblée générale des lutins",
            "x-5678 y-120 z-33 Atelier secret",
            Date(),
            Date(),
            "Public",
            "Loisirs",
            "Assemblée très importante, humains interdits",
            "https://www.paperlesspost.com/blog/wp-content/uploads/Blog00_FallParty_PeopleToastInField.png",
            "Santa Inc"
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
            3,
            "Marriage Turc",
            "123 rue Chemin, Montréal, QC",
            dateFormat.parse("22-10-2023"),
            Date(),
            "Private",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReKR1mY3PoROch9FHQjLZprkNGmFHArn4uvQ&usqp=CAU",
            "Turkish Airlines",
        ),
        Evenement(
            4,
            "Conférence sur les mathématiques ésotériques",
            "1928 rue Rue, Montréal, QC",
            dateFormat.parse("22-10-2023"),
            Date(),
            "Public",
            "Divers",
            "----_________-----__-_-----------___________----------____---____-----------",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Hip%2C_Hip%2C_Hurrah%21_Artists%E2%80%99_Party%2C_Skagen_%28Peder_Severin_Kr%C3%B8yer%29_-_Gothenburg_Museum_of_Art_-_F_62.tif/lossy-page1-1200px-Hip%2C_Hip%2C_Hurrah%21_Artists%E2%80%99_Party%2C_Skagen_%28Peder_Severin_Kr%C3%B8yer%29_-_Gothenburg_Museum_of_Art_-_F_62.tif.jpg",
            "Monster Inc."
        )
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
        return ArrayList(evenements)
    }

    override suspend fun obtenirOrganisations(): ArrayList<String> {
        return listeOrganisations
    }

    override suspend fun obtenirConditionMeteorologique(): ConditionMeterologique {
        TODO("Not yet implemented")
    }
}