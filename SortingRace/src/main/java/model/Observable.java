package model;

import view.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private final List<Observer> obs;

    public Observable() {
        obs = new ArrayList<>();
    }

    /**
     * Allow to add a observer to a list of observer of the model.
     *
     * @param ob the observer.
     */
    void subscribe(Observer ob){
        if(!obs.contains(ob))
        obs.add(ob);
    }

    /**
     * Allow to remove a observer to a list of observer of the model.
     *
     * @param ob the observer.
     */
    void unsubscribe(Observer ob){
        if(obs.contains(ob))
            obs.remove(ob);
    }

    /**
     * Notifies the observer that there is a change of state and that an update should be made to the view.
     *
     */
    void notifyObs(){
        for(Observer ob : obs){
            ob.update(this);
        }
    }
}
