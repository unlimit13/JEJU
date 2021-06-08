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
				if(rs.getString(2).contains("����") || rs.getString(2).contains("����") || rs.getString(2).contains("���ֽ�")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
					System.out.println(colValue);
					ArrayList<String> data = new ArrayList<>();
					data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
					datas.add(data);

				}
		    }
			System.out.println("���ڸ� ���ÿ�.");
			int num = scan.nextInt();
			sql = "create table if not exists best_lodgment(uid varchar(20), lname varchar(50), class_l varchar(20));";
			statement.executeUpdate(sql);
			sql = "insert into best_lodgment values('"+ uID + "', '" + datas.get(num-1).get(0) + "' , '" + select + "');";
			statement.executeUpdate(sql);
			
		}else if(direction.contains("w")) {
			while (rs.next()) {
				if(rs.getString(2).contains("�Ѱ�") || rs.getString(2).contains("�Ѹ�") || rs.getString(2).contains("����")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
			        System.out.println(colValue);
			        ArrayList<String> data = new ArrayList<>();
			        data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
					datas.add(data);
				}
		    }
			System.out.println("���ڸ� ���ÿ�.");
			int num = scan.nextInt();
			sql = "create table if not exists best_lodgment(uid varchar(20), lname varchar(50), class_l varchar(20));";
			statement.executeUpdate(sql);
			sql = "insert into best_lodgment values('"+ uID + "', '" + datas.get(num-1).get(0) + "' , '" + select + "');";
			statement.executeUpdate(sql);
			
		}else if(direction.contains("s")) {
			while (rs.next()) {
				if(rs.getString(2).contains("�ȴ�") || rs.getString(2).contains("ǥ��") || rs.getString(2).contains("����") || rs.getString(2).contains("������")) {
					System.out.println(rs.getString(2));
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
			        System.out.println(colValue);
			        ArrayList<String> data = new ArrayList<>();
			        data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
				}
		    }
			System.out.println("���ڸ� ���ÿ�.");
			int num = scan.nextInt();
			sql = "create table if not exists best_lodgment(uid varchar(20), lname varchar(50), class_l varchar(20));";
			statement.executeUpdate(sql);
			sql = "insert into best_lodgment values('"+ uID + "', '" + datas.get(num-1).get(0) + "' , '" + select + "');";
			statement.executeUpdate(sql);
			
		}else if(direction.contains("n")){
			while (rs.next()) {
				if(rs.getString(2).contains("�ֿ�") || rs.getString(2).contains("��õ") || rs.getString(2).contains("���ֽ�")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
			        System.out.println(colValue);
			        ArrayList<String> data = new ArrayList<>();
			        data.add(rs.getString(1));
					data.add(rs.getString(2));
					data.add(rs.getString(3));
					data.add(rs.getString(4));
				}
		    }
			System.out.println("���ڸ� ���ÿ�.");
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
				String colValue = "�̸�: " + rs.getString(1) + " / ��ġ: " + rs.getString(2) + " / ��ȭ��ȣ: " + rs.getString(3);
			    System.out.println(colValue);
		    }
		}else if(select.contains("121")) {
			String sql = "select aname, addr from att_1_2_h;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / ��ġ: " + rs.getString(2);
			    System.out.println(colValue);
		    }
		}else if(select.contains("122")) {
			String sql = "select aname, addr from att_1_2_m;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / ��ġ: " + rs.getString(2);
			    System.out.println(colValue);
		    }
			
		}else if(select.contains("211")) {
			String sql = "select aname, addr, pnum from att_2_1_b;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / ��ġ: " + rs.getString(2) + " / ��ȭ��ȣ: " + rs.getString(3);
			    System.out.println(colValue);
		    }
		}else if(select.contains("212")) {
			String sql = "select aname, harbor from att_2_1_s;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / �ױ�: " + rs.getString(2) + " / ��ȭ��ȣ: " + rs.getString(3);
			    System.out.println(colValue);
		    }
		}else if(select.contains("221")) {
			String sql = "select aname, addr from att_2_2_b;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2);
			    System.out.println(colValue);
		    }
		}else if(select.contains("222")) {
			String sql = "select aname, addr from att_2_2_p;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2);
			    System.out.println(colValue);
		    }
		}else if(select.contains("223")) {
			String sql = "select aname, dist from att_2_2_f;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / �Ÿ�: " + rs.getString(2);
			    System.out.println(colValue);
			}
		}else if(select.contains("23")) {
			String sql = "select aname, addr, pnum from att_2_3;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / ��ȭ��ȣ: " + rs.getString(3);
			    System.out.println(colValue);
			}
		}else if(select.contains("3")) {
			String sql = "select aname, time from att_3;";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / �ɸ��� �ð�: " + rs.getString(2);
			    System.out.println(colValue);
			}
		}else if(select.contains("4")) {
			String sql = "select aname, addr, pnum from att_4 where class_3 == 'Ŀ�Ǽ�';";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / ��ȭ��ȣ: " + rs.getString(3);
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
				{ 	//bit1�� sign in
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
				
					if(!PWD.equals(PWD_in_table)) //��й�ȣ �Է��� Ʋ����,
					{
						System.out.println("Password is incorrect\n");
		
						continue;
					}
				
					System.out.println();
					System.out.println("Welcome!\n");
					System.out.println("-----------------");
					System.out.println("1. ����");
					System.out.println("2. �Ĵ�");
					System.out.println("3. ����");
					System.out.println("4. ����Ȯ��");
					System.out.println("5. exit");
					System.out.println("-----------------");
				
					int token = scan.nextInt();
					
					if(token==1) {//����, ��������
						String first = "";
						String second = "";
						String third = "";
						String class_l = "";
						System.out.println();
						System.out.println("Select 1 or 2");
						System.out.println("-----------------");
						System.out.println("1. ���ֽ�����");
						System.out.println("2. ���Ÿ���");
						System.out.println("-----------------");
					
						int token1 = scan.nextInt();
						System.out.println();
						if(token1==1) {
							System.out.println("Selected '���ֽ�����'");
							first ="���ֽ�����";
						}
						else if(token1==2) {
							System.out.println("Select '���Ÿ���'");
							first ="���Ÿ���";
						}
					
						System.out.println();
						System.out.println("-----------------");
						System.out.println("1. ��ä ");
						System.out.println("2. �ټ��� ");
						System.out.println("-----------------");
						int token11 = scan.nextInt();
						System.out.println();
						if(token11 == 1) {
							System.out.println("Select '��ä' ");
							second = "��ä ";
						}
						if(token11 == 2) {
							System.out.println("Select '�ټ���'");
							second = "�ټ��� ";
						}
					
						scan.nextLine();
						System.out.println();
						System.out.println("-----------------");
						System.out.println("1. ����");
						System.out.println("2. ������");
						System.out.println("-----------------");
					
					
						String temp = scan.nextLine();
					
						int token111;
						if(temp.contains("1"))
							token111 = 1;
						else
							token111 = 2;
						System.out.println();
						if(token111 == 1) {
							System.out.println("Select '����' ");
							third = "���� ";
						}
						if(token111 == 2) {
							System.out.println("Select '������'");
							third = "������ ";
						}
					
						String select = String.valueOf(token1).concat(String.valueOf(token11));
						select = select.concat(String.valueOf(token111));
					//select�� 111~222
					
						Statement statement = conn.createStatement();
						sql = "select direction from host where uid = "+ uID +"::varchar"+";";
						String direction = user_dir(statement, sql);
						
						
						System.out.println(select);
						System.out.println();
						System.out.println("---------------���----------------");
						System.out.println("���õ� " + first + " , " + second +  " , " + third);
						lodg(statement, direction, select, uID);
						System.out.println();
					
					}
				
					else if(token==2) {//����, ������ ����
						System.out.println("Select");
						System.out.println("-----------------");
						System.out.println("1. ��������");
						System.out.println("2. �Ϲ�����");
						System.out.println("-----------------");
					
						int token2 = scan.nextInt();
						if(token2==1) {
						//������ ����
							System.out.println("------����� �԰���� ������?-------");
							System.out.println("--------16��---------");
							System.out.println("1. ��¦����");
							System.out.println("2. ��ⱹ��");
							System.out.println("-----------------");
							int token21 = scan.nextInt();
							System.out.println(".\n.\n.\n.\n.\n.\n.\n.\n");
							System.out.println("------����� �԰���� ������?-------");
							System.out.println("--------���---------");
							System.out.println("1. ��¦����");
							System.out.println("2. �����");
							System.out.println("-----------------");
							int token22 = scan.nextInt();
							System.out.println("--------���---------");
							System.out.println("���õ� '��¦����'�޴��� �Ǹ��ϴ� �������� �Ĵ���");
							System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
							System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
							System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
						}
						else if(token2==2) {
						//�����Ž���
						}
					}
				
					else if(token==3) {//������ ��
						String first = "";
						String select = "";
						System.out.println();
						System.out.println("Select 1 or 2");
						System.out.println("-----------------");
						System.out.println("1. Ȱ������ ");
						System.out.println("2. ������ ");
						System.out.println("3. �÷��� ");
						System.out.println("4. ī������ ");
						System.out.println("-----------------");
					
						int token1 = scan.nextInt();
						System.out.println();
						if(token1==1) {
							System.out.println("Selected 'Ȱ������'");
							first ="  Ȱ������ ";
							select= select + Integer.toString(token1);
							
							System.out.println();
							System.out.println("-----------------");
							System.out.println("1. �ٴ� ");
							System.out.println("2. �� ");
							System.out.println("-----------------");
							int token11 = scan.nextInt();
							
							if(token11 == 1) {
								System.out.println("Select '�ٴ�'");
								first = first + "  �ٴ� ";
								select = select + Integer.toString(token11);
								
							}else if(token11 == 2) {
								System.out.println("Select '��'");
								first = first + "  �� ";
								select = select + Integer.toString(token11);
								
								System.out.println();
								System.out.println("-----------------");
								System.out.println("1. �¸� ");
								System.out.println("2. ���� ");
								System.out.println("-----------------");
								int token111 = scan.nextInt();
								
								if(token111 == 1) {
									System.out.println("Selected '�¸�'");
									first = first + "  �¸� ";
									select = select + Integer.toString(token111);
								}else if(token111 == 2) {
									System.out.println("Selected '����'");
									first = first + "  ���� ";
									select = select + Integer.toString(token111);
								}
							}
						}
						else if(token1==2) {
							System.out.println("Select '������'");
							first ="  ������ ";
							select = select + Integer.toString(token1);
							
							System.out.println();
							System.out.println("-----------------");
							System.out.println("1. �ٴ� ");
							System.out.println("2. �� ");
							System.out.println("3. ��Ÿ ");
							System.out.println("-----------------");
							int token11 = scan.nextInt();
							
							if(token11 == 1) {
								System.out.println("Select '�ٴ�'");
								first = first + "  �ٴ� ";
								select = select + Integer.toString(token11);
								
								System.out.println();
								System.out.println("-----------------");
								System.out.println("1. ���������� ");
								System.out.println("2. ���� ");
								System.out.println("-----------------");
								int token111 = scan.nextInt();
								
								if(token111 == 1) {
									System.out.println("Select '����������'");
									first = first + "  ���������� ";
									select = select + Integer.toString(token111);
								}else if(token111 == 2) {
									System.out.println("Select '����'");
									first = first + "  ���� ";
									select = select + Integer.toString(token111);
								}
							}else if(token11 == 2) {
								System.out.println("Select '��'");
								first = first +  "  �� ";
								select = select + Integer.toString(token11);
								
								System.out.println();
								System.out.println("-----------------");
								System.out.println("1. �Ĺ��� ");
								System.out.println("2. ������� ");
								System.out.println("3. ���� ");
								System.out.println("-----------------");
								int token111 = scan.nextInt();
								
								if(token111 == 1) {
									System.out.println("Select '�Ĺ���'");
									first = first + "  �Ĺ��� ";
									select = select + Integer.toString(token111);
								}else if(token111 == 2) {
									System.out.println("Select '�������'");
									first = first + "  ������� ";
									select = select + Integer.toString(token111);
								}else if(token111 == 3) {
									System.out.println("Select '����'");
									first = first + "  ���� ";
									select = select + Integer.toString(token111);
								}
							}else if(token11 == 3) {
								System.out.println("Select '��Ÿ'");
								first = first +  "  ��Ÿ ";
								select = select + Integer.toString(token11);
							}
						}
						else if(token1==3) {
							System.out.println("Select '�÷���'");
							first ="  �÷��� ";
							select = select + Integer.toString(token1);
						}
						else if(token1==4) {
							System.out.println("Select 'ī������'");
							first ="  ī������ ";
							select = select + Integer.toString(token1);
						}
						
						System.out.println();
						
						Statement statement = conn.createStatement();

						System.out.println(select);
						System.out.println();
						System.out.println("---------------���----------------");
						System.out.println(first);
						attraction(statement, select);
					
				
					}
					else if(token==4) {
						System.out.println("Select");
						System.out.println("-----------------");
						System.out.println("1. Most selected in ����");
						System.out.println("2. Most selected in ����");
						System.out.println("2. Most selected in ����");
						System.out.println("-----------------");
					
						int token4 = scan.nextInt();
						if(token4==1) {
							System.out.println("ID : 131���� ������ ��/20��/���ֵ� ���� ������ ���� �α��ִ� ������");
							System.out.println("ī�װ� : ��������, ����:��¦����");
							System.out.println("�Ĵ�");
							System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
							System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
							System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
						
						}
						else if(token4==2) {
							System.out.println("ID : ~~~���� ������ ��/20��/���ֵ� ���� ������ ���� �α��ִ� ��������");
							System.out.println("ī�װ�: ~~~-~~~-~~~ �������� : ~~~~~~~~~~");
						}
						else if(token4==3) {
							System.out.println("ID : ~~~���� ������ ��/20��/���ֵ� ���� ������ ���� �α��ִ� �������´�");
							System.out.println("ī�װ�: ~~~-~~~-~~~ ���� : ~~~~~~~~~~");
						}
					
				
					
						}
					else {
						continue;
						}
					
				
				}
				else if(bit.contains("3")) 	//bit3��exit
				{
					return;
				
				}
				else if(bit.contains("4")) //bit4�� �����ڸ�
				{
					System.out.println();
					System.out.println("�����ڸ�� ");
					System.out.println("-----------------");
					System.out.println("1. �����Ͱ���");
					System.out.println("2. ȸ������");
					System.out.println("-----------------");
					String bit2 = scan.nextLine();
					if(bit2.contains("1"))
					{
						System.out.println();
						System.out.println("�����Ͱ��� ");
						String bit3 = scan.nextLine();
						if(bit3.contains("1"))
						{
							System.out.println();
							System.out.println("�������߰�");
						}
						else if(bit3.contains("2"))
						{
							System.out.println();
							System.out.println("�����ͻ���");
						}
					
					}
					else if(bit2.contains("2"))
					{
						System.out.println();
						System.out.println("ȸ������ ");
					}
				}
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
