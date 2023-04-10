package labo6.profiler;

import labo6.Ressources;
import labo6.User;
import labo6.bots.ChatBot;
import labo6.check.CheckBehavior;
import labo6.database.Picture;
import labo6.database.PictureList;
import labo6.database.TextList;
import labo6.database.TextMessage;
import labo6.wait.WaitBehavior;

public abstract class Profiler {
    private ChatBot robot;
    private User human;

    public ChatBot createChatBot(User user, String name, Picture pic, Ressources.Gender g){
        this.robot = new ChatBot(user, name, pic, g);
        return this.robot;
    }
    public TextMessage generateGreeting(){
        return getSuitableMessages().keep(TextMessage.TextKey.isGreeting, true).random();
    }
    public TextMessage generateAnswer(){
        return getSuitableMessages().random();
    }

    public abstract TextList getSuitableMessages();
    public abstract PictureList getSuitablePictures();
    public abstract CheckBehavior createCheckBehavior();
    public abstract WaitBehavior createWaitBehavior();
}
