package com.example.mydiary_01;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mydiary_01.Database.StoryDataSource;
import com.example.mydiary_01.NodesClasses.Story;

public class StoryActivity extends AppCompatActivity {
    Story story = new Story() ;
    Story nStory ;
    StoryDataSource db ;
    Button back;
    TextView title;
    TextView text;
    ImageView img;
    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        title = findViewById(R.id.viewTitle);
        text = findViewById(R.id.viewStory);
        img = findViewById(R.id.imageView);
        back = findViewById(R.id.btnBack);

        int id = getIntent().getIntExtra("storyId",0);

        story = getData(id);

        title.setText(story.getStoryTitle());
        text.setText(story.getStory());
        Bitmap bitmap = BitmapFactory.decodeByteArray(story.getStoryImage(), 0, story.getStoryImage().length);
        img.setImageBitmap(bitmap);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            finish();
           }
       });

    }

    public  Story getData(int id){
        db = new StoryDataSource(getApplicationContext());
        Cursor cursor = db.initializeData();


        int idDB;
        String text;
        String title;
        byte [] img;

        if ( cursor.getCount() >= 0 && cursor.moveToFirst()) {

             do
                 {
                 idDB = cursor.getInt(0);
                 text = cursor.getString(1);
                 img = cursor.getBlob(2);
                 title = cursor.getString(3);
                 cursor.moveToNext();
             } while(idDB != id);
                    nStory = new Story(text,title,idDB,img);


        }
        return nStory;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
