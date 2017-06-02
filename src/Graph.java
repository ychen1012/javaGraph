import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Graph {
	private List<Node>List =new ArrayList<Node>();
	class Node{
		String word;
		String yiyuan;
		int id;
		String cixing;
	}
	
public void add(String word,String yiyuan,int id,String cixing){
	Node n=new Node();
	n.id=id;
	n.word=word;
	n.yiyuan=yiyuan;
	n.cixing=cixing;
	List.add(n);
}
public boolean chongfu(String word){
	int number=0;
	for(int i=0;i<List.size();i++){
		if(List.get(i).word.equals(word)){
			number=number+1;
		}
	}
	if(number>=2){
		return true;
	}
	else{
		return false;
	}
	
}






public List<String> getWord(String x){
	List<String>newList =new ArrayList<String>();
	for(int i=0;i<List.size();i++){
		if(List.get(i).yiyuan.equals(x)){
			newList.add(List.get(i).word);
			
		}
	}
	return newList;
}
public List<String>getYiyuan(String x ){
	List<String>newList=new ArrayList<String>();
	for(int i=0;i<List.size();i++){
		if(List.get(i).word.equals(x)){
			newList.add(List.get(i).yiyuan);
		}
	}
	return newList;
}
public List<String>getYiyuanbyid(int id){
	List<String>newList =new ArrayList<String>();
	for(int i=0;i<List.size();i++){
		if(List.get(i).id==id){
			newList.add(List.get(i).yiyuan);
		}
	}
	return newList;
}
public String getWord1(int id){
	List<String>newList =new ArrayList<String>();
	for(int i=0;i<List.size();i++){
		if(List.get(i).id==id){
			return List.get(i).word;
			
		}
	}
	return null;
}
public static void main(String[] args) throws IOException{
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
	
			for(int i=0;i<g.getYiyuanbyid(100).size();i++){
				System.out.println(g.getYiyuanbyid(100).get(i));
			}
		System.out.println(g.getYiyuan("null"));
		//for(int i=0;i<g.getYiyuan("奥林匹克运动会").size();i++){
			//System.out.println(g.getWord(String.valueOf(g.getYiyuan("奥林匹克运动会"))));

System.out.println(g.getWord("#disease|疾病"));
//System.out.println(g.chongfu("爱好"));

}
	
	
		
		
		
	}





