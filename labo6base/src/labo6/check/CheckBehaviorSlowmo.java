package labo6.check;

public class CheckBehaviorSlowmo extends CheckBehavior{
    private String oldText = "";
    @Override
    public boolean checkForWakeUp(String text) {
        boolean ret = false;
        if(text.equals(oldText))
            ret = true;
        oldText = text;
        return ret;
    }
}
