package labo6.wait;

import labo6.database.TextDatabase;
import labo6.database.TextMessage;

public class WaitBehaviorAsk extends WaitBehavior{
    @Override
    public void waitForUser() {
        sleep(2000);
        //appendMessage(TextDatabase.getAllMessages().keep(TextMessage.TextKey.isQuestion, true).random().getMessage());
    }
}
