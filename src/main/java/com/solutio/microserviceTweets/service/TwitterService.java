package com.solutio.microserviceTweets.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solutio.microserviceTweets.dto.Tweet;

import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public interface TwitterService {
	
	Twitter getInstance() throws TwitterException;
	
	List<Tweet> getTweets () throws TwitterException;
	
	List<String> getTrends(int top) throws TwitterException;

}

