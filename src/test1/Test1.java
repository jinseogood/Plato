package test1;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.table.*;

public class Test1 extends JFrame implements ActionListener, MouseListener, ChangeListener{
	JLabel[] la=new JLabel[5]; JTextField[] tf=new JTextField[5]; 
	JLabel[] la1=new JLabel[5]; JTextField[] tf1=new JTextField[5];
	JLabel[] la2=new JLabel[6]; JTextField[] tf2=new JTextField[5];
	JLabel[] la3=new JLabel[6]; JTextField[] tf3=new JTextField[5];
	JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,itemla;
	JTextArea ta1,ta2,ta3;
	JTabbedPane tb=new JTabbedPane();
	JMenuItem exit,option,addstore,searchstore,additem,searchitem,delstore,delitem;
	JPanel p_main,p_addst,p_searchst,p_editst,pp_main,pp_additem,pp_searchitem,pp_edititem,ppp_main,ppp_write,ppp_edit,pppp_main,pppp_view,ppppp_main;
	JButton b_req_open,b_req_search,b_noti_search;
	JButton bu_store[],bu_store_edit[],bu_item[],bu_item_edit[],bu_notice[],bu_noti_edit[],bu_req_view[],bu_order[];
	JComboBox com_st,com_st2,com_item,com_noti,com_req,com_order;
	JTextField st_search,st_search2,item_search,item_search2,req_search,noti_search,noti_write,noti_edit,req_view1,req_view2,order_search;
	JSpinner spi,spi2;
	SpinnerNumberModel snm;
	DefaultTableModel model,model2,model3,model4,model5;
	JTable st_table,item_table,notice_table,request_table,order_table;
	JScrollPane jsp,jsp2,jsp3,jsp4,jsp5;
	JFrame f_store_edit,f_item_edit,f_noti_edit,f_req_view;
	String url;
	String user;
	String password;
	Connection con=null;
	Statement st=null;
	int tableno,tablerow;
	int stno,itemno,reqno,ordno;
	int count=0;
	int count2=0;
	static String d_id;
	static String d_pw;
	static JLabel[] login_la=new JLabel[2];
	JTextField login_tf_id=new JTextField();
	JPasswordField login_pw=new JPasswordField();
	JPanel setting;
	
	String la_store[]={"가맹점번호","사업자번호","주소","전화번호","대표자"};
	String la_item[]={"제품종류","제품번호","제품명","제품가격","납품업체","수량"};
	String la_request[]={"번호","사업자번호","작성자","내용","작성일"};
	String la_notice[]={"번호","제목","작성자","작성일"};
	String la_order[]={"제품번호","수량","주문일자","주문자"};
	String bu_sub[]={"삭제","수정","확인","닫기"};
	String bu_stit[]={"불러오기","추가"};
	String bu_noti_m[]={"새로고침","글쓰기"};
	String bu_req[]={"처리하기","닫기"};
	String bu_order_m[]={"불러오기","처리","검색"};
	String com_item_m[]={"제품종류","제품번호","제품명","제품가격","납품업체"};
	String com_notice[]={"번호","제목","작성자"};
	String com_request[]={"번호","사업자번호","작성자","내용"};
	String com_ord[]={"주문자","제품번호"};
	static String log_la[]={"ID","PW"};
	
