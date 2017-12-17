package com.marcgdiez.imagesearchdemo.app.historic.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class HistoricAdapter extends RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder> {

  private List<HistoricEntity> items;

  @Inject public HistoricAdapter() {
    items = new ArrayList<>();
    setHasStableIds(true);
  }

  @Override public HistoricViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historic, parent, false);
    return new HistoricAdapter.HistoricViewHolder(view);
  }

  @Override public void onBindViewHolder(HistoricViewHolder holder, int position) {
    HistoricEntity historicEntity = items.get(position);
    Context context = holder.itemView.getContext();

    Resources res = context.getResources();
    String text = String.format(res.getString(R.string.historic_result), historicEntity.getQuery(),
        historicEntity.getnResults());
    CharSequence styledText = Html.fromHtml(text);
    holder.text.setText(styledText);
  }

  @Override public int getItemCount() {
    return items != null ? items.size() : 0;
  }

  public void setItems(List<HistoricEntity> items) {
    this.items = items;
    notifyDataSetChanged();
  }

  static class HistoricViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text) TextView text;

    public HistoricViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
