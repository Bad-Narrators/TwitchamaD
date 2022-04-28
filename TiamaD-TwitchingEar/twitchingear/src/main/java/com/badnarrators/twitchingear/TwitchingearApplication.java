package com.badnarrators.twitchingear;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badnarrators.twitchingear.polls.controller.PollController;
import com.badnarrators.twitchingear.polls.entity.Poll;
import com.badnarrators.twitchingear.polls.entity.PollVote;
import com.badnarrators.twitchingear.polls.repository.PollRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TwitchingearApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitchingearApplication.class, args);

		Map<Integer, PollVote> m = new LinkedHashMap<Integer, PollVote>();
		m.put(1, new PollVote("Vecchia Londra"));
		m.put(2, new PollVote("Messicano"));
		m.get(2).vote();
		List<String> t = new ArrayList<String>();
		t.add("Twitch");
		Poll p = new Poll("Vecchia londra o messicano?", m, t);
		PollController.getRepository().getRepository().add(p);
	}

}
