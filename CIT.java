	import javax.swing.JOptionPane;import java.io.File; 
import java.awt.event.*;
import javax.swing.*;
	import java.awt.*;
	import java.util.List; 
	import java.util.ArrayList;
	import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.text.ParseException;
public class CIT extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton gay=new JButton("next");
	private JButton dgay=new JButton("last");
	public static String bookname;
	public static int day_num;
	private JLabel bknm;
	private JLabel dnm;
	private JList<String> bookJList;
	private JTextArea exp=new JTextArea(5,20);
	int todayMax,bookMax;
	private DefaultListModel<String> listmode;
	public  void getDayNum(){
		
		List<String> list = new ArrayList<String>();    
		list=ReadFromFile.readFileByLines("current_plan.txt");
		String[] toBeStored = list.toArray(new String[list.size()]); 
		bookname=toBeStored[0];
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String hehe = dateFormat.format( now ); 
		
		int day=0;
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
        Date beginDate;
        Date endDate;
	try
        {
            beginDate = dateFormat.parse(toBeStored[1]);
            endDate= dateFormat.parse(hehe);   
            day=(int)((endDate.getTime()-beginDate.getTime())/(24*60*60*1000));   
        } catch (ParseException e)
        {
            e.printStackTrace();
        }   
		day_num=day;
	}
	
	public CIT(){
		
		setLayout(null);
		getDayNum();
		System.out.println(bookname+" "+day_num);
		File file=new File(bookname+"/day"+Integer.toString(day_num)+".txt");
		
		if (!file.exists()){JOptionPane.showMessageDialog(null,"You have finished your plan!");}
		else{
			List<String> list = new ArrayList<String>();  
			list=ReadFromFile.readFileByLines(bookname+"/day"+Integer.toString(day_num)+".txt");
			String []todayVacs = list.toArray(new String[list.size()]);
			 todayMax=list.size();
			list=ReadFromFile.readFileByLines(bookname+".txt");
			String[]Vacs = list.toArray(new String[list.size()]);
			
			 bookMax=list.size();

			
			
			
			bknm=new JLabel(bookname+":");
			dnm=new JLabel("day "+day_num);
			bknm.setFont(new Font("Arial", Font.PLAIN, 21));
			bknm.setBounds(63, 29, 308, 38);
			
			dnm.setFont(new Font("Arial", Font.PLAIN, 21));
			dnm.setBounds(443, 37, 118, 23);
			add(bknm);add(dnm);
			
			listmode=new DefaultListModel<String> ();
			listmode.clear();
			for (int i=0;i<todayMax;i++){
				int word_num=Integer.parseInt(todayVacs[i]);
				
				//word_num--;
				if (word_num*2<bookMax) listmode.addElement(Vacs[word_num*2]);
				//System.out.println(Vacs[word_num*2]);
			}
				
				bookJList=new JList<String> (listmode);
				JScrollPane sclist=new JScrollPane(bookJList);
				bookJList.setVisibleRowCount(10);
				bookJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						bookJList.addListSelectionListener(
				new ListSelectionListener() // anonymous inner class
				 {
					 // handle list selection events
					 @Override
					 public void valueChanged(ListSelectionEvent event)
					{
						int word_num=Integer.parseInt(todayVacs[bookJList.getSelectedIndex()]);
						//word_num--;
						if (bookJList.getSelectedIndex()>=0)
						exp.setText(Vacs[word_num*2+1]);
						exp.setEditable(false);
					}
				 }
				 );
				 bookJList.setSelectedIndex(0);
				 
				 sclist.setBounds(26, 135, 251, 382);
				add(sclist);
				exp.setBounds(307, 133, 236, 205);
				add(exp);
				exp.setEditable(false);
				exp.setLineWrap(true);  
				exp.setWrapStyleWord(true); 
			
				gay.setFont(new Font("Arial", Font.PLAIN, 20));
				gay.setBounds(306, 393, 105, 48);
				dgay.setFont(new Font("Arial", Font.PLAIN, 20));
				dgay.setBounds(438, 393, 105, 48);
				add(dgay);
			add(gay);
			gay.addActionListener(this);
			add(dgay);
			dgay.addActionListener(this);
		
		}
		
		
	}
	public void actionPerformed(ActionEvent actionEvent){
		if (actionEvent.getSource()==gay){int id=bookJList.getSelectedIndex();id++;if (id<todayMax)bookJList.setSelectedIndex(id); }
		if (actionEvent.getSource()==dgay){int id=bookJList.getSelectedIndex();id--;if (id>=0)bookJList.setSelectedIndex(id); }
	}
}