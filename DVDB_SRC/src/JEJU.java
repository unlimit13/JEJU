import java.util.*;
import java.sql.*;

public class JEJUDEMO {

	public static void main(String[] args) throws InterruptedException, SQLException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int result;
		String sql;
	    PreparedStatement ps;
	    ResultSet rs;
		String ID = "postgres", pwd = "bj@980613";
        String url = "jdbc:postgresql://localhost:5432/";
        Connection conn = DriverManager.getConnection(url, ID, pwd); //연결
        sql = "create table host(uID varchar(20), pwd varchar(20), sex char(2), age int, direction char(2));";
               
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
				
				
				System.out.println(select);
				System.out.println();
				System.out.println("---------------결과----------------");
				System.out.println("선택된 " + first + " , " + second +  " , " + third);
				System.out.println("이름 : ~~~~ / 주소:~~~~ /  체크인시간:~~~ / 전화번호:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ /  체크인시간:~~~ / 전화번호:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ /  체크인시간:~~~ / 전화번호:~~~");
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
				String second = "";
				String third = "";
				System.out.println();
				System.out.println("Select 1 or 2");
				System.out.println("-----------------");
				System.out.println("1. 활동적인 ");
				System.out.println("2. 잔잔한 ");
				System.out.println("-----------------");
				
				int token1 = scan.nextInt();
				System.out.println();
				if(token1==1) {
					System.out.println("Selected '활동적인'");
					first ="활동적인 ";
					
				}
				else if(token1==2) {
					System.out.println("Select '잔잔한'");
					first ="잔잔한 ";
				}
				
				System.out.println();
				System.out.println("-----------------");
				System.out.println("1. 인스타명소 ");
				System.out.println("2. 자연이 아름다운 ");
				System.out.println("-----------------");
				int token11 = scan.nextInt();
				System.out.println();
				if(token11 == 1) {
					System.out.println("Select '인스타명소' ");
					second = "인스타명소 ";
				}
				if(token11 == 2) {
					System.out.println("Select '자연이아름다운'");
					second = "자연이아름다운 ";
				}
				
				scan.nextLine();
				int token111 = 0;
				
				if(token11 == 1)
				{
					System.out.println();
					System.out.println("-----------------");
					System.out.println("1. 카페 ");
					System.out.println("2. 감성술집 ");
					System.out.println("-----------------");
					String temp = scan.nextLine();
					
					if(temp.contains("1"))
						token111 = 1;
					else
						token111 = 2;
					System.out.println();
					if(token111 == 1) {
						System.out.println("Select '카페' ");
						third = "카페 ";
					}
					if(token111 == 2) {
						System.out.println("Select '감성술집'");
						third = "감성술집";
					}
				}
				else if(token11 == 2)
				{
					System.out.println();
					System.out.println("-----------------");
					System.out.println("1. 산 ");
					System.out.println("2. 바다");
					System.out.println("-----------------");
					String temp = scan.nextLine();
					
					if(temp.contains("1"))
						token111 = 1;
					else
						token111 = 2;
					System.out.println();
					if(token111 == 1) {
						System.out.println("Select '산' ");
						third = "산 ";
					}
					if(token111 == 2) {
						System.out.println("Select '바다'");
						third = "바다";
					}
				}
				
				
				
				
				
				String select = String.valueOf(token1).concat(String.valueOf(token11));
				select = select.concat(String.valueOf(token111));
				//select는 111~222
				
				
				System.out.println(select);
				System.out.println();
				System.out.println("---------------결과----------------");
				System.out.println("선택된 " + first + " , " + second +  " , " + third);
				
				System.out.println("이름 : ~~~~ / 주소:~~~~ / 테마:~~~~ / 영업시간:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ / 테마:~~~~ / 영업시간:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ / 테마:~~~~ / 영업시간:~~~");
				System.out.println("초기메뉴로 돌아가시겠습니까?");

				
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
	}
}