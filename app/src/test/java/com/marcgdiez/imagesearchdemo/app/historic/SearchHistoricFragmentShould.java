package com.marcgdiez.imagesearchdemo.app.historic;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.MainActivity;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import com.marcgdiez.imagesearchdemo.rule.RobolectricMockComponentRule;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class) public class SearchHistoricFragmentShould {
  @Rule public RobolectricMockComponentRule rule = new RobolectricMockComponentRule();

  @Mock SearchHistoricPresenter mockPresenter;

  @Test public void set_view_to_presenter() {
    SearchHistoricFragment fragment = new SearchHistoricFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    verify(mockPresenter).setView(eq(fragment));
  }

  @Test public void show_empty_state() {
    SearchHistoricFragment fragment = new SearchHistoricFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    fragment.showNoData();

    TextView feedbackTextView = fragment.getView().findViewById(R.id.feedback);
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedbackTextView.getText().toString(), is(equalTo("No historic data found")));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void show_images_correctly() {
    List<HistoricEntity> historicEntities = new ArrayList<>();
    historicEntities.add(new HistoricEntity("FooQuery", 1));
    historicEntities.add(new HistoricEntity("FooQuery2", 15));

    SearchHistoricFragment fragment = new SearchHistoricFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    fragment.showData(historicEntities);

    RecyclerView recyclerView = fragment.getView().findViewById(R.id.recyclerView);
    TextView feedback = fragment.getView().findViewById(R.id.feedback);
    assertThat(recyclerView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedback.getVisibility(), is(equalTo(View.GONE)));
    assertThat(recyclerView.getAdapter().getItemCount(), is(equalTo(2)));
  }
}