package com.marcgdiez.imagesearchdemo.app.gallery.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

  public static final int NUMBER_OF_COLUMNS = 3;

  private List<ImageEntity> items;
  private int imageSize;

  @Inject public ImageAdapter(Context context) {
    items = new ArrayList<>();
    setHasStableIds(true);

    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    imageSize = size.x / NUMBER_OF_COLUMNS;
  }

  @Override public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
    return new ImageViewHolder(view);
  }

  @Override public void onBindViewHolder(ImageViewHolder holder, int position) {
    ImageEntity imageEntity = items.get(position);
    Context context = holder.itemView.getContext();

    ViewGroup.LayoutParams layoutParams = holder.image.getLayoutParams();
    layoutParams.width = imageSize;
    layoutParams.height = imageSize;
    holder.image.setLayoutParams(layoutParams);

    Picasso.with(context)
        .load(imageEntity.getUrl())
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(holder.image);
  }

  @Override public int getItemCount() {
    return items != null ? items.size() : 0;
  }

  public void setItems(List<ImageEntity> items) {
    this.items.addAll(items);
    notifyDataSetChanged();
  }

  static class ImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image) ImageView image;

    public ImageViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
