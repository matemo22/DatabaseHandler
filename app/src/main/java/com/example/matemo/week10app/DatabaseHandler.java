package com.example.matemo.week10app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Matemo on 10/17/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "ContactDB";

    private static final String TABLE_CONTACT = "Contact";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PHONE = "phone";

    private static final String CREATE_TABLE_CONTACT = "CREATE TABLE "+TABLE_CONTACT+" ("+FIELD_ID+" INTEGER PRIMARY KEY, "+FIELD_NAME+" VARCHAR(50), "+FIELD_PHONE+" VARCHAR(20))";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addContact(Contact newContact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, newContact.getName());
        values.put(FIELD_PHONE, newContact.getPhone());
        db.insert(TABLE_CONTACT, null, values);
        db.close();
    }

    public ArrayList<Contact> getAllContact()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {FIELD_ID, FIELD_NAME, FIELD_PHONE};
        Cursor cursor = db.query(TABLE_CONTACT, column, null, null, null, null, null);
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        while(cursor.moveToNext())
        {
            Contact contact = new Contact();
            contact.setId(cursor.getInt(cursor.getColumnIndex(FIELD_ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(FIELD_NAME)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(FIELD_PHONE)));
            contactList.add(contact);
        }
        cursor.close();
        db.close();
        return contactList;
    }

    public void updateContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, contact.getName());
        values.put(FIELD_PHONE, contact.getPhone());

        String whereFields = FIELD_ID + " = ?";
        String[] whereValues = {String.valueOf(contact.getId())};

        db.update(TABLE_CONTACT, values, whereFields, whereValues);
        db.close();
    }

    public void deleteContact(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereField = FIELD_ID + " = ?";
        String[] whereValues = {String.valueOf(id)};
        db.delete(TABLE_CONTACT, whereField, whereValues);
        db.close();
    }
}
