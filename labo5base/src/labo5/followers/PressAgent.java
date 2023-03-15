package labo5.followers;

import labo5.Util;

public class PressAgent implements Follower{
    @Override
    public void update(String message, String celebName) {

    }

    public boolean checkVeto(String message){
        if(Util.contains(message, Util.TROLL_DICTIONNARY1))
            return true;
        return false;
    }
}
