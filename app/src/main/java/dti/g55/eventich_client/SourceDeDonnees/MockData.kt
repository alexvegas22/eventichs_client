package dti.g55.eventich_client.SourceDeDonnees
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.ConditionMeterologique
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat
import java.util.Date

class MockData {
    companion object {

        val testÉvénement1 = Evenement(0,R.drawable.ic_search, "Événement de Test",  9999, Date(), "Repentigny, QC J5Y 2C2", "Alpha Group", "Divers",
             "Objet factice créé à des fins de tests")
        val testÉvénement2 = Evenement(1, R.drawable.ic_search, "Conférence sur les mathématiques ésotériques",  124, Date(), "6400 16e Avenue, Montréal, QC H1X 2S9", "Cégep de Rosemont", "Éducation",
             "Venez en apprendre plus sur les mathématiques ésotériques de second niveau")
        val testÉvénement3 = Evenement(2, R.drawable.ic_search, "Assemblée générale des lutins",  50, Date(), "x-5678 y-120 z-33 Atelier secret", "Ho Ho Ho", "Loisirs",
             "Assemblée très importante, humains interdits")
        val testÉvénement4 = Evenement(3,R.drawable.ic_search, "Festival du hamburger",  546, Date(), "3451 Rue Fleury E, Montréal-Nord, Quebec H1H 5R2", "B Burger", "Commerce",
             "L'opportunité de gouter à nos nouveaux burgers pour seulement 10$ l'entrée. À vos marques, pret, régalez-vous!")
        val testÉvénement5 = Evenement(4,R.drawable.ic_search, "R A Izmash Forum", 1128, Date(), "Proyezd Deryabina, 3/435, Izhevsk, Udmurt Republic, Russia, 426008", "Izmash", "Commerce",
             "Крупнейший российский производитель боевого автоматического и снайперского оружия, управляемых артиллерийских снарядов, а также широкого спектра высокоточного оружия.")
        val testÉvénement6 = Evenement(5,R.drawable.ic_search, "V O I D",  0, Date(), "...", "null", "Divers",
             "----_________-----__-_-----------___________----------____---____-----------")
        val testÉvénement7 = Evenement(6,R.drawable.ic_search, "Événement vide",  2, Date(), "362 Rue du Domaine #350, Sainte-Sophie, QC J5J 1K9", "Clairview", "Loisirs",
             "Un autre objet factice créé à des fins de tests")

        val evenements = mutableListOf(testÉvénement1, testÉvénement2, testÉvénement3, testÉvénement4, testÉvénement5, testÉvénement6, testÉvénement7)

        val profilUtilisateur: ProfilUtilisateur = ProfilUtilisateur(R.drawable.alistaire_cockburn, "Cockburn", "Alistaire", "agileKing@alliance.com","6400 16e Avenue, Montreal, Quebec")


        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        /*
        val evenementChoisi =  Evenement(R.drawable.ic_search, "Spectacle de musique", dateFormat.parse("26-10-2023"), "1928 rue Rue, Montréal, QC","Lutins Inc")

        val evenementsListe = arrayListOf(
            Evenement(R.drawable.ic_search, "Événement 1", dateFormat.parse("22-10-2023"), "123 rue Chemin, Montréal, QC","Rosemont"),
            Evenement(R.drawable.ic_search, "Un autre événement", dateFormat.parse("22-10-2023"), "1928 rue Rue, Montréal, QC","Rosemont"),
            Evenement(R.drawable.ic_search, "Festival de quelque chose", dateFormat.parse("23-10-2023"), "8765 rue Principale, Chambly, QC","Rosemont"),
            Evenement(R.drawable.ic_search, "Événement 4", dateFormat.parse("23-10-2023"), "1928 rue Rue, Montréal, QC","Rosemont"),
            Evenement(R.drawable.ic_search, "Événement 5", dateFormat.parse("31-10-2023"), "123 rue Chemin, Montréal, QC","Rosemont"),
            Evenement(R.drawable.ic_search, "Spectacle de musique", dateFormat.parse("26-10-2023"), "1928 rue Rue, Montréal, QC","Rosemont"),
            Evenement(R.drawable.ic_search, "Festival du hamburger", dateFormat.parse("28-10-2023"), "8765 rue Principale, Chambly, QC","Maisonneuve"),
            Evenement(R.drawable.ic_search, "Danse en ligne", dateFormat.parse("4-11-2023"), "123 rue Chemin, Montréal, QC","Monster Inc"),
        )
        val evenementsInscrits = arrayListOf(
            Evenement(R.drawable.ic_search, "Marriage Turc", dateFormat.parse("22-10-2023"), "123 rue Chemin, Montréal, QC","Turkish Airlines"),
            Evenement(R.drawable.ic_search, "Conférence sur les mathématiques ésotériques", dateFormat.parse("22-10-2023"), "1928 rue Rue, Montréal, QC", "Monster Inc."),
            Evenement(R.drawable.ic_search, "Festival des grilles d'égouts", dateFormat.parse("23-10-2023"), "8765 rue Principale, Chambly, QC","Sainte-Julie Org."),
            Evenement(R.drawable.ic_search, "Assemblée générale des lutins", dateFormat.parse("23-10-2023"), "1928 rue Rue, Montréal, QC","Lutins Inc."),
            Evenement(R.drawable.ic_search, "Fin du monde", dateFormat.parse("31-10-2023"), "123 rue Chemin, Montréal, QC","Mayas et Co."),
        )
        val listeOrganisations = arrayListOf(
            "Lutins Inc.","Monster  Inc.","Collège Rosemont"
        )
        val evenementsOrganisations = arrayListOf(
            Evenement(R.drawable.ic_search, "Spectacle de musique", dateFormat.parse("26-10-2023"), "1928 rue Rue, Montréal, QC","Lutins Inc"),
            Evenement(R.drawable.ic_search, "Festival du hamburger", dateFormat.parse("28-10-2023"), "8765 rue Principale, Chambly, QC","Lutins Inc"),
            Evenement(R.drawable.ic_search, "Danse en ligne", dateFormat.parse("4-11-2023"), "123 rue Chemin, Montréal, QC","Lutins Inc"),
            Evenement(R.drawable.ic_search, "Événement 1", dateFormat.parse("22-10-2023"), "123 rue Chemin, Montréal, QC","Monster Inc."),
            Evenement(R.drawable.ic_search, "Un autre événement", dateFormat.parse("22-10-2023"), "1928 rue Rue, Montréal, QC","Monster Inc."),
            Evenement(R.drawable.ic_search, "Festival de quelque chose", dateFormat.parse("23-10-2023"), "8765 rue Principale, Chambly, QC","Monster Inc."),
            Evenement(R.drawable.ic_search, "Grève étudiante", dateFormat.parse("10-11-2023"), "123 rue Chemin, Montréal, QC","Collège Rosemont"),
            Evenement(R.drawable.ic_search, "Grève des profs", dateFormat.parse("06-10-2023"), "1928 rue Rue, Montréal, QC","Collège Rosemont"),
            Evenement(R.drawable.ic_search, "Grève de la coop", dateFormat.parse("23-11-2023"), "8765 rue Principale, Chambly, QC","Collège Rosemont")
        )
        val listeLogins: Array<Array<String>> = arrayOf(
            arrayOf("agileking@alliance.com", "burn"),
            arrayOf("alex@email.com", "root"),
            arrayOf("user@email.com", "root")
        )

         */

    }

}