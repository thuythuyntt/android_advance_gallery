package ntt.thuy.practice.framgia.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuy on 16/07/2018.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mArray;

    public ImageAdapter(List<String> imageArray, Context context) {
        this.mArray = imageArray;
        this.mContext = context;
    }

    public void setList(ArrayList<String> images) {
        this.mArray.addAll(images);
        Log.d("TEST", mArray.size()+"");
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindData(mArray.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("TEST", mArray.size()+"");
        return mArray == null ? 0 : mArray.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageHero;

        private MyViewHolder(View itemView) {
            super(itemView);
            mImageHero = itemView.findViewById(R.id.image_item);
        }

        private void bindData(String imagePath) {
            Glide.with(mContext)
                    .load(new File(imagePath))
                    .into(mImageHero);
        }
    }
}
