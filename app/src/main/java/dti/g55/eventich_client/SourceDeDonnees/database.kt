package dti.g55.eventich_client.SourceDeDonnees

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dti.g55.eventich_client.R
import dti.g55.eventich_client.SourceDeDonnees.MockData
import dti.g55.eventich_client.domaine.entite.Evenement
import dti.g55.eventich_client.domaine.entite.Preference
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat
import java.util.Date

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "EventichsDB"

        private const val TABLE_Utilisateur = "Utilisateur"
        private const val COLUMN_idUtilisateur = "idUtilisateur"
        private const val COLUMN_nom = "nom"
        private const val COLUMN_prénom = "prénom"
        private const val COLUMN_numéroDeTéléphone = "numéroDeTéléphone"
        private const val COLUMN_email = "email"
        private const val COLUMN_adresse_utilisateur = "adresse"

        private const val TABLE_Evenement = "Evenement"
        private const val COLUMN_idEvenement = "idEvenement"
        private const val COLUMN_titre = "titre"
        private const val COLUMN_description = "description"
        private const val COLUMN_dateDebut = "date_debut"
        private const val COLUMN_dateFin = "date_fin"
        private const val COLUMN_location = "location"
        private const val COLUMN_cheminImage = "cheminImage"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_Utilisateur = "CREATE TABLE $TABLE_Utilisateur ($COLUMN_idUtilisateur INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_nom TEXT, $COLUMN_prénom TEXT, $COLUMN_numéroDeTéléphone INTEGER,$COLUMN_email TEXT, $COLUMN_adresse_utilisateur TEXT);"
        db.execSQL(CREATE_TABLE_Utilisateur)
        val CREATE_TABLE_Evenement = "CREATE TABLE $TABLE_Evenement ($COLUMN_idEvenement INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_titre TEXT,$COLUMN_description TEXT,$COLUMN_dateDebut Date, $COLUMN_dateFin Date,$COLUMN_location Text, $COLUMN_cheminImage TEXT);"
        db.execSQL(CREATE_TABLE_Evenement)

        creerUtilisateur(db)
        creerEvenement(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Utilisateur)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Evenement)
        onCreate(db)
    }
    fun creerUtilisateur(db: SQLiteDatabase) {
        val utilisateur =ProfilUtilisateur(1, R.drawable.alistaire_cockburn,"Cockburn", "Alistaire",  "5142345622","agile@alliance.com","124 rue Street, Montreal, Quebec")
        val valeur = ContentValues()
            valeur.put(COLUMN_nom, utilisateur.nom)
            valeur.put(COLUMN_prénom, utilisateur.prenom)
            valeur.put(COLUMN_numéroDeTéléphone, utilisateur.telephone)
            valeur.put(COLUMN_email, utilisateur.email)
            valeur.put(COLUMN_adresse_utilisateur, utilisateur.adresse)
            db.insert(TABLE_Utilisateur, null, valeur)
    }

    fun ajouterUtilisateur(h: ProfilUtilisateur){
        val valeur = ContentValues()
        valeur.put(COLUMN_nom, h.nom)
        valeur.put(COLUMN_email, h.email)
        valeur.put(COLUMN_prénom, h.prenom)
        valeur.put(COLUMN_numéroDeTéléphone, h.telephone)
        val db = writableDatabase
        db.insert(TABLE_Utilisateur, null, valeur)
        db.close()
    }
    fun modifierUtilisateur(idUtilisateur: Int,
                            nom: String,
                            prénom: String,
                            numéroDeTéléphone: Long,
                            email: String){
        val db = writableDatabase
        db.execSQL("UPDATE $TABLE_Utilisateur SET " +
                "$COLUMN_nom = '$nom' WHERE $COLUMN_idUtilisateur = $idUtilisateur,"+
                "$COLUMN_prénom = '$prénom' WHERE $COLUMN_idUtilisateur = $idUtilisateur,"+
                "$COLUMN_numéroDeTéléphone = $numéroDeTéléphone WHERE $COLUMN_idUtilisateur = $idUtilisateur,"+
                "$COLUMN_email = '$email' WHERE $COLUMN_idUtilisateur = $idUtilisateur")
        db.close()
    }

    fun creerEvenement(db: SQLiteDatabase) {
        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val e = Evenement(0, "Événement de Test",  "Repentigny, QC J5Y 2C2", dateFormat.parse("26-10-2023"),
                dateFormat.parse("27-10-2023"), "Public", "Divers",
                "Objet factice créé à des fins de tests", "https://www.vmcdn.ca/f/files/princegeorgematters/images/getty/house-party-getty-images.jpg","Rosemont")

            val valeur = ContentValues()
            valeur.put(COLUMN_titre, e.nom)
            valeur.put(COLUMN_description, e.description)
            valeur.put(COLUMN_dateDebut, e.dateDebut.toString())
            valeur.put(COLUMN_dateFin, e.dateFin.toString())
            valeur.put(COLUMN_location, e.adresse)
            valeur.put(COLUMN_cheminImage, e.image)
            db.insert(TABLE_Evenement, null, valeur)

    }
    fun ajouterEvenement(h: Evenement){
        val valeur = ContentValues()
        valeur.put(COLUMN_titre, h.nom)
        valeur.put(COLUMN_description, h.description)
        valeur.put(COLUMN_dateDebut, h.dateDebut.toString())
        valeur.put(COLUMN_dateFin, h.dateFin.toString())
        valeur.put(COLUMN_location, h.adresse)
        valeur.put(COLUMN_cheminImage, h.image)
        val db = writableDatabase
        db.insert(TABLE_Evenement, null, valeur)
        db.close()
    }
    fun supprimerEvenement(idEvenement: Int) {
        val db = writableDatabase
        db.execSQL(
            "DELETE FROM " + TABLE_Evenement + " WHERE " +
                    COLUMN_idEvenement + "= \"" + idEvenement + "\";"
        )
    }
    fun modifierEvenement(idEvenement: Int,
                         titre:String,
                         description:String,
                         debut: LocalDate,
                         fin:LocalDate,
                         location:Int,
                         cheminImage:String?){
        val db = writableDatabase
        db.execSQL("UPDATE $TABLE_Evenement SET " +

                "$COLUMN_titre = '$titre' WHERE $COLUMN_idEvenement = $idEvenement, "+
                "$COLUMN_description = '$description' WHERE $COLUMN_idEvenement = $idEvenement,"+
                "$COLUMN_dateDebut = '$debut' WHERE $COLUMN_idEvenement = $idEvenement,"+
                "$COLUMN_dateFin = '$fin' WHERE $COLUMN_idEvenement = $idEvenement,"+
                "$COLUMN_location = $location WHERE $COLUMN_idEvenement = $idEvenement," +
                "$COLUMN_cheminImage = '$cheminImage' WHERE $COLUMN_idEvenement = $idEvenement")
        db.close()
    }
}

