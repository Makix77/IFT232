package labo5.celebrities;

import labo5.Util;

public class DramaticCelebrity extends SocialMediaCelebrity{

		
	
	
	public DramaticCelebrity(String n,int w) {
		super(n,w);		
	}
	
	
	@Override
	public void changeStatus() {
		
		setStatus(Util.getRandomString(Util.DRAMATIC_STATUS));
		
	}

}
