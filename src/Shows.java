import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Shows extends Application {
	String yiyuan= new Getfile().readTostring("D:/JAVA/ECLIPSE/demo/src/tree/WHOLE.txt", "utf-8");
	 ArrayList<yiyuan>yiyuans=new ArrayList(getyiyuan());
		
	 public static void main(String[] args) {
	        launch(args);
	    }
	 
	    
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("（^-^）");        
	 
	        TreeItem<String> rootNode = new TreeItem<>("义原");
	       
	        rootNode.setExpanded(true);
	        for (yiyuan yiyuan : yiyuans) {
	            TreeItem<String> empLeaf = new TreeItem<>(yiyuan.getChild());
	            boolean found = false;
	            for (TreeItem<String> depNode : rootNode.getChildren()) {
	                if (depNode.getValue().contentEquals(yiyuan.getFather())){
	                    depNode.getChildren().add(empLeaf);
	                    found = true;
	                    break;
	                }
	            }
	            if (!found) {
	                TreeItem depNode = new TreeItem(yiyuan.getFather());
	                rootNode.getChildren().add(depNode);
	                depNode.getChildren().add(empLeaf);
	            }
	        }       
	        



	                
	TreeItem<String> rootItem = new TreeItem<> ("词表");
	rootItem.setExpanded(false);
	ArrayList glo =new ArrayList();
	String gloss=new String();
	gloss=new Getfile().readTostring("D:/JAVA/ECLIPSE/demo/src/tree/glossary.txt", "gbk");
	gloss=gloss.replaceAll("\\s+", "/");
	glo=new Getfile().toarray1(gloss);//转成ArrayList的glossary;
	for(int i=0;i<glo.size()/3;i++){
	TreeItem<String> item = new TreeItem<> (String.valueOf(glo.get(3*i)));
	rootItem.getChildren().add(item);
	        }    

	TreeView<String> tree = new TreeView<> (rootItem);
	        StackPane root = new StackPane();
	        root.getChildren().add(tree);
	        VBox box = new VBox();
	        final Scene scene = new Scene(box, 500, 500);
	       // scene.setFill(Color.LIGHTGRAY);
	 
	        TreeView<String> treeView = new TreeView<>(rootNode);
	        treeView.setEditable(true);
	        
	        
	        
	 Text label1=new Text("词语：");
	 
	 
	 TextField text1=new TextField();
	
	 HBox hb=new HBox();//横
	 HBox hb2=new HBox();
	 HBox hb3=new HBox();
	 
	 VBox vb=new VBox();//竖
	 Label length=new Label();
	 Button len=new Button("shows");
	 Button liulan =new Button("open");
	
	 hb.getChildren().add(label1);
	 hb.getChildren().add(text1);
	 hb.setSpacing(10);
	
	 hb2.setSpacing(10);
	 hb3.getChildren().addAll(len);
	
	 hb3.setSpacing(10);
	 vb.getChildren().addAll(hb,hb2,length,hb3);
	 vb.setSpacing(10);
		len.setOnAction((ActionEvent e)->{//
		try {
			new wtojs3().writerrrr(text1.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   try {
           	URI uri = new URI("file:///D:/template.htm"); 
				Desktop.getDesktop().browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		
	});
		//liulan.setOnAction((ActionEvent e)->{
			 String str = "cmd /c start iexplore file:///D:/template.htm";
			  
	            //try {
	            	//URI uri = new URI("file:///D:/template.htm"); 
					//Desktop.getDesktop().browse(uri);
				//} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				//} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
			//	}  
		//});
		
	       
	 
	        box.getChildren().add(treeView);
	        GridPane g=new GridPane();
	        g.add(tree, 0, 0);
	        g.add(box, 1, 0);
	        g.add(vb, 2, 0);
	        primaryStage.setScene(new Scene(g, 800, 400));
	        primaryStage.show();
	    }
	    public static class yiyuan {
	    	 
	        private final SimpleStringProperty child;
	        private final SimpleStringProperty father;
	 
	        private yiyuan(String child, String father) {
	            this.child = new SimpleStringProperty(child);
	            this.father= new SimpleStringProperty(father);
	        }
	 
	        public String getChild() {
	            return child.get();
	        }
	 
	        public void setName(String fChild) {
	            child.set(fChild);
	        }
	 
	        public String getFather() {
	            return father.get();
	        }
	 
	        public void setFather(String fFather) {
	            father.set(fFather);
	        }
	    }


	public ArrayList<yiyuan> getyiyuan(){
		String ss;
		ArrayList words;
		ss = new Getfile().readTostring("D:/JAVA/ECLIPSE/demo/src/tree/WHOLE.txt", "utf-8");
		ss=ss.replaceAll("\\s+", ",");
		words =new Getfile().toarray(ss);//原始数组;
		words.remove(0);
		ArrayList Treelist =new ArrayList();
		for(int i=0;i<words.size()/3;i++){
			Treelist.add(String.valueOf(words.get(3*i+0)).trim());
			Treelist.add(String.valueOf(words.get(3*i+2)).trim());
		}
		ArrayList hashmap1=new ArrayList();//hash匹配用数组；
		for(int i=0;i<words.size()/3;i++){
			hashmap1.add(words.get(3*i+0));
			hashmap1.add(words.get(3*i+1));
		}
		HashMap<String,String>map=new HashMap<String,String>();
		for(int i=0;i<hashmap1.size()/2;i++){
			map.put(String.valueOf(hashmap1.get(2*i)),String.valueOf(hashmap1.get(2*i+1)));
		}
		ArrayList zhong =new ArrayList();
		for(int i=0;i<Treelist.size();i++){
			zhong.add(map.get(Treelist.get(i)));
			
		}
		ArrayList<yiyuan> jeguo=new ArrayList();
		for(int i=0;i<zhong.size()/2;i++){
			jeguo.add(new yiyuan(String.valueOf(zhong.get(i)),String.valueOf(zhong.get(i+1))));
		}
		return jeguo;
		}
	}




