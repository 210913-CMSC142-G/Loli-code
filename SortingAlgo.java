import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class bsort {
	
	private JFrame frame;
	JTextField tf[]=new JTextField[10];
	int nums[]=new int[10];
	int nums2[]=new int[10];
	Color color[]= {null,new Color(255,102,102),new Color(255,153,0),new Color(255,255,153),new Color(218,112,214),Color.GRAY,Color.PINK};
	String sortalgo=null;
	JButton btn_sort = new JButton("Sort");
	JComboBox TypeOfSort = new JComboBox();
	JButton btn_next = new JButton("Next");
	JButton btn_back = new JButton("Input Again");
	JLabel lbl_index = new JLabel("");
	int n1,n2, snum1,snum2, ins1,inscount,index=0;
	private final JLabel lbl_0 = new JLabel("0");
	private final JLabel lbl_1 = new JLabel("1");
	private final JLabel lbl_2 = new JLabel("2");
	private final JLabel lbl_3 = new JLabel("3");
	private final JLabel lbl_4 = new JLabel("4");
	private final JLabel lbl_5 = new JLabel("5");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bsort window = new bsort();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public bsort() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sorting algorithm");
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 826, 465);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btn_sort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_sort.setBounds(325, 342, 167, 56);
		panel.add(btn_sort);
		
		for (int x=1;x<7;x++) {
			tf[x] = new JTextField();
			tf[x].setFont(new Font("Tahoma", Font.PLAIN, 34));
			tf[x].setHorizontalAlignment(SwingConstants.CENTER);
			tf[x].setColumns(10);
			tf[x].setBackground(color[x]);
			panel.add(tf[x]);
		}
		
		tf[1].setBounds(60, 198, 96, 73);
		tf[2].setBounds(186, 198, 96, 73);
		tf[3].setBounds(316, 198, 96, 73);
		tf[4].setBounds(440, 198, 96, 73);
		tf[5].setBounds(568, 198, 96, 73);
		tf[6].setBounds(689, 198, 96, 73);
	
		TypeOfSort.setFont(new Font("Tahoma", Font.PLAIN, 26));
		TypeOfSort.setModel(new DefaultComboBoxModel(new String[] {"Bubble Sort", "Selection Sort", "Insertion Sort"}));
		TypeOfSort.setBounds(292, 63, 231, 67);
		panel.add(TypeOfSort);
		
		btn_next.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_next.setBounds(115, 342, 167, 56);
		btn_next.setVisible(false);
		panel.add(btn_next);

		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_back.setBounds(537, 342, 167, 56);
		btn_back.setVisible(false);
		panel.add(btn_back);
		
	
		lbl_index.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbl_index.setBounds(636, 75, 152, 55);
		panel.add(lbl_index);
		lbl_0.setForeground(Color.GRAY);
		lbl_0.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_0.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbl_0.setBounds(84, 272, 49, 35);
		
		panel.add(lbl_0);
		lbl_1.setForeground(Color.GRAY);
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbl_1.setBounds(208, 272, 49, 35);
		
		panel.add(lbl_1);
		lbl_2.setForeground(Color.GRAY);
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbl_2.setBounds(342, 272, 49, 35);
		
		panel.add(lbl_2);
		lbl_3.setForeground(Color.GRAY);
		lbl_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_3.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbl_3.setBounds(466, 272, 49, 35);
		
		panel.add(lbl_3);
		lbl_4.setForeground(Color.GRAY);
		lbl_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_4.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbl_4.setBounds(593, 272, 49, 35);
		
		panel.add(lbl_4);
		lbl_5.setForeground(Color.GRAY);
		lbl_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_5.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbl_5.setBounds(706, 272, 49, 35);
		
		panel.add(lbl_5);
		actionlisteners();
	}
	
	void BubbleSort(){
		int temp=0;
		Color tempc=null;
		if (nums[n1]>nums[n2]) {
			temp=nums[n1];
			nums[n1]=nums[n2];
			nums[n2]=temp;
			
			tempc=color[n1];
			color[n1]=color[n2];
			color[n2]=tempc;
			
		}
		
		for (int x=1;x<7;x++) {
	    	tf[x].setText(Integer.toString(nums[x]));
	    	tf[x].setBackground(color[x]);
	    	tf[x].setBorder(null);
	    }
		tf[n1].setBorder(new LineBorder(Color.GREEN, 4));
		tf[n2].setBorder(new LineBorder(Color.GREEN, 4));
		int next=n2+1;
		if (next!=7)
			tf[next].setBorder(new LineBorder(Color.YELLOW, 4));
		
	}
	
	void SelectionSort() {
		int temp=0,min=nums[snum1];
		Color tempc;
		for (int x=snum1;x<7;x++) {
			if(nums[x] < min){ 
		        min = nums[x]; 
		        snum2=x;
		      } 
		}
		if (nums[snum1]>nums[snum2]) {	
		temp=nums[snum1];
		nums[snum1]=nums[snum2];
		nums[snum2]=temp;
		
		tempc=color[snum1];
		color[snum1]=color[snum2];
		color[snum2]=tempc;
	
		}	
		for (int x=1;x<7;x++) {
	    	tf[x].setText(Integer.toString(nums[x]));
	    	tf[x].setBackground(color[x]);
	    	tf[x].setBorder(null);
	    }
		tf[snum1].setBorder(new LineBorder(Color.GREEN, 4));
		tf[snum2].setBorder(new LineBorder(Color.GREEN, 4));
		
	}
	
	void InsertionSort() {
		int temp=0,ins2;
		Color tempc;
		if ( ins1!=1) {
			 ins2=ins1-1;
		
			if(nums[ins1]<nums[ins2]) {
				temp=nums[ins1];
				nums[ins1]=nums[ins2];
				nums[ins2]=temp;
				
				tempc=color[ins1];
				color[ins1]=color[ins2];
				color[ins2]=tempc;
				ins1--;
				
				}
			else {
				inscount++;
				ins1=inscount;
			}
			
		}
		else if (ins1==1) {
			inscount++;
			ins1=inscount;
		}
		
		for (int x=1;x<7;x++) {
	    	tf[x].setText(Integer.toString(nums[x]));
	    	tf[x].setBackground(color[x]);
	    	tf[x].setBorder(null);
	    }
		tf[ins1].setBorder(new LineBorder(Color.GREEN, 4));
		
	}
	
	void QuickSort() {
		
	}
	
	public void actionlisteners() {		
		btn_sort.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	sortalgo=(String)TypeOfSort.getSelectedItem();
		    	TypeOfSort.setEnabled(false);
		    	btn_sort.setVisible(false);
		    	btn_next.setVisible(true);
		    	btn_back.setVisible(true);
		    	
		    	for (int x=1;x<7;x++) 
		    		nums[x]=Integer.parseInt(tf[x].getText());
		    	
		    	 if (sortalgo=="Bubble Sort") {
		    		 n1=1;n2=2;
		    		 BubbleSort();		   
		    	 }
		    	 else if (sortalgo=="Selection Sort") {
		    		 snum1=1;
		    		 SelectionSort();
		    	 }
		    	 else if (sortalgo=="Insertion Sort") {
		    		 index=1;
		    		 ins1=1;
		    		 inscount=1;
		    		 lbl_index.setText("Index: "+index);
		    		 InsertionSort();
		    	 }
		    	 else if (sortalgo=="Quick Sort") {
		    		 QuickSort();
		    		 
		    	 }
			    	
		    }  
		 }); 
		
		btn_back.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){ 
		    	TypeOfSort.setEnabled(true);
		    	lbl_index.setText("");
		    	for (int x=1;x<7;x++) 
		    		tf[x].setBorder(null);
		    	btn_sort.setVisible(true);
		    	btn_next.setVisible(false);
		    	btn_back.setVisible(false);
		    	for (int x=1;x<7;x++) {
		    		tf[x].setText("");
		    	}
		    }  
		 });   
		

		btn_next.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	
		    	 if (sortalgo=="Bubble Sort") {
		    		 if(n2==6) {
		 		    	n1=1;n2=2;
		 		    }
		 		    else {
		 		    	n1++;
		 		    	n2++;
		 		    }  
		    		 lbl_index.setText("");
		 		    BubbleSort();	
		    	 }
		    	 else if (sortalgo=="Selection Sort") {
		    		 snum1++;
		    		 SelectionSort();
		    	 }
		    	 else if (sortalgo=="Insertion Sort") {
		    		 InsertionSort();
		    		 index=inscount-1;
		    		 lbl_index.setText("Index: "+index);
		    	 }
		    	 else if (sortalgo=="Quick Sort") {
		    		 QuickSort();
		    	 }
		    	
		    }
		 });   
	}
}
