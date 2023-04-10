package labo6.session;

import labo6.Labo6Main;
import labo6.Ressources;
import labo6.User;
import labo6.bots.ChatBot;
import labo6.database.*;
import labo6.profiler.Profiler;
import labo6.profiler.SeductiveProfile;

public class SeductionSession extends Session{
    public SeductionSession(Labo6Main l, User u) {
        super(l, u);
        profiler = createProfiler();
    }

    public static Profiler createProfiler(){
        return new SeductiveProfile();
    }
}
