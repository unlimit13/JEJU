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
		
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password );
			sql = "create table if not exists host(uID varchar(20), pwd varchar(20), sex char(2), age int, direction char(2));";
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			Statement statement = conn.createStatement();
			//total_attraction(statement); //(total attraction 테이블 생성후 attraction 모두 합침)
			
			while(true) 
			{	
				System.out.println("-----------------");
				System.out.println("1. sign up");
				System.out.println("2. sign in");
				System.out.println("3. Exit");
				System.out.println("-----------------");
				String Direction = "";
				String bit = scan.nextLine(); 

				
				if(bit.contains("1")) { 	//bit1은 sign up
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
					
					System.out.print("plan to visit[E,W,S,N] : ");
					Direction = scan.nextLine();
					
					System.out.println("-----------------");
					
					sql = "insert into host values(?,?,?,?,?);";
			        ps = conn.prepareStatement(sql);
			        ps.clearParameters();
			        ps.setString(1,uID);
			        ps.setString(2,PWD);
			        ps.setString(3,Sex);
			        ps.setInt(4,Age);
			        ps.setString(5,Direction);
			        result = ps.executeUpdate();
			        
			        System.out.println();
					System.out.println("Sign up complete!");
					System.out.println();
					continue;
				}
			
				else if(bit.contains("2")) 
				{ 	//bit1은 sign in
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
						System.out.println("가입하지 않은 사용자입니다");
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
				
					if(!PWD.equals(PWD_in_table)) //비밀번호 입력이 틀리면,
					{
						System.out.println("Password is incorrect\n");
		
						continue;
					}
				
					System.out.println();
					System.out.println("Welcome!\n");
					System.out.println("-----------------");
					System.out.println("1. 숙박");
					System.out.println("2. 식당");
					System.out.println("3. 관광");
					System.out.println("4. 순위확인");
					System.out.println("5. exit");
					System.out.println("-----------------");
				
					int token = scan.nextInt();
					
					if(token==1) {//숙박, 질문형태
						String first = "";
						String second = "";
						String third = "";
						String class_l = "";
						System.out.println();
						System.out.println("Select 1 or 2");
						System.out.println("-----------------");
						System.out.println("1. 제주스러운");
						System.out.println("2. 럭셔리한");
						System.out.println("-----------------");
					
						int token1 = scan.nextInt();
						System.out.println();
						if(token1==1) {
							System.out.println("Selected '제주스러운'");
							first ="제주스러운";
						}
						else if(token1==2) {
							System.out.println("Select '럭셔리한'");
							first ="럭셔리한";
						}
					
						System.out.println();
						System.out.println("-----------------");
						System.out.println("1. 독채 ");
						System.out.println("2. 다세대 ");
						System.out.println("-----------------");
						int token11 = scan.nextInt();
						System.out.println();
						if(token11 == 1) {
							System.out.println("Select '독채' ");
							second = "독채 ";
						}
						if(token11 == 2) {
							System.out.println("Select '다세대'");
							second = "다세대 ";
						}
					
						scan.nextLine();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("1. 도심");
						System.out.println("2. 한적한");
						System.out.println("-----------------");
					
					
						String temp = scan.nextLine();
					
						int token111;
						if(temp.contains("1"))
							token111 = 1;
						else
							token111 = 2;
						System.out.println();
						if(token111 == 1) {
							System.out.println("Select '도심' ");
							third = "도심 ";
						}
						if(token111 == 2) {
							System.out.println("Select '한적한'");
							third = "한적한 ";
						}
					
						String select = String.valueOf(token1).concat(String.valueOf(token11));
						select = select.concat(String.valueOf(token111));
					//select는 111~222
					
						statement = conn.createStatement();
						sql = "select direction from host where uid = "+ uID +"::varchar"+";";
						String direction = Methods.user_dir(statement, sql);
						
						
						System.out.println(select);
						System.out.println();
						System.out.println("---------------결과----------------");
						System.out.println("선택된 " + first + " , " + second +  " , " + third);
						Methods.lodg(statement, direction, select, uID);
						System.out.println();
					
					}
				
					else if(token==2) {//음식, 월드컵 형태
						System.out.println();
						System.out.println("Select");
						System.out.println("-----------------");
						System.out.println("1. 향토음식");
						System.out.println("2. 일반적인");
						System.out.println("-----------------");
			            ArrayList<String> candidate_menu = new ArrayList<>();
						int token2 = scan.nextInt();
						
						if(token2==1) 
						{
							//향토음
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
				            
			
				            Collections.shuffle(candidate_menu); //Array요소들 셔플 
				            
				          
				            
						}
						else if(token2==2) 
						{
							//일반음식
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
				            Collections.shuffle(candidate_menu); //Array요소들 셔플   
						}
						
						//candidate_menu에 다 넣었고, 이제 앞에서 16개뽑아서 d드컵시작
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
						System.out.println("       16강      ");
						System.out.println("-----------------");
						for(int i=0; i<8; i++)
						{
							System.out.println();
							System.out.println("Select");
							System.out.println("1. " + round1[i]);
							System.out.println("2. " + round1[k]);
							if(scan.nextInt() == 1)
							{
								System.out.println(round1[i] + " 승리!");
								round2[i] = round1[i];
							}
							else
							{
								System.out.println(round1[k] + " 승리!");
								round2[i] = round1[k];
							}
							k--;
						}
						
						System.out.println();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("       8강      ");
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
								System.out.println(round2[i] + " 승리!");
								round3[i] = round2[i];
							}
							else
							{
								System.out.println(round2[k] + " 승리!");
								round3[i] = round2[k];
							}
							k--;
						}
						
						System.out.println();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("       4강      ");
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
								System.out.println(round3[i] + " 승리!");
								round4[i] = round3[i];
							}
							else
							{
								System.out.println(round2[k] + " 승리!");
								round4[i] = round3[k];
							}
							k--;
						}
						
						System.out.println();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("       결승      ");
						System.out.println("-----------------");
						k = 1;
						
						System.out.println();
						System.out.println("Select");
						System.out.println("1. " + round4[0]);
						System.out.println("2. " + round4[1]);
						if(scan.nextInt() == 1)
						{
							System.out.println(round4[0] + " 승리!");
							winner= round4[0];
						}
						else
						{
							System.out.println(round2[k] + " 승리!");
							winner = round4[1];
						}
						
						System.out.println();
						System.out.println("-----------------");
						System.out.println("   최종우승" + winner);
						System.out.println("-----------------");
						
						System.out.println();
						System.out.println();
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println();
						System.out.println(winner + " 판매업소");
						String tableName = "ordinaryfood";
						if(token2==1) //winner가 포함된 nativefood table정보를보여준다 
						{
							if(Direction.equals("N"))
							{
								sql = "select rname,loc,phonenum,menu from nativefood where menu LIKE ? and (loc LIKE '%애월%' or loc LIKE '%조천%');";
							}
							else if(Direction.equals("E"))
							{
								sql = "select rname,loc,phonenum,menu from nativefood where menu LIKE ? and (loc LIKE '%성산%' or loc LIKE '%구좌%');";
							}
							else if(Direction.equals("W"))
							{
								sql = "select rname,loc,phonenum,menu from nativefood where menu LIKE ? and (loc LIKE '%한경%' or loc LIKE '%한림%' or loc LIKE '%대정%');";
							}
							else
							{
								sql = "select rname,loc,phonenum,menu from nativefood where menu LIKE ? and (loc LIKE '%안덕%' or loc LIKE '%표선%' or loc LIKE '%남원%');";
							}
						}
						else
						{
							if(Direction.equals("N"))
							{
								sql = "select rname,loc,phonenum,menu from ordinaryfood where menu LIKE ? and (loc LIKE '%애월%' or loc LIKE '%조천%');";
							}
							else if(Direction.equals("E"))
							{
								sql = "select rname,loc,phonenum,menu from ordinaryfood where menu LIKE ? and (loc LIKE '%성산%' or loc LIKE '%구좌%');";
							}
							else if(Direction.equals("W"))
							{
								sql = "select rname,loc,phonenum,menu from ordinaryfood where menu LIKE ? and (loc LIKE '%한경%' or loc LIKE '%한림%' or loc LIKE '%대정%');";
							}
							else
							{
								sql = "select rname,loc,phonenum,menu from ordinaryfood where menu LIKE ? and (loc LIKE '%안덕%' or loc LIKE '%표선%' or loc LIKE '%남원%');";
							}
		
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
						System.out.println("초기화면으로돌아갑니다 ");
						System.out.println();
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
								}
							}else if(token11 == 3) {
								System.out.println("Select '기타'");
								first = first +  "  기타 ";
								select = select + Integer.toString(token11);
							}
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
						String direction = Methods.user_dir(statement, sql);
						Methods.attraction(statement, select, direction, uID);
						
						scan.nextLine();

					}
					else if(token==4) {
						System.out.println("Select");
						System.out.println("-----------------");
						System.out.println("1. Most selected in 음식");
						System.out.println("2. Most selected in 관광");
						System.out.println("2. Most selected in 숙박");
						System.out.println("-----------------");
					
						int token4 = scan.nextInt();
						if(token4==1) {
							System.out.println("ID : 131님이 설정한 남/20대/제주도 동부 에서의 가장 인기있는 음식은");
							System.out.println("카테고리 : 향토음식, 음식:접짝뼈국");
							System.out.println("식당");
							System.out.println("이름 : ~~~~ / 주소:~~~~ /  영업시간:~~~");
							System.out.println("이름 : ~~~~ / 주소:~~~~ /  영업시간:~~~");
							System.out.println("이름 : ~~~~ / 주소:~~~~ /  영업시간:~~~");
						
						}
						else if(token4==2) {
							System.out.println("ID : ~~~님이 설정한 남/20대/제주도 동부 에서의 가장 인기있는 관광지는");
							System.out.println("카테고리: ~~~-~~~-~~~ 관광지명 : ~~~~~~~~~~");
						}
						else if(token4==3) {
							System.out.println("ID : ~~~님이 설정한 남/20대/제주도 동부 에서의 가장 인기있는 숙박형태는");
							System.out.println("카테고리: ~~~-~~~-~~~ 숙박 : ~~~~~~~~~~");
						}
					
				
					
						}
					else {
						continue;
						}
					
				
				}
				else if(bit.contains("3")) 	//bit3은exit
				{
					return;
				
				}
				else if(bit.contains("4")) //bit4는 관리자모
			      {
			         System.out.println();
			         System.out.println("관리자모드 ");
			         System.out.println("-----------------");
			         System.out.println("1. 데이터관리");
			         System.out.println("2. 회원관리");
			         System.out.println("-----------------");
			         String bit2 = scan.nextLine();
			         if(bit2.contains("1"))
			         {
			            System.out.println();
			            System.out.println("데이터관리 ");
			            String bit3 = scan.nextLine();
			            if(bit3.contains("1"))
			            {
			               System.out.println();
			               System.out.println("데이터추가");
			            }
			            else if(bit3.contains("2"))
			            {
			               System.out.println();
			               System.out.println("데이터삭제");
			            }
			            
			         }
			         else if(bit2.contains("2"))//회원관리
			         {
			            System.out.println();
			            System.out.println("회원관리 ");
			            System.out.println("-----------------");
			            System.out.println("1. 전체 사용자 확인");
			            System.out.println("2. 사용자 삭제");
			            System.out.println("2. 사용자 수정");
			            System.out.println("-----------------");
			            String bit3 = scan.nextLine();
			            String uID, upwd, sex,age, dir;
			            
			            if(bit3.contains("1")) {//전체 사용자 확인
			               sql="select * from host;";
			               ps = conn.prepareStatement(sql);
			               rs = ps.executeQuery();
			         
			               System.out.println("-----------------------------------------------------");
			               System.out.println("  \tuID\tpwd\tsex\tage\tdir");
			               int index=1;
			               while (rs.next()) {
			                  uID = rs.getString(1);
			                  upwd = rs.getString(2);
			                  sex = rs.getString(3);
			                  age = rs.getString(4);
			                  dir = rs.getString(5);
			                  System.out.printf("%d:\t%-16s\t%-16s\t%-16s\t%-16s\t%-16s\n",index,uID, upwd,sex,age,dir);
			                  index++;
			               }
			               System.out.println("-----------------------------------------------------");
			               index=1;
			            }
			            else if(bit3.contains("2")) { // 사용자 삭제
			               System.out.println("-----------------------------------------------------");
			               System.out.println("삭제할 사용자 ID : ");
			               String del_uID = scan.nextLine();
			               sql = "select * from host where uID = ?;";
			               ps = conn.prepareStatement(sql);
			                 ps.clearParameters();
			                 ps.setString(1,del_uID);
			                 rs = ps.executeQuery();
			                 
			                 System.out.println("-----------------------------------------------------");
			               System.out.println("  \tuID\tpwd\tsex\tage\tdir");
			               int index=1;
			               while (rs.next()) {
			                  uID = rs.getString(1);
			                  upwd = rs.getString(2);
			                  sex = rs.getString(3);
			                  age = rs.getString(4);
			                  dir = rs.getString(5);
			                  System.out.printf("%d:\t%-16s\t%-16s\t%-16s\t%-16s\t%-16s\n",index,uID, upwd,sex,age,dir);
			                  index++;
			               }
			               System.out.println("-----------------------------------------------------");
			               index=1;
			               
			               System.out.println("다음 사용자를 삭제하시겠습니까? (y/n)");
			               String bit4 = scan.nextLine();
			               if(bit4.contains("y")){
			                  sql = "delete from host where uID = ?;";
			                  ps = conn.prepareStatement(sql);
			                    ps.clearParameters();
			                    ps.setString(1,del_uID);
			                    rs = ps.executeQuery();
			                    System.out.println("삭제가 완료되었습니다.");
			               }
			               else {
			                  System.out.println("취소");
			               }
			               
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