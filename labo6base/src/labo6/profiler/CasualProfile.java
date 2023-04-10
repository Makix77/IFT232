package labo6.profiler;

import labo6.check.CheckBehavior;
import labo6.check.CheckBehaviorSlowmo;
import labo6.database.*;
import labo6.wait.WaitBehavior;
import labo6.wait.WaitBehaviorNothing;

public class CasualProfile extends Profiler{
    @Override
    public TextList getSuitableMessages() {
        return TextDatabase.getAllMessages().remove(TextMessage.TextKey.isSeductive, true);
    }

    @Override
    public PictureList getSuitablePictures() {
        return PictureDatabase.getAllPictures().remove(Picture.PictureKey.isSeductive, true);
    }

    @Override
    public CheckBehavior createCheckBehavior() {
        return new CheckBehaviorSlowmo();
    }

    @Override
    public WaitBehavior createWaitBehavior() {
        return new WaitBehaviorNothing();
    }
}
