package de.tudarmstadt.gdi1.project;

import java.util.*;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.alphabet.Dictionary;

/**
 * 
 * @author Janik
 *
 */
public class DictionaryC implements Dictionary{
	
	TreeSet<String> dictionarybook= new TreeSet<String>();
	Alphabet alphabet;
	
	public DictionaryC(de.tudarmstadt.gdi1.project.Alphabet source, String text){
		
		this.alphabet = source;
		char[] breaks = new char[] {',' , '.' , '!' , '?' , ' '};
		String word = "";
		String addword = "";
		boolean allowed;
		char actchar;
		char actbreak;
		int length = text.length();
		boolean add = false;
		
		for(int i=0; i < length ; i++){
			allowed = true;
			actchar = text.charAt(i);
			for(int b = 0; b<breaks.length; b++){
				actbreak = breaks[b];
				if(actchar == actbreak){
					allowed = false;
					if(source.allows(word)){
						add = true;
						addword = word;
					}
					word = "";
				}
				
			}
			if ( allowed) word = word + text.charAt(i);
			if ( i == length - 1){
				add = true;
				addword = word;
			}
			if ( add ){
				dictionarybook.add(addword);
			}
			add = false;
		}
	}

	@Override
	public Iterator<String> iterator() {
		return dictionarybook.iterator();
	}

	@Override
	public boolean contains(String word) {
		
		boolean contains = false;
		String dict = "";
		int size = this.size();
		
		for(int i = 0 ; i < size ; i++){
			
			dict = this.get(i);
			
			if ( dict.equals(word)){
				contains = true;
			}
		}
		return contains;
	}

	@Override
	public Alphabet getAlphabet() {
		return alphabet;
	}

	@Override
	public int size() {
		
		int size = dictionarybook.size();

		return size;
	}

	@Override
	public String get(int index) {

		return dictionarybook.toArray()[index].toString();
	}




}