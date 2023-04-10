package labo6.profiler;

import labo6.check.CheckBehavior;
import labo6.check.CheckBehaviorPatient;
import labo6.database.PictureDatabase;
import labo6.database.PictureList;
import labo6.database.TextDatabase;
import labo6.database.TextList;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorAsk;

public class NormalProfile extends Profiler{
    @Override
    public TextList getSuitableMessages() {
        return TextDatabase.getAllMessages();
    }

    @Override
    public PictureList getSuitablePictures() {
        return PictureDatabase.getAllPictures();
    }

    @Override
    public CheckBehavior createCheckBehavior() {
        return new CheckBehaviorPatient();
    }

    @Override
    public WaitBehavior createWaitBehavior() {
        return new WaitBehaviorAsk();
    }
}
