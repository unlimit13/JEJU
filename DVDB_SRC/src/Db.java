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
import java.util.Scanner;


public class Db {
	
	private static String url = "jdbc:postgresql://localhost:5432/postgres";
	private static String user = "postgres";
	private static String password = "inter7477";
	
	
	public static void lodg(Statement statement, String direction, String select, String uID) throws SQLException {

		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		Scanner scan = new Scanner(System.in);
		String sql = "select lname, addr, room_num, pnum from lodgment_with_class where class_l ="+ select + ";";
		ResultSet rs = statement.executeQuery(sql);
		System.out.println(direction);
		

		if (direction.contains("e")) {
			while (rs.next()) {
				if(rs.getString(2).contains("성산") || rs.getString(2).contains("구좌") || rs.getString(2).contains("제주시")) {
					String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2) + " / 방 개수: " + rs.getString(3)+ " / 전화번호: " + rs.getString(4);
					System.out.println(colValue);
					ArrayList<String> data = new ArrayList<>();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
					datas.add(data);

				}
		    }
			System.out.println("숫자를 고르시오.");
			int num = scan.nextInt();
			sql = "create table if not exists best_lodgment(uid varchar(20), lname varchar(50), class_l varchar(20));";
			statement.executeUpdate(sql);
			sql = "insert into best_lodgment values('"+ uID + "', '" + datas.get(num-1).get(0) + "' , '" + select + "');";
			statement.executeUpdate(sql);
			
		}else if(direction.contains("w")) {
			while (rs.next()) {
				if(rs.getString(2).contains("한경") || rs.getString(2).contains("한림") || rs.getString(2).contains("대정")) {
					String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2) + " / 방 개수: " + rs.getString(3)+ " / 전화번호: " + rs.getString(4);
			        System.out.println(colValue);
			        ArrayList<String> data = new ArrayList<>();
			        data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
					datas.add(data);
				}
		    }
			System.out.println("숫자를 고르시오.");
			int num = scan.nextInt();
			sql = "create table if not exists best_lodgment(uid varchar(20), lname varchar(50), class_l varchar(20));";
			statement.executeUpdate(sql);
			sql = "insert into best_lodgment values('"+ uID + "', '" + datas.get(num-1).get(0) + "' , '" + select + "');";
			statement.executeUpdate(sql);
			
		}else if(direction.contains("s")) {
			while (rs.next()) {
				if(rs.getString(2).contains("안덕") || rs.getString(2).contains("표선") || rs.getString(2).contains("남원") || rs.getString(2).contains("서귀포")) {
					System.out.println(rs.getString(2));
					String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2) + " / 방 개수: " + rs.getString(3)+ " / 전화번호: " + rs.getString(4);
			        System.out.println(colValue);
			        ArrayList<String> data = new ArrayList<>();
			        data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
				}
		    }
			System.out.println("숫자를 고르시오.");
			int num = scan.nextInt();
			sql = "create table if not exists best_lodgment(uid varchar(20), lname varchar(50), class_l varchar(20));";
			statement.executeUpdate(sql);
			sql = "insert into best_lodgment values('"+ uID + "', '" + datas.get(num-1).get(0) + "' , '" + select + "');";
			statement.executeUpdate(sql);
			
		}else if(direction.contains("n")){
			while (rs.next()) {
				if(rs.getString(2).contains("애월") || rs.getString(2).contains("조천") || rs.getString(2).contains("제주시")) {
					String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2) + " / 방 개수: " + rs.getString(3)+ " / 전화번호: " + rs.getString(4);
			        System.out.println(colValue);
			        ArrayList<String> data = new ArrayList<>();
			        data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
				}
		    }
			System.out.println("숫자를 고르시오.");
			int num = scan.nextInt();
			sql = "create table if not exists best_lodgment(uid varchar(20), lname varchar(50), class_l varchar(20));";
			statement.executeUpdate(sql);
			sql = "insert into best_lodgment values('"+ uID + "', '" + datas.get(num-1).get(0) + "' , '" + select + "');";
			statement.executeUpdate(sql);
		}
	}
	
	
	
	
	
	public static String user_dir(Statement statement, String sql) throws SQLException {
		ResultSet rs = statement.executeQuery(sql);
		String direction = "";
	    if(rs.next()) {
	    	direction = rs.getString(1);
	    }
	    return direction;
	}
	

	
	public static void attraction(Statement statement, String select) throws SQLException {
		if (select.contains("11")) {
			String sql = "select aname, loc, pnum from att_1_1;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 위치: " + rs.getString(2) + " / 전화번호: " + rs.getString(3);
			    System.out.println(colValue);
		    }
		}else if(select.contains("121")) {
			String sql = "select aname, addr from att_1_2_h;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 위치: " + rs.getString(2);
			    System.out.println(colValue);
		    }
		}else if(select.contains("122")) {
			String sql = "select aname, addr from att_1_2_m;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 위치: " + rs.getString(2);
			    System.out.println(colValue);
		    }
			
		}else if(select.contains("211")) {
			String sql = "select aname, addr, pnum from att_2_1_b;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 위치: " + rs.getString(2) + " / 전화번호: " + rs.getString(3);
			    System.out.println(colValue);
		    }
		}else if(select.contains("212")) {
			String sql = "select aname, harbor from att_2_1_s;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 항구: " + rs.getString(2) + " / 전화번호: " + rs.getString(3);
			    System.out.println(colValue);
		    }
		}else if(select.contains("221")) {
			String sql = "select aname, addr from att_2_2_b;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2);
			    System.out.println(colValue);
		    }
		}else if(select.contains("222")) {
			String sql = "select aname, addr from att_2_2_p;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2);
			    System.out.println(colValue);
		    }
		}else if(select.contains("223")) {
			String sql = "select aname, dist from att_2_2_f;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 거리: " + rs.getString(2);
			    System.out.println(colValue);
			}
		}else if(select.contains("23")) {
			String sql = "select aname, addr, pnum from att_2_3;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2) + " / 전화번호: " + rs.getString(3);
			    System.out.println(colValue);
			}
		}else if(select.contains("3")) {
			String sql = "select aname, time from att_3;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 걸리는 시간: " + rs.getString(2);
			    System.out.println(colValue);
			}
		}else if(select.contains("4")) {
			String sql = "select aname, addr, pnum from att_4 where class_3 == '커피숍';";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2) + " / 전화번호: " + rs.getString(3);
			    System.out.println(colValue);
			}
		}
		
		
    }

	public static void main(String args[]) throws SQLException, IOException {
		Connection conn = null;
		
		Scanner scan = new Scanner(System.in);
		int result;
		String sql;
		PreparedStatement ps;
	    ResultSet rs;
		
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(Db.url, Db.user, Db.password );
			sql = "create table if not exists host(uID varchar(20), pwd varchar(20), sex char(2), age int, direction char(2));";
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			
			while(true) 
			{	
				System.out.println("-----------------");
				System.out.println("1. sign up");
				System.out.println("2. sign in");
				System.out.println("3. Exit");
				System.out.println("-----------------");
				
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
					String Direction = scan.nextLine();
					
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
					
						Statement statement = conn.createStatement();
						sql = "select direction from host where uid = "+ uID +"::varchar"+";";
						String direction = user_dir(statement, sql);
						
						
						System.out.println(select);
						System.out.println();
						System.out.println("---------------결과----------------");
						System.out.println("선택된 " + first + " , " + second +  " , " + third);
						lodg(statement, direction, select, uID);
						System.out.println();
					
					}
				
					else if(token==2) {//음식, 월드컵 형태
						System.out.println("Select");
						System.out.println("-----------------");
						System.out.println("1. 향토음식");
						System.out.println("2. 일반적인");
						System.out.println("-----------------");
					
						int token2 = scan.nextInt();
						if(token2==1) {
						//월드컵 시작
							System.out.println("------당신이 먹고싶은 음식은?-------");
							System.out.println("--------16강---------");
							System.out.println("1. 접짝뼈국");
							System.out.println("2. 고기국수");
							System.out.println("-----------------");
							int token21 = scan.nextInt();
							System.out.println(".\n.\n.\n.\n.\n.\n.\n.\n");
							System.out.println("------당신이 먹고싶은 음식은?-------");
							System.out.println("--------결승---------");
							System.out.println("1. 접짝뼈국");
							System.out.println("2. 흑돼지");
							System.out.println("-----------------");
							int token22 = scan.nextInt();
							System.out.println("--------결과---------");
							System.out.println("선택된 '접짝뼈국'메뉴를 판매하는 여행지내 식당은");
							System.out.println("이름 : ~~~~ / 주소:~~~~ /  영업시간:~~~");
							System.out.println("이름 : ~~~~ / 주소:~~~~ /  영업시간:~~~");
							System.out.println("이름 : ~~~~ / 주소:~~~~ /  영업시간:~~~");
						}
						else if(token2==2) {
						//월드컵시작
						}
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
						}
						else if(token1==3) {
							System.out.println("Select '올레길'");
							first ="  올레길 ";
							select = select + Integer.toString(token1);
						}
						else if(token1==4) {
							System.out.println("Select '카페투어'");
							first ="  카페투어 ";
							select = select + Integer.toString(token1);
						}
						
						System.out.println();
						
						Statement statement = conn.createStatement();

						System.out.println(select);
						System.out.println();
						System.out.println("---------------결과----------------");
						System.out.println(first);
						attraction(statement, select);
					
				
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
					else if(bit2.contains("2"))
					{
						System.out.println();
						System.out.println("회원관리 ");
					}
				}
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
