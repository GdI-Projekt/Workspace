package de.tudarmstadt.gdi1.project;

import java.util.*;

/**
 * 
 * @author Janik
 *
 */

public class DistributionC implements de.tudarmstadt.gdi1.project.alphabet.Distribution {
	
	String text;
	Alphabet source;
	int ngramsize;
	/**
	 * Konstruktor
	 * @param source Die Liste der zu �berpr�fenden Buchstaben als Alphabet
	 * @param text  der zu �berpr�fende Text
	 * @param ngramsize
	 */
	public DistributionC(Alphabet source, String text, int ngramsize){

		this.text = source.normalize(text);
		this.source = source;
		this.ngramsize = ngramsize;
		
	}

	/**
	 * getFrequency z�hlt die Anzahl der Vorkommnisse eines Strings in einem Text
	 * @param character der zu �berpr�fende String
	 * @return die anzahl der Vorkommnisse des key's
	 */
	@Override
	public double getFrequency(String key) {
		double counter = 0;
		if(text.length()<key.length()) return counter;
		int length = text.length()-key.length();
		for (int i=0; i <= length; i++){
			if (text.substring(i,i+key.length()).equals(key)){
				counter++;
			}
		}
		counter = counter/(length + 1);
		return counter;
	}

	/**
	 * getSorted sortiert alle in einem Text vorkommenden Strings der L�nge length nach ihrer H�ufigkeit.
	 * @param length L�nge der zu �berpr�fenden Zeichenketten
	 */
	@Override
	public List<String> getSorted(int length) {
		List<String[]> ergebnis = new ArrayList<String[]>();
		List<String> result = new ArrayList<String>();
		
		//man gehe die Liste durch bis zur Stelle x, welche definiert ist, als die Stelle, welche um length-1 vor dem Ende des Textes steht
		for (int i = 0; i<= text.length()-length; i++){
			
			//man nehme den Text mit der L�nge length und speichere ihn tempor�r
			String tempstring = text.substring(i,i+length);
			
			//man �berpr�fe ob das Alphabet source der Klasse den tempor�ren String zul�sst
			if(source.allows(tempstring)){

				boolean add = true;
				
				//man gehe die Ergebnisliste durch und vergleiche, ob der tempstring schon in ihr enthalten ist.
				for(int e = 0 ; e < ergebnis.size(); e++){
					
					String[] actergebnis = ergebnis.get(e);
					
					if (actergebnis[1].equals(tempstring)){
						add = false;
					}
						
				}
				
				//wenn der tempstring noch nicht enthalten ist, dann f�ge man ihn mit der H�ufigkeit zur Ergebnisliste hinzu
				if (add){
					
					double frequency = getFrequency(tempstring);
					
					ergebnis.add(new String[] {String.valueOf(frequency), tempstring});
					
				}
			}
			
		}
		
		//man gehe nun die Ergebnisliste durch und f�ge jeweils das Element mit der h�chsten frequency zu der result liste hinzu.
		//anschlie�end l�sche man das entsprechende Glied in der Ergebnisliste
		while(ergebnis.size()>0){
			double temp= 0;
			int pos = 0;
			for(int i = 0 ; i<ergebnis.size();i++){
				
				double wert = Double.valueOf(ergebnis.get(i)[0]);
				
				if(wert > temp){
					temp=wert;
					pos = i;
					i=0;
				}
			}
			result.add(ergebnis.get(pos)[1]);
			ergebnis.remove(pos);
		}
		
		return result;
	}

	public Alphabet getAlphabet(){
		return source;
	}

	@Override
	public String getByRank(int length, int rank) {
		
		boolean valid = false;
		List<String> sort = this.getSorted(length);
		
		if( text.length() > length){
			if (sort.toArray().length >= rank){
				valid = true;
			}
		}
		
		if(valid){
			return sort.get(rank-1).toString();
		}
		else return null;
		
		
	}
	

}