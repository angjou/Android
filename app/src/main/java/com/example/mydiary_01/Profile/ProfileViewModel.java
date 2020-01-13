package com.example.mydiary_01.Profile;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {


private MutableLiveData<User> selected = new MutableLiveData<>();
   /* public void setViewItem(String item) {
        selected.setValue(item);
    }*/

    public MutableLiveData<User> getItem(User user) {
     /*   ProfileDataSource db = new ProfileDataSource(context);
        db.open();
        db.loadName(user);

      */
        user.setUserName("Andela");
        user.setUserSurname("Vitturi");
        selected.setValue(user);

        return selected;
    }

    public void setItem(String name,String surname, String address,String email,String oib,String pass,String id, Context context) {
       ProfileDataSource db = new ProfileDataSource(context);
        db.open();

        //int i = Integer.parseInt(id);
       // User user = new User();
       // user.setUser(i,name,surname,address,email,oib,pass);


    }
    public void set( Context context) {
        MyDBHandlerProfile db =  new MyDBHandlerProfile(context);
        db.getWritableDatabase();

        //int i = Integer.parseInt(id);
        // User user = new User();
        // user.setUser(i,name,surname,address,email,oib,pass);
        //db.proba();

    }


    void SaveUserToDB(User user, Context context) {
        ProfileDataSource db = new ProfileDataSource(context);
        db.open();
        db.addUser(user);
        db.close();
    }


}
