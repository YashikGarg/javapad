package abcd;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.*; 
 


public class javapad {
	
	boolean first_save=true;
	String path="";
	int zoom_size=0;
	int zoom_size_out=0;
	String find_last="";
	String replace_last="";
	boolean counter_find=false;
	boolean commit_save=true;
	boolean open_commit=false;
	
	
	public class menubar implements ActionListener {
	
		JFrame main_frame;
		JMenuBar main;
		JMenu file,edit,format,view,help;
		JMenuItem cut,copy,paste,selectall,open,save,saveas,newf,exit,delete,find,replace,about;
		JTextArea textarea;
		JMenu style,fontfamily,fontsize,zoom;
		JCheckBoxMenuItem bold,italic;
		JMenuItem Serif,SansSerif,Dialog,DialogInput,TimesNewRoman,s8,s12,s18,s32,s45,s64,zoomin,zoomout,reset;
		int find_last_index=0;
		int replace_last_index=0;
		
		menubar(JFrame frame,JTextArea area)
		{
			main_frame=frame;
			textarea=area;
			//Add menu item 
			cut=new JMenuItem("Cut");
			copy=new JMenuItem("Copy");
			paste=new JMenuItem("Paste");
			delete=new JMenuItem("Delete");
			selectall=new JMenuItem("Select All");
			newf=new JMenuItem("New");
			open=new JMenuItem("Open File");
			save=new JMenuItem("Save");
			saveas=new JMenuItem("Save As");
			exit=new JMenuItem("Exit");
			style=new JMenu("Font Style");
			fontfamily=new JMenu("Font");
			Serif=new JMenuItem("Serif");
			SansSerif=new JMenuItem("SansSerif");
			Dialog=new JMenuItem("Dialog");
			DialogInput=new JMenuItem("DialogInput");
			TimesNewRoman=new JMenuItem("Times New Roman");
			fontsize=new JMenu("Font Size");
			s8=new JMenuItem("8px");
			s12=new JMenuItem("12px");
			s18=new JMenuItem("18px");
			s32=new JMenuItem("32px");
			s45=new JMenuItem("45px");
			s64=new JMenuItem("64px");
			zoom=new JMenu("Zoom");
			zoomin=new JMenuItem("Zoom In");
			zoomout=new JMenuItem("Zoom Out");
			reset=new JMenuItem("Reset Zoom");
			find=new JMenuItem("Find ");
			replace=new JMenuItem("Find And Replace ");
			about=new JMenuItem("About ");
			
			
			
			//sub mneu
			bold=new JCheckBoxMenuItem("Bold");
			italic=new JCheckBoxMenuItem("Italic");
			
			//Add menubar
			main = new JMenuBar();
			
			//Add jmenu
			file= new JMenu("File");
			edit= new JMenu("Edit");
			format=new JMenu("Format");
			view=new JMenu("View");
			help= new JMenu("Help");
			
			//Add add listner
			cut.addActionListener(this);    
			copy.addActionListener(this);    
			paste.addActionListener(this);
			delete.addActionListener(this);
			selectall.addActionListener(this);
			newf.addActionListener(this);
			open.addActionListener(this);
			save.addActionListener(this);
			saveas.addActionListener(this);
			exit.addActionListener(this);
			bold.addActionListener(this);
			italic.addActionListener(this);
			Serif.addActionListener(this);
			SansSerif.addActionListener(this);
			Dialog.addActionListener(this);
			DialogInput.addActionListener(this);
			TimesNewRoman.addActionListener(this);
			s8.addActionListener(this);
			s12.addActionListener(this);
			s18.addActionListener(this);
			s32.addActionListener(this);
			s45.addActionListener(this);
			s64.addActionListener(this);
			zoomin.addActionListener(this);
			zoomout.addActionListener(this);
			reset.addActionListener(this);
			find.addActionListener(this);
			replace.addActionListener(this);
			about.addActionListener(this);
			
			//Add submenu
			style.add(bold);
			style.add(italic);
		    fontfamily.add(TimesNewRoman);
		    fontfamily.add(DialogInput);
		    fontfamily.add(Dialog);
		    fontfamily.add(SansSerif);
		    fontfamily.add(Serif);
		    fontsize.add(s8);
		    fontsize.add(s12);
		    fontsize.add(s18);
		    fontsize.add(s32);
		    fontsize.add(s45);
		    fontsize.add(s64);
		    zoom.add(zoomin);
		    zoom.add(zoomout);
		    zoom.add(reset);
			
			
			//Add menu item into menu
			edit.add(cut);edit.add(copy);edit.add(paste);edit.add(delete);edit.addSeparator();edit.add(find);edit.add(replace);edit.addSeparator();edit.add(selectall);
			file.add(newf);file.addSeparator();file.add(open);file.add(save);file.add(saveas);file.addSeparator();file.add(exit);
			format.add(fontfamily);format.add(style);format.add(fontsize);
			view.add(zoom);
			help.add(about);
			
			//Add menu to menubar
			main.add(file);main.add(edit);main.add(format);main.add(view);main.add(help);
			
			frame.add(main);
			frame.setJMenuBar(main);
			Dialog.setEnabled(false);
			s12.setEnabled(false);
			
			
			textarea.getDocument().addDocumentListener(new DocumentListener() {
				  public void changedUpdate(DocumentEvent e) {
					    warn();
					  }
					  public void removeUpdate(DocumentEvent e) {
					    warn();
					  }
					  public void insertUpdate(DocumentEvent e) {
					    warn();
					  }

					  public void warn() {
					    commit_save=false;
					    if(open_commit)
					    {
					    	open_commit=false;
					    	commit_save=true;
					    }
					  }
					});
			
			
			
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
					
			//edit
			if(e.getSource()==cut)
			textarea.cut();	
			if(e.getSource()==copy)
			textarea.copy();
			if(e.getSource()==paste)
			textarea.paste();
			if(e.getSource()==selectall)
			textarea.selectAll();
			if(e.getSource()==delete)
			if(textarea.getSelectedText()!=null)
			textarea.replaceSelection("");
			if(e.getSource()==find)
			find_function();
			if(e.getSource()==replace)
			replace_function();	
			
			//file
			if(e.getSource()==open)
			{
				before_open_function();
			}
			
			//save
			if(e.getSource()==save)
			{
				save_function();
				
			}
			
				//save as
			
			if(e.getSource()==saveas)
			{
				JFileChooser fc=new JFileChooser();
				int i=fc.showSaveDialog(null);
				if(i==JFileChooser.APPROVE_OPTION)
				{
					  
			        try
			        { 
			        	FileWriter file = new FileWriter(fc.getSelectedFile().getAbsolutePath());
			        	path=fc.getSelectedFile().getAbsolutePath();
			        	BufferedWriter bfile = new BufferedWriter(file);  
			        	bfile.write(textarea.getText());
			        	bfile.close();
			            
			        } 
			        catch (IOException excpt) 
			        { 
			            excpt.printStackTrace(); 
			        } 
					
				}
				
			}
			
			//new
			
			if(e.getSource()==newf)
			{
				
				
				if(commit_save)
					textarea.setText("");
				else {
				int i=JOptionPane.showConfirmDialog(null, "You have not save? Do you want Save");
	            if(i==0) {
	            	if(!commit_save)
	            	save_function();
	            	if(commit_save)
	            		textarea.setText("");}
	            if(i==1)
	            	textarea.setText("");
				}
				
				first_save=true;
				counter_find=false;
				commit_save=true;
				open_commit=false;
			}
			
			//exit
			
			if(e.getSource()==exit)
			{
				System.exit(0); 
			}
			
			//file_end
			
			//format
			
			if(e.getSource()==bold)
			textarea.setFont(textarea.getFont().deriveFont(textarea.getFont().getStyle() ^ Font.BOLD));
			if(e.getSource()==italic)
			textarea.setFont(textarea.getFont().deriveFont(textarea.getFont().getStyle() ^ Font.ITALIC));

			
			//font famoly
			if(e.getSource()==TimesNewRoman)
			{
				int font_size=textarea.getFont().getSize();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font("Times New Roman", font_style, font_size));
				
				//select true
				Dialog.setEnabled(true);
				TimesNewRoman.setEnabled(false);
				DialogInput.setEnabled(true);
				SansSerif.setEnabled(true);
				Serif.setEnabled(true);
				
			}
			if(e.getSource()==DialogInput)
			{
				int font_size=textarea.getFont().getSize();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font("DialogInput", font_style, font_size));
				
