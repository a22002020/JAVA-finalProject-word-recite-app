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
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private JButton makeN=new JButton("Add Book");
	private JButton addWord=new JButton("Add Word");
	private JLabel word=new JLabel("Word:");
	private JTextField TextWord=new JTextField(10);
	private JLabel expl=new JLabel("Paraphrase:");
	private JTextField TextExpl=new JTextField(30);
	private String st;
	private JComboBox<String> VACComboBox=new JComboBox();
	private String[] toBeStored;
	private List<String> list;
	private JPanel jp1, jp2, jp3, jp4, jp5;
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
		jp5 = new JPanel();
		makeN.addActionListener(this);
		jp5.add(makeN);
		jp4 = new JPanel();
		jp3 = new JPanel();
		jp4.add(VACComboBox);
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
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp1.add(word);
		jp1.add(TextWord);
		jp2.add(expl);
		jp2.add(TextExpl);
		jp3.add(jp1);
		jp3.add(jp2);
		jp3.setLayout(new GridLayout(2, 1));
		jp4.add(jp3);
		add(jp4);
		addWord.addActionListener(this);
		jp5.add(addWord);
		jp5.setLayout(new GridLayout(1, 2));
		add(jp5);
		setLayout(new GridLayout(2, 1));
		//setSize(500,600);
	    //setVisible(true);  
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
			JOptionPane.showMessageDialog(null, "successfully add the book:"+st);
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
			//JOptionPane.showMessageDialog(null, "successfully add the word:"+wd);
			TextWord.setText("");
			TextExpl.setText("");
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