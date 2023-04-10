package labo6.check;

public class CheckBehaviorImpatient extends CheckBehavior{
    @Override
    public boolean checkForWakeUp(String text) {
        return !text.equals("");
    }
}
