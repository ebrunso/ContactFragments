package com.example.contactfragments

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class ContactDatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        sqLiteDatabase?.execSQL(CREATE_CONTACT_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(sqLiteDatabase)
    }

    fun insertContactIntoDatabase(contact: Contact){
        val database = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_NAME, contact.name)
        contentValues.put(COL_EMAIL, contact.email)
        contentValues.put(COL_PHONE, contact.phone)

        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    fun getOneContactFromDatabase(phone: String) : Contact? {
        val database = readableDatabase
        var contact : Contact? = null
        val cursor = database.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_PHONE = '$phone'",
            null)

        if(cursor.moveToFirst()){
            val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
            val email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
            val phone = cursor.getString(cursor.getColumnIndex(COL_PHONE))
            contact = Contact(name, email, phone)
        }
        cursor.close()
        database.close()
        return contact
    }

    fun getAllContactsFromDatabase() : ArrayList<Contact> {
        val database = readableDatabase
        var contactList : ArrayList<Contact> = ArrayList<Contact>()
        val cursor = database.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()){
            do {
                val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                val email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                val phone = cursor.getString(cursor.getColumnIndex(COL_PHONE))
                val contact = Contact(name, email, phone)
                contactList.add(contact)
            } while(cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return contactList
    }

    fun updateContactInDatabase(contact: Contact) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, contact.name)
        contentValues.put(COL_EMAIL, contact.email)
        contentValues.put(COL_PHONE, contact.phone)

        database.update(TABLE_NAME, contentValues, "$COL_PHONE = ?", arrayOf(contact.phone))
        database.close()
    }

    fun removeContactFromDatabase(phone: String){
        val database = writableDatabase
        database.delete(TABLE_NAME, "$COL_PHONE = ?", arrayOf(phone))
        database.close()
    }

}