package com.example.mydiary_01.Profile;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mydiary_01.MainActivity;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.profiledit_fragment, container, false);

        save = view.findViewById(R.id.saveButton);
        id = view.findViewById(R.id.idEdit);
        passport = view.findViewById(R.id.passportEdit);
        oib = view.findViewById(R.id.oibEdit);
        email = view.findViewById(R.id.mailEdit);
        address = view.findViewById(R.id.addressEdit);
        surname = view.findViewById(R.id.surnameEdit);
        name = view.findViewById(R.id.nameEdit);

        Name = name.getText().toString();

        Toast.makeText(this.getActivity(),"Name: ", LENGTH_SHORT).show();
        /*ProfileViewModel model = ViewModelProviders.of(this).get(ProfileViewModel.class);
        final LiveData<User> Profile = model.setItem(Name,Surname,Address, Emil,oib,pass,id);
        Profile.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User s) {

                s.setUserName(name.getText().toString());
                s.setUserSurname(surname.getText().toString());
            }
        });

*/
        final ProfileViewModel model = ViewModelProviders.of(this).get(ProfileViewModel.class);
        save.setOnClickListener( new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                        /* Name = name.getText().toString();
                                         Address = address.getText().toString();
                                         Surname = surname.getText().toString();
                                         Email = email.getText().toString();
                                         Oib = oib.getText().toString();
                                         pass =passport.getText().toString();
                                         Id =id.getText().toString();*/
                                         model.set(getActivity());
                                         Toast.makeText(getActivity(),"Button clicked "+Oib ,LENGTH_SHORT).show();
                                     }
                                 }
        );

        return view;
    }

}
