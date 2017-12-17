package com.marcgdiez.imagesearchdemo.app.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.MainActivity;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import com.marcgdiez.imagesearchdemo.rule.RobolectricMockComponentRule;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class) public class SearchGalleryFragmentShould {

  @Rule public RobolectricMockComponentRule rule = new RobolectricMockComponentRule();
  @Mock private SearchGalleryPresenter mockPresenter;

  @Test public void set_view_to_presenter() {
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    verify(mockPresenter).setView(eq(fragment));
  }

  @Test public void show_images_correctly() {
    List<ImageEntity> imageEntityList = createFakeImages();
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);
    fragment.showData(imageEntityList);

    View progressView = fragment.getView().findViewById(R.id.progressBar);
    RecyclerView recyclerView = fragment.getView().findViewById(R.id.recyclerView);
    TextView feedback = fragment.getView().findViewById(R.id.feedback);

    assertThat(progressView.getVisibility(), is(equalTo(View.GONE)));
    assertThat(recyclerView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedback.getVisibility(), is(equalTo(View.GONE)));
    assertThat(recyclerView.getAdapter().getItemCount(), is(equalTo(3)));
  }

  @Test public void contain_two_menu_item() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    Menu optionsMenu = shadowOf(mainActivity).getOptionsMenu();
    assertThat(optionsMenu, notNullValue());
    assertThat(optionsMenu.size(), is(equalTo(2)));
  }

  @Test public void show_search_and_history_menu() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    Menu optionsMenu = shadowOf(mainActivity).getOptionsMenu();
    MenuItem searchItem = optionsMenu.findItem(R.id.search);
    MenuItem historyItem = optionsMenu.findItem(R.id.historic);

    assertThat(searchItem, notNullValue());
    assertThat(searchItem.getTitle().toString(), is(equalTo("Search")));
    assertThat(historyItem, notNullValue());
    assertThat(historyItem.getTitle().toString(), is(equalTo("Historic")));
  }

  @Test public void show_empty_state() {
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    fragment.showEmptyState();

    TextView feedbackTextView = fragment.getView().findViewById(R.id.feedback);
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedbackTextView.getText().toString(),
        is(equalTo("Start searching some awesome images")));
    assertThat(fragment.getView().findViewById(R.id.progressBar).getVisibility(),
        is(equalTo(View.GONE)));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void show_progress() {
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    fragment.showProgress();

    assertThat(fragment.getView().findViewById(R.id.progressBar).getVisibility(),
        is(equalTo(View.VISIBLE)));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
    assertThat(fragment.getView().findViewById(R.id.feedback).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void hide_progress() {
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    fragment.hideProgress();

    assertThat(fragment.getView().findViewById(R.id.progressBar).getVisibility(),
        is(equalTo(View.GONE)));
    assertThat(fragment.getView().findViewById(R.id.feedback).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void show_error() {
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    fragment.showError();

    assertThat(fragment.getView().findViewById(R.id.progressBar).getVisibility(),
        is(equalTo(View.GONE)));
    TextView feedbackTextView = fragment.getView().findViewById(R.id.feedback);
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedbackTextView.getText().toString(), is(equalTo("Something went wrong :(")));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void show_no_results() {
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    fragment.showNoResults();

    assertThat(fragment.getView().findViewById(R.id.progressBar).getVisibility(),
        is(equalTo(View.GONE)));
    TextView feedbackTextView = fragment.getView().findViewById(R.id.feedback);
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedbackTextView.getText().toString(), is(equalTo("No results found")));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void tell_presenter_historic_clicked() {
    SearchGalleryFragment fragment = new SearchGalleryFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class,
        R.id.fragment_container);

    Menu optionsMenu = shadowOf(fragment.getActivity()).getOptionsMenu();
    MenuItem historyItem = optionsMenu.findItem(R.id.historic);
    fragment.onOptionsItemSelected(historyItem);

    verify(mockPresenter).onHistoricClicked();
  }

  private List<ImageEntity> createFakeImages() {
    List<ImageEntity> imageEntities = new ArrayList<>();
    imageEntities.add(new ImageEntity("fakeUrl1"));
    imageEntities.add(new ImageEntity("fakeUrl2"));
    imageEntities.add(new ImageEntity("fakeUrl3"));
    return imageEntities;
  }
}