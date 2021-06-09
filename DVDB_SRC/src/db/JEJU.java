package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class JEJU {
	
	private static String url = "jdbc:postgresql://localhost:5432/postgres";
	private static String user = "unlimit13";
	private static String password = "1q2w3e4r!";
	

	public static void main(String args[]) throws SQLException, IOException {
		Connection conn = null;
		
		Scanner scan = new Scanner(System.in);
		
		int result;
		String sql;
		PreparedStatement ps;
	    ResultSet rs;

	    int state = 0;
		int check_total_attraction=1;

		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password );
			sql = "create table if not exists host(uID varchar(20) primary key, pwd varchar(20), sex char(2), age int);"+
					"create table if not exists best_lodgement(uID varchar(20), sel_class varchar(10), sex char(2), age int);"+
					"create table if not exists best_food(uID varchar(20),class_f int, menu varchar(30), sex char(2), age int);"+
					"create table if not exists best_attraction(uID varchar(20), sel_class varchar(10), sex char(2), age int);";
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate(); //host, rank table�깮�꽦
			
			sql = "create or replace view bt_food as select sex,age,class_f,menu,count(uID) from best_food group by sex,age,class_f,menu order by count(uID) desc;"+
					"create or replace view bt_lodgement as select sex,age,sel_class,count(uID) from best_lodgement group by sex,age,sel_class order by count(uID) desc;"+
					"create or replace view bt_attraction as select sex,age,sel_class, count(uID) from best_attraction group by sex,age,sel_class order by count(uID) desc;";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate(); //rank view�깮�꽦
			
			
			Statement statement = conn.createStatement();
			view_att(statement);
			view_att(statement);

			
			if(check_total_attraction == 1)
			{
				Methods.total_attraction(statement); //(total attraction �뀒�씠釉� �깮�꽦�썑 attraction 紐⑤몢 �빀移�)
				check_total_attraction = -1;
			}
			

			
			while(true) 
			{	
				System.out.println("-----------------");
				System.out.println("1. sign up");
				System.out.println("2. sign in");
				System.out.println("3. Exit");
				System.out.println("-----------------");
				String bit = scan.nextLine(); 

				
				if(bit.contains("1")) { 	//bit1�� sign up
					System.out.println();
					System.out.println("-----------------");
					System.out.print("ID : ");
					
					String uID = scan.nextLine();
					
					System.out.print("PW : ");
					String PWD = scan.nextLine();
					
					System.out.print("Sex(F,M) : ");
					String Sex = scan.nextLine();
					
					System.out.print("Age : ");
					String temp = scan.nextLine();
					int Age = Integer.parseInt(temp);
					
					
					System.out.println("-----------------");
					
					sql = "insert into host values(?,?,?,?);";
			        ps = conn.prepareStatement(sql);
			        ps.clearParameters();
			        ps.setString(1,uID);
			        ps.setString(2,PWD);
			        if(Sex == "F") {
			        	Sex="f";
			        }
			        else if(Sex =="M") {
			        	Sex="m";
			        }
			        ps.setString(3,Sex);
			        ps.setInt(4,Age);
			        result = ps.executeUpdate();
			        
			        System.out.println();
					System.out.println("Sign up complete!");
					System.out.println();
					continue;
				}
			
				else if(bit.contains("2")) 
				{ 	//bit1�� sign in
					System.out.println();
					System.out.print("ID : ");
					String uID = scan.nextLine();
					System.out.print("PW : ");
					String PWD = scan.nextLine();
					sql = "select count(*) from host where uID = ?;";
					ps = conn.prepareStatement(sql);
					ps.clearParameters();
				    ps.setString(1,uID);
					rs = ps.executeQuery();
					rs.next();
					
					
					int count = rs.getInt(1);
					if(count < 1)
					{
						System.out.println("媛��엯�븯吏� �븡�� �궗�슜�옄�엯�땲�떎");
						System.out.println();
						continue;
					}
					sql = "select pwd from host where uID = ?;";
					ps = conn.prepareStatement(sql);
					ps.clearParameters();
					ps.setString(1,uID);
					rs = ps.executeQuery();
				
					rs.next();
					String PWD_in_table = rs.getString(1);
				
					if(!PWD.equals(PWD_in_table)) //鍮꾨�踰덊샇 �엯�젰�씠 ��由щ㈃,
					{
						System.out.println("Password is incorrect\n");
		
						continue;
					}
					
					int exit =0;
					while (exit ==0) {
					String sex = "";
					int age = 0;
					sql = "select uID,sex,age from host where uID = ?;";
					ps = conn.prepareStatement(sql);
					ps.clearParameters();
					ps.setString(1,uID);
					rs = ps.executeQuery();
					while(rs.next())
						{
							uID = rs.getString(1);
							sex = rs.getString(2);
							age = rs.getInt(3);
						} // best���옣�쓣 �쐞�븳 �젙蹂� 異붿텧
					
					System.out.println();
					System.out.println("Welcome!\n");
					System.out.println("-----------------");
					System.out.println("1. �닕諛�");
					System.out.println("2. �떇�떦");
					System.out.println("3. 愿�愿�");
					System.out.println("4. �닚�쐞�솗�씤");
					System.out.println("5. log out");
					System.out.println("-----------------");
				
					int token = scan.nextInt();
					
					if(token==1) {//�닕諛�, 吏덈Ц�삎�깭
						String first = "";
						String second = "";
						String third = "";
						String class_l = "";
						System.out.println();
						System.out.println("Select 1 or 2");
						System.out.println("-----------------");
						System.out.println("1. �젣二쇱뒪�윭�슫");
						System.out.println("2. �윮�뀛由ы븳");
						System.out.println("-----------------");
					
						int token1 = scan.nextInt();
						System.out.println();
						if(token1==1) {
							System.out.println("Selected '�젣二쇱뒪�윭�슫'");
							first ="�젣二쇱뒪�윭�슫";
						}
						else if(token1==2) {
							System.out.println("Select '�윮�뀛由ы븳'");
							first ="�윮�뀛由ы븳";
						}
					
						System.out.println();
						System.out.println("-----------------");
						System.out.println("1. �룆梨� ");
						System.out.println("2. �떎�꽭�� ");
						System.out.println("-----------------");
						int token11 = scan.nextInt();
						System.out.println();
						if(token11 == 1) {
							System.out.println("Select '�룆梨�' ");
							second = "�룆梨� ";
						}
						if(token11 == 2) {
							System.out.println("Select '�떎�꽭��'");
							second = "�떎�꽭�� ";
						}
					
						scan.nextLine();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("1. �룄�떖");
						System.out.println("2. �븳�쟻�븳");
						System.out.println("-----------------");
					
					
						String temp = scan.nextLine();
					
						int token111;
						if(temp.contains("1"))
							token111 = 1;
						else
							token111 = 2;
						System.out.println();
						if(token111 == 1) {
							System.out.println("Select '�룄�떖' ");
							third = "�룄�떖 ";
						}
						if(token111 == 2) {
							System.out.println("Select '�븳�쟻�븳'");
							third = "�븳�쟻�븳 ";
						}
					
						String select = String.valueOf(token1).concat(String.valueOf(token11));
						select = select.concat(String.valueOf(token111));
					//select�뒗 111~222
					
						statement = conn.createStatement();
						
						second = second.concat(","+third);
						first = first.concat(","+second);
						System.out.println();
						System.out.println("---------------寃곌낵----------------");
						System.out.println("�꽑�깮�맂 " + first);
						Methods.lodg(statement, select);
						
						sql = "insert into best_lodgement values(?,?,?,?);";
						ps = conn.prepareStatement(sql);
						ps.clearParameters();
					    ps.setString(1, uID);
					    ps.setString(2, select);
					    ps.setString(3, sex);
					    ps.setInt(4, (age/10)*10);
						ps.execute();
						
						System.out.println();
					
					}
				
					else if(token==2) {//�쓬�떇, �썡�뱶而� �삎�깭
						System.out.println();
						System.out.println("Select");
						System.out.println("-----------------");
						System.out.println("1. �뼢�넗�쓬�떇");
						System.out.println("2. �씪諛섏쟻�씤");
						System.out.println("-----------------");
			            ArrayList<String> candidate_menu = new ArrayList<>();
						int token2 = scan.nextInt();
						int class_f=1;
						if(token2==1) 
						{
							//�뼢�넗�쓬
							sql = "select distinct menu from nativefood;";
				            ps = conn.prepareStatement(sql);
				            rs = ps.executeQuery();
				            String[] native_menu = new String[115];
				            int i = 0;
				            while(rs.next())
				            {
				            	if(rs.getString(1).contains("("))
				            	{
				            		continue;
				            	}
				            	else if(rs.getString(1).contains("+"))
				            	{
				            		String[] temp = rs.getString(1).split("\\+");
				            		for(int k=0;k<temp.length;k++)
				            		{
				            			native_menu[i] = temp[k];
				            			i++;
				            		}
				            		continue;
				            	}
				            	native_menu[i] = rs.getString(1);
				                i++;
				            }
				            
				            System.out.println();

				            for(String menu : native_menu){
				                if(!candidate_menu.contains(menu))
				                {
				                	if(!menu.contains("."))
				                	{
				                		if(!menu.contains(","))
				                		{
				                			candidate_menu.add(menu);
				                		}
				                	}
				                }
				            }
				            
			
				            Collections.shuffle(candidate_menu); //Array�슂�냼�뱾 �뀛�뵆 
				            
				          
				            
						}
						else if(token2==2) 
						{
							//�씪諛섏쓬�떇
							class_f=2;
							sql = "select distinct menu from ordinaryfood;";
							ps = conn.prepareStatement(sql);
				            rs = ps.executeQuery();
				            
				            String[] ordinary_menu = new String[370];
				            int i = 0;
				            while(rs.next())
				            {
				            	if(rs.getString(1) == null || rs.getString(1).contains("(") )
				            	{
				            		continue;
				            	}
				            	else if(rs.getString(1).contains(","))
				            	{
				            		String[] temp = rs.getString(1).split("\\,");
				            		for(int k=0;k<temp.length;k++)
				            		{
				            			ordinary_menu[i] = temp[k];
				            			i++;
				            		}
				            		continue;
				            	}
				            	ordinary_menu[i] = rs.getString(1);
				                i++;
				            }
				            
				            System.out.println();

				            for(String menu : ordinary_menu){
				                if(!candidate_menu.contains(menu))
				                {
				                	if(!menu.contains("."))
				                	{
				                		if(!menu.contains(","))
				                		{
				                			candidate_menu.add(menu);
				                		}
				                	}
				                }
				            }
				            Collections.shuffle(candidate_menu); //Array�슂�냼�뱾 �뀛�뵆   
						}
						
						//candidate_menu�뿉 �떎 �꽔�뿀怨�, �씠�젣 �븵�뿉�꽌 16媛쒕퐨�븘�꽌 혺d�뱶而듭떆�옉
						String[] round1 = new String[16];
						String[] round2 = new String[8];
						String[] round3 = new String[4];
						String[] round4 = new String[2];
						String winner;
						for(int i=0;i<16;i++)
						{
							round1[i] = candidate_menu.get(i);
						}
						
						int k = 15;
						System.out.println("-----------------");
						System.out.println("       16媛�      ");
						System.out.println("-----------------");
						for(int i=0; i<8; i++)
						{
							System.out.println();
							System.out.println("Select");
							System.out.println("1. " + round1[i]);
							System.out.println("2. " + round1[k]);
							if(scan.nextInt() == 1)
							{
								System.out.println(round1[i] + " �듅由�!");
								round2[i] = round1[i];
							}
							else
							{
								System.out.println(round1[k] + " �듅由�!");
								round2[i] = round1[k];
							}
							k--;
						}
						
						System.out.println();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("       8媛�      ");
						System.out.println("-----------------");
						k = 7;
						for(int i=0; i<4; i++)
						{
							System.out.println();
							System.out.println("Select");
							System.out.println("1. " + round2[i]);
							System.out.println("2. " + round2[k]);
							if(scan.nextInt() == 1)
							{
								System.out.println(round2[i] + " �듅由�!");
								round3[i] = round2[i];
							}
							else
							{
								System.out.println(round2[k] + " �듅由�!");
								round3[i] = round2[k];
							}
							k--;
						}
						
						System.out.println();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("       4媛�      ");
						System.out.println("-----------------");
						k = 3;
						for(int i=0; i<2; i++)
						{
							System.out.println();
							System.out.println("Select");
							System.out.println("1. " + round3[i]);
							System.out.println("2. " + round3[k]);
							if(scan.nextInt() == 1)
							{
								System.out.println(round3[i] + " �듅由�!");
								round4[i] = round3[i];
							}
							else
							{
								System.out.println(round2[k] + " �듅由�!");
								round4[i] = round3[k];
							}
							k--;
						}
						
						System.out.println();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("       寃곗듅      ");
						System.out.println("-----------------");
						k = 1;
						
						System.out.println();
						System.out.println("Select");
						System.out.println("1. " + round4[0]);
						System.out.println("2. " + round4[1]);
						if(scan.nextInt() == 1)
						{
							System.out.println(round4[0] + " �듅由�!");
							winner= round4[0];
						}
						else
						{
							System.out.println(round2[k] + " �듅由�!");
							winner = round4[1];
						}
						
						System.out.println();
						System.out.println("-----------------");
						System.out.println("   理쒖쥌�슦�듅" + winner);
						System.out.println("-----------------");
						
						System.out.println();
						System.out.println();
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println();
						System.out.println(winner + " �뙋留ㅼ뾽�냼");
						if(token2==1) //winner媛� �룷�븿�맂 nativefood table�젙蹂대�쇰낫�뿬以��떎 
						{
							sql = "select rname,loc,phonenum,menu from nativefood where menu LIKE ?;";
						}
						else
						{
							sql = "select rname,loc,phonenum,menu from ordinaryfood where menu LIKE ?;";
		
						}
						
						ps = conn.prepareStatement(sql);
						ps.clearParameters();
					    ps.setString(1, "%" + winner + "%");
						rs = ps.executeQuery();
						
						while(rs.next())
						{
							System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getString(4));
						}
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println();
						System.out.println("珥덇린�솕硫댁쑝濡쒕룎�븘媛묐땲�떎 ");
						System.out.println();
						sql = "insert into best_food values(?,?,?,?,?);";
						ps = conn.prepareStatement(sql);
						ps.clearParameters();
					    ps.setString(1, uID);
					    ps.setInt(2, class_f);
					    ps.setString(3, winner);
					    ps.setString(4, sex);
					    ps.setInt(5, (age/10)*10);
						ps.execute();
						scan.nextLine();
					}
				
					else if(token==3) {//관광지 질
						String first = "";
						String select = "";
						System.out.println();
						System.out.println("Select 1 or 2");
						System.out.println("-----------------");
						System.out.println("1. 활동적인 ");
						System.out.println("2. 잔잔한 ");
						System.out.println("3. 올레길 ");
						System.out.println("4. 카페투어 ");
						System.out.println("-----------------");
					
						int token1 = scan.nextInt();
						System.out.println();
						if(token1==1) {
							System.out.println("Selected '활동적인'");
							first ="  활동적인 ";
							select= select + Integer.toString(token1);
							
							System.out.println();
							System.out.println("-----------------");
							System.out.println("1. 바다 ");
							System.out.println("2. 산 ");
							System.out.println("-----------------");
							int token11 = scan.nextInt();
							
							if(token11 == 1) {
								System.out.println("Select '바다'");
								first = first + "  바다 ";
								select = select + Integer.toString(token11);
								
							}else if(token11 == 2) {
								System.out.println("Select '산'");
								first = first + "  산 ";
								select = select + Integer.toString(token11);
								
								System.out.println();
								System.out.println("-----------------");
								System.out.println("1. 승마 ");
								System.out.println("2. 오름 ");
								System.out.println("-----------------");
								int token111 = scan.nextInt();
								
								if(token111 == 1) {
									System.out.println("Selected '승마'");
									first = first + "  승마 ";
									select = select + Integer.toString(token111);
								}else if(token111 == 2) {
									System.out.println("Selected '오름'");
									first = first + "  오름 ";
									select = select + Integer.toString(token111);
								}
							}
						}
						else if(token1==2) {
							System.out.println("Select '잔잔한'");
							first ="  잔잔한 ";
							select = select + Integer.toString(token1);
							
							System.out.println();
							System.out.println("-----------------");
							System.out.println("1. 바다 ");
							System.out.println("2. 산 ");
							System.out.println("3. 기타 ");
							System.out.println("-----------------");
							int token11 = scan.nextInt();
							
							if(token11 == 1) {
								System.out.println("Select '바다'");
								first = first + "  바다 ";
								select = select + Integer.toString(token11);
								
								System.out.println();
								System.out.println("-----------------");
								System.out.println("1. 관광유람선 ");
								System.out.println("2. 레저 ");
								System.out.println("-----------------");
								int token111 = scan.nextInt();
								if(token111 == 1) {
									System.out.println("Select '관광유람선'");
									first = first + "  관광유람선 ";
									select = select + Integer.toString(token111);
								}else if(token111 == 2) {
									System.out.println("Select '레저'");
									first = first + "  레저 ";
									select = select + Integer.toString(token111);
								}
							}else if(token11 == 2) {
								System.out.println("Select '산'");
								first = first +  "  산 ";
								select = select + Integer.toString(token11);
								
								System.out.println();
								System.out.println("-----------------");
								System.out.println("1. 식물원 ");
								System.out.println("2. 관광농원 ");
								System.out.println("3. 숲길 ");
								System.out.println("-----------------");
								int token111 = scan.nextInt();
								
								if(token111 == 1) {
									System.out.println("Select '식물원'");
									first = first + "  식물원 ";
									select = select + Integer.toString(token111);
								}else if(token111 == 2) {
									System.out.println("Select '관광농원'");
									first = first + "  관광농원 ";
									select = select + Integer.toString(token111);
								}else if(token111 == 3) {
									System.out.println("Select '숲길'");
									first = first + "  숲길 ";
									select = select + Integer.toString(token111);
								}
								
							}else if(token11 == 3) {
								System.out.println("Select '기타'");
								first = first +  "  기타 ";
								select = select + Integer.toString(token11);
							}
						}else if(token1==3) {
							System.out.println("Select '오름'");
							first ="  오름 ";
							select = select + Integer.toString(token1);
						}
						else if(token1==4) {
							System.out.println("Select '카페투어'");
							first ="  카페투어 ";
							select = select + Integer.toString(token1);
						}
						
						System.out.println();
						
						statement = conn.createStatement();

						System.out.println(select);
						System.out.println();
						System.out.println("---------------결과----------------");
						System.out.println(first);
						sql = "select direction from host where uid = "+ uID +"::varchar"+";";
						String direction = user_dir(statement, sql);
						attraction(statement, select);
						
						scan.nextLine();

					}
					else if(token==4) {
						
						
						System.out.println("Select");
						System.out.println("-----------------");
						System.out.println("1. Most selected in �쓬�떇");
						System.out.println("2. Most selected in 愿�愿�");
						System.out.println("3. Most selected in �닕諛�");
						System.out.println("-----------------");
					
						int token4 = scan.nextInt();
						if(token4==1) {
							
							System.out.printf("ID : %s�떂�씠 �꽕�젙�븳 %s/%d�� �뿉�꽌 媛��옣 �씤湲곗엳�뒗 �쓬�떇�� ", uID, (sex=="f") ? "�뿬" : "�궓", (age/10)*10);
							sql = "select class_f,menu from bt_food where sex = ? and age = ?;";
							ps = conn.prepareStatement(sql);
							ps.clearParameters();
						    ps.setString(1, sex);
						    ps.setInt(2, (age/10)*10);
							rs = ps.executeQuery();
							rs.next();
							int cate = rs.getInt(1);
							String btmenu=rs.getString(2);
							
							System.out.printf("移댄뀒怨좊━ : %s, �쓬�떇:%s", cate==1 ? "�뼢�넗�쓬�떇" : "�씪諛섏쓬�떇",btmenu);
							System.out.println("�떇�떦");
							if(cate==1) 
							{
								sql = "select rname,loc,phonenum,menu from nativefood where menu LIKE ?;";
							}
							else
							{
								sql = "select rname,loc,phonenum,menu from ordinaryfood where menu LIKE ?;";
			
							}
							
							ps = conn.prepareStatement(sql);
							ps.clearParameters();
						    ps.setString(1, "%" + btmenu + "%");
							rs = ps.executeQuery();
							
							while(rs.next())
							{
								System.out.println(rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getString(4));
							}
							
						
						}
						else if(token4==2) {
							System.out.printf("ID : %s�떂�씠 �꽕�젙�븳 %s/%d�� �뿉�꽌�쓽 媛��옣 �씤湲곗엳�뒗 愿�愿묒��뒗",uID, (sex=="f") ? "�뿬" : "�궓", (age/10)*10);
							sql = "select sel_class from bt_attraction where sex = ? and age = ?;";
							ps = conn.prepareStatement(sql);
							ps.clearParameters();
						    ps.setString(1, sex);
						    ps.setInt(2, (age/10)*10);
							rs = ps.executeQuery();
							rs.next();
							
							String sentence ="";
							String bt_sel1 = rs.getString(1);
							sentence = Methods.getSentence1(sentence, bt_sel1);
							
							System.out.printf("移댄뀒怨좊━: %s",sentence);
							Methods.attraction(statement,bt_sel1);
							
						}
						else if(token4==3) {
							System.out.printf("ID : %s�떂�씠 �꽕�젙�븳 %s/%d�� �뿉�꽌�쓽 媛��옣 �씤湲곗엳�뒗 �닕諛뺥삎�깭�뒗",uID, (sex=="f") ? "�뿬" : "�궓", (age/10)*10);
							sql = "select sel_class from bt_lodgement where sex = ? and age = ?;";
							ps = conn.prepareStatement(sql);
							ps.clearParameters();
						    ps.setString(1, sex);
						    ps.setInt(2, (age/10)*10);
							rs = ps.executeQuery();
							rs.next();
							
							
							
							
							String sentence ="";
							String bt_sel2 = rs.getString(1);
							System.out.println(bt_sel2);
							sentence= Methods.getSentence2(sentence, bt_sel2);
							
							System.out.printf("移댄뀒怨좊━: %s",sentence);
							Methods.lodg(statement, bt_sel2);
						}
					
				
					
						}
					else {
						System.out.printf("%s�떂, 濡쒓렇�븘�썐 �릺�뿀�뒿�땲�떎.\n",uID);
						
						exit=1;
						
						
						}
					}scan.nextLine();
					
				
				}
				else if(bit.contains("3")) 	//bit3��exit
				{
					break;
				
				}
				else if(bit.contains("4")) //bit4�뒗 愿�由ъ옄紐�
			      {
					int ex = 0;
					while(ex==0) {
			         System.out.println();
			         System.out.println("愿�由ъ옄紐⑤뱶 ");
			         System.out.println("-----------------");
			         System.out.println("1. �뜲�씠�꽣愿�由�");
			         System.out.println("2. �쉶�썝愿�由�");
			         System.out.println("3. exit");
			         System.out.println("-----------------");
			         String bit2 = scan.nextLine();
			         if(bit2.contains("1"))
			         {
			            System.out.println();
			            System.out.println("�뜲�씠�꽣愿�由� ");
			            String bit3 = scan.nextLine();
			            if(bit3.contains("1"))
			            {
			               System.out.println();
			               System.out.println("�뜲�씠�꽣異붽�");
			            }
			            else if(bit3.contains("2"))
			            {
			               System.out.println();
			               System.out.println("�뜲�씠�꽣�궘�젣");
			            }
			            
			         }
			         else if(bit2.contains("2"))//�쉶�썝愿�由�
			         {
			            System.out.println();
			            System.out.println("�쉶�썝愿�由� ");
			            System.out.println("-----------------");
			            System.out.println("1. �쟾泥� �궗�슜�옄 �솗�씤");
			            System.out.println("2. �궗�슜�옄 �궘�젣");
			            System.out.println("-----------------");
			            String bit3 = scan.nextLine();
			            String uID, upwd, sex,age;
			            
			            if(bit3.contains("1")) {//�쟾泥� �궗�슜�옄 �솗�씤
			               sql="select * from host;";
			               ps = conn.prepareStatement(sql);
			               rs = ps.executeQuery();
			         
			               System.out.println("-----------------------------------------------------");
			               System.out.println("  \tuID\tpwd\tsex\tage");
			               int index=1;
			               while (rs.next()) {
			                  uID = rs.getString(1);
			                  upwd = rs.getString(2);
			                  sex = rs.getString(3);
			                  age = rs.getString(4);
			                  System.out.printf("%d:\t%s\t%s\t%s\t%s\n",index,uID, upwd,sex,age);
			                  index++;
			               }
			               System.out.println("-----------------------------------------------------");
			               index=1;
			            }
			            else if(bit3.contains("2")) { // �궗�슜�옄 �궘�젣
			               System.out.println("-----------------------------------------------------");
			               System.out.println("�궘�젣�븷 �궗�슜�옄 ID : ");
			               String del_uID = scan.nextLine();
			               sql = "select * from host where uID = ?;";
			               ps = conn.prepareStatement(sql);
			                 ps.clearParameters();
			                 ps.setString(1,del_uID);
			                 rs = ps.executeQuery();
			                 
			                 System.out.println("-----------------------------------------------------");
			               System.out.println("  \tuID\tpwd\tsex\tage");
			               int index=1;
			               while (rs.next()) {
			                  uID = rs.getString(1);
			                  upwd = rs.getString(2);
			                  sex = rs.getString(3);
			                  age = rs.getString(4);
			                  System.out.printf("%d:\t%s\t%s\t%s\t%s\n",index,uID, upwd,sex,age);
			                  index++;
			               }
			               System.out.println("-----------------------------------------------------");
			               index=1;
			               
			               System.out.println("�떎�쓬 �궗�슜�옄瑜� �궘�젣�븯�떆寃좎뒿�땲源�? (y/n)");
			               String bit4 = scan.nextLine();
			               if(bit4.contains("y")){
			                  sql = "delete from host where uID = ?;";
			                  ps = conn.prepareStatement(sql);
			                    ps.clearParameters();
			                    ps.setString(1,del_uID);
			                    rs = ps.executeQuery();
			                    System.out.println("�궘�젣媛� �셿猷뚮릺�뿀�뒿�땲�떎.");
			               }
			               
			               
			            }
			            
			            
			            
			         }
			         else if(bit2.contains("3")) {//醫낅즺
			               System.out.println("珥덇린�솕硫댁쑝濡� �룎�븘媛묐땲�떎.");
			               ex=1;
			            }
					}
					
					
			      }
		            
		     
		      }
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}