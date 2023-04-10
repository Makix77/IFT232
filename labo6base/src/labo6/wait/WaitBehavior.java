package labo6.wait;

import labo6.session.Session;

public abstract class WaitBehavior {

    public static WaitBehavior createWaitBehavior(String type){
        WaitBehavior wait = null;
        switch(type){
            case Session.NORMAL_SESSION:
                wait = new WaitBehaviorAsk();
                break;
            case Session.SEDUCTION_SESSION:
                wait = new WaitBehaviorSaySomething();
                break;
            case Session.CASUAL_SESSION:
                wait = new WaitBehaviorNothing();
                break;
            default:
                throw new IllegalArgumentException("Wrong session type: "+type);
        }
        return wait;
    }
    public abstract void waitForUser();

    public void sleep(int time) {
        try {

            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}
