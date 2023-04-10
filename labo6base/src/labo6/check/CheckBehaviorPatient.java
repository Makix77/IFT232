package labo6.check;

public class CheckBehaviorPatient extends CheckBehavior{
    @Override
    public boolean checkForWakeUp(String text) {
        return text.contains("?");
    }
}
