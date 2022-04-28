package com.badnarrators.twitchingear.polls.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.badnarrators.twitchingear.polls.entity.Poll;
import com.badnarrators.twitchingear.polls.model.PollResponse;
import com.badnarrators.twitchingear.polls.repository.PollRepository;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/twitch/api/poll")
public class PollController {
    
    private static PollRepository repository;

    private static Logger LOGGER = LogManager.getLogger(PollController.class);
    
    @Autowired
    public PollController(PollRepository repo){
        PollController.repository = repo;
    }
    
    /** 
     * @return List<Poll>
     */
    @GetMapping(value="/getPollList")
    public List<Poll> getPollList() {
        return repository.getRepository();
    }

    public static PollRepository getRepository() {
        return repository;
    }

    
    /** 
     * @param repo
     * @return PollResponse
     */
    @RequestMapping(method=RequestMethod.POST, value="/setRepository")
    public PollResponse setRepository(@RequestBody List<Poll> repo){
        repository.setRepository(repo);
        return new PollResponse("1", "Successful", null, null);
    }

    @RequestMapping(method=RequestMethod.GET, value="/getPollString")
    public PollResponse getPollString(@RequestBody String pollString){
        Poll poll = searchPollByString(pollString);
        PollResponse resp;

        if(poll == null){
            resp  = new PollResponse();
        }else {
            resp = new PollResponse(poll);
        }

        return resp;
    }

    private Poll searchPollByString(String s){
        try{
            UUID id = UUID.fromString(s);
            for(Poll p : repository.getRepository()){
                if(id.equals(p.getId())){
                    return p;
                }
            }

            LOGGER.warn("Value "+s+" not found in PollRepository.");
            return null;

        }catch(IllegalArgumentException e) {
            LOGGER.warn("Request '/twitch/api/poll/getPollString' sent with an invalid UUID, automatically checking if it's available as a question instead.");
            
            for(Poll p : repository.getRepository()){
                if(s.equals(p.getQuestion())){
                    return p;
                }
            }

            LOGGER.warn("Value "+s+" not found in PollRepository.");
            return null;
        }

    }

    
}
