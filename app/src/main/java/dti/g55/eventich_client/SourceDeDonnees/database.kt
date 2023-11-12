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

        private const val TABLE_Preference = "Preference"
        private const val COLUMN_idPreference = "idPreference"
        private const val COLUMN_langue= "langue"
        private const val COLUMN_modeUI = "modeUI"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_preference = "CREATE TABLE $TABLE_Preference ($COLUMN_idPreference INTEGER PRIMARY KEY, $COLUMN_langue TEXT, $COLUMN_modeUI TEXT);"
        db.execSQL(CREATE_TABLE_preference)

        val CREATE_TABLE_Utilisateur = "CREATE TABLE $TABLE_Utilisateur ($COLUMN_idUtilisateur INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_nom TEXT, $COLUMN_prénom TEXT, $COLUMN_numéroDeTéléphone INTEGER,$COLUMN_email TEXT);"
        db.execSQL(CREATE_TABLE_Utilisateur)

        val CREATE_TABLE_Evenement = "CREATE TABLE $TABLE_Evenement ($COLUMN_idEvenement INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_titre TEXT,$COLUMN_description TEXT,$COLUMN_dateDebut Date, $COLUMN_dateFin Date,$COLUMN_location Text, $COLUMN_cheminImage TEXT);"
        db.execSQL(CREATE_TABLE_Evenement)

      //  val CREATE_TABLE_Images = "CREATE TABLE $TABLE_Image ($COLUMN_idImage INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_type text, $COLUMN_img BLOB);"
        //db.execSQL(CREATE_TABLE_Images)

        creerUtilisateurs(db)
        creerEvenement(db)
        creerPreference(db)
        //creerImages(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Utilisateur)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Evenement)
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_Images)
        onCreate(db)
    }

    fun creerPreference(db: SQLiteDatabase){
        val pref = Preference("Français","Light")
        val valeur = ContentValues()
        valeur.put(COLUMN_langue, pref.langue)
        valeur.put(COLUMN_modeUI, pref.modeUI)
        db.insert(TABLE_Preference, null, valeur)
    }

    fun creerUtilisateurs(db: SQLiteDatabase) {
        val utilisateurs = mutableListOf(
            ProfilUtilisateur(R.drawable.alistaire_cockburn,"Cockburn", "Alistaire",  5142345622,"agile@alliance.com","124 rue Street, Montreal, Quebec")
        )

        for (utilisateur in utilisateurs){
            val valeur = ContentValues()
            valeur.put(COLUMN_nom, utilisateur.nom)
            valeur.put(COLUMN_prénom, utilisateur.prenom)
            valeur.put(COLUMN_numéroDeTéléphone, utilisateur.telephone)
            valeur.put(COLUMN_email, utilisateur.email)
            db.insert(TABLE_Utilisateur, null, valeur)
        }
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
        val evenements = mutableListOf(
            Evenement(0,R.drawable.ic_search, "Événement de Test",  9999, dateFormat.parse("26-10-2023"),
                dateFormat.parse("27-10-2023"), "Repentigny, QC J5Y 2C2", "Alpha Group", "Divers",
                "Objet factice créé à des fins de tests"),
            Evenement(1, R.drawable.ic_search, "Conférence sur les mathématiques ésotériques",  124, dateFormat.parse("20-11-2023"),dateFormat.parse("22-11-2023"), "6400 16e Avenue, Montréal, QC H1X 2S9", "Cégep de Rosemont", "Éducation",
                "Venez en apprendre plus sur les mathématiques ésotériques de second niveau"),
            Evenement(2, R.drawable.ic_search, "Assemblée générale des lutins",  50, dateFormat.parse("14-10-2023"),dateFormat.parse("16-10-2023"), "x-5678 y-120 z-33 Atelier secret", "Ho Ho Ho", "Loisirs",
                "Assemblée très importante, humains interdits")
        )

        for (e in evenements){
            val valeur = ContentValues()
            valeur.put(COLUMN_titre, e.nom)
            valeur.put(COLUMN_description, e.description)
            valeur.put(COLUMN_dateDebut, e.dateDebut.toString())
            valeur.put(COLUMN_dateFin, e.dateFin.toString())
            valeur.put(COLUMN_location, e.location)
            valeur.put(COLUMN_cheminImage, e.imageId)
            db.insert(TABLE_Evenement, null, valeur)
        }
    }

    fun ajouterEvenement(h: Evenement){
        val valeur = ContentValues()
        valeur.put(COLUMN_titre, h.nom)
        valeur.put(COLUMN_description, h.description)
        valeur.put(COLUMN_dateDebut, h.dateDebut.toString())
        valeur.put(COLUMN_dateFin, h.dateFin.toString())
        valeur.put(COLUMN_location, h.location)
        valeur.put(COLUMN_cheminImage, h.imageId)
        val db = writableDatabase
        db.insert(TABLE_Evenement, null, valeur)
        db.close()
    }
    fun supprimerActivité(idEvenement: Int) {
        val db = writableDatabase
        db.execSQL(
            "DELETE FROM " + TABLE_Evenement + " WHERE " +
                    COLUMN_idEvenement + "= \"" + idEvenement + "\";"
        )
    }

    fun modifierActivité(idEvenement: Int,
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


    fun rechercherPref(): Preference {
        lateinit var preference: Preference
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_Preference LIMIT 1", null)

        if (!cursor.moveToFirst())
            preference = Preference("Français", "Light")
        else {
            val langueIndex = cursor.getColumnIndex(COLUMN_langue)
            val modeIndex = cursor.getColumnIndex(COLUMN_modeUI)
            val langue = cursor.getString(langueIndex)
            val mode = cursor.getString(modeIndex)
            preference = Preference(langue, mode)
        }

        cursor.close()
        db.close()

        return preference
    }

}