				//select true
				Dialog.setEnabled(true);
				TimesNewRoman.setEnabled(true);
				DialogInput.setEnabled(false);
				SansSerif.setEnabled(true);
				Serif.setEnabled(true);
			}
			if(e.getSource()==Dialog)
			{
				int font_size=textarea.getFont().getSize();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font("Dialog", font_style, font_size));	
				
				//select true
				Dialog.setEnabled(false);
				TimesNewRoman.setEnabled(true);
				DialogInput.setEnabled(true);
				SansSerif.setEnabled(true);
				Serif.setEnabled(true);
			}
			if(e.getSource()==SansSerif)
			{
				int font_size=textarea.getFont().getSize();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font("SansSerif", font_style, font_size));	
				
				//select true
				Dialog.setEnabled(true);
				TimesNewRoman.setEnabled(true);
				DialogInput.setEnabled(true);
				SansSerif.setEnabled(false);
				Serif.setEnabled(true);
			}
			if(e.getSource()==Serif)
			{
				int font_size=textarea.getFont().getSize();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font("Serif", font_style, font_size));
				
				//select true
				Dialog.setEnabled(true);
				TimesNewRoman.setEnabled(true);
				DialogInput.setEnabled(true);
				SansSerif.setEnabled(true);
				Serif.setEnabled(false);
			}
			
			//font size
			
			if(e.getSource()==s8)
			{
				String font_family=textarea.getFont().getFamily();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font(font_family, font_style, 8+(zoom_size*5)+(zoom_size_out*5)));
				
				
				s8.setEnabled(false);
				s12.setEnabled(true);
				s18.setEnabled(true);
				s32.setEnabled(true);
				s45.setEnabled(true);
				s64.setEnabled(true);
			}
			
			if(e.getSource()==s12)
			{
				String font_family=textarea.getFont().getFamily();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font(font_family, font_style, 12+(zoom_size*5)+(zoom_size_out*5)));
				
				s8.setEnabled(true);
				s12.setEnabled(false);
				s18.setEnabled(true);
				s32.setEnabled(true);
				s45.setEnabled(true);
				s64.setEnabled(true);
			}
			
			if(e.getSource()==s18)
			{
				String font_family=textarea.getFont().getFamily();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font(font_family, font_style, 18+(zoom_size*5)+(zoom_size_out*5)));
				
				s8.setEnabled(true);
				s12.setEnabled(true);
				s18.setEnabled(false);
				s32.setEnabled(true);
				s45.setEnabled(true);
				s64.setEnabled(true);
				
			}
			
			if(e.getSource()==s32)
			{
				String font_family=textarea.getFont().getFamily();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font(font_family, font_style, 32+(zoom_size*5)+(zoom_size_out*5)));
				
				s8.setEnabled(true);
				s12.setEnabled(true);
				s18.setEnabled(true);
				s32.setEnabled(false);
				s45.setEnabled(true);
				s64.setEnabled(true);
				
			}
			
			if(e.getSource()==s45)
			{
				String font_family=textarea.getFont().getFamily();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font(font_family, font_style, 45+(zoom_size*5)+(zoom_size_out*5)));
				
				s8.setEnabled(true);
				s12.setEnabled(true);
				s18.setEnabled(true);
				s32.setEnabled(true);
				s45.setEnabled(false);
				s64.setEnabled(true);
				
			}
			
			if(e.getSource()==s64)
			{
				String font_family=textarea.getFont().getFamily();
				int font_style=textarea.getFont().getStyle();
				textarea.setFont(new Font(font_family, font_style, 64+(zoom_size*5)+(zoom_size_out*5)));
				
				s8.setEnabled(true);
				s12.setEnabled(true);
				s18.setEnabled(true);
				s32.setEnabled(true);
				s45.setEnabled(true);
				s64.setEnabled(false);
				
			}
			
			//format end
			//view
			
			if(e.getSource()==zoomin)
			{
				
				zoom_size++;
				textarea.setFont(new Font(textarea.getFont().getFamily(), textarea.getFont().getStyle(), textarea.getFont().getSize()+5));
			}
			if(e.getSource()==zoomout)
			{
				if(textarea.getFont().getSize()>4)
				{	
				zoom_size_out--;
				textarea.setFont(new Font(textarea.getFont().getFamily(), textarea.getFont().getStyle(), textarea.getFont().getSize()-5));
				}
			}
			if(e.getSource()==reset)
			{
				
				zoom_size+=zoom_size_out;
				zoom_size*=-1;
				textarea.setFont(new Font(textarea.getFont().getFamily(), textarea.getFont().getStyle(), textarea.getFont().getSize()+zoom_size*5));
				zoom_size=0;
				zoom_size_out=0;
			}
			
			//view end
			
			if(e.getSource()==about)
			{
				
				
				JFrame help_frame=new JFrame("About");
				help_frame.getContentPane().setBackground(Color.WHITE);
				String url_img="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjpBDPGfPvXsOSM2SU2nbisFn1PlHzGc2c_w&usqp=CAU";
				JLabel help_label_main=new JLabel("<html><body><img src='"+url_img+"' width=\"100\" height=\"100\" ></body></html>");
				help_label_main.setBounds(5,30,400,100);
				help_frame.add(help_label_main);
				
				JLabel help_label_main1=new JLabel("<html><body><h1>JavaPad</h1></body></html>");
				help_label_main1.setBounds(170,30,400,100);
				help_frame.add(help_label_main1);
				
				JLabel help_label=new JLabel("<html><body><br><hr style=\"width:400px;text-align:left;margin-left:0\">\r\n"
						+ "<br><p style=\"font-size:10px\">This is basic text editor programme <br> developed by using java</p><br><hr style=\"width:400px;text-align:left;margin-left:0\">\r\n"
						+ "</body></html>");
				help_label.setBounds(5,120,400,100);
				help_frame.add(help_label);
				JLabel help_label1=new JLabel("<html><body><br><p style=\"font-size:10px\">If you like it Plz drop a star <img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqZi6aSu8nv0yhlLv-xzogrn3tVPNx8zhN1Q&usqp=CAU' width=\"30\" height=\"30\"> in my github profile</p><br></body></html>");
				help_label1.setBounds(5,190,400,100);
				help_frame.add(help_label1);
				JLabel help_label2=new JLabel("<html> <br><p style=\"font-size:10px\"> <a href='https://github.com/YashikGarg'> https://github.com/YashikGarg </p> <br><hr style=\"width:400px;text-align:left;margin-left:0\">\r\n"
						+ "</html>");
				help_label2.setBounds(5,230,400,100);
				help_frame.add(help_label2);
				JLabel help_label3=new JLabel("<html> <br><p style=\"font-size:10px\"> Developed by <br> Yashik </p></html>");
				help_label3.setBounds(5,280,400,100);
				help_frame.add(help_label3);
				help_frame.setSize(400,450);
				help_frame.setLayout(null);
				help_frame.setVisible(true);
			}
			
		}

		
		private void before_open_function() {
			
			if(commit_save)
				open_function();
			else {
			int i=JOptionPane.showConfirmDialog(null, "You have not save? Do you want Save");
            if(i==0) {
            	if(!commit_save)
            	save_function();
            	if(commit_save)
            		open_function();}
            if(i==1)
            	open_function();
			}
		}

		//open
		
		private void open_function() {

			JFileChooser fc=new JFileChooser();
			int i=fc.showOpenDialog(null);
			if(i==JFileChooser.APPROVE_OPTION)
			{
				File f=fc.getSelectedFile();    
		        String filepath=f.getPath();    
		        try{  
		            BufferedReader br=new BufferedReader(new FileReader(filepath));    
		            String s1="",s2="";                         
		            while((s1=br.readLine())!=null){    
		            s2+=s1+"\n";    
		            path=filepath;
		            first_save=false;
		            commit_save=true;
		            open_commit=true;
		            
		            }    
		            textarea.setText(s2);    
		            br.close();    
		            }catch (Exception ex) {ex.printStackTrace();  }   
			}
			
			
		}
		
		//save

		private void save_function() {

			if(first_save)
			{JFileChooser fc=new JFileChooser();
			int i=fc.showSaveDialog(null);
			if(i==JFileChooser.APPROVE_OPTION)
			{
				  
		        try
		        { 
		        	FileWriter file = new FileWriter(fc.getSelectedFile().getAbsolutePath());
		        	path=fc.getSelectedFile().getAbsolutePath();
		        	BufferedWriter bfile = new BufferedWriter(file);  
		        	bfile.write(textarea.getText());
		        	bfile.close();
		            first_save=false;
		            commit_save=true;
		            System.out.println("save");
		        } 
		        catch (IOException excpt) 
		        { 
		            excpt.printStackTrace(); 
		        } 
				
			}
			}
			else
			{
				try
		        { 
		        	FileWriter file = new FileWriter(path);
		        	BufferedWriter bfile = new BufferedWriter(file);  
		        	bfile.write(textarea.getText());
		        	bfile.close();
		            first_save=false;
		            commit_save=true;
		        } 
		        catch (IOException excpt) 
		        { 
		            excpt.printStackTrace(); 
		        } 
				
			}
			
			
		}

		
		//replace function
		private void replace_function() {
			
			replace_last="";
			
			JDialog replace_dialog = new JDialog(main_frame, "Replace");
			
			JLabel l=new JLabel("Find what : ");
			l.setBounds(10,10,100,30); 
			replace_dialog.add(l);
			final JTextField find_text=new JTextField();
			find_text.setBounds(100,15,250,20);  			
			replace_dialog.add(find_text);
			
			JLabel l1=new JLabel("Replace with : ");
			l1.setBounds(10,35,100,30); 
			replace_dialog.add(l1);
			final JTextField replace_text=new JTextField();
			replace_text.setBounds(100,40,250,20);  			
			replace_dialog.add(replace_text);
			
			JButton b=new JButton("Find Next");
			b.setBounds(360,15,100,20);
			replace_dialog.add(b);
			
			JButton b1=new JButton("Replace It");
			b1.setBounds(360,40,100,20);
			replace_dialog.add(b1);
			
			JButton b2=new JButton("Replace All");
			b2.setBounds(360,65,100,20);
			replace_dialog.add(b2);
			
			replace_dialog.setSize(500,140);
			replace_dialog.setLayout(null); 
			replace_dialog.setVisible(true);
			
			 b.addActionListener ( new ActionListener()  
		        {  
		            public void actionPerformed( ActionEvent e )  
		            {  
		            	String str_main=textarea.getText();
		            	String find_str=find_text.getText();
		            	if(!replace_last.equals(find_str))
		            	{replace_last_index=0; replace_last=find_str;	}
		            	int strt=str_main.indexOf(find_str,replace_last_index);
		            	if(strt!=-1)
						{
						int end=find_str.length();
		            	textarea.setSelectionStart(strt); 
						textarea.setSelectionEnd(end+strt);
						str_main=str_main.substring(strt+end);
						replace_last_index=strt+end;
						counter_find=true;
						}
		            	else
		            	JOptionPane.showMessageDialog(main_frame,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
		            	
		            	
		            }  
		        });  
			 
			 b1.addActionListener ( new ActionListener()  
		        {  
		            public void actionPerformed( ActionEvent e )  
		            {  
		            	if(counter_find)
		            	textarea.replaceRange(replace_text.getText(),replace_last_index-find_text.getText().length(),replace_last_index);
		            	else
		            	{
		            		String str_main=textarea.getText();
			            	String find_str=find_text.getText();
			            	if(!replace_last.equals(find_str))
			            	{replace_last_index=0; replace_last=find_str;	}
			            	int strt=str_main.indexOf(find_str,replace_last_index);
			            	if(strt!=-1)
							{
							int end=find_str.length();
			            	str_main=str_main.substring(strt+end);
							replace_last_index=strt+end;
							counter_find=false;
							textarea.replaceRange(replace_text.getText(),strt,strt+end);
							textarea.setSelectionStart(strt); 
							textarea.setSelectionEnd(replace_text.getText().length()+strt);
							
							
							}
			            	else
			            	JOptionPane.showMessageDialog(main_frame,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
			            	
		            	}
		            	
		            }  
		        });
			 
			 
			 b2.addActionListener ( new ActionListener()  
		        {  
		            public void actionPerformed( ActionEvent e )  
		            {  
		            	int track=0;
		            	String str_main=textarea.getText();
		            	String find_str=find_text.getText();
		            	int strt=str_main.indexOf(find_str,track);
		            	if(strt!=-1)
						{
		            	while(strt!=-1)	{		
						int end=find_str.length();
						str_main=str_main.substring(strt+end);
						textarea.replaceRange(replace_text.getText(),strt,strt+end);
						str_main=textarea.getText();
		            	find_str=find_text.getText();
		            	strt=str_main.indexOf(find_str,track);
						
						
		            	}
						}
		            	else
		            	JOptionPane.showMessageDialog(main_frame,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
		            	
		            	
		            }  
		        });
			 
			
		}
		
		//find

		private void find_function() {
			{
				JDialog find_dialog = new JDialog(main_frame, "Find..");
				
				find_last="";
				
				JLabel l=new JLabel("Find What....");
				l.setBounds(10,5,100,30); 
				find_dialog.add(l);
				final JTextField find_text=new JTextField();
				find_text.setBounds(10,37,250,20);  			
				find_dialog.add(find_text);
				JButton b=new JButton("Find Next");
				b.setBounds(10,63,100,20);
				find_dialog.add(b);
				find_dialog.setSize(300,140);
				find_dialog.setLayout(null); 
				find_dialog.setVisible(true);
				 b.addActionListener ( new ActionListener()  
			        {  
			            public void actionPerformed( ActionEvent e )  
			            {  
			            	String str_main=textarea.getText();
			            	String find_str=find_text.getText();
			            	if(!find_last.equals(find_str))
			            	{find_last_index=0; find_last=find_str;	}
			            	int strt=str_main.indexOf(find_str,find_last_index);
			            	if(strt!=-1)
							{
							int end=find_str.length();
			            	textarea.setSelectionStart(strt); 
							textarea.setSelectionEnd(end+strt);
							str_main=str_main.substring(strt+end);
							find_last_index=strt+end;
							}
			            	else
			            	JOptionPane.showMessageDialog(main_frame,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
			            	
			            	
			            }  
			        });  
				
			}
			
		}
	
	//find end 
		
	
	}
	
	
	
	
	
	javapad()
	{
		JFrame frame =new JFrame("JavaPad");
		frame.setSize(700,500);
		JPanel panel=new JPanel();   
		JTextArea main_area = new JTextArea(20,20);
		JScrollPane scrollableTextArea = new JScrollPane(main_area);
		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		final menubar obj=new menubar(frame,main_area);
		frame.add(scrollableTextArea);
		frame.setVisible(true); 
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	if(commit_save)
                    System.exit(0);
            	
            	int i=JOptionPane.showConfirmDialog(null, "You have not save? Do you want Save");
                if(i==0) {
                	if(commit_save)
                        System.exit(0);
                	if(!commit_save)
                	obj.save_function();
                	if(commit_save)
                    System.exit(0);}
                if(i==1)
                    System.exit(0);
            }
        });
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
	}

	public static void main(String[] args) {
		
		new javapad(); 
	}

}
