package com.example.mydiary_01.Story;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydiary_01.Profile.User;


public class StoryViewModel extends ViewModel {

    private final MutableLiveData<Story> selected = new MutableLiveData<Story>();
    public MutableLiveData<Story> setStory(Story story) {

        story.setStory("Andela");
        story.setStoryImg("Vitturi");
        story.setStoryID(1);
        selected.setValue(story);

        return selected;
    }
    public void getStory (String s,String img, int id){
        Story story = new Story();
        story.setStory(s);
        story.setStoryImg(img);
        story.setStoryID(id);
    }

}
