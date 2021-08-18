package com.gusman.uas.biodata.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gusman.uas.biodata.model.BioData;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "biodata.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableTeman.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TableTeman.DROP_QUERY);
    }

    public boolean saveBioData(BioData data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableTeman.NAME, data.getNama());
        values.put(TableTeman.ADDRESS, data.getAlamat());
        values.put(TableTeman.PHONE, data.getNomorHp());
        values.put(TableTeman.EMAIL, data.getEmail());
        values.put(TableTeman.DOB, data.getTanggalLahir());
        values.put(TableTeman.GENDER, data.getJenisKelamin());
        return db.insert(TableTeman.TABLE_NAME, null, values) > 0;
    }

    public ArrayList<BioData> getAllBioData() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<BioData> dataList = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * FROM " + TableTeman.TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dataList.add(new BioData(
                        cursor.getInt(cursor.getColumnIndex(TableTeman.ID)),
                        cursor.getString(cursor.getColumnIndex(TableTeman.NAME)),
                        cursor.getString(cursor.getColumnIndex(TableTeman.ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(TableTeman.PHONE)),
                        cursor.getString(cursor.getColumnIndex(TableTeman.EMAIL)),
                        cursor.getString(cursor.getColumnIndex(TableTeman.DOB)),
                        cursor.getString(cursor.getColumnIndex(TableTeman.GENDER))
                ));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return dataList;
    }

    public boolean saveBioData(BioData data, int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableTeman.NAME, data.getNama());
        values.put(TableTeman.ADDRESS, data.getAlamat());
        values.put(TableTeman.PHONE, data.getNomorHp());
        values.put(TableTeman.EMAIL, data.getEmail());
        values.put(TableTeman.DOB, data.getTanggalLahir());
        values.put(TableTeman.GENDER, data.getJenisKelamin());
        return db.update(TableTeman.TABLE_NAME, values, TableTeman.ID + " = ?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean deleteBioData(int id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.delete(TableTeman.TABLE_NAME, TableTeman.ID + " = ?", new String[]{String.valueOf(id)}) > 0;
    }
}
