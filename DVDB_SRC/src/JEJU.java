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
		
		
		if(bit ==1) {
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
		
		else if(bit==2) {
			System.out.print("ID : ");
			String ID = scan.next();
			System.out.print("PW : ");
			String PW = scan.next();
			
			Thread.sleep(1000);
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
				
				System.out.println("Select 1 or 2");
				System.out.println("-----------------");
				System.out.println("1. ���ֽ�����");
				System.out.println("2. ���Ÿ���");
				System.out.println("-----------------");
				
				int token1 = scan.nextInt();
				if(token1==1) {
					System.out.println("Selected '���ֽ�����'");
					System.out.println("-----------------");
					System.out.println("1. ģ�����");
					System.out.println("2. �������");
					System.out.println("-----------------");
				}
				else if(token1==2) {
					System.out.println("Select '���Ÿ���'");
					System.out.println("-----------------");
					System.out.println("1. ��ä");
					System.out.println("2. �Խ�Ʈ�Ͽ콺");
					System.out.println("-----------------");
					int token11 = scan.nextInt();
				}
				System.out.println("---------------���----------------");
				System.out.println("���õ� '���ֽ�����','��ä' ������ ���ھ��Ҵ�");
				System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  üũ�νð�:~~~ / ��ȭ��ȣ:~~~");
				System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  üũ�νð�:~~~ / ��ȭ��ȣ:~~~");
				System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  üũ�νð�:~~~ / ��ȭ��ȣ:~~~");
				
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
					System.out.println("------����� �԰����� ������?-------");
					System.out.println("--------16��---------");
					System.out.println("1. ��¦����");
					System.out.println("2. ���ⱹ��");
					System.out.println("-----------------");
					int token21 = scan.nextInt();
					System.out.println(".\n.\n.\n.\n.\n.\n.\n.\n");
					System.out.println("------����� �԰����� ������?-------");
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
			
			else if(token==3) {//����
				System.out.println("Select");
				System.out.println("-----------------");
				System.out.println("1. Ȱ������");
				System.out.println("2. ������");
				System.out.println("-----------------");
				
				int token3 = scan.nextInt();
				if(token3==1) {
					
				}
				else if(token3==2) {
					System.out.println("Selected '������'");
					System.out.println("-----------------");
					System.out.println("1. �ν�Ÿ ����");
					System.out.println("2. �ڿ��� �Ƹ��ٿ�");
					System.out.println("-----------------");
					int token131 = scan.nextInt();
					}
				System.out.println("'������', '�ν�Ÿ����'�� ��õ�Ǵ� �������� : ");
				System.out.println("�̸� : ~~~~ / �ּ�:~~~~ / �׸�:~~~~ / �����ð�:~~~");
				System.out.println("�̸� : ~~~~ / �ּ�:~~~~ / �׸�:~~~~ / �����ð�:~~~");
				System.out.println("�̸� : ~~~~ / �ּ�:~~~~ / �׸�:~~~~ / �����ð�:~~~");
				System.out.println("�ʱ�޴��� ���ư��ðڽ��ϱ�?");
				Thread.sleep(7000);
				
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
					System.out.println("ī�װ��� : ��������, ����:��¦����");
					System.out.println("�Ĵ�");
					System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
					System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
					System.out.println("�̸� : ~~~~ / �ּ�:~~~~ /  �����ð�:~~~");
					
				}
				else if(token4==2) {
					System.out.println("ID : ~~~���� ������ ��/20��/���ֵ� ���� ������ ���� �α��ִ� ��������");
					System.out.println("ī�װ���: ~~~-~~~-~~~ �������� : ~~~~~~~~~~");
				}
				else if(token4==3) {
					System.out.println("ID : ~~~���� ������ ��/20��/���ֵ� ���� ������ ���� �α��ִ� �������´�");
					System.out.println("ī�װ���: ~~~-~~~-~~~ ���� : ~~~~~~~~~~");
				}
				
			
				
				}
			else {
				continue;
				}
			
			
		}
		else if(bit==3) {
			return;
			}
		}
	}
}