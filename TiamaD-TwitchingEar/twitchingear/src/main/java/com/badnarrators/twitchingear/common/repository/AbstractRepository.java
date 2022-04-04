package com.badnarrators.twitchingear.common.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRepository<T> {
    
    protected List<T> repository;

    public boolean add(T t){
        Object o = repository.add(t);
        if(o instanceof Integer)
            return false;
        else
            return true;
    }
    
    boolean remove(T t){
        Object o = repository.remove(t);
        if(o instanceof Integer)
            return false;
        else
            return true;
    }


    public abstract void setRepository(List<T> repository);

    public abstract List<T> getRepository();
}
