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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
      
    public class WordEditor extends JPanel implements ActionListener {  
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private DefaultListModel<String> listmode;
	private JList<String> bookJList;
	private String st;
	private JLabel hint=new JLabel("");
	private JComboBox<String> VACComboBox=new JComboBox();
	private String[] toBeStored;
	private List<String> list;
	private String[] Vacs;
	private String[] Exps;
	private JTextArea exp=new JTextArea(5,20);
	private JButton edit=new JButton("edit");
	private JButton save=new JButton("save");
	
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
	
	void setModel(){
		list = new ArrayList<String>();  
		list=ReadFromFile.readFileByLines(st+".txt");
		Vacs = list.toArray(new String[list.size()]);
		Exps = new String[list.size()/2];
		
		
		listmode.clear();
		for (int i=0;i<list.size();i++){
			if (i%2==0)
			listmode.addElement(Vacs[i]);
		else Exps[i/2]=Vacs[i];
			} // set up JComboBox
		//bookJList.updateUI();
	}
	public WordEditor(){
		getVacs();
		//setLayout(new FlowLayout());
		//makeN.addActionListener(this);
		add(VACComboBox);
		VACComboBox.addItemListener(
			new ItemListener() 
			{
			@Override
				public void itemStateChanged(ItemEvent event)
				{
			
					if (event.getStateChange() == ItemEvent.SELECTED)
					st=toBeStored[VACComboBox.getSelectedIndex()];
					setModel();
					bookJList.updateUI();
				}
			}			 
			);
			listmode=new DefaultListModel<String> ();
			setModel();
			
			bookJList=new JList (listmode);
			JScrollPane sclist=new JScrollPane(bookJList);
			bookJList.setVisibleRowCount(10);
			bookJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//sclist.setPreferredSize(100);
			   bookJList.setFixedCellWidth(150);
			bookJList.addListSelectionListener(
			new ListSelectionListener() // anonymous inner class
			 {
				 // handle list selection events
				 @Override
				 public void valueChanged(ListSelectionEvent event)
				{
					if (bookJList.getSelectedIndex()>=0)
					exp.setText(Exps[bookJList.getSelectedIndex()]);
					exp.setEditable(false);
				}
			 }
			 );
			   
			   
			add(sclist);
			add(exp);
			exp.setEditable(false);
			exp.setLineWrap(true);  
			exp.setWrapStyleWord(true); 
			add(edit);
			edit.addActionListener(this);
			add(save);
			save.addActionListener(this);

		//setSize(500,600);
	    //setVisible(true);  
	} 
	
	
	public void actionPerformed(ActionEvent actionEvent){

		if (actionEvent.getSource()==edit){exp.setEditable(true);}
		if (actionEvent.getSource()==save){
			st=toBeStored[VACComboBox.getSelectedIndex()];
			ReadFromFile.saveWord(list,bookJList.getSelectedIndex(),exp.getText(),st);
			
			setModel();
			bookJList.updateUI();
		}
		
		
	} 

          
    }  