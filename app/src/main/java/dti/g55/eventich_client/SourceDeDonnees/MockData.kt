package dti.g55.eventich_client.SourceDeDonnees
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat

class MockData {
    companion object {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        val evenementChoisi =  Evenement(R.drawable.ic_search, "Spectacle de musique", dateFormat.parse("26-10-2023"), "1928 rue Rue, Montréal, QC","Lutins Inc")

        val profilUtilisateur: ProfilUtilisateur = ProfilUtilisateur(R.drawable.alistaire_cockburn, "Cockburn", "Alistaire", "agileKing@alliance.com","6400 16e Avenue, Montreal, Quebec")

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
    }
}