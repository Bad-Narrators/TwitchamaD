package com.badnarrators.twitchingear.polls.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.badnarrators.twitchingear.polls.entity.Poll;
import com.badnarrators.twitchingear.polls.model.PollResponse;
import com.badnarrators.twitchingear.polls.repository.PollRepository;

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
    
    private static AnnotationConfigApplicationContext context;

    
    /** 
     * @return AnnotationConfigApplicationContext
     */
    public static AnnotationConfigApplicationContext getContext(){
        if(PollController.context == null){
            PollController.context = new AnnotationConfigApplicationContext();
            PollController.context.scan("com.badnarrators.twitchingear.polls.repository");
            PollController.context.refresh();
        }
        return PollController.context;
    }
    
    
    /** 
     * @return List<Poll>
     */
    @GetMapping(value="/getRepository")
    public List<Poll> getRepository() {
        return context.getBean(PollRepository.class).getRepository();
    }

    
    /** 
     * @param repo
     * @return PollResponse
     */
    @RequestMapping(method=RequestMethod.POST, value="/setRepository")
    public PollResponse setRepository(@RequestBody List<Poll> repo){
        context.getBean(PollRepository.class).setRepository(repo);
        return new PollResponse("1", "Successful");
    }
}
