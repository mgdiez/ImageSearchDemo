package com.marcgdiez.imagesearchdemo.app.historic;

import com.marcgdiez.imagesearchdemo.core.view.IView;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;

public interface SearchHistoricView extends IView {
  void showData(List<HistoricEntity> historicEntities);

  void showNoData();
}
