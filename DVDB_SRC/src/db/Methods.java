package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods {

	public static void view_lodg(Statement statement) throws SQLException {
		String sql = "create or replace view lodg as select lname, addr, room_num, pnum, class_l from total_lodgment;";
		statement.executeUpdate(sql);
	}
	
   public static void lodg(Statement statement, String select) throws SQLException {

      Scanner scan = new Scanner(System.in);
      String sql = "select lname, addr, room_num, pnum from lodg where class_l ='"+ select + "';";
      ResultSet rs = statement.executeQuery(sql);
      
      
      while(rs.next()) {
         String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2) + " / �� ����: " + rs.getString(3)+ " / ��ȭ��ȣ: " + rs.getString(4);
         System.out.println(colValue);
      }

   }
   
   public static void total_lodgment(Statement statement) throws SQLException {
		String sql = "create table if not exists total_lodgment(lname varchar(50), addr varchar(100), room_num int, pnum varchar(20), class_l varchar(10));";
		statement.executeUpdate(sql);
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		
		sql = "select aname, loc from att_1_1;";
		ResultSet rs = statement.executeQuery(sql);
		
		
		datas = new ArrayList<ArrayList<String>>();

		String[] lodg = { "1", "2" };

		
		for(int i=0; i < lodg.length; i++) {
			datas = new ArrayList<ArrayList<String>>();
			sql = "select lname, addr, room_num, pnum, class_l from lodgment"+ lodg[i] +";";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				ArrayList<String> data = new ArrayList<>();
		        data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				data.add(rs.getString(5));
				datas.add(data);
		    }
			for(int j = 0; j < datas.size(); j++) {
				sql = "insert into total_lodgment values('" + datas.get(j).get(0) +"','" + datas.get(j).get(1) + "','"+datas.get(j).get(2)+"' ,'"+datas.get(j).get(3)+"','" + datas.get(j).get(4) +"');";
				statement.execute(sql);
			}
		}
	}
   
   
   public static String getSentence2(String sentence, String class_l) {
      if (class_l.equals("111")) {
         sentence = "���ֽ�����, ��ä, ����";
      }
      else if(class_l.equals("112")) {
         sentence = "���ֽ�����, ��ä, ������";
      }
      else if(class_l.equals("121")) {
         sentence = "���ֽ�����, �ټ���, ����";
      }
      else if(class_l.equals("122")) {
         sentence = "���ֽ�����, �ټ���, ������";
      }
      else if(class_l.equals("211")) {
         sentence = "���Ÿ���, ��ä, ����";
      }
      else if(class_l.equals("212")) {
         sentence = "���Ÿ���, ��ä, ������";
      }
      else if(class_l.equals("211")) {
         sentence = "���Ÿ���, �ټ���, ����";
      }
      else if(class_l.equals("222")) {
         sentence = "���Ÿ���, �ټ���, ������";
      }
      return sentence;
   }
   public static String getSentence1(String sentence, String class_l) {
      if (class_l.equals("11")) {
         sentence = "Ȱ������, �ٴ�";
      }
      else if(class_l.equals("121")) {
         sentence = "Ȱ������, ��, �¸�";
      }
      else if(class_l.equals("122")) {
         sentence = "Ȱ������, ��, ����";
      }
      else if(class_l.equals("211")) {
         sentence = "������, �ٴ�, ����������";
      }
      else if(class_l.equals("212")) {
         sentence = "������, �ٴ�, ����";
      }
      else if(class_l.equals("221")) {
         sentence = "������, ��, �Ĺ���";
      }
      else if(class_l.equals("222")) {
         sentence = "������, ��, �������";
      }
      else if(class_l.equals("223")) {
         sentence = "������, ��, ����";
      }
      else if(class_l.equals("23")) {
         sentence = "������, ��Ÿ";
      }
      else if(class_l.equals("3")) {
         sentence = "�÷���";
      }
      else if(class_l.equals("4")) {
         sentence = "ī������";
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
		sql = "select aname, harbor from att_2_1_s;";
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			ArrayList<String> data = new ArrayList<>();
	        data.add(rs.getString(1));
			data.add(rs.getString(2));
			datas.add(data);
	    }
		for(int i = 0; i < datas.size(); i++) {
			sql = "insert into total_attraction values('" + datas.get(i).get(0) +"','" + datas.get(i).get(1) + "', 212);";
			statement.execute(sql);
		}
		
		datas = new ArrayList<ArrayList<String>>();
		sql = "select aname, management from att_2_2_f;";
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			ArrayList<String> data = new ArrayList<>();
	        data.add(rs.getString(1));
	        
	        String a = rs.getString(2);
			String[] b = a.split("\\.");
			data.add(b[0]);
			datas.add(data);
	    }
		for(int i = 0; i < datas.size(); i++) {
			sql = "insert into total_attraction values('" + datas.get(i).get(0) +"','" + datas.get(i).get(1) + "', 223);";
			statement.execute(sql);
		}
		
		datas = new ArrayList<ArrayList<String>>();
		sql = "select aname from att_3;";
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			ArrayList<String> data = new ArrayList<>();
	        data.add(rs.getString(1));
	        
	        String a = rs.getString(1);
			String[] b = a.split("-");
			data.add(b[0]);
			datas.add(data);
	    }
		for(int i = 0; i < datas.size(); i++) {
			sql = "insert into total_attraction values('" + datas.get(i).get(0) +"','" + datas.get(i).get(1) + "', 3);";
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
		
		datas = new ArrayList<ArrayList<String>>();
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
   
   public static void view_att(Statement statement) throws SQLException{
		String sql = "create or replace view att as select aname, addr, class_a from total_attraction;";
		statement.executeUpdate(sql);
}
   
   public static void attraction(Statement statement, String select) throws SQLException {
		

		String sql = "select aname, addr from att where class_a = '"+ select +"';";
		ResultSet rs = statement.executeQuery(sql);
		
		int count = 1;
		
		while (rs.next()) {
			String colValue = "�̸�: " + rs.getString(1) + " / �ּ�: " + rs.getString(2);
			System.out.println(colValue);
			if(count == 30)
			{
				return;
			}
			count++;
		}
   }
} 