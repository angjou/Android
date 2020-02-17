package com.example.mydiary_01.Story;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mydiary_01.Database.ProfileDataSource;
import com.example.mydiary_01.Database.StoryDataSource;
import com.example.mydiary_01.MainActivity;
import com.example.mydiary_01.NodesClasses.Story;
import com.example.mydiary_01.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class StoryEditFragment extends Fragment {

    public Button addImg;
    public Button save;
    public EditText text;
    public EditText title;
    public ImageView img;
    public Bitmap bitmap;
    private static  final int SELECT_PHOTO = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.storyeddit_window, container, false);

        addImg = view.findViewById(R.id.addImg);
        save = view.findViewById(R.id.saveStory);
        text = view.findViewById(R.id.inputStory);
        title = view.findViewById(R.id.editTitle);
        img = view.findViewById(R.id.imgView);
        addImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getImageFromAlbum();

            }
                                  });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Story s = new Story();
               if (img.getDrawable() == null)
               {
                   Toast.makeText(getActivity(), "Set image", Toast.LENGTH_SHORT).show();

               }else
                   {
                s.setStory(text.getText().toString());
                s.setStoryTitle(title.getText().toString());
                bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                s.setStoryImage(getBytes(bitmap));
                if ( new StoryDataSource(getActivity()).addStory(s))
                {
                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }}
        });
        return view;
    }

    private void getImageFromAlbum(){
        try{
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_PHOTO);
        }catch(Exception exp){
            Log.i("Error",exp.toString());
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            {
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }

            }
    }

    public static byte[] getBytes(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if( image.getAllocationByteCount() > 2621440 ) {
            image.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        }else{
            image.compress(Bitmap.CompressFormat.PNG, 0, stream);
        }
        return stream.toByteArray();
    }
}
