package com.badnarrators.twitchingear.polls.repository;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.badnarrators.twitchingear.common.repository.AbstractRepository;
import com.badnarrators.twitchingear.polls.entity.Poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PollRepository extends AbstractRepository<Poll>{

 /*    public PollRepository(){
        this.repository = new LinkedList<Poll>();
    } */

    public void resetRepository(){
        this.repository = new LinkedList<Poll>();
    }

    public void setRepository(List<Poll> repository){
        this.repository = repository;
    }
    public List<Poll> getRepository(){
        return this.repository;
    }

}
