package com.example.mydiary_01.Profile;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ProfileDataSource {
    private SQLiteDatabase database;
    private MyDBHandlerProfile dbHelper;
    public	ProfileDataSource(Context context)	{
        dbHelper	=	new MyDBHandlerProfile(context);
    }
    public	void	open()	throws SQLException {
        database	=	dbHelper.getWritableDatabase();
    }
    public	void	addUser(User user)	throws SQLException {
        dbHelper.addHandler(user);
    }
    public	void	close()	{
        database.close();
    }

    public String loadName ( User user){

        return dbHelper.loadNameHandler(user);
    }
    public String loadSurname ( User user){

        return dbHelper.loadSurnameHandler(user);
    }
    public void probaa ( ){

        // dbHelper.proba();
    }
    public String loadAddress ( User user){

        return dbHelper.loadAddressHandler(user);
    }
    public String loadEmail ( User user){

        return dbHelper.loadEmailHandler(user);
    }
    public String loadOIB ( User user){

        return dbHelper.loadOIBHandler(user);
    }
    public String loadID ( User user){

        return dbHelper.loadIDHandler(user);
    }
    public String loadPassport( User user){

        return dbHelper.loadPassportHandler(user);
    }
}
