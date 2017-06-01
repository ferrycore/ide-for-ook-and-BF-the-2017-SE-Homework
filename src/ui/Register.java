package ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

public class Register extends Dialog {

static Font font;
private static Frame frame;
private Dialog registerr;
JButton name;
JButton password;
JTextField namefield;
JTextField passwordfield;
JButton cancelbutton,registerbutton;
JPanel pane;
public Register(JFrame frame){
super(frame,"register",true);
registerr =new Dialog(frame);
registerr.setSize(700,300);
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
passwordfield = new JTextField(16);
passwordfield.setFont(font);

registerbutton =new JButton("Register");
registerbutton.setFont(font);
registerbutton.setBackground(Color.WHITE);

cancelbutton = new JButton("Cancel");
cancelbutton.setFont(font);
cancelbutton.setBackground(Color.WHITE);

pane = new JPanel();
pane.setLayout(new GridLayout(3,1));
pane.add(name);
pane.add(namefield);
pane.add(password);
pane.add(passwordfield);
pane.add(registerbutton);
pane.add(cancelbutton);
pane.setVisible(true);
registerr.add(pane);
registerr.setLocationRelativeTo(null);
registerr.setVisible(true);
//添加监听器
cancelbutton.addActionListener(event -> registerr.setVisible(false));
registerbutton.addActionListener(new ActionListener(){

	@Override
	public void actionPerformed(ActionEvent arg0) {
	String username =namefield.getText();
	String userpassword =String.valueOf(passwordfield.getText());
	boolean nameempty =username.equals("");
	boolean passwordempty =userpassword.equals("");
	//确保用户名密码不能相同
	if(nameempty||passwordempty){
	JOptionPane.showMessageDialog(null, "Name or Password is empty!","Error",JOptionPane.WARNING_MESSAGE);
	}
	else{
		File file =new File("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+username);
		if(!file.exists()){
		file.mkdirs();
		try{
		BufferedWriter write =new BufferedWriter(new FileWriter("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+username+"\\password.txt"));
		write.write(userpassword);
		write.close();
		File file2 =new File("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+username+"\\BF");
		if(!file2.exists()){
		file2.mkdirs();
		File file3 =new File("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+username+"\\Oops");
		file3.mkdirs();
		}
		}
		catch(Exception e){
		e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "You have registered successfully!","Tip",JOptionPane.INFORMATION_MESSAGE);
		registerr.dispose();
		}
		else{
		JOptionPane.showMessageDialog(null, "This account has existed !","error",JOptionPane.WARNING_MESSAGE);
		
		}
	}
	}
	
});
}


}

