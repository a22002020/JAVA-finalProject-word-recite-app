import java.awt.event.*;
import javax.swing.*;
import java.awt.*;import java.io.File; 
public class mainUI extends JFrame implements ActionListener {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JButton VacE = new JButton("Add");
		private JButton WdE = new JButton("View");
		private JButton mkPlan = new JButton("Plan");
		private JButton recitation = new JButton("Recite");
		private JButton test = new JButton("Test");
		private JButton game = new JButton("Game");
		private JTextField text = new JTextField(10);
		private	ValEditor valeditor;//= new ValEditor();
		private CIT cite;
		private TT tet;
		private	WordEditor wordeditor ;//= new WordEditor();
		private Arrange arrange;// = new Arrange();
		private boolean val = false, word = false, arr = false, nulll_b = true,cit=false,tt=false;
		private JPanel nulll;
		//private final Container container;
		public mainUI(){
			val=false;word=false;arr=false;cit=false;tt=false;
			JPanel p = new JPanel();
			//container = getContentPane();	
			p.add(VacE);
			p.add(WdE);
			p.add(mkPlan);
			p.add(recitation);
			p.add(test);
			p.add(game);
			p.setLayout(new GridLayout(1, 6));
			text.setText("Main Menu");
			text.setHorizontalAlignment(JTextField.CENTER);
			text.setEditable(false);
			VacE.addActionListener(this);
			WdE.addActionListener(this);
			recitation.addActionListener(this);
			test.addActionListener(this);
			mkPlan.addActionListener(this);
			setLayout(new BorderLayout());
			add(text, BorderLayout.SOUTH);
			add(p, BorderLayout.NORTH);
			setFocusable(true);
			nulll = new JPanel();
			add(nulll, BorderLayout.CENTER);
			nulll.setVisible(true);
			
			/*
			valeditor= new ValEditor();
			wordeditor = new WordEditor();
			arrange = new Arrange();
			add(valeditor, BorderLayout.CENTER);
			add(wordeditor, BorderLayout.CENTER);
			add(arrange, BorderLayout.CENTER);
			valeditor.setVisible(false);
			wordeditor.setVisible(false);
			arrange.setVisible(false);
			//wordeditor.invalidate();
			//valeditor.invalidate();
			//arrange.invalidate();*/
		}
		public void actionPerformed(ActionEvent actionEvent)
		{

			
			if (actionEvent.getSource() == VacE)
			{
				if (!val)
				{
					valeditor = new ValEditor();
					//valeditor.setFocusable(true);
					add(valeditor, BorderLayout.CENTER);
					valeditor.setVisible(true);
					text.setText("Adding words or phrases...");
					val = true;
				}
				if (word)
				{
					wordeditor.setVisible(false);
					wordeditor.invalidate();
					word = false;
				}
				if (arr)
				{
					arrange.setVisible(false);
					arrange.invalidate();
					arr = false;
				}
				if (nulll_b){
					nulll.setVisible(false);
					nulll.invalidate();
					nulll_b = false;
				}
				if (cit){
					cite.setVisible(false);
					cite.invalidate();
					cit=false;
				}
				if (tt){
					tet.setVisible(false);
					tet.invalidate();
					tt=false;
				}
			}
			else if (actionEvent.getSource()==WdE)
			{
				if (val)
				{
					valeditor.setVisible(false);
					valeditor.invalidate();
					val = false;
				}
				if (!word)
				{
					wordeditor = new WordEditor();
					//wordeditor.setFocusable(true);
					add(wordeditor, BorderLayout.CENTER);
					wordeditor.setVisible(true);
					text.setText("Editing words or phrases...");
					word = true;
				}
				if (arr)
				{
					arrange.setVisible(false);
					arrange.invalidate();
					arr = false;
				}
				if (nulll_b){
					nulll.setVisible(false);
					nulll.invalidate();
					nulll_b = false;
				}
				if (cit){
					cite.setVisible(false);
					cite.invalidate();
					cit=false;
				}
				if (tt){
					tet.setVisible(false);
					tet.invalidate();
					tt=false;
				}
			}
			else if (actionEvent.getSource()==mkPlan)
			{
				if (val)
				{
					valeditor.setVisible(false);
					valeditor.invalidate();
					val = false;
				}
				if (word)
				{
					wordeditor.setVisible(false);
					wordeditor.invalidate();
					word = false;
				}
				if (!arr)
				{
					arrange = new Arrange();
					//arrange.setFocusable(true);
					add(arrange, BorderLayout.CENTER);
					arrange.setVisible(true);
					text.setText("Making plan....");
					arr = true;
				}
				if (nulll_b){
					nulll.setVisible(false);
					nulll.invalidate();
					nulll_b = false;
				}
				if (cit){
					cite.setVisible(false);
					cite.invalidate();
					cit=false;
				}
				if (tt){
					tet.setVisible(false);
					tet.invalidate();
					tt=false;
				}
			}
			else if (actionEvent.getSource()==recitation){
				File file=new File("current_plan.txt");    
				if(file.exists())    
				{
				
				
					if (val)
					{
						valeditor.setVisible(false);
						valeditor.invalidate();
						val = false;
					}
					if (word)
					{
						wordeditor.setVisible(false);
						wordeditor.invalidate();
						word = false;
					}
					if (arr)
					{
						arrange.setVisible(false);
						arrange.invalidate();
						arr = false;
					}
					if (nulll_b){
						nulll.setVisible(false);
						nulll.invalidate();
						nulll_b = false;
					}
					if (!cit){
						cite=new CIT();
						add(cite,BorderLayout.CENTER);
						cite.setVisible(true);
						text.setText("recitating...");
						cit=true;
					}
					if (tt){
					tet.setVisible(false);
					tet.invalidate();
					tt=false;
					}
				
				}else{JOptionPane.showMessageDialog(null,"You haven't make the study plan!");}
			
			}
			else if (actionEvent.getSource()==test){
				File file=new File("current_plan.txt");    
				if(file.exists())    
				{
				
				
					if (val)
					{
						valeditor.setVisible(false);
						valeditor.invalidate();
						val = false;
					}
					if (word)
					{
						wordeditor.setVisible(false);
						wordeditor.invalidate();
						word = false;
					}
					if (arr)
					{
						arrange.setVisible(false);
						arrange.invalidate();
						arr = false;
					}
					if (nulll_b){
						nulll.setVisible(false);
						nulll.invalidate();
						nulll_b = false;
					}
					if (cit){
						cite.setVisible(false);
						cite.invalidate();
						cit=false;
					}
					if (!tt){
						tet=new TT();
						add(tet,BorderLayout.CENTER);
						tet.setVisible(true);
						text.setText("testing...");
						tt=true;
					}
				
				}else{JOptionPane.showMessageDialog(null,"You haven't make the study plan!");}
			
			}
			
		}


	    public static void main(String[] args) {  
			mainUI w = new mainUI();
			w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	 
			w.setSize(650, 650);
			w.setResizable(false);
			w.setVisible(true);	  

        //ReadFromFile.readFileByLines(fileName);  

    } 
 }