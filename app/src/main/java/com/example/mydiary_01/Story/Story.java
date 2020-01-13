package com.example.mydiary_01.Story;

public class Story {

    private String story;
    private String imgSrc;
    private int ID;

    public void Story(){}
    public void Story (String s, String I, int id){
        this.story= s;
        this.imgSrc = I;
        this.ID = id;
    }
    public void setStory(String story) {
        this.story = story;
    }
    public String getStory(){
        return this.story;
    }
    public void setStoryImg(String img) {
        this.imgSrc = img;
    }
    public String getStoryImg(){
        return this.imgSrc;
    }
    public void setStoryID(int id) {
        this.ID = id;
    }
    public int getStoryID(){
        return this.ID;
    }
}
