package dti.g55.eventich_client.SourceDeDonnees

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dti.g55.eventich_client.R
import dti.g55.eventich_client.domaine.entite.Evenement
import java.time.LocalDate
import dti.g55.eventich_client.domaine.entite.ProfilUtilisateur
import java.text.SimpleDateFormat

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "EventichsDB"

        const val TABLE_Utilisateur = "Utilisateur"
        private const val COLUMN_idUtilisateur = "idUtilisateur"
        const val COLUMN_nom = "nom"
        const val COLUMN_prénom = "prénom"
        const val COLUMN_numéroDeTéléphone = "numéroDeTéléphone"
        const val COLUMN_email = "email"
        const val COLUMN_adresse_utilisateur = "adresse"

        const val TABLE_Evenement = "Evenement"
        const val COLUMN_idEvenement = "idEvenement"
        const val COLUMN_titre = "titre"
        const val COLUMN_description = "description"
        const val COLUMN_dateDebut = "date_debut"
        const val COLUMN_dateFin = "date_fin"
        const val COLUMN_location = "location"
        const val COLUMN_cheminImage = "cheminImage"
        const val COLUMN_organisation = "organisation"
        const val COLUMN_type = "type"
        const val COLUMN_categorie = "categorie"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_Utilisateur = "CREATE TABLE $TABLE_Utilisateur ($COLUMN_idUtilisateur INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_nom TEXT, $COLUMN_prénom TEXT, $COLUMN_numéroDeTéléphone INTEGER,$COLUMN_email TEXT, $COLUMN_adresse_utilisateur TEXT);"
        db.execSQL(CREATE_TABLE_Utilisateur)
        val CREATE_TABLE_Evenement = "CREATE TABLE $TABLE_Evenement ($COLUMN_idEvenement INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_titre TEXT,$COLUMN_description TEXT,$COLUMN_dateDebut Date, $COLUMN_dateFin Date,$COLUMN_location Text, $COLUMN_cheminImage TEXT, $COLUMN_organisation TEXT, $COLUMN_type TEXT, $COLUMN_categorie);"
        db.execSQL(CREATE_TABLE_Evenement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Utilisateur)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Evenement)
        onCreate(db)
    }
    fun clearEvenements(){
        val db = writableDatabase
        db.execSQL("DELETE FROM " + TABLE_Evenement)
    }

    @SuppressLint("Range")
    public fun obtenirEvenements(): ArrayList<Evenement> {
        var listeEvenements = mutableListOf<Evenement>()
        val db = readableDatabase
        val query = "SELECT * from $TABLE_Evenement"
        val cursor : Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex("$COLUMN_idEvenement"))
            val nom = cursor.getString(cursor.getColumnIndex("$COLUMN_titre"))
            val adresse = cursor.getString(cursor.getColumnIndex("$COLUMN_location"))
            val dateDebut = cursor.getString(cursor.getColumnIndex("$COLUMN_dateDebut"))
            val dateFin = cursor.getString(cursor.getColumnIndex("$COLUMN_dateFin"))
            val type = cursor.getString(cursor.getColumnIndex("$COLUMN_type"))
            val categorie = cursor.getString(cursor.getColumnIndex("$COLUMN_categorie"))
            val description = cursor.getString(cursor.getColumnIndex("$COLUMN_description"))
            val image = cursor.getString(cursor.getColumnIndex("$COLUMN_cheminImage"))
            val organisation = cursor.getString(cursor.getColumnIndex("$COLUMN_organisation"))
            val evenement = Evenement(id,nom, adresse, dateFormat.parse(dateDebut), dateFormat.parse(dateFin), type, categorie, description, image, organisation)
            listeEvenements.add(evenement)
        }
        cursor.close()
        return ArrayList(listeEvenements)
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
    fun modifierUtilisateur(profil: ProfilUtilisateur){
        val db = writableDatabase
        db.execSQL("UPDATE $TABLE_Utilisateur SET " +
                "$COLUMN_nom = '$profil.nom' WHERE $COLUMN_idUtilisateur = $profil.idUtilisateur,"+
                "$COLUMN_prénom = '$profil.prénom' WHERE $COLUMN_idUtilisateur = $profil.idUtilisateur,"+
                "$COLUMN_numéroDeTéléphone = $profil.numéroDeTéléphone WHERE $COLUMN_idUtilisateur = $profil.idUtilisateur,"+
                "$COLUMN_email = '$profil.email' WHERE $COLUMN_idUtilisateur = $profil.idUtilisateur")
        db.close()
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
                          fin: LocalDate,
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

    @SuppressLint("Range")
    fun obtenirEvenementsParOrganisation(organisation : String): ArrayList<Evenement> {

        var listeEvenements = mutableListOf<Evenement>()
        val db = readableDatabase
        val query = "SELECT * from $TABLE_Evenement WHERE $COLUMN_organisation = '$organisation'"
        val cursor : Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex("$COLUMN_idEvenement"))
            val nom = cursor.getString(cursor.getColumnIndex("$COLUMN_titre"))
            val adresse = cursor.getString(cursor.getColumnIndex("$COLUMN_location"))
            val dateDebut = cursor.getString(cursor.getColumnIndex("$COLUMN_dateDebut"))
            val dateFin = cursor.getString(cursor.getColumnIndex("$COLUMN_dateFin"))
            val type = cursor.getString(cursor.getColumnIndex("$COLUMN_type"))
            val categorie = cursor.getString(cursor.getColumnIndex("$COLUMN_categorie"))
            val description = cursor.getString(cursor.getColumnIndex("$COLUMN_description"))
            val image = cursor.getString(cursor.getColumnIndex("$COLUMN_cheminImage"))
            val organisation = cursor.getString(cursor.getColumnIndex("$COLUMN_organisation"))
            val evenement = Evenement(id,nom, adresse, dateFormat.parse(dateDebut), dateFormat.parse(dateFin), type, categorie, description, image, organisation)
            listeEvenements.add(evenement)
        }
        cursor.close()
        return ArrayList(listeEvenements)
    }

}

