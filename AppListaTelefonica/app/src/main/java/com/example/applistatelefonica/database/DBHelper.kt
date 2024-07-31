package com.example.applistatelefonica.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.applistatelefonica.model.ContactModel
import com.example.applistatelefonica.model.UserModel

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "database.db", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT)",
        "INSERT INTO users (username, password) VALUES ('admin', 'admin')",
        "CREATE TABLE contact (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT,email TEXT, phone INT, imageId INT)",
        "INSERT INTO contact (name, address,email, phone, imageId) VALUES ('Maria', 'Address Maria','maria@mail.pt',911222333,-1)",
        "INSERT INTO contact (name, address, email, phone, imageId) VALUES ('João', 'Address João','joao@mail.pt',833444555, -1)"
    )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    /* ---------- ---------- ---------- ---------- ---------- ---------- ----------
                                    CRUD USERS
     ---------- ---------- ---------- ---------- ---------- ---------- ----------*/

    fun insertUser(username: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val res = db.insert("users", null, contentValues)
        db.close()

        return res
    }

    fun updateUser(id: Int, username: String, password: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val res = db.update("users", contentValues, "id = ?", arrayOf(id.toString()))
        db.close()

        return res
    }

    fun deleteUser(id: Int): Int {
        val db = this.writableDatabase
        val res = db.delete("users", "id = ?", arrayOf(id.toString()))
        db.close()

        return res
    }

    @SuppressLint("Recycle")
    fun getUser(username: String, password: String): UserModel {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE username = ? AND password = ?",
            arrayOf(username, password)
        )
        var userModel = UserModel()
        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val usernameIndex = c.getColumnIndex("username")
            val passwordIndex = c.getColumnIndex("password")

            userModel = UserModel(
                id = c.getInt(idIndex),
                username = c.getString(usernameIndex),
                password = c.getString(passwordIndex)
            )

        }
        db.close()

        return userModel
    }

    @SuppressLint("Recycle")
    fun login(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE username = ? AND password = ?",
            arrayOf(username, password)
        )

        return if (c.count == 1) {
            true
        } else {
            db.close()
            false
        }
    }

    /* ---------- ---------- ---------- ---------- ---------- ---------- ----------
                                    CRUD Contacts
     ---------- ---------- ---------- ---------- ---------- ---------- ----------*/

    fun insertContact(
        name: String,
        address: String,
        email: String,
        phone: Int,
        imageId: Int
    ): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("email", email)
        contentValues.put("phone", phone)
        contentValues.put("imageId", imageId)
        val res = db.insert("contact", null, contentValues)
        db.close()

        return res
    }

    fun updateContact(
        id: Int,
        name: String,
        address: String,
        email: String,
        phone: Int,
        imageId: Int
    ): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("email", email)
        contentValues.put("phone", phone)
        contentValues.put("imageId", imageId)
        val res = db.update("contact", contentValues, "id = ?", arrayOf(id.toString()))
        db.close()

        return res
    }

    fun deleteContact(id: Int): Int {
        val db = this.writableDatabase
        val res = db.delete("contact", "id = ?", arrayOf(id.toString()))
        db.close()

        return res
    }

    @SuppressLint("Recycle")
    fun getContact(id: Int): ContactModel {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM contact WHERE id = ?",
            arrayOf(id.toString())
        )
        var contactModel = ContactModel()
        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            val addressIndex = c.getColumnIndex("address")
            val emailIndex = c.getColumnIndex("email")
            val phoneIndex = c.getColumnIndex("phone")
            val imageIdIndex = c.getColumnIndex("imageId")

            contactModel = ContactModel(
                id = c.getInt(idIndex),
                name = c.getString(nameIndex),
                address = c.getString(addressIndex),
                email = c.getString(emailIndex),
                phone = c.getInt(phoneIndex),
                imageId = c.getInt(imageIdIndex)
            )

        }
        db.close()

        return contactModel
    }

    @SuppressLint("Recycle")
    fun getAllContact(): ArrayList<ContactModel> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM contact", null)
        val listContactModel = ArrayList<ContactModel>()

        if (c.count > 0) {
            c.moveToFirst()

            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            val addressIndex = c.getColumnIndex("address")
            val emailIndex = c.getColumnIndex("email")
            val phoneIndex = c.getColumnIndex("phone")
            val imageIdIndex = c.getColumnIndex("imageId")

            do {
                val contactModel = ContactModel(
                    id = c.getInt(idIndex),
                    name = c.getString(nameIndex),
                    address = c.getString(addressIndex),
                    email = c.getString(emailIndex),
                    phone = c.getInt(phoneIndex),
                    imageId = c.getInt(imageIdIndex)
                )
                listContactModel.add(contactModel)
            } while (c.moveToNext())
        }
        db.close()

        return listContactModel
    }
}