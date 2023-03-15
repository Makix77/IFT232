package labo5.followers;

import labo5.Labo5Main;
import labo5.Util;

public class Troll extends Reposter{

    private String[] dict;
    private String reply;
    public Troll(Labo5Main ui, String n, String[] dictionnary, String reply) {
        super(ui, n);
        dict = dictionnary;
        this.reply = reply;
    }

    @Override
    public void update(String message, String celebName) {
        if(Util.contains(message, dict)) {
            viewport.appendMessage(Util.personnalize(celebName, reply));
        }
    }
}
