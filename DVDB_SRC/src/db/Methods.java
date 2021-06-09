package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods {

	

	public static void lodg(Statement statement, String select) throws SQLException {

		Scanner scan = new Scanner(System.in);
		String sql = "select lname, addr, room_num, pnum from lodgment_with_class where class_l ='"+ select + "';";
		ResultSet rs = statement.executeQuery(sql);
		
		
		while(rs.next()) {
			String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2) + " / 방 개수: " + rs.getString(3)+ " / 전화번호: " + rs.getString(4);
			System.out.println(colValue);
		}

		
	}
	
	
	public static String getSentence2(String sentence, String class_l) {
		if (class_l.equals("111")) {
			sentence = "제주스러운, 독채, 도심";
		}
		else if(class_l.equals("112")) {
			sentence = "제주스러운, 독채, 한적한";
		}
		else if(class_l.equals("121")) {
			sentence = "제주스러운, 다세대, 도심";
		}
		else if(class_l.equals("122")) {
			sentence = "제주스러운, 다세대, 한적한";
		}
		else if(class_l.equals("211")) {
			sentence = "럭셔리한, 독채, 도심";
		}
		else if(class_l.equals("212")) {
			sentence = "럭셔리한, 독채, 한적한";
		}
		else if(class_l.equals("211")) {
			sentence = "럭셔리한, 다세대, 도심";
		}
		else if(class_l.equals("222")) {
			sentence = "럭셔리한, 다세대, 한적한";
		}
		return sentence;
	}
	public static String getSentence1(String sentence, String class_l) {
		if (class_l.equals("11")) {
			sentence = "활동적인, 바다";
		}
		else if(class_l.equals("121")) {
			sentence = "활동적인, 산, 승마";
		}
		else if(class_l.equals("122")) {
			sentence = "활동적인, 산, 오름";
		}
		else if(class_l.equals("21")) {
			sentence = "잔잔한, 바다";
		}
		else if(class_l.equals("221")) {
			sentence = "잔잔한, 산, 식물원";
		}
		else if(class_l.equals("222")) {
			sentence = "잔잔한, 산, 관광농원";
		}
		else if(class_l.equals("211")) {
			sentence = "잔잔한, 산, 숲길";
		}
		else if(class_l.equals("23")) {
			sentence = "잔잔한, 기타";
		}
		else if(class_l.equals("3")) {
			sentence = "올레길";
		}
		else if(class_l.equals("4")) {
			sentence = "카페투어";
		}
		return sentence;
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
		
		sql = "select aname, addr from att_4 where class_3 = '커피숍';";
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
	
public static void attraction(Statement statement, String select) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		String sql = "select aname, addr from total_attraction where class_a = '"+ select +"';";
		ResultSet rs = statement.executeQuery(sql);
		

		while (rs.next()) {
			String colValue = "이름: " + rs.getString(1) + " / 주소: " + rs.getString(2);
			System.out.println(colValue);
		}
		
    }
}
