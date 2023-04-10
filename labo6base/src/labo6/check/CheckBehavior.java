package labo6.check;

import labo6.session.Session;

public abstract class CheckBehavior {
    public static CheckBehavior createCheckBehavior(String type){
        CheckBehavior check = null;
        switch(type){
            case Session.NORMAL_SESSION:
                check = new CheckBehaviorPatient();
                break;
            case Session.SEDUCTION_SESSION:
                check = new CheckBehaviorImpatient();
                break;
            case Session.CASUAL_SESSION:
                check = new CheckBehaviorSlowmo();
                break;
            default:
                throw new IllegalArgumentException("Wrong session type: "+type);
        }
        return check;
    }
    public abstract boolean checkForWakeUp(String text);
}
