import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GetGlossary{
	public ArrayList readFileByLines(String filename) throws IOException{
		File file =new File(filename);
		BufferedReader reader=null;
		reader=new BufferedReader(new FileReader(file));
		String temp=null;
		String what;
		String[] words;
		ArrayList s=new ArrayList();
		
		temp=reader.readLine();
		while(temp!=null){
			temp=temp.replaceAll("\\s+", " ");
			words=temp.split(" ");
			for(int i=0;i<words.length;i++){
				s.add(String.valueOf(words[i]).trim());
			}
			temp=reader.readLine();
		}
		
			return s;
	}		
			
			
		

	public static void main(String[] args) throws IOException{
		ArrayList s=new ArrayList();
		ArrayList b=new ArrayList();
		s=new GetGlossary().readFileByLines("D:/JAVA/ECLIPSE/demo/src/tree/glossary.txt");
		System.out.println(s.size());
		for(int i=0;i<s.size()/3;i++){
			b.add(s.get(3*i+2));
		}
		System.out.println(b.toString());
	}
}
	