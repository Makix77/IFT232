package labo6.profiler;

import labo6.check.CheckBehavior;
import labo6.check.CheckBehaviorImpatient;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.database.TextMessage;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorSaySomething;

public class SeductiveProfile extends Profiler{
    @Override
    public TextList getSuitableMessages() {
        return TextDatabase.getAllMessages().keep(TextMessage.TextKey.isSeductive, true);
    }

    @Override
    public PictureList getSuitablePictures() {
        return null;
    }

    @Override
    public CheckBehavior createCheckBehavior() {
        return new CheckBehaviorImpatient();
    }

    @Override
    public WaitBehavior createWaitBehavior() {
        return new WaitBehaviorSaySomething();
    }
}
