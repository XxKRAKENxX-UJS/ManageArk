package com.example.manageark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterClass extends
        SliderViewAdapter<SliderAdapterClass.Holder> {

    int[] images;

    public SliderAdapterClass(int[] images){
        this.images=images;

    }

    @Override
    public SliderAdapterClass.Holder onCreateViewHolder(ViewGroup parent) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterClass.Holder viewHolder, int position) {
    viewHolder.imageView.setImageResource( images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
