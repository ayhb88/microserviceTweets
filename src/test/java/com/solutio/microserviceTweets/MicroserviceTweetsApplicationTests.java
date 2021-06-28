package com.solutio.microserviceTweets;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import com.solutio.microserviceTweets.dto.Tweet;
import com.solutio.microserviceTweets.service.impl.TwitterServiceImpl;

import twitter4j.TwitterException;

@SpringBootTest
class MicroserviceTweetsApplicationTests {

	@Autowired
	TwitterServiceImpl twitterServiceImpl;

	@Test
	@GetMapping("/tweets")
	public void getTweets() {
		List<Tweet> tweets;
		try {
			tweets = twitterServiceImpl.getTweets();

			for (Tweet tweet : tweets) {
				System.out.println("user: " + tweet.getUser() + 
								   " tweet: " + tweet.getText() + 
								   " location: "+ tweet.getLocation());
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@GetMapping("/trends")
	public void getTrends() {
		List<String> trends;
		try {
			trends = twitterServiceImpl.getTrends(10);

			for (String trend : trends) {
				System.out.println(trend);
			}

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
