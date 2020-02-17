package com.example.mydiary_01.Story;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiary_01.Database.StoryDataSource;
import com.example.mydiary_01.NodesClasses.Story;
import com.example.mydiary_01.R;
import com.example.mydiary_01.StoryActivity;

import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    MyAdapter Adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Story> mStory;
    StoryDataSource dataSource = new StoryDataSource(getActivity());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.story_fragment, container, false);

        recyclerView = root.findViewById(R.id.stories_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        mStory = new ArrayList<>();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        viewData();
        Adapter = new MyAdapter(mStory,getActivity());
        Adapter.setOnItemClickListener(new MyAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d("click", "onItemClick position: " + position);
                Story story = new Story() ;
                story = mStory.get(position);
                int id = story.getStoryID();
                Intent intent = new Intent(getActivity(), StoryActivity.class);
                intent.putExtra("storyId",id);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d("click", "onItemLongClick pos = " + position);
            }
        });
        return root;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        dataSource = new StoryDataSource(context);

    }

    private void viewData(){
        Cursor cursor = dataSource.initializeData();

        int id;
        String text;
        String title;
        byte [] img;

        if ( cursor.getCount() >= 0 && cursor.moveToFirst()) {
            do{

                id = cursor.getInt(0);
                text = cursor.getString(1);
                img = cursor.getBlob(2);
                title = cursor.getString(3);
                mStory.add(new Story(text,title,id,img));
            }while(cursor.moveToNext());
            mAdapter = new MyAdapter(mStory,getActivity());
            recyclerView.setAdapter(mAdapter);

        }
        else{
            Toast.makeText(this.getActivity(),"Empty database", Toast.LENGTH_SHORT).show();

        }
    cursor.close();
    }


}

