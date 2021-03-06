package com.example.mydiary_01.Profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.mydiary_01.Database.ProfileDataSource;
import com.example.mydiary_01.NodesClasses.User;
import com.example.mydiary_01.R;

import static android.widget.Toast.LENGTH_SHORT;

public class ProfileEditFragment extends Fragment {

    Button save;
    EditText name;
    EditText surname;
    EditText address;
    EditText email;
    EditText oib;
    EditText passport;
    EditText id;
    String Name;
    String Surname;
    String Address;
    String Email;
    String pass;
    String Id;
    String Oib;
    User user = new User();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.profiledit_fragment, container, false);

        save = view.findViewById(R.id.saveButton);
        passport = view.findViewById(R.id.passportEdit);
        oib = view.findViewById(R.id.oibEdit);
        email = view.findViewById(R.id.mailEdit);
        address = view.findViewById(R.id.addressEdit);
        surname = view.findViewById(R.id.surnameEdit);
        name = view.findViewById(R.id.nameEdit);

        final ProfileViewModel model = ViewModelProviders.of(this).get(ProfileViewModel.class);

      if(new ProfileDataSource(getActivity()).isDbEmpty()) {
          Toast.makeText(getActivity(), "Please fill all the info  " , Toast.LENGTH_LONG).show();

      }else {

           model.get(view,getActivity());

      }

        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Name = name.getText().toString();
                                        Surname = surname.getText().toString();
                                        Address = address.getText().toString();
                                        Email = email.getText().toString();
                                        Oib = oib.getText().toString();
                                        pass = passport.getText().toString();

                                        user = model.setItem(Name, Surname, Address, Email, Oib, pass);
                                        model.set(getActivity(), user);
                                        Toast.makeText(getActivity(), "Saved" , LENGTH_SHORT).show();
                                    }
                                }
        );

        return view;
    }

}
