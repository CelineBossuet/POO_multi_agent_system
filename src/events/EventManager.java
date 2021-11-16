package events;

import gui.Simulable;
import java.util.ArrayList;

public class EventManager implements Simulable {
    // La date courante
    private long currentDate;
    // Liste des events
    private ArrayList<Event> events;

    /**
     * Classe qui gère l'event manager
     */
    public EventManager(){
        this.currentDate = 0;
        this.events = new ArrayList<Event>();
        Event.setManager(this);
    }

    /**
     * Ajouter un evenement à la liste
     * @param event
     */
    public void addEvent(Event event){
        this.events.add(event);
    }

    /**
     * La fonction next qui s'execute à chaque pas
     */
    public void next(){
        // On incrémente la date
        this.currentDate++;

        if(isFinished()){
            return;
        }

        // On execute pour chaque event et on le supprime
        for(Event event: this.events){
            if(event.getDate() <= this.currentDate){
                event.execute();
                this.events.remove(event);
            }
        }
    }

    /**
     *
     * @return si tout est fini
     */
    public boolean isFinished(){
        return this.events.isEmpty();
    }

    /**
     * Reset tout
     */
    public void restart(){
        this.currentDate = 0;

        for(Event event: this.events){
            event.reset();
        }
    }

}
