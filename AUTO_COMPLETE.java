import java.util.*;
import java.io.*;
import java.math.BigInteger; 
class AUTO_COMPLETE{
	
	//*************TRIE-NODE VERTEX*******************//
	public static class TrieNode{
			char c;
			Map<Character,TrieNode> children;
			boolean endOfWord;
			public TrieNode(){
				children = new HashMap<Character,TrieNode>();
				endOfWord = false;
			}
	}
	
	public static class trie{
		
		//*************INSERT*******************//
		
		public static void insert(TrieNode root,String word){
			TrieNode current = root;
			int length = word.length();
			for(int i=0;i<length;i++){
				char ch = word.charAt(i);
				TrieNode node = current.children.get(ch);
				if(node == null){
					node = new TrieNode();
					current.children.put(ch,node);
				}
				current = node;
			}
			current.endOfWord = true;
		}
		
		//*************SEARCH*******************//
		
		public static void search(TrieNode root, String word){
			TrieNode current = root;
			int length = word.length();
			String soFarMatch = "";
			if(length == 0){
				System.out.println("Sorry, Enter a letter at least. \n");
				System.exit(0);
			}
			for(int i=0;i<length;i++){
				char ch = word.charAt(i);
				TrieNode node = current.children.get(ch);
				if(node == null){
					System.out.println("Match Not Found!");
					break;
				}
				soFarMatch += ch;
				current = node;
			}	
			if(current.endOfWord == true){
				System.out.println("Match: "+word);
			}
			else{
				System.out.println("Matched only till: "+soFarMatch);
			}
				//return current.endOfWord;
			/*	Iterator<Character> iterator = root.children.listIterator();
				while(iterator.hasNext()){
					
				} */
		}
	}
	
	public static TrieNode root = null;
	public static trie t = null;
	
	//*************START-ENGINE(LOADING UP THE FILE!)*******************//
	
	public static void startEngine(){
		
		t = new trie();
		root = new TrieNode();
		
		File file;
		BufferedReader br = null;
		
		try{
			file = new File("words.txt");
			br = new BufferedReader(new FileReader(file));
		}catch(Exception e){
			System.out.println("File not found!");
		}
		
		String s = "";
		try{
			while((s=br.readLine())!=null){
		
			t.insert(root,s);	
		
			}
		}catch(Exception e){ 
			System.out.println("end!");
		}
	}
	
	//*************INPUT*******************//
	
	public static void input(){
		Scanner in = new Scanner(System.in);
		System.out.println("Type \"$$\" to quit \n");
		System.out.print("Enter Query: ");
		String s =in.next();
		if(s.equals("$$")){
			System.exit(0);
		}
		while(!s.equals("$$")){
			t.search(root,s);
			System.out.println();
			System.out.print("Enter Query: ");
			s = in.next();
			//System.out.print();
		} 
	}
	
	public static void main(String lol[]){
		startEngine();
		input();
	}
}
