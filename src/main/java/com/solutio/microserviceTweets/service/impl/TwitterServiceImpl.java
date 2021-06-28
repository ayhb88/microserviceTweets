/**
 * 
 */
package com.solutio.microserviceTweets.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.solutio.microserviceTweets.dto.Tweet;
import com.solutio.microserviceTweets.service.TwitterService;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Yuto
 *
 */
@Service
public class TwitterServiceImpl implements TwitterService {

	private static int followers = 1500;
	private static String es = "es";
	private static String fr = "fr";
	private static String it = "it";

	@Override
	public Twitter getInstance() throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("4UfjGdfd5LUCbRIMqCbDafhGh")
				.setOAuthConsumerSecret("MYCU9mp35LrRyu01BKbAcDoJUupVb1WlSZlGQDSQXeOIDNwmbc")
				.setOAuthAccessToken("147012412-k3cvCtJ4fCVR4pjSmq7py4c4AfDLq4WIYZ0q5uaq")
				.setOAuthAccessTokenSecret("0um2ptmhMlS3flfgHj2ma9IXOrmkOUif07Yms2wVj6MvM");

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		return twitter;
	}

	@Override
	public List<Tweet> getTweets() throws TwitterException {

		List<Tweet> tweets = new ArrayList<Tweet>();
		
		//Retrieving tweets from french language
		retrieveTweets(fr, tweets);
		//Retrieving tweets from italian language
		retrieveTweets(it, tweets);
		//Retrieving tweets from spanish language
		retrieveTweets(es, tweets);

		return tweets;
	}

	public List<String> getTrends(int top) throws TwitterException {

		List<String> trends = new ArrayList<String>();

		Trends list = getInstance().getPlaceTrends(1);

		for (int i = 0; i < top; i++) {
			Trend trend = list.getTrends()[i];
			trends.add(trend.getName());
		}

		return trends;

	}

	public void retrieveTweets(String lang, List<Tweet> tweets) throws TwitterException {
		Twitter twitter = getInstance();
		Query query = new Query("a");
		query.setCount(100);
		query.setLang(lang);
		QueryResult resultFr = twitter.search(query);

		/* user with more than 1500 followers */
		List<Status> statusTweetsFr = resultFr.getTweets().stream()
				.filter(item -> item.getUser().getFollowersCount() >= followers).collect(Collectors.toList());

		for (Status status : statusTweetsFr) {
			Tweet tweet = new Tweet();
			tweet.setUser(status.getUser().getScreenName());
			tweet.setText(status.getText());
			tweet.setLocation(status.getUser().getLocation());
			tweets.add(tweet);
		}
	}

}
