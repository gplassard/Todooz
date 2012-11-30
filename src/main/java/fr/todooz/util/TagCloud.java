package fr.todooz.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagCloud {
	
    private List<String> tags = new ArrayList<String>();

    public void add(String...tableauDeTags) {
    	if (tableauDeTags == null)
    		return;
    	for (String tag : tableauDeTags){
    		if (tag!= null && !tag.isEmpty() && !this.contains(tag)){
    			tags.add(tag);
    		}
    	}
    }

    public int size() {
        return tags.size();
    }

	public boolean contains(String string) {
		return tags.contains(string);
	}

	public void top(int taille) {
		if (taille<0){
			taille = 0;
		}		
		tags = tags.subList(0, Math.min(taille,this.size()));
	}

	public void shuffle() {
		Collections.shuffle(tags);
	}

}