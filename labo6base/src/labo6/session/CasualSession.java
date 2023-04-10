package labo6.session;

import labo6.Labo6Main;
import labo6.Ressources;
import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.*;
import labo6.profiler.CasualProfile;
import labo6.profiler.Profiler;

public class CasualSession extends Session{
    public CasualSession(Labo6Main l, User u) {
        super(l, u);
        profiler = createProfiler();
    }

    public static Profiler createProfiler(){
        return new CasualProfile();
    }
}
