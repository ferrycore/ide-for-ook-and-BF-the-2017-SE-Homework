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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class Login extends Dialog {
static Font font;
private static Frame frame;
private Dialog loginn;
JButton name;
JButton password;
JTextField namefield;
JPasswordField passwordfield;
JButton loginbutton,cancelbutton;
JPanel pane;
public Login(JFrame frame,JMenu openitem,JMenu openitem2,JMenu versionmenu,JTextArea textarea){
super(frame,"login",false);
loginn =new Dialog(frame);
loginn.setSize(700,300);
font =new Font("Times New Roman",Font.PLAIN,18);

name =new JButton("Name");
name.setFont(font);
name.setBackground(Color.WHITE);
name.setEnabled(false);
namefield = new JTextField(16);
namefield.setFont(font);

password =new JButton("Password");
password.setFont(font);
password.setBackground(Color.WHITE);
password.setEnabled(false);
passwordfield =new JPasswordField(16);
passwordfield.setFont(font);

loginbutton =new JButton("Login");
loginbutton.setFont(font);
loginbutton.setBackground(Color.WHITE);

cancelbutton = new JButton("Cancel");
cancelbutton.setFont(font);
cancelbutton.setBackground(Color.WHITE);

pane = new JPanel();
pane.setLayout(new GridLayout(3,1));
pane.add(name);
pane.add(namefield);
pane.add(password);
pane.add(passwordfield);
pane.add(loginbutton);
pane.add(cancelbutton);
pane.setVisible(true);
loginn.add(pane);
loginn.setLocationRelativeTo(null);
loginn.setVisible(true);
//添加监听器
cancelbutton.addActionListener(event -> loginn.setVisible(false));
loginbutton.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
	 String cherry =namefield.getText();
	 
	 String ferry =String.valueOf(passwordfield.getPassword());
	
	 try{
	  boolean haha =RemoteHelper.getInstance().getUserService().isok(cherry,ferry);
	  boolean ishaha =RemoteHelper.getInstance().getUserService().islogging();
	  if(haha==true){
	  if(ishaha==true){
	  JOptionPane.showMessageDialog(null, "You have logined!","Warning",JOptionPane.INFORMATION_MESSAGE);  
	  }
	  else{
      JOptionPane.showMessageDialog(null, "Login successfully!","Tip",JOptionPane.INFORMATION_MESSAGE); 
      
      loginn.dispose();

      String name5 =RemoteHelper.getInstance().getUserService().getname();

      boolean gun =RemoteHelper.getInstance().getUserService().login(ferry, cherry);
      MainFrame.addfile(name5, openitem, textarea, frame, versionmenu);
      MainFrame.addfile2(name5, openitem2, textarea, frame, versionmenu);
	  }
	  
	  }
	  else{
		  JOptionPane.showMessageDialog(null, "Wrong password!","Warning",JOptionPane.INFORMATION_MESSAGE);  

	  } }
	
	 catch(RemoteException e){
		e.printStackTrace(); 
	 }
	}
	
});
}


}
