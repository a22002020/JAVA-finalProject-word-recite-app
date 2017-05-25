    import java.io.File;  
    import java.io.FileInputStream;  
    import java.io.FileNotFoundException; 
	import java.io.UnsupportedEncodingException;
    import java.io.FileOutputStream;  
    import java.io.IOException;  
    import java.io.InputStreamReader;  
	import java.io.*;  
	import java.awt.*;
	import java.util.List; 
	import java.util.ArrayList;
	import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.*;
      
    public class ValEditor extends JPanel implements ActionListener {  
	private JButton makeN=new JButton("New");
	private JButton addWord=new JButton("Add word");
	private JLabel word=new JLabel("Word:");
	private JTextField TextWord=new JTextField(10);
	private JLabel expl=new JLabel("paraphrase");
	private JTextField TextExpl=new JTextField(30);
	private String st;
	private JLabel hint=new JLabel("");
	private  JComboBox<String> VACComboBox=new JComboBox();
	private String[] toBeStored;
	private List<String> list;
	public void getVacs(){
		list = new ArrayList<String>();    
		list=ReadFromFile.readFileByLines("VAC_info.txt");
		toBeStored = list.toArray(new String[list.size()]); 
		//String[] toBeStored = {"sdsd","dsdsds"};
		JComboBox<String> VACCB = new JComboBox<String>(toBeStored); // set up JComboBox
		ComboBoxModel<String> temp=VACCB.getModel();
		VACComboBox.setModel(temp);
		VACComboBox.setSelectedIndex(list.size()-1);
		st=toBeStored[VACComboBox.getSelectedIndex()];
	/*	VACComboBox.setMaximumRowCount(3);
		VACComboBox.addItemListener(
				new ItemListener() // anonymous inner class
				{
				// handle JComboBox event
				@Override
				public void itemStateChanged(ItemEvent event)
					{
						}
				} // end anonymous inner class
				);*/
	}
	
	
	public ValEditor(){
		getVacs();
		//setLayout(new FlowLayout());
		makeN.addActionListener(this);
		add(VACComboBox);
		VACComboBox.addItemListener(
			new ItemListener() 
			{
			@Override
				public void itemStateChanged(ItemEvent event)
				{
			
					if (event.getStateChange() == ItemEvent.SELECTED)
					st=toBeStored[VACComboBox.getSelectedIndex()];
				}
			}			 
			);
		add(makeN);
		add(word);
		add(TextWord);
		add(expl);
		add(TextExpl);
		addWord.addActionListener(this);
		add(addWord);
		add(hint);
		setSize(400,500);
	    setVisible(true);  
	} 
	
	public static void creatVal(String s){
		 // TODO Auto-generated method stub  
            File file = new File(s+".txt");  
			//file.mkdir();
            try {  
                file.createNewFile(); //
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
      

            String str = "";  
            byte bt[] = new byte[1024];  
            bt = str.getBytes();  
            try {  
                FileOutputStream in = new FileOutputStream(file);  
                try {  
                    in.write(bt, 0, bt.length);  
                    in.close();  
                    // boolean success=true;  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            } catch (FileNotFoundException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
			}
	}
	
	
	/* public static String changeEncoding(String inputHtml)
            throws UnsupportedEncodingException {

			String newHtml = new String(inputHtml.getBytes("iso-8859-1"),"gb2312");
        return newHtml;

    } */
	
	
	public void actionPerformed(ActionEvent actionEvent){
		if (actionEvent.getSource()==makeN){
			st=JOptionPane.showInputDialog("Enter the name of vocabulary:");
			creatVal(st);
			AppendToFile.appendMethodA("VAC_info.txt",st+"\n");
			AppendToFile.appendMethodA("book_num.txt","0\n");
			getVacs();
			hint.setText("successfully add the book:"+st);
		}
		if (actionEvent.getSource()==addWord){
			
			String wd=new String();
			
			//try{
			 wd=  TextWord.getText();
			//}catch (UnsupportedEncodingException e){System.err.println("gaygay!");}
			int w_num=ReadFromFile.getNum(st)+1; 
			String el=TextExpl.getText();
			AppendToFile.appendMethodA(st+".txt",w_num+", "+wd+"\n");
			AppendToFile.appendMethodA(st+".txt",el+"\n");
			hint.setText("successfully add the word:"+wd);
			ReadFromFile.changeNum(st,w_num);
		}
		
		
	}

        /*public static void main(String[] args) {  
			
			ValEditor w=new ValEditor();
			w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	 
			w.setSize(500,600);
			w.setVisible(true);	  

        }  */

          
    }  