package events;

public abstract class Event {
    private long date;
    private static EventManager manager;

    public Event(long date){
        this.date = date;
    }

    public long getDate(){ return this.date; }

    public void setDate(long date){ this.date = date; }

    public static void setManager(EventManager newManager) {
        manager = newManager;
    }

    public static EventManager getManager() {
        return manager;
    }

    public abstract void execute();

    public abstract void reset();
}
