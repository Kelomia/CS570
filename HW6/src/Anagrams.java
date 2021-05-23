import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
    final Integer[] primes={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
    Map<Character,Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    // Constructor:
    public Anagrams(){
        buildLetterTable();
        anagramTable =new HashMap<Long,ArrayList<String>>();
    }

    // Build Table for Hash
    private void buildLetterTable(){
        letterTable= new HashMap<Character,Integer>();
        // The alphabet to fit with prime-number:
        Character[] alphabets={'a','b','c','d','e','f','g','h','i','j','k',
                'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        for(int i=0;i<26;i++){
            letterTable.put(alphabets[i],primes[i]);
            // a=2, b=3, ..., z=101
        }
    }

    // Add String(s)'s hash and word to Hash-Table
    private void addWord(String s){
        Long hash= this.myHashCode(s);
        if(anagramTable.get(hash)==null){
            // When Table=null, create one.
            ArrayList<String> array=new ArrayList<String>();
            array.add(s);
            anagramTable.put(hash,array);
        }else{
            // Else, add hash and string.
            anagramTable.get(hash).add(s);
        }
    }

    // Get Hash of String(s)
    private Long myHashCode(String s){
        long k=1;
        for(int i=0;i<s.length();i++){
            k=k*(long)s.charAt(i);
            // Get Hash with index-i of String s;
        }
        return k;
    }

    // Get String in File(name: String(s))
    public void processFile(String s)throws IOException {
        FileInputStream file_stream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(file_stream));
        String strLine;
        while((strLine=br.readLine())!=null) {
            this.addWord(strLine);
            // When file not end, add next line to anagram table.
        }
        br.close();
        // Close the reader.
    }

    // Return the MaxEntries of anagram
    private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
        ArrayList<Map.Entry<Long,ArrayList<String>>> list= new ArrayList<>();

        int max=0;  // The mark of max number;
        for(Map.Entry<Long,ArrayList<String>> entry: anagramTable.entrySet()){
            if(entry.getValue().size()>max){
                list.clear();
                list.add(entry);
                max=entry.getValue().size();
                // When new max, cls list, add it and reset max.
            } else if(entry.getValue().size()==max){
                list.add(entry);
                // Add to entry when same.
            }
        }
        return list;
        // Return the recorded-list.
    }

    public static void main(String[] args){
        Anagrams a = new Anagrams();

        final long startTime = System.nanoTime();
        try{
            a.processFile("words_alpha.txt");
        }catch(IOException e1){
            e1.printStackTrace();
        }
        ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
        final long estimatedTime = System.nanoTime()-startTime;
        final double seconds = ((double)estimatedTime/1000000000);
        System.out.println("Time:"+seconds);
        System.out.println("List of max anagrams:"+maxEntries);
        // The result as the .pdf show.
    }
}
