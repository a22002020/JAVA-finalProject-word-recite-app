	import javax.swing.JOptionPane;import java.io.File; 
import java.awt.event.*;
import javax.swing.*;
	import java.awt.*;
	import java.util.List; 
	import java.util.ArrayList;
	import java.util.Date;
import java.text.SimpleDateFormat;
public class Arrange extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField day_num=new JTextField(10);
	private JLabel dayH=new JLabel("Please input the number of days that you will keep learning:");
	private JButton makPlan=new JButton("make plan");
	private  JComboBox<String> VACComboBox=new JComboBox();
	private String[] toBeStored;
	private List<String> list;
	private String st;
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
	}
	public Arrange(){
		getVacs();
		setLayout(new FlowLayout());
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
		add(dayH);
		add(day_num);
		add(makPlan);
		makPlan.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent actionEvent){
		String xVal=day_num.getText();
		if (xVal=="")JOptionPane.showMessageDialog(null, "error!");
		else{
		int x=Integer.parseInt(xVal);
		
	List<String> ttempB=ReadFromFile.readFileByLines("VAC_info.txt");
	List<String> ttempN=ReadFromFile.readFileByLines("book_num.txt");
	String[] tempB=ttempB.toArray(new String[ttempB.size()]);
	String[] tempN=ttempN.toArray(new String[ttempN.size()]);
	int mark=0;
	for (int i=0;i<ttempB.size();i++) if (tempB[i].compareTo(st)==0) mark=i;
	int wordNum=Integer.parseInt(tempN[mark]);
	
	
		int dayWord=wordNum/x;
		
		if (dayWord<=0)
			JOptionPane.showMessageDialog(null, "The days are too long!");
		else{
			File file = new File(st+"/");  
			file.mkdirs();
			dayWord++;
			int[][]plan=new int[x][100];
			int []num=new int[x];
			for (int i=0;i<x;i++){
				for (int j=0;j<100;j++)
					plan[i][j]=-1;
				num[i]=0;
			}
			for (int i=0;i<x;i++){
				plan[i][num[i]]=i;num[i]++;
				if (i+1<x) {plan[i+1][num[i+1]]=i;num[i+1]++;}
				if (i+2<x) {plan[i+2][num[i+2]]=i;num[i+2]++;}
				if (i+4<x) {plan[i+4][num[i+4]]=i;num[i+4]++;}
				if (i+7<x) {plan[i+7][num[i+7]]=i;num[i+7]++;}
				if (i+15<x) {plan[i+15][num[i+15]]=i;num[i+15]++;}
			}
			for (int i=0;i<x;i++){

				 ValEditor.creatVal(st+"/day"+Integer.toString(i));
				 String buffer=new String("");
				 for (int j=0;j<num[i];j++){
					int t=plan[i][j];
					for (int k=t*dayWord;k<t*dayWord+dayWord;k++){
						if (buffer.length()<3000)buffer=buffer+Integer.toString(k)+"\n";
						else{
						AppendToFile.appendMethodA(st+"/day"+Integer.toString(i)+".txt",buffer);
						buffer="";
						}
					}
				 }
				AppendToFile.appendMethodA(st+"/day"+Integer.toString(i)+".txt",buffer);
			}
			
			
			 ValEditor.creatVal("current_plan");
			 AppendToFile.appendMethodA("current_plan.txt",st+"\n");
			 Date now = new Date();
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 String hehe = dateFormat.format( now ); 
			 AppendToFile.appendMethodA("current_plan.txt",hehe+"\n");
			JOptionPane.showMessageDialog(null, "successfully build the studying plan!");
		}
		
		
		}
	}

 }