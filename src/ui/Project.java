package ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class Project extends Dialog{
	static Font font;
	private static Frame frame;
	private Dialog loginn;
	JButton name;
	JButton password;
	JTextField namefield;
	JPasswordField passwordfield;
	JButton loginbutton,cancelbutton;
	JPanel pane;
	public Project(JFrame frame,String username,String file,String  dive,JFrame frame1,JMenu openitem,JMenu openitem2,JMenu versionmenu,JTextArea textarea){
	super(frame1,"Project",false);
	loginn =new Dialog(frame1);
	loginn.setSize(700,300);
	font =new Font("Times New Roman",Font.PLAIN,18);

	name =new JButton("Project Name");
	name.setFont(font);
	name.setBackground(Color.WHITE);
	name.setEnabled(false);
	namefield = new JTextField(16);
	namefield.setFont(font);
	loginbutton =new JButton("Save");
	loginbutton.setFont(font);
	loginbutton.setBackground(Color.WHITE);
	cancelbutton = new JButton("Cancel");
	cancelbutton.setFont(font);
	cancelbutton.setBackground(Color.WHITE);
    
	pane = new JPanel();
	pane.setLayout(new GridLayout(2,1));
	pane.add(name);
	pane.add(namefield);
	pane.add(loginbutton);
	pane.add(cancelbutton);
	pane.setVisible(true);
	loginn.add(pane);
	loginn.setLocationRelativeTo(null);
	loginn.setVisible(true);
	//添加监听器
	cancelbutton.addActionListener(event -> loginn.setVisible(false));
	loginbutton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		String filename =namefield.getText();
		try {
			RemoteHelper.getInstance().getIOService().writeFile(file, username, filename, dive);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      JOptionPane.showMessageDialog(null, "Save successfully!","Tip",JOptionPane.INFORMATION_MESSAGE);
	      loginn.setVisible(false);
	      try{
	    	  String  name1 =RemoteHelper.getInstance().getUserService().getname();
	    	   MainFrame.addfile(name1, openitem, textarea, frame1, versionmenu);
	    	      MainFrame.addfile2(name1, openitem2, textarea, frame1, versionmenu);
	    	  }
	    	   catch(Exception e2){
	    		e2.printStackTrace();
	    	   }
	      
		}

	
	});

}}
