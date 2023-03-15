package labo5.celebrities;

import labo5.Util;

public class BroScienceCelebrity extends SocialMediaCelebrity {

	public BroScienceCelebrity(String n, int wait) {
		super(n, wait);
		
	}

	@Override
	public void changeStatus() {
		setStatus(Util.getRandomString(Util.BROSCIENCE_STATUS));
	}
	

}
