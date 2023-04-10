package labo6.database;

import java.util.ArrayList;
import java.util.Random;

import labo6.Ressources.Gender;
import labo6.database.TextMessage.Language;
import labo6.database.TextMessage.TextKey;

public class TextList {

	private ArrayList<TextMessage> msgs = new ArrayList<TextMessage>();

	public TextList() {

	}

	public TextList(ArrayList<TextMessage> src) {
		msgs = src;
	}

	public TextList keep(TextKey key,Object value) {
		
		ArrayList<TextMessage> res = new ArrayList<TextMessage>();
		
		for(TextMessage msg:msgs){
			if(msg.match(key,value)){
				res.add(msg);
			}
		}

		return new TextList(res);
	}
	
	public TextList remove(TextKey key,Object value) {
		
		ArrayList<TextMessage> res = new ArrayList<TextMessage>();
		
		for(TextMessage msg:msgs){
			if(!msg.match(key,value)){
				res.add(msg);
			}
		}

		return new TextList(res);
	}	
	
	public void merge(TextList l2){
		
		for(TextMessage msg: l2.msgs){
			if(!msgs.contains(msg)){
				msgs.add(msg);
			}
		}
	
	}

	public void add(String msg, Language l, boolean q, boolean o,boolean s,boolean pa,boolean g,Gender gen) {
		msgs.add(new TextMessage(msg, l, q, o,s,pa,g,gen));
	}

	public int size() {
		return msgs.size();
	}

	public TextMessage get(int index) {
		return msgs.get(index);
	}

	@SuppressWarnings("unchecked")
	public TextList clone() {

		return new TextList((ArrayList<TextMessage>) msgs.clone());
	}
	
	public TextMessage random(){
		Random generator = new Random();
		return msgs.get(generator.nextInt(msgs.size()));		
	}
}
