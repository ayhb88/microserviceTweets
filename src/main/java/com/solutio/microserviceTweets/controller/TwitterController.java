/**
 * 
 */
package com.solutio.microserviceTweets.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solutio.microserviceTweets.dto.Tweet;
import com.solutio.microserviceTweets.service.impl.TwitterServiceImpl;

import twitter4j.TwitterException;

/**
 * @author Yuto
 *
 */
@RestController
public class TwitterController {
	
	@Autowired
	TwitterServiceImpl twitterServiceImpl;

	/*
	 * List of tweets
	 * */
	@GetMapping("/tweets")
	public ResponseEntity<List<Tweet>> getTweets() throws TwitterException {
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		tweets = twitterServiceImpl.getTweets();
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(tweets);
	}
	
	/*
	 * 
	 * List of top 10 trends
	 * */
	
	@GetMapping("/trends")
	public ResponseEntity<List<String>> getTrends(@RequestParam(required=false, defaultValue = "10") int top) throws TwitterException {
				
		if(top > 10) {
			return ResponseEntity.badRequest().body(new ArrayList<String>());
		}
				
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(twitterServiceImpl.getTrends(top));
	}
}

