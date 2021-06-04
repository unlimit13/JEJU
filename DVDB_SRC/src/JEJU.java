import java.util.*;
import java.sql.*;

public class JEJU {

	public static void main(String[] args) throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		
	while(true) {	
		System.out.println("-----------------");
		System.out.println("1. sign up");
		System.out.println("2. sign in");
		System.out.println("3. Exit");
		System.out.println("-----------------");
		
		int bit = scan.nextInt(); 
		//bit1은 sign up
		
		
		if(bit ==1) { 	//bit1은 sign up
			System.out.println("-----------------");
			System.out.println("ID : ");
			System.out.println("PW : ");
			System.out.println("Sex(F,M) : ");
			System.out.println("Age : ");
			System.out.println("plan to visit[E,W,S,N] : ");
			System.out.println("-----------------");
			
			String detail = scan.next();
			System.out.println("Sign up complete!");
			Thread.sleep(2000);
			continue;
		}
		
		else if(bit==2) { 	//bit1은 sign in
			System.out.print("ID : ");
			String ID = scan.next();
			System.out.print("PW : ");
			String PW = scan.next();
			
			Thread.sleep(1000);
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
				
				System.out.println("Select 1 or 2");
				System.out.println("-----------------");
				System.out.println("1. 제주스러운");
				System.out.println("2. 럭셔리한");
				System.out.println("-----------------");
				
				int token1 = scan.nextInt();
				if(token1==1) {
					System.out.println("Selected '제주스러운'");
					
				}
				else if(token1==2) {
					System.out.println("Select '럭셔리한'");
				}
				
				int token11 = scan.nextInt();
				System.out.println("-----------------");
				System.out.println("1. 독채 ");
				System.out.println("2. 다세대 ");
				System.out.println("-----------------");
				
				if(token11 == 1) {
					System.out.println("Select '독채' ");
				}
				if(token11 == 2) {
					System.out.println("Select '다세대'");
				}
				
				int token111 = scan.nextInt();
				System.out.println("-----------------");
				System.out.println("1. 도심");
				System.out.println("2. 한적한");
				System.out.println("-----------------");
				
				if(token111 == 1) {
					System.out.println("Select '도심' ");
				}
				if(token111 == 2) {
					System.out.println("Select '한적한'");
				}
				
				String select = String.valueOf(token1) + String.valueOf(token11) + String.valueOf(token111);
				//select는 111~222
				
				System.out.println("---------------결과----------------");
				System.out.println("선택된 '제주스러운','독채' 컨셉의 숙박업소는");
				System.out.println("이름 : ~~~~ / 주소:~~~~ /  체크인시간:~~~ / 전화번호:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ /  체크인시간:~~~ / 전화번호:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ /  체크인시간:~~~ / 전화번호:~~~");
				
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
			
			else if(token==3) {//질문
				System.out.println("Select");
				System.out.println("-----------------");
				System.out.println("1. 활동적인");
				System.out.println("2. 잔잔한");
				System.out.println("-----------------");
				
				int token3 = scan.nextInt();
				if(token3==1) {
					
				}
				else if(token3==2) {
					System.out.println("Selected '잔잔한'");
					System.out.println("-----------------");
					System.out.println("1. 인스타 명소");
					System.out.println("2. 자연이 아름다운");
					System.out.println("-----------------");
					int token131 = scan.nextInt();
					}
				System.out.println("'잔잔한', '인스타명소'로 추천되는 관광지는 : ");
				System.out.println("이름 : ~~~~ / 주소:~~~~ / 테마:~~~~ / 영업시간:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ / 테마:~~~~ / 영업시간:~~~");
				System.out.println("이름 : ~~~~ / 주소:~~~~ / 테마:~~~~ / 영업시간:~~~");
				System.out.println("초기메뉴로 돌아가시겠습니까?");
				Thread.sleep(7000);
				
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
		else if(bit==3) 	//bit3은exit
		{
			return;
			
		}
		}
	}
}