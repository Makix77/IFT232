package labo5.followers;

import labo5.Util;

import java.util.ArrayList;

public abstract class Sujet {
    private ArrayList<Follower> followers = new ArrayList<>();

    private ArrayList<Follower> veto = new ArrayList<>();

    public void attach(Follower follower){
        followers.add(follower);
    }

    public boolean checkVeto(String message){
        if(veto.size() == 0)
            return false;
        for(Follower follower : veto){
            if(follower instanceof PressAgent){
                if(((PressAgent)follower).checkVeto(message))
                    return true;
            }
        }
        return false;
    }

    public void attachVeto(Follower follower){
        veto.add(follower);
    }

    public void notifyFollowers(String notification, String celebName){
        for(Follower follower : followers){
            if(!checkVeto(notification))
                follower.update(notification, celebName);
        }
    }
}