	public Test1(String title){
		super(title);
		this.setLayout(null);
		this.setBounds(15,15,550, 720);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Option_account
		setting=new JPanel();
		setting.setLayout(null);
		setting.setPreferredSize(new Dimension(150,60));
		for(int i=0;i<log_la.length;i++){
			login_la[i]=new JLabel(log_la[i]);
			login_la[i].setBounds(30, 20+(i*20), 40, 20);
			setting.add(login_la[i]);
		}
		login_tf_id=new JTextField();
		login_tf_id.setBounds(70, 20, 120, 20);
		login_tf_id.setText(d_id);
		setting.add(login_tf_id);
		login_pw=new JPasswordField();
		login_pw.setBounds(70,40,120,20);
		login_pw.setText(d_pw);
		setting.add(login_pw);
		
		//Tab 1_store
		p_main=new JPanel();
		p_main.setLayout(null);
		model=new DefaultTableModel(la_store,0){
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		st_table=new JTable(model);
		st_table.addMouseListener(this);
		st_table.setAutoscrolls(true);
		jsp=new JScrollPane(st_table);
		jsp.setBounds(0,25,530,600);
		p_main.add(jsp);
		bu_store=new JButton[bu_stit.length+1];
		for(int i=0;i<bu_stit.length;i++){
			bu_store[i]=new JButton(bu_stit[i]);
			bu_store[i].setBounds(0+(100*i), 0, 100, 25);
			bu_store[i].addActionListener(this);
			p_main.add(bu_store[i]);
		}
		com_st2=new JComboBox(la_store);
		com_st2.setBounds(270,0,90,25);
		com_st2.addActionListener(this);
		p_main.add(com_st2);
		st_search2=new JTextField();
		st_search2.setBounds(360,0,100,25);
		p_main.add(st_search2);
		bu_store[2]=new JButton("검색");
		bu_store[2].setBounds(460, 0, 70, 25);
		bu_store[2].addActionListener(this);
		p_main.add(bu_store[2]);
		
		//AddStore
		p_addst=new JPanel();
		p_addst.setLayout(null);
		p_addst.setPreferredSize(new Dimension(200,150));
		for(int i=0;i<5;i++){
			la[i]=new JLabel(la_store[i]);
			la[i].setBounds(30,20+(i*20),80,20);
			p_addst.add(la[i]);
			tf[i]=new JTextField();
			tf[i].setBounds(115, 20+(i*20), 100, 20);
			p_addst.add(tf[i]);
		}
		
		//SearchStore
		p_searchst=new JPanel();
		p_searchst.setLayout(null);
		p_searchst.setPreferredSize(new Dimension(200,150));
		com_st=new JComboBox(la_store);
		com_st.setBounds(10,50,100,20);
		com_st.addActionListener(this);
		p_searchst.add(com_st);
		st_search=new JTextField();
		st_search.setBounds(115, 50, 100, 20);
		p_searchst.add(st_search);
		
		//EditStore
		p_editst=new JPanel();
		p_editst.setLayout(null);
		p_editst.setPreferredSize(new Dimension(200,150));
		for(int i=0;i<5;i++){
			la1[i]=new JLabel(la_store[i]);
			la1[i].setBounds(45,40+(i*20),80,20);
			p_editst.add(la1[i]);
			tf1[i]=new JTextField();
			tf1[i].setBounds(130, 40+(i*20), 100, 20);
			p_editst.add(tf1[i]);
		}
		bu_store_edit=new JButton[bu_sub.length];
		for(int i=0;i<bu_sub.length;i++){
			bu_store_edit[i]=new JButton(bu_sub[i]);
			bu_store_edit[i].addActionListener(this);
			p_editst.add(bu_store_edit[i]);
		}
		bu_store_edit[0].setBounds(164, 0, 60, 25);
		bu_store_edit[1].setBounds(224, 0, 60, 25);
		bu_store_edit[2].setBounds(75, 165, 70, 25);
		bu_store_edit[2].setEnabled(false);
		bu_store_edit[3].setBounds(145, 165, 70, 25);
		
		//Tab 2_item
		pp_main=new JPanel();
		pp_main.setLayout(null);
		model2=new DefaultTableModel(la_item,0){
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		item_table=new JTable(model2);
		item_table.addMouseListener(this);
		jsp2=new JScrollPane(item_table);
		jsp2.setBounds(0,25,530,600);
		pp_main.add(jsp2);
		bu_item=new JButton[bu_stit.length+1];
		for(int i=0;i<bu_stit.length;i++){
			bu_item[i]=new JButton(bu_stit[i]);
			bu_item[i].setBounds(0+(100*i), 0, 100, 25);
			bu_item[i].addActionListener(this);
			pp_main.add(bu_item[i]);
		}
		com_item=new JComboBox(com_item_m);
		com_item.setBounds(280,0,80,25);
		com_item.addActionListener(this);
		pp_main.add(com_item);
		item_search2=new JTextField();
		item_search2.setBounds(360, 0, 100, 25);
		pp_main.add(item_search2);
		bu_item[2]=new JButton("검색");
		bu_item[2].setBounds(460, 0, 70, 25);
		bu_item[2].addActionListener(this);
		pp_main.add(bu_item[2]);
		
		
		//AddItem
		pp_additem=new JPanel();
		pp_additem.setLayout(null);
		pp_additem.setPreferredSize(new Dimension(200,150));
		for(int i=0;i<la_item.length;i++){
			la2[i]=new JLabel(la_item[i]);
			la2[i].setBounds(30,20+(i*20),80,20);
			pp_additem.add(la2[i]);
		}
		for(int i=0;i<5;i++){
			tf2[i]=new JTextField();
			tf2[i].setBounds(115, 20+(i*20), 100, 20);
			pp_additem.add(tf2[i]);
		}
		snm=new SpinnerNumberModel(1,1,999,1);
		spi=new JSpinner();
		spi.setBounds(115, 120, 40, 20);
		spi.addChangeListener(this);
		spi.setModel(snm);
		pp_additem.add(spi);
				
		//SearchItem
		pp_searchitem=new JPanel();
		pp_searchitem.setLayout(null);
		pp_searchitem.setPreferredSize(new Dimension(200,120));
		JLabel itemla=new JLabel("제품 번호");
		itemla.setBounds(10,50,100,20);
		pp_searchitem.add(itemla);
		item_search=new JTextField();
		item_search.setBounds(115, 50, 100, 20);
		pp_searchitem.add(item_search);
		
		//EditItem
		pp_edititem=new JPanel();
		pp_edititem.setLayout(null);
		pp_edititem.setPreferredSize(new Dimension(200,150));
		for(int i=0;i<la_item.length;i++){
			la3[i]=new JLabel(la_item[i]);
			la3[i].setBounds(45,40+(i*20),80,20);
			pp_edititem.add(la3[i]);
		}
		for(int i=0;i<5;i++){
			tf3[i]=new JTextField();
			tf3[i].setBounds(130, 40+(i*20), 100, 20);
			pp_edititem.add(tf3[i]);
		}
		snm=new SpinnerNumberModel(1,1,999,1);
		spi2=new JSpinner();
		spi2.setBounds(130, 140, 40, 20);
		spi2.setModel(snm);
		spi2.addChangeListener(this);
		pp_edititem.add(spi2);
		bu_item_edit=new JButton[bu_sub.length];
		for(int i=0;i<bu_sub.length;i++){
			bu_item_edit[i]=new JButton(bu_sub[i]);
			bu_item_edit[i].addActionListener(this);
			pp_edititem.add(bu_item_edit[i]);
		}
		bu_item_edit[0].setBounds(164, 0, 60, 25);
		bu_item_edit[1].setBounds(224, 0, 60, 25);
		bu_item_edit[2].setBounds(75, 165, 70, 25);
		bu_item_edit[2].setEnabled(false);
		bu_item_edit[3].setBounds(145, 165, 70, 25);
		
		//Tab3_notice
		ppp_main=new JPanel();
		ppp_main.setLayout(null);
		model3=new DefaultTableModel(la_notice,0){
			public boolean isCellEditable(int row, int column) {
		       return false;
			}
		};
		notice_table=new JTable(model3);
		notice_table.addMouseListener(this);
		jsp3=new JScrollPane(notice_table);
		jsp3.setBounds(0,25,530,600);
		ppp_main.add(jsp3);
		bu_notice=new JButton[bu_noti_m.length];
		for(int i=0;i<bu_noti_m.length;i++){
			bu_notice[i]=new JButton(bu_noti_m[i]);
			bu_notice[i].setBounds(0+(i*100), 0, 100, 25);
			bu_notice[i].addActionListener(this);
			ppp_main.add(bu_notice[i]);
		}
		com_noti=new JComboBox(com_notice);
		com_noti.setBounds(290, 0, 70, 25);
		com_noti.addActionListener(this);
		ppp_main.add(com_noti);
		noti_search=new JTextField();
		noti_search.setBounds(360, 0, 100, 25);
		ppp_main.add(noti_search);
		b_noti_search=new JButton("검색");
		b_noti_search.setBounds(460,0,70,25);
		b_noti_search.addActionListener(this);
		ppp_main.add(b_noti_search);
		
		//Write_notice
		ppp_write=new JPanel();
		ppp_write.setLayout(null);
		ppp_write.setPreferredSize(new Dimension(500,450));
		lb1=new JLabel("제목");
		lb1.setBounds(20,20,50,25);
		ppp_write.add(lb1);
		lb2=new JLabel("내용");
		lb2.setBounds(20,50,50,25);
		ppp_write.add(lb2);
		noti_write=new JTextField();
		noti_write.setBounds(60, 20, 420, 25);
		ppp_write.add(noti_write);
		ta1=new JTextArea();
		ta1.setBounds(20,80,460,400);
		ppp_write.add(ta1);
		
		//Edit_notice
		ppp_edit=new JPanel();
		ppp_edit.setLayout(null);
		ppp_edit.setPreferredSize(new Dimension(500,450));
		lb3=new JLabel("제목");
		lb3.setBounds(20,50,50,25);
		ppp_edit.add(lb3);
		lb4=new JLabel("내용");
		lb4.setBounds(20,80,50,25);
		ppp_edit.add(lb4);
		noti_edit=new JTextField();
		noti_edit.setBounds(60, 50, 440, 25);
		ppp_edit.add(noti_edit);
		ta2=new JTextArea();
		ta2.setBounds(20,110,480,400);
		ppp_edit.add(ta2);
		bu_noti_edit=new JButton[bu_sub.length];
		for(int i=0;i<bu_sub.length;i++){
			bu_noti_edit[i]=new JButton(bu_sub[i]);
			bu_noti_edit[i].addActionListener(this);
			ppp_edit.add(bu_noti_edit[i]);
		}
		bu_noti_edit[0].setBounds(300, 20, 100, 25);
		bu_noti_edit[1].setBounds(400, 20, 100, 25);
		bu_noti_edit[2].setBounds(170, 520, 80, 25);
		bu_noti_edit[2].setEnabled(false);
		bu_noti_edit[3].setBounds(260, 520, 80, 25);
		
		//Tab4_request
		pppp_main=new JPanel();
		pppp_main.setLayout(null);
		model4=new DefaultTableModel(la_request,0){
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		request_table=new JTable(model4);
		request_table.addMouseListener(this);
		jsp4=new JScrollPane(request_table);
		jsp4.setBounds(0,25,530,600);
		pppp_main.add(jsp4);
		b_req_open=new JButton("불러오기");
		b_req_open.setBounds(0,0,100,25);
		b_req_open.addActionListener(this);
		pppp_main.add(b_req_open);
		com_req=new JComboBox(com_request);
		com_req.setBounds(270, 0, 90, 25);
		com_req.addActionListener(this);
		pppp_main.add(com_req);
		req_search=new JTextField();
		req_search.setBounds(360, 0, 100, 25);
		pppp_main.add(req_search);
		b_req_search=new JButton("검색");
		b_req_search.setBounds(460,0,70,25);
		b_req_search.addActionListener(this);
		pppp_main.add(b_req_search);
		
		//View_request
		pppp_view=new JPanel();
		pppp_view.setLayout(null);
		pppp_view.setPreferredSize(new Dimension(500,450));
		lb5=new JLabel("작성자");
		lb5.setBounds(20,50,50,25);
		pppp_view.add(lb5);
		lb6=new JLabel("작성일");
		lb6.setBounds(200,50,50,25);
		pppp_view.add(lb6);
		lb7=new JLabel("내용");
		lb7.setBounds(20,80,50,25);
		pppp_view.add(lb7);
		req_view1=new JTextField();
		req_view1.setBounds(70, 50, 100, 25);
		pppp_view.add(req_view1);
		req_view2=new JTextField();
		req_view2.setBounds(250, 50, 100, 25);
		pppp_view.add(req_view2);
		ta3=new JTextArea();
		ta3.setBounds(20,110,480,400);
		pppp_view.add(ta3);
		bu_req_view=new JButton[bu_req.length];
		for(int i=0;i<bu_req.length;i++){
			bu_req_view[i]=new JButton(bu_req[i]);
			bu_req_view[i].addActionListener(this);
			pppp_view.add(bu_req_view[i]);
		}
		bu_req_view[0].setBounds(400, 20, 100, 25);
		bu_req_view[1].setBounds(230, 520, 80, 25);
		
		//Tab5_order
		ppppp_main=new JPanel();
		ppppp_main.setLayout(null);
		model5=new DefaultTableModel(la_order,0){
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		order_table=new JTable(model5);
		order_table.addMouseListener(this);
		jsp5=new JScrollPane(order_table);
		jsp5.setBounds(0,25,530,600);
		ppppp_main.add(jsp5);
		bu_order=new JButton[bu_order_m.length+1];
		for(int i=0;i<bu_order_m.length;i++){
			bu_order[i]=new JButton(bu_order_m[i]);
			bu_order[i].addActionListener(this);
			ppppp_main.add(bu_order[i]);
		}
		bu_order[0].setBounds(0,0,100,25);
		bu_order[1].setBounds(100, 0, 100, 25);
		bu_order[2].setBounds(460,0,70,25);
		com_order=new JComboBox(com_ord);
		com_order.setBounds(270, 0, 90, 25);
		com_order.addActionListener(this);
		ppppp_main.add(com_order);
		order_search=new JTextField();
		order_search.setBounds(360, 0, 100, 25);
		ppppp_main.add(order_search);
		
		tb.add("매장 관리", p_main);
		tb.add("제품 관리", pp_main);
		tb.add("공지사항",ppp_main);
		tb.add("요구사항",pppp_main);
		tb.add("주문내역",ppppp_main);
		
		tb.setSize(535,656);	// width-15, height-64
		
		this.add(tb);
		
		JMenuBar mb=new JMenuBar();
		JMenu file=new JMenu("File");
		JMenu store=new JMenu("Store");
		JMenu item=new JMenu("Item");
		
		mb.add(file);
		option=new JMenuItem("계정 설정");
		file.add(option);
		option.addActionListener(this);
		file.addSeparator();
		exit=new JMenuItem("종료");
		file.add(exit);
		exit.addActionListener(this);
		
		mb.add(store);
		addstore=new JMenuItem("매장 추가");
		store.add(addstore);
		addstore.addActionListener(this);
		store.addSeparator();
		searchstore=new JMenuItem("매장 검색");
		store.add(searchstore);
		searchstore.addActionListener(this);
		store.addSeparator();
		delstore=new JMenuItem("매장 삭제");
		store.add(delstore);
		delstore.addActionListener(this);
		
		mb.add(item);
		additem=new JMenuItem("제품 추가");
		item.add(additem);
		additem.addActionListener(this);
		item.addSeparator();
		searchitem=new JMenuItem("제품 검색");
		item.add(searchitem);
		searchitem.addActionListener(this);
		item.addSeparator();
		delitem=new JMenuItem("제품 삭제");
		item.add(delitem);
		delitem.addActionListener(this);
				
		this.setJMenuBar(mb);
			
		this.setVisible(true);
	}
	//DBConnect M
	public void DBConnect(String url, String user, String password){ 
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch(ClassNotFoundException e){
		}
		this.url=url;
		this.user=user;
		this.password=password;
		try{
			con=DriverManager.getConnection(url,user,password);
		}
		catch(SQLException e){
		}
	}
	//DBConnect JSP M
	public void DBConnect_JSP(String url){ 
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch(ClassNotFoundException e){
		}
		this.url=url;
		try{
			con=DriverManager.getConnection(url);
		}
		catch(SQLException e){
		}
	}
	//Refresh M
	public void Refresh(String table, int len ,DefaultTableModel m){ 
		String sql="Select * from "+table;
		try{
				st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				for(int i=m.getRowCount()-1;i>=0;i--){
					m.removeRow(i);
				}
				while(rs.next()){
					String row[]=new String[len];
					int j=1;
					
					for(int i=0;i<len;i++){
					row[i]=rs.getString(j);
					j++;
					}
					
					m.addRow(row);
				}
				tablerow=m.getRowCount();
				rs.close();
				this.st.close();
				this.con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
	//Date M
	public String GetDate(){
		Calendar rightnow=Calendar.getInstance();
		String Date=rightnow.get(Calendar.YEAR)+"-"+(rightnow.get(Calendar.MONTH)+1)+"-"+
				rightnow.get(Calendar.DATE);
		return Date;
	}
	//Spinner M
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==spi){
			count=(int)spi.getValue();
		}
		else if(e.getSource()==spi2){
			count2=(int)spi.getValue();
		}
	}
	//Rownum M
	public void Rownum(String table){
		try{
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			//this.Refresh(table);
			int i=0;
			String sql="Select 번호 from "+table;
			st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			String row[]=new String[tablerow];
			while(rs.next()){
					row[i]=rs.getString(1);	
					i++;
			}
			
			sql="set @rownum:=0;";
			st=con.createStatement();
			int k=st.executeUpdate(sql);
			
			sql="select @rownum:=@rownum+1 from "+table;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			String num[]=new String[tablerow];
			i=0;
			while(rs.next()){
					num[i]=rs.getString(1);	
					i++;
				}
			for(i=0;i<tablerow;i++){
			sql="update "+table+" as a set a.번호="+Integer.parseInt(num[i])+" where a.번호="+Integer.parseInt(row[i])+"";
			st=con.createStatement();
			int n=st.executeUpdate(sql);
			}
			
			rs.close();
			st.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	//Mouse M
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(st_table)){
			stno=Integer.parseInt((String)st_table.getValueAt(st_table.getSelectedRow(),0));
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			String titsql="select * from store where 가맹점번호='"+stno+"'";
			try{
				st=con.createStatement();
				ResultSet rs=st.executeQuery(titsql);
				while(rs.next()){
					String row[]=new String[5];
					int j=1;
					
					for(int i=0;i<5;i++){
					row[i]=rs.getString(j);
					tf1[i].setText(row[i]);
					j++;
					}					
				}
				for(int i=0;i<tf1.length;i++){
					tf1[i].setEditable(false);
				}
				rs.close();
				st.close();
				con.close();	
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			//더블클릭
		if(e.getClickCount()==2){
			f_store_edit=new JFrame();
			f_store_edit.setVisible(true);
			f_store_edit.setSize(300,250);
			f_store_edit.setTitle("매장 수정");
			f_store_edit.add(p_editst);
		}
		}
		else if(e.getSource().equals(item_table)){
			itemno=Integer.parseInt((String)item_table.getValueAt(item_table.getSelectedRow(),1));
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			String titsql="select * from item where 제품번호='"+itemno+"'";
			try{
				st=con.createStatement();
				ResultSet rs=st.executeQuery(titsql);
				while(rs.next()){
					String row[]=new String[6];
					int j=1;
					
					for(int i=0;i<5;i++){
					row[i]=rs.getString(j);
					tf3[i].setText(row[i]);
					j++;
					}
					spi2.setModel(snm);
					
				}
				for(int i=0;i<tf3.length;i++){
					tf3[i].setEditable(false);
					spi2.setEnabled(false);
				}
				rs.close();
				st.close();
				con.close();	
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			//더블클릭
		if(e.getClickCount()==2){
			f_item_edit=new JFrame();
			f_item_edit.setVisible(true);
			f_item_edit.setSize(300, 	250);
			f_item_edit.setTitle("제품 수정");
			f_item_edit.add(pp_edititem);
		}
		}
		else if(e.getSource().equals(notice_table)){
			tableno=Integer.parseInt((String)notice_table.getValueAt(notice_table.getSelectedRow(),0));
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			String titsql="select a.`제목`, b.`내용` from notice as a, content as b where a.`번호`=b.`번호` group by a.`번호`"
					+ " having a.`번호`='"+tableno+"'";
			try{
				st=con.createStatement();
				ResultSet rs=st.executeQuery(titsql);
				while(rs.next()){
					String row[]=new String[2];
					int j=1;
					
					for(int i=0;i<2;i++){
					row[i]=rs.getString(j);
					j++;
					}
						noti_edit.setText(row[0]);
						ta2.setText(row[1]);
				}
				noti_edit.setEditable(false);
				ta2.setEditable(false);
				rs.close();
				st.close();
				con.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			//더블클릭
		if(e.getClickCount()==2){
			f_noti_edit=new JFrame();
			f_noti_edit.setVisible(true);
			f_noti_edit.setSize(535, 600);
			f_noti_edit.setTitle("공지사항 수정");
			f_noti_edit.add(ppp_edit);
		}
		}
		else if(e.getSource().equals(request_table)){
			reqno=Integer.parseInt((String)request_table.getValueAt(request_table.getSelectedRow(),0));
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			String titsql="select * from request where `번호`='"+reqno+"'";
			try{
				st=con.createStatement();
				ResultSet rs=st.executeQuery(titsql);
				while(rs.next()){
					String row[]=new String[5];
					int j=1;
					
					for(int i=0;i<5;i++){
					row[i]=rs.getString(j);
					j++;
					}
						req_view1.setText(row[1]);
						req_view2.setText(row[4]);
						ta3.setText(row[3]);
				}
				req_view1.setEditable(false);
				req_view2.setEditable(false);
				ta3.setEditable(false);
				rs.close();
				st.close();
				con.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
			//더블클릭
		if(e.getClickCount()==2){
			f_req_view=new JFrame();
			f_req_view.setVisible(true);
			f_req_view.setSize(535, 600);
			f_req_view.setTitle("요구사항 내용");
			f_req_view.add(pppp_view);
		}
		}
		else if(e.getSource().equals(order_table)){
			ordno=Integer.parseInt((String)order_table.getValueAt(order_table.getSelectedRow(),0));
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			String titsql="select * from orderitem where `제품번호`='"+ordno+"'";
			try{
				st=con.createStatement();
				ResultSet rs=st.executeQuery(titsql);
				while(rs.next()){
					String row[]=new String[4];
					int j=1;
					
					for(int i=0;i<4;i++){
					row[i]=rs.getString(j);
					j++;
					}
				}
				rs.close();
				st.close();
				con.close();
			}
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==option){		//계정 설정
			int result=JOptionPane.showConfirmDialog(new JFrame(),setting, "계정 설정",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				String id="";
				String pw="";
				for(char a : login_pw.getPassword()){
					pw+=a;
				}
				id=login_tf_id.getText();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					String update="Update login set id='"+id+"',pw='"+pw+"'";
					st=con.createStatement();
					int n=st.executeUpdate(update);
					
					if(n>0){
						JOptionPane.showMessageDialog(null, "수정 성공", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "수정 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
				
					st.close();
					con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
	        } else {
	        	JOptionPane.showMessageDialog(null, "Canceled");
	        }
		}
		else if(ev.getSource()==exit){		//종료
			System.exit(0);
		}
		else if(ev.getSource()==bu_store[0]){		//매장 불러오기
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			this.Refresh("store", 5, model);
		}
		else if(ev.getSource()==addstore){		//매장 추가
			int result=JOptionPane.showConfirmDialog(new JFrame(),p_addst, "매장 추가",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				for(int i= model.getRowCount()-1; i>=0; i--) {
				    model.removeRow(i);
				}
	            int n1=Integer.parseInt(tf[0].getText());
	            int n2=Integer.parseInt(tf[1].getText());
	            this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					
					String sql_log="insert into login_test values('"+tf[1].getText()+"','"+tf[0].getText()+"','"+tf[4].getText()+"')";
					st=con.createStatement();
					int m=st.executeUpdate(sql_log);
					
					st=con.createStatement();
					String sql="insert into store values('"+n1+"','"+n2+"','"+tf[2].getText()+"','"+tf[3].getText()+"','"+tf[4].getText()+"')";
					int n=st.executeUpdate(sql);
					if(n>0){
						JOptionPane.showMessageDialog(null, "매장 추가 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						ResultSet rs=st.executeQuery("Select * from store");
						while(rs.next()){
							String row[]=new String[5];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);

							model.addRow(row);
						}
						rs.close();
					}
					else{
						JOptionPane.showMessageDialog(null, "매장 추가 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					
					st.close();
					con.close();
					for(int i=0;i<tf.length;i++){
						tf[i].setText("");
					}
				}
				catch(SQLException e){
					e.printStackTrace();
				}
	        } else {
	        	JOptionPane.showMessageDialog(null, "Canceled");
	        }
		}
		else if(ev.getSource()==bu_store[1]){		//매장 추가 버튼
			int result=JOptionPane.showConfirmDialog(new JFrame(),p_addst, "매장 추가",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				for(int i= model.getRowCount()-1; i>=0; i--) {
				    model.removeRow(i);
				}
	            this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					String sql_log="insert into login_test values('"+tf[1].getText()+"','"+tf[0].getText()+"','"+tf[4].getText()+"')";
					st=con.createStatement();
					int m=st.executeUpdate(sql_log);
					
					
					String sql="insert into store values('"+tf[0].getText()+"','"+tf[1].getText()+"','"+tf[2].getText()+"','"+tf[3].getText()+"','"+tf[4].getText()+"')";
					st=con.createStatement();
					int n=st.executeUpdate(sql);
					if(n>0){
						JOptionPane.showMessageDialog(null, "매장 추가 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						ResultSet rs=st.executeQuery("Select * from store");
						while(rs.next()){
							String row[]=new String[5];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
					
							model.addRow(row);
						}
						rs.close();
					}
					else{
						JOptionPane.showMessageDialog(null, "매장 추가 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					
					st.close();
					con.close();
					for(int i=0;i<tf2.length;i++){
						tf[i].setText("");
					}
				}
				catch(SQLException e){
					e.printStackTrace();
				}
	        } else {
	        	JOptionPane.showMessageDialog(null, "Canceled");
	        }
		}
		else if(ev.getSource()==searchstore){		//매장 검색
			int result=JOptionPane.showConfirmDialog(new JFrame(),p_searchst, "매장 검색",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(result==JOptionPane.OK_OPTION){
				for(int i= model.getRowCount()-1; i>=0; i--) {
				    model.removeRow(i);
				}
				String c=(String)com_st.getSelectedItem();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					 
					String s_sql="Select * from store where "+c+"='"+st_search.getText()+"'";
					st=con.createStatement();
					ResultSet rs=st.executeQuery(s_sql);
					while(rs.next()){
						String row[]=new String[5];
						
						row[0]=rs.getString(1);
						row[1]=rs.getString(2);
						row[2]=rs.getString(3);
						row[3]=rs.getString(4);
						row[4]=rs.getString(5);
					
						model.addRow(row);
					}
					rs.close();
					st.close();
					con.close();
					st_search.setText("");
				
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Canceled");
			}
			}
		else if(ev.getSource()==bu_store[2]){		//매장 검색 버튼
				for(int i= model.getRowCount()-1; i>=0; i--) {
				    model.removeRow(i);
				}
				String c=(String)com_st2.getSelectedItem();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					 
					String s_sql="Select * from store where "+c+"='"+st_search2.getText()+"'";
					st=con.createStatement();
					ResultSet rs=st.executeQuery(s_sql);
					while(rs.next()){
						String row[]=new String[5];
						
						row[0]=rs.getString(1);
						row[1]=rs.getString(2);
						row[2]=rs.getString(3);
						row[3]=rs.getString(4);
						row[4]=rs.getString(5);
					
						model.addRow(row);
					}
					rs.close();
					st.close();
					con.close();
					st_search2.setText("");
				
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		else if(ev.getSource()==delstore){		//매장 삭제
			int result=JOptionPane.showConfirmDialog(new JFrame(),p_searchst, "매장 삭제",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(result==JOptionPane.OK_OPTION){
				for(int i= model.getRowCount()-1; i>=0; i--) {
				    model.removeRow(i);
				}
				String c=(String)com_st.getSelectedItem();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{ 
					String s_sql="Delete from store where "+c+"='"+st_search.getText()+"'";
					st=con.createStatement();
					int n=st.executeUpdate(s_sql);
					if(n>0){
						ResultSet rs=st.executeQuery("Select * from store");
						while(rs.next()){
							String row[]=new String[5];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
					
							model.addRow(row);
						}
						JOptionPane.showMessageDialog(null, "삭제 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						rs.close();
					}
					st.close();
					con.close();
					st_search.setText("");
				
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Canceled");
			}
			}
		else if(ev.getSource()==bu_store_edit[0]){		//매장 삭제 버튼
				for(int i= model.getRowCount()-1; i>=0; i--) {
				    model.removeRow(i);
				}
				String c=(String)com_st.getSelectedItem();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					 
					String s_sql="Delete from store where 가맹점번호='"+stno+"'";
					st=con.createStatement();
					int n=st.executeUpdate(s_sql);
					if(n>0){
						ResultSet rs=st.executeQuery("Select * from store");
						while(rs.next()){
							String row[]=new String[5];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
					
							model.addRow(row);
						}
						JOptionPane.showMessageDialog(null, "삭제 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						rs.close();
					}
					st.close();
					con.close();
					f_store_edit.dispose();
				
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		else if(ev.getSource()==bu_store_edit[1]){				// 매장 수정
			bu_store_edit[2].setEnabled(true);
			for(int i=0;i<tf1.length;i++){
				tf1[i].setEditable(true);
			}
		}
		else if(ev.getSource()==bu_store_edit[2]){				// 매장 수정확인
			
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					
					String update_a="Update login_test set id='"+tf1[1].getText()+"',stnum='"+tf1[0].getText()+"',대표자='"+tf1[4].getText()+"' where stnum='"+stno+"'";
					st=con.createStatement();
					st.executeUpdate(update_a);
					
					String update="Update store set 가맹점번호='"+tf1[0].getText()+"',사업자번호='"+tf1[1].getText()+"',주소='"+tf1[2].getText()+"',전화번호='"+tf1[3].getText()+"',대표자='"+tf1[4].getText()+"' where 가맹점번호='"+stno+"'";
					st=con.createStatement();
					int n=st.executeUpdate(update);
					if(n>0){
						this.Refresh("store", 5,model);
						f_store_edit.dispose();
						JOptionPane.showMessageDialog(null, "수정 성공", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						f_store_edit.dispose();
						JOptionPane.showMessageDialog(null, "수정 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					st.close();
					con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			else if(ev.getSource()==bu_store_edit[3]){		// 매장 수정 취소
				f_store_edit.dispose();
				JOptionPane.showMessageDialog(null, "Canceled", "info", JOptionPane.INFORMATION_MESSAGE);
			}
		else if(ev.getSource()==bu_item[0]){		//제품 불러오기
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			this.Refresh("item", 6, model2);
		}
		else if(ev.getSource()==additem){		//제품 추가
			int result=JOptionPane.showConfirmDialog(new JFrame(),pp_additem, "제품 추가",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				for(int i= model2.getRowCount()-1; i>=0; i--) {
				    model2.removeRow(i);
				}
	            int n1=Integer.parseInt(tf2[1].getText());
	            int n2=Integer.parseInt(tf2[3].getText());
	            this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					st=con.createStatement();
					String sql="insert into item values('"+tf2[0].getText()+"','"+n1+"','"+tf2[2].getText()+"','"+n2+"','"+tf2[4].getText()+"','"+count+"')";
					
					int n=st.executeUpdate(sql);
					if(n>0){
						JOptionPane.showMessageDialog(null, "제품 추가 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						ResultSet rs=st.executeQuery("Select * from item");
						while(rs.next()){
							String row[]=new String[6];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
							row[5]=rs.getString(6);
					
							model2.addRow(row);
						}
						rs.close();
					}
					else{
						JOptionPane.showMessageDialog(null, "제품 추가 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					
					st.close();
					con.close();
					for(int i=0;i<tf2.length;i++){
						tf2[i].setText("");
					}
					spi.setModel(snm);
				}
				catch(SQLException e){
					e.printStackTrace();
				}
	        } else {
	        	JOptionPane.showMessageDialog(null, "Canceled");
	        }
		}
		else if(ev.getSource()==bu_item[1]){		//제품 추가 버튼
			int result=JOptionPane.showConfirmDialog(new JFrame(),pp_additem, "제품 추가",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				for(int i= model2.getRowCount()-1; i>=0; i--) {
				    model2.removeRow(i);
				}
	            int n1=Integer.parseInt(tf2[1].getText());
	            int n2=Integer.parseInt(tf2[3].getText());
	            this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					st=con.createStatement();
					String sql="insert into item values('"+tf2[0].getText()+"','"+n1+"','"+tf2[2].getText()+"','"+n2+"','"+tf2[4].getText()+"','"+count+"')";
					
					int n=st.executeUpdate(sql);
					if(n>0){
						JOptionPane.showMessageDialog(null, "제품 추가 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						ResultSet rs=st.executeQuery("Select * from item");
						while(rs.next()){
							String row[]=new String[6];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
							row[5]=rs.getString(6);
					
							model2.addRow(row);
						}
						rs.close();
					}
					else{
						JOptionPane.showMessageDialog(null, "제품 추가 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					
					st.close();
					con.close();
					for(int i=0;i<tf2.length;i++){
						tf2[i].setText("");
					}
					spi.setModel(snm);
				}
				catch(SQLException e){
					e.printStackTrace();
				}
	        } else {
	        	JOptionPane.showMessageDialog(null, "Canceled");
	        }
		}
		else if(ev.getSource()==searchitem){	//제품 검색
			int result=JOptionPane.showConfirmDialog(new JFrame(),pp_searchitem, "제품 검색",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(result==JOptionPane.OK_OPTION){
				for(int i= model2.getRowCount()-1; i>=0; i--) {
				    model2.removeRow(i);
				}
				String c2=(String)com_item.getSelectedItem();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					String s_sql="Select * from item where "+c2+"='"+item_search.getText()+"'";
					st=con.createStatement();
					ResultSet rs=st.executeQuery(s_sql);
					while(rs.next()){
						String row[]=new String[6];
						
						row[0]=rs.getString(1);
						row[1]=rs.getString(2);
						row[2]=rs.getString(3);
						row[3]=rs.getString(4);
						row[4]=rs.getString(5);
						row[5]=rs.getString(6);
					
						model2.addRow(row);
					}
					rs.close();
					st.close();
					con.close();
					item_search.setText("");
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Canceled");
			}
			}
		else if(ev.getSource()==bu_item[2]){	//제품 검색 버튼
				for(int i= model2.getRowCount()-1; i>=0; i--) {
				    model2.removeRow(i);
				}
				String c=(String)com_item.getSelectedItem();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					String s_sql="Select * from item where "+c+"='"+item_search2.getText()+"'";
					st=con.createStatement();
					ResultSet rs=st.executeQuery(s_sql);
					while(rs.next()){
						String row[]=new String[6];
						
						row[0]=rs.getString(1);
						row[1]=rs.getString(2);
						row[2]=rs.getString(3);
						row[3]=rs.getString(4);
						row[4]=rs.getString(5);
						row[5]=rs.getString(6);
					
						model2.addRow(row);
					}
					rs.close();
					st.close();
					con.close();
					item_search2.setText("");
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}		
		else if(ev.getSource()==delitem){		//제품 삭제
			int result=JOptionPane.showConfirmDialog(new JFrame(),pp_searchitem, "제품 삭제",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(result==JOptionPane.OK_OPTION){
				for(int i= model2.getRowCount()-1; i>=0; i--) {
				    model2.removeRow(i);
				}
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				int c=Integer.parseInt(item_search.getText());
				try{
					String sql_s="Delete from orderitem where 제품번호 ='"+c+"'";
					st=con.createStatement();
					st.executeUpdate(sql_s);
					String s_sql="Delete from item  where 제품번호 ='"+c+"'";
					st=con.createStatement();
					int n=st.executeUpdate(s_sql);
					if(n>0){
						ResultSet rs=st.executeQuery("Select * from item");
						while(rs.next()){
							String row[]=new String[6];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
							row[5]=rs.getString(6);
					
							model2.addRow(row);
						}
						JOptionPane.showMessageDialog(null, "삭제 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						rs.close();
					}
					st.close();
					con.close();
					item_search.setText("");
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Canceled");
			}
		}
		else if(ev.getSource()==bu_item_edit[0]){		//제품 삭제 버튼
				for(int i= model2.getRowCount()-1; i>=0; i--) {
				    model2.removeRow(i);
				}
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					String sql_s="Delete from orderitem where 제품번호 ='"+itemno+"'";
					st=con.createStatement();
					st.executeUpdate(sql_s);
					String s_sql="Delete from item where 제품번호='"+itemno+"'";
					st=con.createStatement();
					int n=st.executeUpdate(s_sql);
					if(n>0){
						ResultSet rs=st.executeQuery("Select * from item");
						while(rs.next()){
							String row[]=new String[6];
							
							row[0]=rs.getString(1);
							row[1]=rs.getString(2);
							row[2]=rs.getString(3);
							row[3]=rs.getString(4);
							row[4]=rs.getString(5);
							row[5]=rs.getString(6);
					
							model2.addRow(row);
						}
						JOptionPane.showMessageDialog(null, "삭제 성공", "info", JOptionPane.INFORMATION_MESSAGE);
						rs.close();
					}
					st.close();
					con.close();
					f_item_edit.dispose();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}
		else if(ev.getSource()==bu_item_edit[1]){				// 제품 수정
			bu_item_edit[2].setEnabled(true);
			for(int i=0;i<tf3.length;i++){
				tf3[i].setEditable(true);
				spi2.setEnabled(true);
			}
		}
		else if(ev.getSource()==bu_item_edit[2]){				// 제품 수정확인
			
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					String update="Update item set 제품종류='"+tf3[0].getText()+"',제품번호='"+tf3[1].getText()+"',제품명='"+tf3[2].getText()+"',제품가격='"+tf3[3].getText()+"',납품업체='"+tf3[4].getText()+"',수량='"+spi2.getValue()+"' where 제품번호='"+itemno+"'";
					st=con.createStatement();
					int n=st.executeUpdate(update);
					
					if(n>0){
						this.Refresh("item", 6,model2);
						f_item_edit.dispose();
						JOptionPane.showMessageDialog(null, "수정 성공", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						f_item_edit.dispose();
						JOptionPane.showMessageDialog(null, "수정 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
				
					st.close();
					con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			else if(ev.getSource()==bu_item_edit[3]){		// 제품 수정 취소
				f_item_edit.dispose();
				JOptionPane.showMessageDialog(null, "Canceled", "info", JOptionPane.INFORMATION_MESSAGE);
			}
		else if(ev.getSource()==bu_notice[0]){					// 공지 새로고침
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			this.Refresh("notice", 4,model3);
			
		}
		else if(ev.getSource()==b_noti_search){		// 공지 검색
			for(int i=model3.getRowCount()-1;i>=0;i--){
				model3.removeRow(i);
			}
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			String c=(String)com_noti.getSelectedItem();
			try{
				String searchsql="Select * from notice where "+c+"='"+noti_search.getText()+"'";
				st=con.createStatement();
				ResultSet rs=st.executeQuery(searchsql);
				while(rs.next()){
					String row[]=new String[4];
					
					
					int j=1;
					for(int i=0;i<4;i++){
						row[i]=rs.getString(j);
						j++;
						}
					
					model3.addRow(row);
				}
				rs.close();
				st.close();
				con.close();
				noti_search.setText("");
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	else if(ev.getSource()==bu_notice[1]){					// 공지 글쓰기
		noti_write.setText("");
		ta1.setText("");
		int result=JOptionPane.showConfirmDialog(new JFrame(), ppp_write, "글쓰기",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		if(result==JOptionPane.OK_OPTION){
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			try{
				String sql="Select id from login";
				st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				rs.next();
				String writer=rs.getString(1);
				
				sql="Insert into notice(`제목`,`작성자`,`작성일`) values('"+noti_write.getText()+"','"+writer+"','"+this.GetDate()+"')";
				st=con.createStatement();
				int n=st.executeUpdate(sql);
				
				sql="Select `번호` from notice group by `번호` desc";
				st=con.createStatement();
				rs=st.executeQuery(sql);
				int num;
				rs.next();
				num=Integer.parseInt(rs.getString(1));
	
				sql="Insert into content(`번호`,`내용`) values('"+num+"','"+ta1.getText()+"')";
				st=con.createStatement();
				n=st.executeUpdate(sql);
				
				if(n>0){
					tablerow++;
					this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
					this.Rownum("notice");
					this.Rownum("content");
					this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
					this.Refresh("notice", 4,model3);
					JOptionPane.showMessageDialog(null, "글쓰기 성공", "info", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "글쓰기 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					
				}
				rs.close();
				st.close();
				con.close();
				noti_search.setText("");
			
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	else if(ev.getSource()==bu_noti_edit[0]){				// 공지 삭제
		this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
		
		try{
			String sql="Delete from content where `번호`='"+tableno+"'";
			st=con.createStatement();
			int n=st.executeUpdate(sql);
			
			sql="Delete from notice where `번호`='"+tableno+"'";
			st=con.createStatement();
			n=st.executeUpdate(sql);
			
			sql="select (select count(*) from notice as b where a.`번호`>b.`번호`) as "
					+ "`번호` ,a.`제목`,a.`작성자`, a.`작성일` from notice as a"	;
			ResultSet rs=st.executeQuery(sql);
			for(int i=model3.getRowCount()-1;i>=0;i--){
				model3.removeRow(i);
			}
			while(rs.next()){
				String row[]=new String[4];
				int j=1;
				
				for(int i=0;i<4;i++){
				row[i]=rs.getString(j);
				j++;
				}
				
				model3.addRow(row);
			}
			
			if(n>0){
				tablerow--;
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				this.Rownum("notice");
				this.Rownum("content");
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				this.Refresh("notice", 4,model3);
				JOptionPane.showMessageDialog(null, "삭제 성공", "info", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "삭제 실패", "info", JOptionPane.INFORMATION_MESSAGE);
			}
			st.close();
			con.close();
			f_noti_edit.dispose();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	else if(ev.getSource()==bu_noti_edit[1]){				// 공지 수정
		bu_noti_edit[2].setEnabled(true);
		noti_edit.setEditable(true);
		ta2.setEditable(true);
	}
	else if(ev.getSource()==bu_noti_edit[2]){				// 공지 수정확인
		
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			try{
				String update="Update notice set `제목`='"+noti_edit.getText()+"' where `번호`='"+tableno+"'";
				st=con.createStatement();
				int n=st.executeUpdate(update);

				update="Update content set `내용`='"+ta2.getText()+"' where `번호`='"+tableno+"'";
				st=con.createStatement();
				n=st.executeUpdate(update);
				
				if(n>0){
					this.Refresh("notice", 4,model3);
					f_noti_edit.dispose();
					JOptionPane.showMessageDialog(null, "수정 성공", "info", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					f_noti_edit.dispose();
					JOptionPane.showMessageDialog(null, "수정 실패", "info", JOptionPane.INFORMATION_MESSAGE);
				}
			
				st.close();
				con.close();
				noti_search.setText("");
			
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		else if(ev.getSource()==bu_noti_edit[3]){		// 공지 수정 취소
			f_noti_edit.dispose();
			JOptionPane.showMessageDialog(null, "Canceled", "info", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(ev.getSource() == b_req_open){		// 요구사항 불러오기
			this.DBConnect_JSP("jdbc:mysql://203.250.133.104:3306/s1207?user=s1207&password=1234");
			this.Refresh("request", 5, model4);
			}
		else if(ev.getSource()==b_req_search){		// 요구사항 검색
			for(int i=model4.getRowCount()-1;i>=0;i--){
				model4.removeRow(i);
			}
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			String c=(String)com_req.getSelectedItem();
			try{
				String searchsql="Select * from request where "+c+"='"+req_search.getText()+"'";
				st=con.createStatement();
				ResultSet rs=st.executeQuery(searchsql);
				while(rs.next()){
					String row[]=new String[5];
					int j=1;
					for(int i=0;i<5;i++){
						row[i]=rs.getString(j);
						j++;
						}
					
					model4.addRow(row);
				}
				rs.close();
				st.close();
				con.close();
				req_search.setText("");
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		else if(ev.getSource()==bu_req_view[0]){		//요구사항 처리 버튼
			for(int i= model4.getRowCount()-1; i>=0; i--) {
			    model4.removeRow(i);
			}
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			try{
				 
				String s_sql="Delete from request where 번호='"+reqno+"'";
				st=con.createStatement();
				int n=st.executeUpdate(s_sql);
				if(n>0){
					if(n>0){
						tablerow--;
						this.Rownum("request");
						this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
						this.Refresh("request", 5,model4);
						f_req_view.dispose();
						JOptionPane.showMessageDialog(null, "처리 완료", "info", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						f_req_view.dispose();
						JOptionPane.showMessageDialog(null, "처리 실패", "info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				st.close();
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
		else if(ev.getSource()==bu_req_view[1]){		// 요구사항 상세보기 닫기
			f_req_view.dispose();
		}
		else if(ev.getSource()==bu_order[0]){		//주문내역 불러오기
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			this.Refresh("orderitem", 4, model5);
		}
		else if(ev.getSource()==bu_order[1]){		//주문내역 처리 버튼
			for(int i= model5.getRowCount()-1; i>=0; i--) {
			    model5.removeRow(i);
			}
			this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
			try{
				 
				String s_sql="Delete from orderitem where 제품번호='"+ordno+"'";
				st=con.createStatement();
				int n=st.executeUpdate(s_sql);
				if(n>0){
					ResultSet rs=st.executeQuery("Select * from orderitem");
					while(rs.next()){
						String row[]=new String[4];
						
						row[0]=rs.getString(1);
						row[1]=rs.getString(2);
						row[2]=rs.getString(3);
						row[3]=rs.getString(4);
				
						model5.addRow(row);
					}
					JOptionPane.showMessageDialog(null, "처리 완료", "info", JOptionPane.INFORMATION_MESSAGE);
					rs.close();
				}
				st.close();
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
	}
		else if(ev.getSource()==bu_order[2]){	//주문내역 검색 버튼
				for(int i= model5.getRowCount()-1; i>=0; i--) {
				    model5.removeRow(i);
				}
				String c2=(String)com_order.getSelectedItem();
				this.DBConnect("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
				try{
					String s_sql="Select * from orderitem where "+c2+"='"+order_search.getText()+"'";
					st=con.createStatement();
					ResultSet rs=st.executeQuery(s_sql);
					while(rs.next()){
						String row[]=new String[4];
						
						row[0]=rs.getString(1);
						row[1]=rs.getString(2);
						row[2]=rs.getString(3);
						row[3]=rs.getString(4);
					
						model5.addRow(row);
					}
					rs.close();
					st.close();
					con.close();
					order_search.setText("");
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}
		}
	public static void main(String[] args) {
		//Login
		JPanel	l_p=new JPanel();
		l_p.setLayout(null);
		l_p.setPreferredSize(new Dimension(180,100));
		JLabel	log=new JLabel("ID&PW 입력");
		log.setBounds(20,0,160,40);
		l_p.add(log);
		for(int i=0;i<2;i++){
			login_la[i]=new JLabel(log_la[i]);
			login_la[i].setBounds(20,40+(i*20),20,20);
			l_p.add(login_la[i]);
		}
		JTextField l_tf=new JTextField();
		l_tf.setBounds(60, 40,150, 20);
		l_p.add(l_tf);
		JPasswordField l_pw=new JPasswordField();
		l_pw.setBounds(60,60,150,20);
		l_p.add(l_pw);
		int count=1;
		while(count!=-1){
			int result=JOptionPane.showConfirmDialog(new JFrame(),l_p, "StoreManager final",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(result==JOptionPane.OK_OPTION){
				try{
					Class.forName("org.gjt.mm.mysql.Driver");
				}
				catch(ClassNotFoundException e){
				}
				Connection con=null;
				String url="jdbc:mysql://203.250.133.104:3306/s1207";
				String user="s1207";
				String password="1234";
				try{
					con=DriverManager.getConnection(url,user,password);
				}
				catch(SQLException e){
				}
				Statement st=null;
				try{
					String m_pw="";
					for(char a : l_pw.getPassword()){
						m_pw+=a;
					}
					st=con.createStatement();
					ResultSet rs=st.executeQuery("Select * from login");
					rs.next();
					d_id=rs.getString(1);
					d_pw=rs.getString(2);
					if(d_id.equals(l_tf.getText())&&d_pw.equals(m_pw)){
						Test1 ps=new Test1("StoreManager ver 5.0");
						JOptionPane.showMessageDialog(null, "프로그램을 시작합니다.");
						count=-1;
					}
					else {
						JOptionPane.showMessageDialog(null, "일치하지 않습니다.");
						l_tf.setText("");
						l_pw.setText("");
					}
					rs.close();
					st.close();
					con.close();	
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				}
			else{
				JOptionPane.showMessageDialog(null, "Canceled");
				System.exit(0);
				count=-1;
			}
				
		}	
		//Test1 ps=new Test1("StoreManager ver 5.0");
	}
}