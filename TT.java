import javax.swing.JOptionPane;
import java.io.File; 
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
import java.util.Random;
import java.awt.Robot;  
import java.util.Timer;
public class TT extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton gay=new JButton("next");
	private JButton dgay=new JButton("last");
	private String bookname;
	private int day_num;
	private JLabel bknm;
	private JLabel dnm;
	private JLabel is_right,duilegangcuole,duile,cuole;
	private JLabel words=new JLabel();
	private JButton AS1=new JButton();
	private JButton AS2=new JButton();
	private JButton AS3=new JButton();
	private JButton AS4=new JButton();
	private JList<String> bookJList;
	private JTextArea exp=new JTextArea(5,20);
	int todayMax,bookMax;
	private List<String> listmode;
	private List<String> explist;
	String[] realV_today;
	String[] exp_today;
	String[]Vacs;
	int correct,duilejige=0,cuolejige=0;
	private int i_n;
	String []todayVacs ;
	
	void drawP(int wdnum){
		
		words.setText(realV_today[wdnum]);
		int max=bookMax/2-1;
		 Random random = new Random();
		 int s = random.nextInt(max);
		 while (s==wdnum)s = random.nextInt(max);
		 String ans=Vacs[s*2+1];
		 if (ans.length()>20)ans=ans.substring(0,20)+"...";
		AS1.setText(ans);
		s = random.nextInt(max);while (s==wdnum)s = random.nextInt(max);
		ans=Vacs[s*2+1];
		 if (ans.length()>20)ans=ans.substring(0,20)+"...";
		AS2.setText(ans);
		s = random.nextInt(max);while (s==wdnum)s = random.nextInt(max);
		ans=Vacs[s*2+1];
		 if (ans.length()>20)ans=ans.substring(0,20)+"...";
		AS3.setText(ans);
		s = random.nextInt(max);while (s==wdnum)s = random.nextInt(max);
		ans=Vacs[s*2+1];
		 if (ans.length()>20)ans=ans.substring(0,20)+"...";
		AS4.setText(ans);
		s= random.nextInt(3);
		correct=s;
		ans=exp_today[wdnum];
		 if (ans.length()>20)ans=ans.substring(0,20)+"...";
		if (s==0)AS1.setText(ans);
		if (s==1)AS2.setText(ans);
		if (s==2)AS3.setText(ans);
		if (s==3)AS4.setText(ans);
		
		
	}
	
	public TT(){
		setLayout(new FlowLayout());
		day_num=CIT.day_num;
		bookname=CIT.bookname;
		//System.out.println(bookname+" "+day_num);
		File file=new File(bookname+"/day"+Integer.toString(day_num)+".txt");
		
		if (!file.exists()){    if (bookname==null) JOptionPane.showMessageDialog(null,"please recite words first!");    else   JOptionPane.showMessageDialog(null,"You have finished your plan!");}
		else{
			List<String> list = new ArrayList<String>();  
			list=ReadFromFile.readFileByLines(bookname+"/day"+Integer.toString(day_num)+".txt");
			todayVacs = list.toArray(new String[list.size()]);
			 todayMax=list.size();
			list=ReadFromFile.readFileByLines(bookname+".txt");
			Vacs = list.toArray(new String[list.size()]);
			
			bookMax=list.size();

			
			
			bknm=new JLabel(bookname+":");
			dnm=new JLabel("day "+day_num);
			is_right=new JLabel("");
			duilegangcuole=new JLabel("right/tested:");
			duile=new JLabel("0"+"/");cuole=new JLabel("0");
			/*add(bknm);add(dnm);add(words);add(AS1);add(AS2);add(AS3);add(AS4);add(is_right);add(duilegangcuole);
			add(duile);add(cuole);*/
			
			
		setLayout(null);
		
		
		bknm.setFont(new Font("Arial", Font.PLAIN, 21));
		bknm.setBounds(63, 29, 308, 38);
		add(bknm);
		
		
		dnm.setFont(new Font("Arial", Font.PLAIN, 21));
		dnm.setBounds(443, 37, 118, 23);
		add(dnm);
		
		
		words.setFont(new Font("SimSun", Font.PLAIN, 25));
		words.setHorizontalAlignment(SwingConstants.CENTER);
		words.setBounds(113, 94, 374, 98);
		add(words);
		
		
		AS1.setFont(new Font("黑体", Font.PLAIN, 15));
		AS1.setBounds(164, 202, 271, 38);
		add(AS1);
		
		
		AS2.setFont(new Font("黑体", Font.PLAIN, 15));
		AS2.setBounds(164, 269, 271, 38);
		add(AS2);
		
		
		AS3.setFont(new Font("黑体", Font.PLAIN, 15));
		AS3.setBounds(164, 337, 271, 38);
		add(AS3);
		
		
		AS4.setFont(new Font("黑体", Font.PLAIN, 15));
		AS4.setBounds(164, 410, 271, 38);
		add(AS4);
		
		
		duilegangcuole.setFont(new Font("Arial", Font.PLAIN, 14));
		duilegangcuole.setBounds(62, 500, 118, 50);
		add(duilegangcuole);
		
		
		duile.setFont(new Font("Arial", Font.PLAIN, 14));
		duile.setBounds(190, 500, 29, 50);
		add(duile);
		
		
		cuole.setFont(new Font("Arial", Font.PLAIN, 14));
		cuole.setBounds(227, 500, 29, 50);
		add(cuole);
		
		
		is_right.setForeground(Color.RED);
		is_right.setHorizontalAlignment(SwingConstants.CENTER);
		is_right.setFont(new Font("Berlin Sans FB", Font.PLAIN, 55));
		is_right.setBounds(478, 86, 112, 106);
		add(is_right);
			
			
			
			
			
			AS1.addActionListener(this);
			AS2.addActionListener(this);
			AS3.addActionListener(this);
			AS4.addActionListener(this);
			listmode=new ArrayList<String> ();
			listmode.clear();
			explist=new ArrayList<String>();
			
			for (int i=0;i<todayMax;i++){
				int word_num=Integer.parseInt(todayVacs[i]);	
				//word_num--;
				if (word_num*2<bookMax) {listmode.add(Vacs[word_num*2]);explist.add(Vacs[word_num*2+1]);}
				//System.out.println(Vacs[word_num*2]);
			}
			realV_today=listmode.toArray(new String[listmode.size()]);	
			exp_today=explist.toArray(new String[explist.size()]);
			i_n=0;
			drawP(i_n);
			System.out.println(bookMax);
			
		
		}
		
		
	}

	
	public void actionPerformed(ActionEvent actionEvent) {
		boolean right=false;
		if (correct==0&&actionEvent.getSource()==AS1)right=true;
		if (correct==1&&actionEvent.getSource()==AS2)right=true;
		if (correct==2&&actionEvent.getSource()==AS3)right=true;
		if (correct==3&&actionEvent.getSource()==AS4)right=true;
		if (right){is_right.setText("\u221A");duilejige++;is_right.setForeground(Color.GREEN);is_right.setFont(new Font("Berlin Sans FB", Font.PLAIN, 55));}
		else{ is_right.setText("\u00D7");cuolejige++;is_right.setForeground(Color.RED);is_right.setFont(new Font("Berlin Sans FB", Font.PLAIN, 85));}
		duile.setText(Integer.toString(duilejige)+"/");
		cuole.setText(Integer.toString(duilejige+cuolejige));
		
		javax.swing.Timer timer = new javax.swing.Timer(500, new ActionListener() {public void actionPerformed(ActionEvent e){
		is_right.setText("");
		i_n++;
		int shu=Integer.parseInt(todayVacs[i_n]);
		System.out.println(i_n+" "+shu);
		if(i_n<todayMax&&shu<bookMax/2)drawP(i_n);else  JOptionPane.showMessageDialog(null,"you have finished today's test!");
		
		}});
		timer.setRepeats(false);
		timer.start(); 
		
		
	}
}