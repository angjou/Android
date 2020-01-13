package com.example.mydiary_01.Profile;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBHandlerProfile extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final  String DATABASE_NAME = "myDiaryDB.db";
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_NAME = "UserName";
    private static final String COLUMN_ID = "UserID";
    private static final String COLUMN_SURNAME = "UserSurname";
    private static final String COLUMN_ADDRESS = "UserAddress";
    private static final String COLUMN_EMAIL = "UserEmail";
    private static final String COLUMN_OIB  = "UserOib";
    private static final String COLUMN_PASSPORT  = "UserPassport";

    //initialize database

    public MyDBHandlerProfile(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db){
        if(db == null)
            Log.d("baza", "ne postoji");
        else
            Log.d("baza", "postoji");

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +" ("+COLUMN_ID + " INTEGER PRIMARY KEY, " +COLUMN_NAME+ " TEXT, "+
                COLUMN_SURNAME+" TEXT, "+COLUMN_ADDRESS+
                " TEXT, "+COLUMN_EMAIL+" TEXT, "+COLUMN_OIB+" TEXT, "+COLUMN_PASSPORT+" TEXT )";
        db.execSQL(CREATE_TABLE);
        db = getWritableDatabase();

        proba(db);




}
    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {

    }
//load functions
    public String loadNameHandler(User user){

    String query= "Select "+COLUMN_NAME + " FROM" + TABLE_NAME;
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(query, null);

    String name = cursor.getString(0);

    if (name == "")
    {
        user.setUserName("Name");
    }else{
        user.setUserName(name);
    }
    cursor.close();
    db.close();
    return user.getUserName();
}
    public void proba(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,"aa");
        values.put(COLUMN_ID,"AUTO_INCREMENT");
        values.put(COLUMN_SURNAME,"AA");
        values.put(COLUMN_ADDRESS,"ifsu");
        values.put(COLUMN_EMAIL,"faj");
        values.put(COLUMN_OIB,"adfhjai");
        values.put(COLUMN_PASSPORT,"fai");
        String id="";
        values.put(id,COLUMN_ID);

            db.insert(TABLE_NAME, null, values);

        db.close();
    }
    public String loadSurnameHandler(User user){
        String query= "Select "+COLUMN_SURNAME + " FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String surname = cursor.getString(0);
        if (surname == "")
        {
            user.setUserSurname("Surname");
        }else{
            user.setUserEmail(surname);
        }
        Log.i("debug", surname);
        cursor.close();
        db.close();
        return user.getUserSurname();
    }
    public String loadAddressHandler(User user){
        String query= "Select "+COLUMN_ADDRESS + " FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String adr = cursor.getString(0);
        if (adr == "")
        {
            user.setUserAddress("Address");
        }else{
            user.setUserAddress(adr);
        }
        cursor.close();
        db.close();
        return user.getUserAddress();
    }
    public String loadEmailHandler(User user){
        String query= "Select "+COLUMN_EMAIL + " FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String adr = cursor.getString(0);
        if (adr == "")
        {
            user.setUserEmail("E-mail");
        }else{
            user.setUserEmail(adr);
        }
        cursor.close();
        db.close();
        return user.getUserEmail();
    }
    public String loadOIBHandler(User user){
        String query= "Select "+COLUMN_OIB + " FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String adr = cursor.getString(0);
        if (adr == "")
        {
            user.setUserOib("OIB" );
        }else{
            user.setUserOib(adr);
        }

        String Oib = user.getUserOib();
        cursor.close();
        db.close();
        return Oib;
    }
    public String loadPassportHandler(User user){
        String query= "Select "+COLUMN_PASSPORT + " FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        String adr = cursor.getString(0);
        if (adr == "")
        {
            user.setUserPassport("passport" );
        }else{
            user.setUserPassport(adr);
        }

        String passport = user.getUserPassport();
        cursor.close();
        db.close();
        return passport;
    }
    public String loadIDHandler(User user){
        String query= "Select "+COLUMN_ID + " FROM" + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        String adr = cursor.getString(0);
        if (adr == "")
        {
            user.setUserID(Integer.parseInt("ID" ));
        }else{
            user.setUserID(Integer.parseInt(adr));
        }

        String id = user.getUserPassport();
        cursor.close();
        db.close();
        return id;
    }

    public void  addHandler (User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,user.getUserName());
        values.put(COLUMN_ID,user.getUserID());
        values.put(COLUMN_SURNAME,user.getUserSurname());
        values.put(COLUMN_ADDRESS,user.getUserAddress());
        values.put(COLUMN_EMAIL,user.getUserEmail());
        values.put(COLUMN_OIB,user.getUserOib());
        values.put(COLUMN_PASSPORT,user.getUserPassport());
        SQLiteDatabase db = this.getWritableDatabase();
        String id="";
        values.put(id,COLUMN_ID);
        if ( Integer.parseInt(id) == user.getUserID())
        {
            db.update(TABLE_NAME, values, COLUMN_ID + "=" + user.getUserID(), null);
        }
        else  {
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }

   /* public boolean updateHandler(int ID, String name, String surname, String address, String email, String oib, String passport) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_SURNAME, surname);
        args.put(COLUMN_ADDRESS, address);
        args.put(COLUMN_EMAIL, email);
        args.put(COLUMN_OIB, oib);
        args.put(COLUMN_PASSPORT, passport);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }*/
}

