package com.example.mydiary_01.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.mydiary_01.Database.ProfileDataSource;
import com.example.mydiary_01.NodesClasses.User;
import com.example.mydiary_01.R;

import static android.widget.Toast.makeText;

public class ProfileFragment extends Fragment {

    public TextView name;
    public TextView surname;
    public TextView address;
    public TextView email;
    public TextView oib;
    public TextView passport;
    public TextView id;
    Button edit;
    User user = new User();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        name = view.findViewById(R.id.nameView);
        surname = view.findViewById(R.id.surnameView);
        address = view.findViewById(R.id.addressView);
        email = view.findViewById(R.id.mailView);
        oib = view.findViewById(R.id.oibView);
        passport = view.findViewById(R.id.passportView);
        ProfileDataSource db = new ProfileDataSource(getActivity());
        db.open();
        final ProfileViewModel model = ViewModelProviders.of(this).get(ProfileViewModel.class);
       // loadUser(view);



      // name.setText(user.getUserName());


       final LiveData<User> Profile = model.getItem(user,getActivity());
       // user = model.get(getActivity(),user);
        Profile.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User s) {

                name.setText(s.getUserName());
                surname.setText(s.getUserSurname());
                address.setText(s.getUserAddress());
                email.setText(s.getUserEmail());
                oib.setText(s.getUserOib());
                passport.setText(s.getUserPassport());

            }
        });


        return view;
    }

  /*  public void loadUser(View view){
        ProfileDataSource db = new ProfileDataSource(this.getActivity());
        db.open();
        User user = new User();
     name = view.findViewById(R.id.nameView);
     name.setText(db.loadName(user));
        surname = view.findViewById(R.id.surnameView);
        surname.setText(db.loadSurname(user));
        address = view.findViewById(R.id.addressView);
        address.setText(db.loadAddress(user));
        email = view.findViewById(R.id.mailView);
        email.setText(db.loadEmail(user));
        oib = view.findViewById(R.id.oibView);
        oib.setText(db.loadOIB(user));
        passport = view.findViewById(R.id.passportView);
        passport.setText(db.loadPassport(user));

    }*/


}
