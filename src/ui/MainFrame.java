package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;

import rmi.RemoteHelper;


public class MainFrame extends JFrame {
	private JFrame frame;
	private JPanel southpanel;
	private JTextField textfield;
	private JTextArea input;
	private JTextArea output;
	private JScrollPane scrollPane;
	private JPanel textareapanel;
	private JTextArea textarea;
	private Font font;
	private Font font1;
	private int count =0;
	private int temp  =0;
	private Login logg;
	private ArrayList<String>stringlist = new ArrayList<>();
	private String name;
	private String codesaved;
	private JMenu openitem;
	private JMenu versionmenu;
	
	//BF 取出并且添加list
	public static void addfile(String name,JMenu openitem,JTextArea textarea,JFrame frame,JMenu versionmenu){
    openitem.removeAll();
    versionmenu.removeAll();
    textarea.setText("");
    File path1 =new File("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+name+"\\BF");
    File filename1[] =path1.listFiles();
    for(int i =0;i<filename1.length;i++){
    String haha =filename1[i].getName();
    JMenuItem gege =new JMenuItem(haha);
    openitem.add(gege);
    String ff =gege.getActionCommand();
    gege.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			 openitem.removeAll();
			    versionmenu.removeAll();
    File path2 =new File("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+name+"\\BF\\"+ff);
    String filelist2[] =path2.list();
    System.out.println(filelist2[0]);
    for(int j=0;j<filelist2.length;j++){
    String[] haha =filelist2[j].split("\\.");
    String firstname =haha[0];
    JMenuItem ffff = new JMenuItem(firstname);
    ffff.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		try{
		BufferedReader reader =new BufferedReader(new FileReader("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+name+"\\BF\\"+ff+"\\"+firstname+".txt"));
	    String fff =reader.readLine();
	    textarea.setText(fff);
	    reader.close();
		}
		catch(Exception e){
		e.printStackTrace();
		}
		}
    	
    });
    versionmenu.add(ffff);
    
    }
		}
    	
    });
  
    }
	}
	
	//Oops 存取 并添加List
	public static void addfile2(String name,JMenu openitem2,JTextArea textarea,JFrame frame,JMenu versionmenu){
	    openitem2.removeAll();
	    versionmenu.removeAll();
	    textarea.setText("");
	    File path1 =new File("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+name+"\\Oops");
	    File filename1[] =path1.listFiles();
	    for(int i =0;i<filename1.length;i++){
	    String haha =filename1[i].getName();
	    JMenuItem gege =new JMenuItem(haha);
	    openitem2.add(gege);
	    String ff =gege.getActionCommand();
	    gege.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
	    File path2 =new File("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+name+"\\Oops\\"+ff);
	    String filelist2[] =path2.list();
	    System.out.println(filelist2[0]);
	    for(int j=0;j<filelist2.length;j++){
	    String[] haha =filelist2[j].split("\\.");
	    String firstname =haha[0];
	    JMenuItem ffff = new JMenuItem(firstname);
	    ffff.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
			try{
			BufferedReader reader =new BufferedReader(new FileReader("C:\\Users\\core\\Desktop\\BFServer\\Users\\information\\"+name+"\\Oops\\"+ff+"\\"+firstname+".txt"));
		    String fff =reader.readLine();
		    textarea.setText(fff);
		    reader.close();
			}
			catch(Exception e){
			e.printStackTrace();
			}
			}
	    	
	    });
	    versionmenu.add(ffff);
	    
	    }
			}
	    	
	    });
	  
	    }
		}
		
	public MainFrame() {
	//初始化frame
	JFrame frame =new JFrame("BF IDE");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1500, 900);
	//设置输入输出基本框架
	frame.setLayout(new GridLayout(2,1));
	southpanel = new JPanel();
	textarea = new JTextArea(20,10);
	textarea.setLineWrap(true);
	font =new Font("Times New Roman",Font.PLAIN,40);
	font1 =new Font("Times New Roman",Font.ITALIC,40);
	textarea.setFont(font);
	textarea.setBackground(Color.WHITE);
	textarea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	textarea.addKeyListener(new KeyAdapter(){
	  public void keyPressed(KeyEvent e){
		 stringlist.add(textarea.getText());
		 count =stringlist.size();
	     temp =count-1;
	  }
	});
	frame.add(textarea);
	southpanel.setLayout(new GridLayout(1,2));
	input = new JTextArea(20,10);
	input.setLineWrap(true);
	input.setFont(font);
	southpanel.add(input);
	input.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	output = new JTextArea(20,10);
	output.setEnabled(false);
	output.setBackground(Color.white);
	output.setFont(font1);
	output.setDisabledTextColor(Color.BLACK);
	output.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	southpanel.add(output);
	frame.add(southpanel);
	int x =frame.getToolkit().getScreenSize().width;
	int y =frame.getToolkit().getScreenSize().height;
	frame.setBounds((x-1500)/2,(y-1000)/2,1500,900);
	frame.setVisible(true);
	//设置菜单项
	//file --new open save exit
	JMenuBar menubar =new JMenuBar();
	JMenu filemenu =new JMenu("Menu");
	JMenuItem newitem =new JMenuItem("New");
	JMenu openitem =new JMenu("Open from BF");
	JMenu openitem2 =new JMenu("Open from Oops");
	JMenu saveitem =new JMenu("Save as");
	saveitem.setBackground(Color.WHITE);
	JMenuItem bfsave =new JMenuItem("BF");
	JMenuItem oopsave =new JMenuItem("Oops");
	bfsave.setBackground(Color.WHITE);
	oopsave.setBackground(Color.WHITE);
	saveitem.add(bfsave);
	saveitem.add(oopsave);
	JMenuItem exititem =new JMenuItem("Exit");
	filemenu.add(newitem);
	filemenu.add(openitem);
	filemenu.add(openitem2);
	filemenu.add(saveitem);
	filemenu.add(exititem);
	menubar.add(filemenu);
	newitem.setBackground(Color.WHITE);
	openitem.setBackground(Color.WHITE);
	openitem2.setBackground(Color.WHITE);
	saveitem.setBackground(Color.WHITE);
	exititem.setBackground(Color.WHITE);
	//Run execute language
	JMenu runmenu =new JMenu("Run");
	JMenuItem executeitem =new JMenuItem("Execute");
	menubar.add(runmenu);
	runmenu.add(executeitem);
	JMenu languageitem =new JMenu("Language");
	ButtonGroup group =new ButtonGroup();
	JRadioButtonMenuItem BF =new JRadioButtonMenuItem("BF");
	BF.setSelected(true);
	JRadioButtonMenuItem Oops =new JRadioButtonMenuItem("Oops");
	group.add(BF);
	group.add(Oops);
    languageitem.add(BF);
    languageitem.add(Oops);
    runmenu.add(languageitem);
    languageitem.setBackground(Color.WHITE);
    executeitem.setBackground(Color.WHITE);
    BF.setBackground(Color.WHITE);
    Oops.setBackground(Color.WHITE);
	//Version
	JMenu versionmenu =new JMenu("Version");
	menubar.add(versionmenu);
	//Tool
	JMenu toolmenu =new JMenu("Tool");
	JMenuItem undoitem =new JMenuItem("Undo");
	JMenuItem redoitem =new JMenuItem("Redo");
	toolmenu.add(undoitem);
	toolmenu.add(redoitem);
	menubar.add(toolmenu);
	undoitem.setBackground(Color.WHITE);
	redoitem.setBackground(Color.WHITE);
	textarea.setText("");
	//Login in Login out Register
	JMenu log =new JMenu("Login");
	JMenuItem loginitem =new JMenuItem("Login in");
	JMenuItem logoutitem =new JMenuItem("Login out");
	JMenuItem registeritem =new JMenuItem("Register");
	menubar.add(javax.swing.Box.createHorizontalGlue());
	log.add(loginitem);
	log.add(logoutitem);
	log.add(registeritem);
	menubar.add(log);
	//以下为测试
	menubar.setBackground(Color.WHITE);
	frame.setJMenuBar(menubar);
	
	//添加监听器 
	//提取用户名 和 text
 
	//new 新建项目
	newitem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		try{
		 boolean islogin =RemoteHelper.getInstance().getUserService().islogging();
		 if(islogin){
		 textarea.setText("");
		 try{
	    	   name =RemoteHelper.getInstance().getUserService().getname();}
	    	   catch(Exception e2){
	    		e2.printStackTrace();
	    	   }
		   MainFrame.addfile(name, openitem, textarea, frame, versionmenu);
		    MainFrame.addfile2(name, openitem2, textarea, frame, versionmenu);
		 }
		 else{
	     JOptionPane.showMessageDialog(null, "Please login first!","Warning",JOptionPane.WARNING_MESSAGE);
		 }
		}catch(RemoteException e){
		e.printStackTrace();
		}
			
		}
		
	});
	//login in 添加监听器
	loginitem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
		//新建登陆界面
	    new Login(frame,openitem,openitem2,versionmenu,textarea);
			 
			
		}
		
	});
	//bfsave 田间监听器
	bfsave.addActionListener(new ActionListener(){
	
		@Override
		public void actionPerformed(ActionEvent e) {
		try{
       boolean gg =RemoteHelper.getInstance().getUserService().islogging();
       if(gg==false){
  	   JOptionPane.showMessageDialog(null, "Please login first!","Warning",JOptionPane.WARNING_MESSAGE);  
       }
       else{
    	   codesaved =textarea.getText();
    	   try{
    	   name =RemoteHelper.getInstance().getUserService().getname();}
    	   catch(Exception e2){
    		e2.printStackTrace();
    	   }
		new Project(frame,name,codesaved,"BF",frame,openitem,openitem2,versionmenu,textarea); 
		MainFrame.addfile(name, openitem, textarea, frame, versionmenu);
		MainFrame.addfile2(name, openitem2, textarea, frame, versionmenu);
       }
       }
		catch(RemoteException e1){
		e1.printStackTrace();
		}
		
		}
		
	});

	//oopsave 添加监听器
	oopsave.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e) {
		try{
       boolean gg =RemoteHelper.getInstance().getUserService().islogging();
       if(gg==false){
  	   JOptionPane.showMessageDialog(null, "Please login first!","Warning",JOptionPane.WARNING_MESSAGE);  
       }
       else{
    	   codesaved =textarea.getText();
    	   try{
    	   name =RemoteHelper.getInstance().getUserService().getname();}
    	   catch(Exception e2){
    		e2.printStackTrace();
    	   }
		new Project(frame,name,codesaved,"Oops",frame,openitem,openitem2,versionmenu,textarea);   
		MainFrame.addfile(name, openitem, textarea, frame, versionmenu);
		MainFrame.addfile2(name, openitem2, textarea, frame, versionmenu);
       }
       }
		catch(RemoteException e1){
		e1.printStackTrace();
		}
		
		}
		
	});
	
			
		
		
	
	
	
	
	
	
	//oopsave 添加监听器
	
	
	
	
	//login out 添加监听器
	logoutitem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				RemoteHelper.getInstance().getUserService().haslogout();
				
			}
			catch(RemoteException e1){
				e1.printStackTrace();
			}
			frame.dispose();
			
				new MainFrame();
		

			
			
		}
	
	});
	//register 添加监听器
	registeritem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			new Register(frame);
			
		}
		
	});
	// open 添加监听器
    openitem.addActionListener(new MenuItemActionListener());
    // save 添加监听器
    saveitem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
        
			
		}
    	
    });
    //exit 添加监听器(已实现)
    exititem.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		 frame.dispose();
			
		}
    	
    });
    //undo redo 的添加器的实现
    undoitem.addActionListener(new undolistener());
    redoitem.addActionListener(new redolistener());
    
    
    executeitem.addActionListener(new ActionListener(){

		@Override
	   	public void actionPerformed(ActionEvent arg0) {
		try{
		if(BF.isSelected()){
		String output1 =RemoteHelper.getInstance().getExecuteService().execute(textarea.getText(),input.getText());
		output.setText(output1);}
		else{
		String word =textarea.getText();
		int length =word.length();
		int time =length/9;
		String word2 ="";
		for(int m =0;m<time;m++){
		String haha =word.substring(9*m, 9*m+9);
		switch(haha){
		case"Ook. Ook?":
		word2 =word2+">";
		break;
		case"Ook? Ook.":
			word2 =word2+"<";
			break;
		case"Ook. Ook.":
			word2 =word2+"+";
			break;
		case"Ook! Ook!":
			word2 =word2+"-";
			break;
		case"Ook! Ook.":
			word2 =word2+".";
			break;
		case"Ook. Ook!":
			word2 =word2+",";
			break;
		case"Ook! Ook?":
			word2 =word2+"[";
			break;
		case"Ook? Ook!":
			word2 =word2+"]";
			break;
		}
			
		}
		String output2 =RemoteHelper.getInstance().getExecuteService().execute(word2,input.getText());
		output.setText(output2);
		}
		}
		catch(RemoteException e3){
		e3.printStackTrace();	
		}
		}
    	
    });
	}
	
	class undolistener implements ActionListener{
		
	    public void actionPerformed(ActionEvent e){
	   	if(temp>0&&temp<count){
	   	temp--;
	   	textarea.setText("");
	   	textarea.setText(stringlist.get(temp));
	   	}
	    }
	    }
	
	class redolistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (temp>=0&&temp<count-1) {
				temp = temp+1;
				textarea.setText("");
				textarea.setText(stringlist.get(temp));
			}
		}
	}
	    
class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				input.setText("Open");
			} else if (cmd.equals("Save")) {
				input.setText("Save");
			} else if (cmd.equals("Run")) {
				input.setText("Hello, result");
			}
		}
	}

class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
		}

	}
	}



