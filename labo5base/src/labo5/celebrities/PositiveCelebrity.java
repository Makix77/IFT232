package labo5.celebrities;

import labo5.Util;

public class PositiveCelebrity extends SocialMediaCelebrity{

	
	public PositiveCelebrity(String n, int wait) {
		super(n, wait);
		
	}

	@Override
	public void changeStatus() {
		
		setStatus(Util.getRandomString(Util.POSITIVE_STATUS));
		
	}

}
