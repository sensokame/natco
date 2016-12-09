package com.example.houcem.natco.data;

/**
 * Created by houcem on 08/12/16.
 */

public class Sessions {
    private String time;
    private Session[] sessions;
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public Sessions(String time, Session[] sessions) {
        this.time = time;
        this.sessions = sessions;
    }

    public void setSessions(Session[] sessions) {
        this.sessions = sessions;
    }

    public Session[] getSessions() {

        return sessions;
    }
}
