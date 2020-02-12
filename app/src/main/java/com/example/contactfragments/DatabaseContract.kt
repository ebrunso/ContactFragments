package com.example.contactfragments

const val DATABASE_NAME = "contact_database"
const val TABLE_NAME = "contact_table"
const val DATABASE_VERSION = 1
const val COL_NAME = "name"
const val COL_EMAIL = "email"
const val COL_PHONE = "phone"

const val CREATE_CONTACT_TABLE =
    "CREATE TABLE $TABLE_NAME (" +
            "$COL_NAME String," +
            "$COL_EMAIL String," +
            "$COL_PHONE String PRIMARY_KEY)"
