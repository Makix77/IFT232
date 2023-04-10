package labo6.database;

import labo6.Ressources.Gender;

public class Picture {
	
	public enum PictureKey {
		gender(0),
		isComic(1),
		isSeductive(2);
		
		private int value;
		private PictureKey(int v){
			value=v;
		}
	}	
	
	
	private Object values[];
	private String path;
	
	
	public Picture(String p, Gender g, boolean c, boolean s) {
		
		values = new Object[3];
		path=p;
		values[PictureKey.gender.value]=g;
		values[PictureKey.isComic.value]=c;
		values[PictureKey.isSeductive.value]=s;		
	}
	
	public boolean match(PictureKey key, Object value){
		return values[key.value].equals(value);
	}
	
	public String getPath(){
		return path;
	}
	
	
}
