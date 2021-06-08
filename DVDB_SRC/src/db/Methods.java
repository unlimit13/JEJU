package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods {

	public static void lodg(Statement statement, String direction, String select, String uID) throws SQLException {

		Scanner scan = new Scanner(System.in);
		String sql = "select lname, addr, room_num, pnum from lodgment_with_class where class_l ='"+ select + "';";
		ResultSet rs = statement.executeQuery(sql);
		System.out.println(direction);

		if (direction.contains("e")) {
			while (rs.next()) {
				if(rs.getString(2).contains("����") || rs.getString(2).contains("����") || rs.getString(2).contains("���ֽ�")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
					System.out.println(colValue);

				}
		    }
			
		}else if(direction.contains("w")) {
			while (rs.next()) {
				if(rs.getString(2).contains("�Ѱ�") || rs.getString(2).contains("�Ѹ�") || rs.getString(2).contains("����")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
			        System.out.println(colValue);
				}
		    }

			
		}else if(direction.contains("s")) {
			while (rs.next()) {
				if(rs.getString(2).contains("�ȴ�") || rs.getString(2).contains("ǥ��") || rs.getString(2).contains("����") || rs.getString(2).contains("������")) {
					System.out.println(rs.getString(2));
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
			        System.out.println(colValue);
				}
		    }
			
		}else if(direction.contains("n")){
			while (rs.next()) {
				if(rs.getString(2).contains("�ֿ�") || rs.getString(2).contains("��õ") || rs.getString(2).contains("���ֽ�")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
			        System.out.println(colValue);
				}
		    }
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
	
	
	public static void total_attraction(Statement statement) throws SQLException {
		String sql = "create table if not exists total_attraction(aname varchar(50), addr varchar(100), class_a varchar(10));";
		statement.executeUpdate(sql);
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		
		sql = "select aname, loc from att_1_1;";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			ArrayList<String> data = new ArrayList<>();
	        data.add(rs.getString(1));
			data.add(rs.getString(2));
			datas.add(data);
	    }
		for(int i = 0; i < datas.size(); i++) {
			sql = "insert into total_attraction values('" + datas.get(i).get(0) +"','" + datas.get(i).get(1) + "', 11);";
			statement.execute(sql);
		}
		
		datas = new ArrayList<ArrayList<String>>();
		
		sql = "select aname, addr from att_4 where class_3 = 'Ŀ�Ǽ�';";
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			ArrayList<String> data = new ArrayList<>();
	        data.add(rs.getString(1));
			data.add(rs.getString(2));
			datas.add(data);
	    }
		for(int i = 0; i < datas.size(); i++) {
			sql = "insert into total_attraction values('" + datas.get(i).get(0) +"','" + datas.get(i).get(1) + "', 4);";
			statement.execute(sql);
		}
		
		String[] att = { "att_1_2_h", "att_1_2_m", "att_2_1_b", "att_2_2_b", "att_2_2_p", "att_2_3" };
		String[] att_num = { "121", "122", "211", "221", "222", "23" };
		sql = "create table if not exists total_attraction(aname varchar(50), addr varchar(100), class_a varchar(10));";
		statement.executeUpdate(sql);
		
		for(int i=0; i < att.length; i++) {
			datas = new ArrayList<ArrayList<String>>();
			sql = "select aname, addr from "+ att[i] +";";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				ArrayList<String> data = new ArrayList<>();
		        data.add(rs.getString(1));
				data.add(rs.getString(2));
				datas.add(data);
		    }
			for(int j = 0; j < datas.size(); j++) {
				sql = "insert into total_attraction values('" + datas.get(j).get(0) +"','" + datas.get(j).get(1) + "','"+att_num[i]+"');";
				statement.execute(sql);
			}
			
		}
	}
	
public static void attraction(Statement statement, String select, String direction, String uID) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		String sql = "select aname, addr from total_attraction where class_a = '"+ select +"';";
		ResultSet rs = statement.executeQuery(sql);
		System.out.println(direction);

		if (direction.contains("e")) {
			while (rs.next()) {
				if(rs.getString(2).contains("����") || rs.getString(2).contains("����") || rs.getString(2).contains("���ֽ�")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2);
					System.out.println(colValue);
				}
		    }	
		}else if(direction.contains("w")) {
			while (rs.next()) {
				if(rs.getString(2).contains("�Ѱ�") || rs.getString(2).contains("�Ѹ�") || rs.getString(2).contains("����")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2);
			        System.out.println(colValue);
				}
		    }
		}else if(direction.contains("s")) {
			while (rs.next()) {
				if(rs.getString(2).contains("�ȴ�") || rs.getString(2).contains("ǥ��") || rs.getString(2).contains("����") || rs.getString(2).contains("������")) {
					System.out.println(rs.getString(2));
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2);
			        System.out.println(colValue);
				}
		    }
		}else if(direction.contains("n")){
			ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
			while (rs.next()) {
				if(rs.getString(2).contains("�ֿ�") || rs.getString(2).contains("��õ") || rs.getString(2).contains("���ֽ�")) {
					String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2);
			        System.out.println(colValue);
				}
		    }
		}
    }
}