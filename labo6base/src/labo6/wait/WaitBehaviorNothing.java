package labo6.wait;

public class WaitBehaviorNothing extends WaitBehavior{
    @Override
    public void waitForUser() {
        sleep(2000);
    }
}
