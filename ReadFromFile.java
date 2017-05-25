import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.FileOutputStream;  
import java.io.RandomAccessFile;  
import java.io.Reader;  
	import java.util.List; 
	import java.util.ArrayList;
  
public class ReadFromFile {  

public static int getNum(String bookname){
	System.out.println("bookname"+bookname);
	List<String> ttempB=readFileByLines("VAC_info.txt");
	List<String> ttempN=readFileByLines("book_num.txt");
	String[] tempB=ttempB.toArray(new String[ttempB.size()]);
	String[] tempN=ttempN.toArray(new String[ttempN.size()]);
	int mark=0;
	String boookname=bookname+"\n";
	
	for (int i=0;i<ttempB.size();i++) if (tempB[i].compareTo(bookname)==0) {mark=i;}
	//System.out.println("bookname:"+bookname+" tempB"+tempB[1]+" rilegele:"+mark+"temp{n}:"+tempN[mark]);
	//AppendToFile.appendMethodA("VAC_info.txt",bookname+tempB[1]);
	
	return Integer.parseInt(tempN[mark]);
		
}

public static void changeNum(String bookname,int num){
	List<String> ttempB=readFileByLines("VAC_info.txt");
	List<String> ttempN=readFileByLines("book_num.txt");
	String[] tempB=ttempB.toArray(new String[ttempB.size()]);
	String[] tempN=ttempN.toArray(new String[ttempN.size()]);
	int mark=0;
	for (int i=0;i<ttempB.size();i++) if (tempB[i].compareTo(bookname)==0) mark=i;
	tempN[mark]=Integer.toString(num);
	ValEditor.creatVal("book_num");
	String buffer=new String("");
	for (int i=0;i<ttempN.size();i++) {
		if (buffer.length()<3000)buffer=buffer+tempN[i]+"\n";
			else
			{AppendToFile.appendMethodA("book_num.txt",buffer);buffer="";}
		}
	AppendToFile.appendMethodA("book_num.txt",buffer);
	
}


public static void saveWord(List<String> list,int num,String exp,String bookname){
	String[] temp=list.toArray(new String[list.size()]);
	ValEditor.creatVal(bookname);
	temp[num*2+1]=exp;
	try{
	FileOutputStream fos=new FileOutputStream(bookname+".txt"); 
	String buffer=new String("");
	for (int i=0;i<list.size();i++) 
		if (buffer.length()<30000)buffer=buffer+temp[i]+"\n";
	else
	/*{AppendToFile.appendMethodA(bookname+".txt",buffer);buffer="";}
	AppendToFile.appendMethodA(bookname+".txt",buffer);*/
	{
		fos.write(buffer.getBytes()); buffer="";
		
	}fos.write(buffer.getBytes());fos.close();  
	} catch (IOException e){ e.printStackTrace();}  
	
	
}


    public static List<String> readFileByLines(String fileName) {  
		List<String> l = new ArrayList<String>();  
        File file = new File(fileName);  
        BufferedReader reader = null;  
        try {  

            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            while ((tempString = reader.readLine()) != null) {  
               // System.out.println("line " + line + ": " + tempString);  
                line++;  
				l.add(tempString);
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
	return l;
    }  

    public static void main(String[] args) {  


        //ReadFromFile.readFileByLines(fileName);  

    }  
}  