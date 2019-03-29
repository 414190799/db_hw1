package hw1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class process {
	
	public List pro() throws Exception 
	  {  
		List<Map<String,Integer>> result=new ArrayList<Map<String,Integer>>();
		HashMap<String,Integer> dic=new HashMap<String,Integer>();
		HashMap<String,Integer> gram=new HashMap<String,Integer>();
		HashMap<String,Integer> gram2=new HashMap<String,Integer>();
		HashMap<String,Integer> gram3=new HashMap<String,Integer>();
		System.out.println("input file location:");
		BufferedReader reader =  
                new BufferedReader(new InputStreamReader(System.in)); 
		String name = reader.readLine();

		//HashMap<String,Integer> gram3=new HashMap<String,Integer>();
		String line;
		int len;
		Stemmer stem=new Stemmer();
	    File stopWords = 
	      new File(name+"stop_words.txt"); 
	    Scanner sc = new Scanner(stopWords); 
	    while (sc.hasNextLine()) { 
	      //System.out.println(sc.nextLine());
	      line=sc.nextLine();
	      dic.put(line,1);
	    }
        BufferedWriter out = new BufferedWriter(new FileWriter("Topics.txt"));
	    
	    for(int i=1; i<=24; i++) {
	    	List<String> w=new ArrayList<String>();
	    	//List<Srring> 
	    	File file = new File(name+i+".txt");
	    	Scanner s = new Scanner(file); 
		    while (s.hasNextLine()) { 
		      //System.out.println(sc.nextLine());
		      line=s.nextLine();
		      String[] words=line.split(" ",0);
		      
		      
		      for(String k:words) {
		    	  k=k.replaceAll("\\pP","");
		    	  if (dic.get(k.toLowerCase())==null && k.length()!=0) {//remove empty line
		    		  k=stem.stem(k);
		    		  w.add(k.toLowerCase());
		    		  //System.out.println(k);
		    		  
		    	  }
		      }
		    }
		      
		    len=w.size();
		    //System.out.println(len);
		    
		    for(int j=0; j<len-1; j++) {
		    	//System.out.println(w.get(j));
		    	line=w.get(j);
		    	if (gram.get(line)==null) {
		    		gram.put(line, 1);
		    	}else {
		    		gram.put(line, gram.get(line) + 1);
		    	}
		    	line=w.get(j)+" "+w.get(j+1);
		    	if (gram.get(line)==null) {
		    		gram.put(line, 1);
		    	}else {
		    		gram.put(line, gram.get(line) + 1);
		    	}
		    }
		      
		    for(int j=0; j<len-2; j++) {
		    	//System.out.println(w.get(j));
		    	line=w.get(j)+" "+w.get(j+1)+" "+w.get(j+2);
		    	if (gram.get(line)==null) {
		    		gram.put(line, 1);
		    	}else {
		    		gram.put(line, gram.get(line) + 1);
		    	}
		    }
		    
			HashMap<String,Integer> g=new HashMap<String,Integer>();
		    for(int j=0; j<len-1; j++) {
		    	//System.out.println(w.get(j));
		    	line=w.get(j);
		    	if (g.get(line)==null) {
		    		g.put(line, 1);
		    	}else {
		    		g.put(line, g.get(line) + 1);
		    	}
		    	
		    	line=w.get(j)+" "+w.get(j+1);
		    	if (gram2.get(line)==null) {
		    		gram2.put(line, 1);
		    	}else {
		    		gram2.put(line, gram2.get(line) + 1);
		    	}
		    }
		      
		    for(int j=0; j<len-2; j++) {
		    	//System.out.println(w.get(j));
		    	line=w.get(j)+" "+w.get(j+1)+" "+w.get(j+2);
		    	if (gram3.get(line)==null) {
		    		gram3.put(line, 1);
		    	}else {
		    		gram3.put(line, gram3.get(line) + 1);
		    	}
		    }
		    for (String q:gram2.keySet()) {
		    	if (gram2.get(q)>3) {
		    		g.put(q, gram2.get(q));
		    	}
		    }
		    for (String q:gram3.keySet()) {
		    	if (gram3.get(q)>3) {
		    		g.put(q, gram3.get(q));
		    	}
		    }
		    gram2.clear();
		    gram3.clear();
		    result.add(g);

		    
		      
		    /*for (String key:gram.keySet()) {
		    	if (gram.get(key)>=4) {//picking the frequency >=4
		    	 System.out.println(key+"  "+gram.get(key));
		    	}
		    }*/
		    if(i%8==0) {
		    	int n=(i/8-1)*3+1;
		    	int max=0;
		    	String temp="";
		    	out.write("Folder C"+n+":  ");
		    	System.out.print("Folder C"+n+":  ");
		    	for(int k=0; k<3;k++) {
		    	for(String p:gram.keySet()) {
		    		if(gram.get(p)>max) {
		    			max=gram.get(p);
		    			temp=p;
		    		}
		    	}
		    	out.write(temp+" "+max+"      ");
		    	System.out.print(temp+" "+max+"      ");
		    	
		    	gram.remove(temp);
		    	max=0;
		    	}
		    	gram.clear();
		    	out.newLine();
		    	System.out.println();
		    }
	        }
	    out.close();

	    return result;
	    

	    
	    }}
	