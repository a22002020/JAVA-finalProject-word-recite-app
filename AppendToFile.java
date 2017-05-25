import java.io.FileWriter;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
  

public class AppendToFile {  

    public static void appendMethodA(String fileName,   String content) {  
        try {  

            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");  

            long fileLength = randomFile.length();  
  
            randomFile.seek(fileLength); 
			randomFile.write((content).getBytes()); 
           // randomFile.writeBytes(content);  
            randomFile.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  


  
    public static void main(String[] args) {  
        String fileName = "gay.txt";  
        String content = "new append!\n";  
 
        AppendToFile.appendMethodA(fileName, content);  
        AppendToFile.appendMethodA(fileName, "append end. \n");  

        //ReadFromFile.readFileByLines(fileName);  

    }  
}  