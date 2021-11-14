package events;

import gui.Simulable;

import java.util.ArrayList;

public class EventManager implements Simulable {
    private long currentDate;
    private ArrayList<Event> events;

    public EventManager(){
        this.currentDate = 0;
        this.events = new ArrayList<Event>();
        Event.setManager(this);
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public void next(){
        // On incrémente la date
        this.currentDate++;

        if(isFinished()){return;}

        for(Event event: this.events){
            if(event.getDate() <= this.currentDate){
                event.execute();
                this.events.remove(event);
            }
        }
    }

    public boolean isFinished(){
        return this.events.isEmpty();
    }

    public void restart(){
        this.currentDate = 0;
        for(Event event: this.events){
            event.reset();
        }
    }

}
