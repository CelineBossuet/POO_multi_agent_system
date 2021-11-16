package events;

public abstract class Event {
    private long date;
    private static EventManager manager;

    /**
     * Classe qui permet de gérer les évenements
     * @param date
     */
    public Event(long date){
        this.date = date;
    }

    /**
     * @return la date
     */
    public long getDate(){ return this.date; }

    /**
     * @param date
     */
    public void setDate(long date){ this.date = date; }

    /**
     * @param newManager
     */
    public static void setManager(EventManager newManager) {
        manager = newManager;
    }

    /**
     * @return manager
     */
    public static EventManager getManager() {
        return manager;
    }

    /**
     * Fonction qui execute
     */
    public abstract void execute();

    /**
     * Fonction qui permet de reset un event
     */
    public abstract void reset();
}
