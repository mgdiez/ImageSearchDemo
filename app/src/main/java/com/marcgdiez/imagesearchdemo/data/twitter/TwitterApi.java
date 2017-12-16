package com.marcgdiez.imagesearchdemo.data.twitter;

import java.util.List;
import javax.inject.Inject;
import rx.Emitter;
import rx.Observable;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApi {

  private boolean isCancelled;

  @Inject public TwitterApi() {
  }

  public Observable<String> search(String query) {

    return Observable.create(emitter -> {

      //This API Key will be valid until 31-12-2017 then it will be revoked.
      ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true)
          .setOAuthConsumerKey("d67lAWyyPx9pwY8VE3VvOIYyR")
          .setOAuthConsumerSecret("lEpl4vY1aO3KKldkGvbX0twg198bqC1GnXuRu53SZ1VfqkMxFH")
          .setOAuthAccessToken("3073689363-2ODpTFyYbnqSn2wOV0piZTuUYCVK6gwSTyg1feJ")
          .setOAuthAccessTokenSecret("BBMLaF212KWFEstasvWFCUM2jUaBpYOiz4QhCRwlNtEFp");

      TwitterFactory tf = new TwitterFactory(cb.build());
      Twitter twitter = tf.getInstance();

      try {
        Query searchQuery = new Query(query);
        QueryResult result;
        isCancelled = false;
        do {
          result = twitter.search(searchQuery);
          List<Status> tweets = result.getTweets();
          for (Status tweet : tweets) {
            MediaEntity[] mediaEntities = tweet.getMediaEntities();
            if (mediaEntities != null) {
              boolean hasPhoto = false;
              int i = 0;
              while (!hasPhoto && i < mediaEntities.length) {
                MediaEntity mediaEntity = mediaEntities[i];
                if (mediaEntity.getType().equals("photo")) {
                  hasPhoto = true;
                  emitter.onNext(mediaEntity.getMediaURL());
                }
                i++;
              }
            }
          }
        } while (!isCancelled && (searchQuery = result.nextQuery()) != null);
      } catch (Exception e) {
        emitter.onError(e);
      }
      emitter.onCompleted();
      emitter.setCancellation(() -> isCancelled = true);
    }, Emitter.BackpressureMode.BUFFER);
  }
}
