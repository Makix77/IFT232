package labo5.followers;

import labo5.Labo5Main;
import labo5.Util;
import labo5.celebrities.SocialMediaCelebrity;
import labo5.ui.MessageView;

public class Hater extends SocialMediaCelebrity implements Follower {
    protected MessageView viewport;

    public Hater(Labo5Main ui, String n, int wait) {
        super(n, wait);
        viewport = new MessageView(ui,name+" news feed");
    }

    @Override
    public void changeStatus() {
        setStatus(status);
    }

    @Override
    public void update(String message, String celebName) {
        status = Util.personnalize(celebName, Util.getRandomString(Util.HATER_DICTIONNARY));
        viewport.appendMessage(status);
        changeStatus();
    }
}
