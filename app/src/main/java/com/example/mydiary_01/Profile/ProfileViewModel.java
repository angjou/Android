package com.example.mydiary_01.Profile;

import android.content.Context;
import android.database.SQLException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydiary_01.Database.MyDBHandlerProfile;
import com.example.mydiary_01.Database.ProfileDataSource;
import com.example.mydiary_01.NodesClasses.User;
import com.example.mydiary_01.R;

public class ProfileViewModel extends ViewModel {


private MutableLiveData<User> selected = new MutableLiveData<>();
    public EditText name;
    public EditText surname;
    public EditText address;
    public EditText email;
    public EditText oib;
    public EditText passport;

   /* public void setViewItem(String item) {
        selected.setValue(item);
    }*/

    public MutableLiveData<User> getItem(User user, Context context) {
        ProfileDataSource db = new ProfileDataSource(context);
        db.open();
        db.loadName(user);



        selected.setValue( db.loadUser(user));

        return selected;
    }

    public User setItem(String name,String surname, String address,String email,String oib,String pass) {


        User user = new User();
       user.setUser(name,surname,address,email,oib,pass);

       return user;
    }
    public boolean set(Context context, User user) {
        try {
            new ProfileDataSource(context).addUser(user);
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

     return false;

    }

    public void get(View view, Context context){
        ProfileDataSource db = new ProfileDataSource(context);
        db.open();
        User user = new User();
        name = view.findViewById(R.id.nameEdit);
        name.setText(db.loadName(user),TextView.BufferType.EDITABLE);
        surname = view.findViewById(R.id.surnameEdit);
        db.close();
    }



}
