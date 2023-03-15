package labo5.followers;

import labo5.Labo5Main;
import labo5.Util;
import labo5.ui.MessageView;

public class SerialReposter implements Follower{
    protected MessageView viewport;
    private String name;
    public SerialReposter(Labo5Main ui, String n){
        name = n;
        viewport = new MessageView(ui, name+" news feed");
    }
    @Override
    public void update(String message, String celebName) {
        viewport.appendMessage(Util.personnalize(celebName, message));
    }
}
