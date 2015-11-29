package edu.sjsu.cmpe277.termproject.drawer;

/**
 * Created by emy on 11/27/15.
 */
public class ObjectDrawer {

    private int icon;
    private String name;

    public ObjectDrawer(int icon, String name){
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

