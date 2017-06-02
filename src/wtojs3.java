import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class wtojs3 {
public ArrayList<JSONObject> write(String word) throws IOException{
	
	ArrayList<JSONObject> over=new ArrayList();
	ArrayList<HashMap<String,Object>>try1=new ArrayList();
	ArrayList<HashMap<String,Object>>try2=new ArrayList();
	//HashMap<String,Object> map1=new HashMap();
	Graph g=new Graph();
	ArrayList gloss =new ArrayList();
	gloss=new GetGlossary().readFileByLines("D:/JAVA/ECLIPSE/demo/src/tree/glossary.txt");
	for(int i=0;i<gloss.size()/3;i++){
		ArrayList yiyuans=new ArrayList();
		String yiyuan=String.valueOf(gloss.get(3*i+2));
		String[] words=yiyuan.split(",");
		for(int x=0;x<words.length;x++){
			yiyuans.add(words[x]);
		}
		for(int k=0;k<yiyuans.size();k++){
			g.add(String.valueOf(gloss.get(3*i+0)), String.valueOf(yiyuans.get(k)), i, String.valueOf(gloss.get(3*i+1)));
		}
		
	}
	
	List wordbyyiyuan=new ArrayList();
	wordbyyiyuan=g.getYiyuan(word);
	for(int i=0;i<wordbyyiyuan.size();i++){
		
		HashMap<String,Object> map1=new HashMap();
		map1.put("name", (String)wordbyyiyuan.get(i));
		map1.put("group", 0);
		try1.add(map1);
	}
	 int target=2;
	for(int  i=0;i<wordbyyiyuan.size();i++){
		List yiyuans=new ArrayList();
		 yiyuans=g.getWord((String)wordbyyiyuan.get(i));
		
		 	for(int k=0;k<yiyuans.size();k++){
		 		
		 		HashMap<String,Object>map2=new HashMap();
		 		HashMap<String,Object>map3=new HashMap();
		 		map2.put("name", (String)yiyuans.get(k));
		 		map2.put("group", 1);
		 		map3.put("source", i);
		 		
		 		
		 		map3.put("target", target);
		 		target=target+1;
		 		map3.put("value", 1);
		 		
		 		try1.add(map2);
		 		try2.add(map3);
		 	}
	
	
	}
	
	JSONObject op=new JSONObject();
	JSONObject opx=new JSONObject();
	opx.put("links", try2);
	op.put("nodes", try1);
	over.add(op);
	over.add(opx);
	return over;
	//System.out.println(op);
	//System.out.println(opx);
}



	

public void writerrrr(String words) throws IOException{
	BufferedWriter writer =null;
	String s;
	ArrayList<JSONObject> ss;
	String s1;
	String s2;
	ss=write(words);
	s1=ss.get(0).toString();
	s2=ss.get(1).toString();
	s=s1+s2;
	File file =new File("d://kidme.json");
	try {
		writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d://temp.json"),"UTF-8"));
		
		String[] ss1=s1.split("\\{");
		writer.write("{"+"\t\t"+ss1[1]);
		for(int i=2;i<ss1.length-2;i++){
			
		writer.write("\r\n"+"{"+ss1[i]);
		}
		String fin=ss1[ss1.length-1];
		fin=fin.substring(0, fin.length()-1);
		writer.write("\r\n"+"{"+fin+",");
		String[] ss2=s2.split("\\{");
		writer.write("\r\n"+ss2[1]);
		for(int i=2;i<ss2.length-2;i++){
			writer.write("\r\n"+"{"+ss2[i]);
		}
		String over=ss2[ss2.length-1];
		over=over.substring(0, over.length()-2);
		writer.write("\r\n"+"{"+over+"\r\n"+"]}");
		writer.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void main(String[] args) throws IOException{
	new wtojs3().write("°ôÇò");

	new wtojs3().writerrrr("°ôÇò");
}





}
