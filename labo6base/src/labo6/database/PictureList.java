package labo6.database;

import java.util.ArrayList;
import java.util.Random;

import labo6.Ressources.Gender;
import labo6.database.Picture.PictureKey;

public class PictureList implements Cloneable {

	private ArrayList<Picture> pics = new ArrayList<Picture>();

	public PictureList() {

	}

	public PictureList(ArrayList<Picture> src) {
		pics = src;
	}

	public PictureList keep(PictureKey key,Object value) {
		
		ArrayList<Picture> res = new ArrayList<Picture>();
		
		for(Picture pic:pics){
			if(pic.match(key,value)){
				res.add(pic);
			}
		}

		return new PictureList(res);
	}
	
	public PictureList remove(PictureKey key,Object value) {
		
		ArrayList<Picture> res = new ArrayList<Picture>();
		
		for(Picture pic:pics){
			if(!pic.match(key,value)){
				res.add(pic);
			}
		}

		return new PictureList(res);
	}	
	
	public void merge(PictureList l2){
		
		for(Picture pic: l2.pics){
			if(!pics.contains(pic)){
				pics.add(pic);
			}
		}
	
	}

	public void add(String path, Gender g, boolean b, boolean c) {
		pics.add(new Picture(path, g, b, c));
	}

	public int size() {
		return pics.size();
	}

	public Picture get(int index) {
		return pics.get(index);
	}

	@SuppressWarnings("unchecked")
	public PictureList clone() {

		return new PictureList((ArrayList<Picture>) pics.clone());
	}
	
	public Picture random(){
		Random generator = new Random();
		return pics.get(generator.nextInt(pics.size()));		
	}

}
