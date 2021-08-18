package com.gusman.uas.biodata.helper;

public class TableTeman {
    public static final String TABLE_NAME = "teman";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String GENDER = "gender";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String DOB = "date_of_birth";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            NAME + " TEXT," +
            ADDRESS + " TEXT," +
            GENDER + " TEXT," +
            PHONE + " TEXT," +
            EMAIL + " TEXT," +
            DOB + " TEXT" +
            ")";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
