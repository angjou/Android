package com.example.mydiary_01.Story;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiary_01.Database.MyDBHandlerProfile;
import com.example.mydiary_01.NodesClasses.Story;
import com.example.mydiary_01.R;

import java.io.ByteArrayInputStream;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<Story> mStory;
    private MyDBHandlerProfile dbHelper ;
    Context context;
        private static ClickListener clickListener;
        // access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
            CardView cardView;
            TextView title;
            ImageView image;
            Button delete;
            public MyViewHolder(View v) {
                super(v);
                v.setOnClickListener(this);
                v.setOnLongClickListener(this);
                cardView =  v.findViewById(R.id.cardview);
                title =  v.findViewById(R.id.cardTitle);
                image =  v.findViewById(R.id.cardImg);
                delete  = v.findViewById(R.id.btn_deleete);
                delete.setOnClickListener(this);
            }
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(getAdapterPosition(), v);
            }

            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        }

         public void setOnItemClickListener(ClickListener clickListener) {
                MyAdapter.clickListener = clickListener;
         }
         public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
         }
        //  constructor
        public MyAdapter(List<Story> storyList, Context context) {
            this.mStory = storyList;
            this.context = context;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_card, parent, false);

            MyViewHolder vh = new MyViewHolder(v);

            return vh;
        }



        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            // - get element from  dataset at this position
            // - replace the contents of the view with that element
            dbHelper =  new MyDBHandlerProfile(context);
            Bitmap bitmap;
            holder.title.setText(mStory.get(position).getStoryTitle());
            bitmap = ByteArrayToBitmap( mStory.get(position).getStoryImage());
            holder.image.setImageBitmap(bitmap);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.delete(mStory.get(position));
                    mStory.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), mStory.size());
                }
            });

        }
    public Bitmap ByteArrayToBitmap(byte[] byteArray)
    {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }


        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mStory.size();
        }
    }

