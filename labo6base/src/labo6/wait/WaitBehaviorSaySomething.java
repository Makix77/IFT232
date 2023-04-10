package labo6.wait;

import labo6.database.TextDatabase;
import labo6.database.TextMessage;

public class WaitBehaviorSaySomething extends WaitBehavior{
    @Override
    public void waitForUser() {
        sleep(1000);
        //appendMessage(TextDatabase.getAllMessages().keep(TextMessage.TextKey.isSeductive, true).random().getMessage());
    }
}
