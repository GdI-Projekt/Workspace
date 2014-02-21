package de.tudarmstadt.gdi1.project;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.tudarmstadt.gdi1.project.alphabet.Alphabet;
import de.tudarmstadt.gdi1.project.utils.Utils;

/**
 * 
 * @author Thorsten
 *
 */
public class UtilsC implements Utils{

	// MEthoden von Utils die nicht gebraucht werden
	@Override
	public String toDisplay(String ciphertext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alphabet shiftAlphabet(Alphabet alphabet, int shift) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alphabet reverseAlphabet(Alphabet alphabet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsSameCharacters(Alphabet alphabet1, Alphabet alphabet2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Alphabet randomizeAlphabet(Alphabet alphabet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public Map<Integer, List<String>> ngramize(String text, int[] lengths) {
        Map<Integer, List<String>> res = new HashMap<Integer, List<String>>();
        
        for (int i = 0; i< lengths.length; i++){
            int index, n, maxngrams, rest;
            n = 0;
            index = 0;
            maxngrams = text.length()/lengths[i];
            rest = text.length()%lengths[i];
            List<String> ngrams = new LinkedList<String>();
            n = lengths[i];
            for (int y = 0; y<maxngrams; y++){
                ngrams.add(text.substring(index, index+n));
                index = index+n;
            }
            if (rest != 0){
                ngrams.add(text.substring(index, index+rest));
            }
            res.put(new Integer(lengths[i]), ngrams);
            
        }
                
        
        return res;
    }
	
}