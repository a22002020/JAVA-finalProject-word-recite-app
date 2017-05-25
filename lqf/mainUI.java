import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class mainUI extends JFrame implements ActionListener {
		private JButton VacE = new JButton("Add");
		private JButton WdE = new JButton("Edit");
		private JButton mkPlan = new JButton("Plan");
		private JTextField text = new JTextField(10);
		private	ValEditor valeditor;//= new ValEditor();
		private	WordEditor wordeditor;// = new WordEditor();
		private Arrange arrange;// = new Arrange();
		private boolean val = false, word = false, arr = false;
		//private final Container container;
		public mainUI(){
			JPanel p = new JPanel();
			//container = getContentPane();	
			p.add(VacE);
			p.add(WdE);
			p.add(mkPlan);
			p.setLayout(new GridLayout(1, 3));
			text.setText("Main Menu");
			text.setHorizontalAlignment(JTextField.CENTER);
			text.setEditable(false);
			VacE.addActionListener(this);
			WdE.addActionListener(this);
			mkPlan.addActionListener(this);
			setLayout(new BorderLayout());
			add(text, BorderLayout.SOUTH);
			add(p, BorderLayout.NORTH);
			setFocusable(true);
			//add(valeditor, BorderLayout.CENTER);
			//add(wordeditor, BorderLayout.CENTER);
			//add(arrange, BorderLayout.CENTER);
			//valeditor.setVisible(false);
			//wordeditor.setVisible(false);
			//arrange.setVisible(false);
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
					text.setText("Making plan...");
					arr = true;
				}
			}	
		}


	    public static void main(String[] args) {  
			mainUI w = new mainUI();
			w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	 
			w.setSize(400,500);
			w.setVisible(true);	  

        //ReadFromFile.readFileByLines(fileName);  

    } 
 }