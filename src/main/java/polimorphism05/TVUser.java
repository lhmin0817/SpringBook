package polimorphism05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		
		//��ü ������ Spring Framework ���� ���� �� DI �� ���ؼ� ��ü�� ����
			//1. XML ���Ͽ��� ��ü�� �����ؼ� ���� : 
				//src/main/resource : �ַ� ������ ���õ� ������ �����ϴ� ��(mybatis ���ۼ���, bean�� xml, db Connection)
					//applicationContext.xml : Bean�� �����ϴ� ���� Spring Framework���� ����
		
			//2. @ (������̼�)�� ����ؼ� ��ü�� ���� �� DI(��ü�� ����) <== Spring Boot ����ϴ� ���
				//a. @(������̼�) �� ����� �� �ֵ��� ������ �ʿ�
					//applicationContext.xml : Bean ���� ����, ������̼��� ����� �� �ֵ��� �����ʿ�
						//<context:component-scan base-package="polimorphism05"></context:component-scan>
						//<context:component-scan base-package="com.spring"></context:component-scan>
				
				//b. Ŭ�������� @Component ������̼��� �ٿ��� ��ü�� ���� <== Ŭ���� �ܺο��� �Ҵ�
						//�پ��� ������ Ŭ������ �����ϱ� ������ Ŭ������ ������ ���� �����̸����� ������̼��� ���δ�.
					//@Component 	: �Ϲ����� Ŭ���� bean(��üȭ) ����
					//@Service		: �����Ͻ� ������ ó���ϴ� Ŭ������ bean ����
					//@repository	: ������ ���̽� ������ ó���ϴ� DAO Ŭ������ ���� (CRUD)
					//@Controller	: ����� ��û�� ControllerŬ������ �ٿ��ִ� ������̼�
		
				//c. Spring Framework �� ������ ��ü�� ������ ����(DI)�ϴ� ������̼� (Ŭ���� �������� �Ҵ�)
						//Ŭ���� ���ο��� Ŭ���� ������ �Ҵ�
					//@Autowired : �ش� Ÿ���� ��ü�� ã�Ƽ� �ڵ����� �Ҵ��ϴ� ������̼� 
						//�ش�Ÿ���� ��ü�� ������ ������ ��� ������ �߻�
					//@Qualifier : Ư�� ��ü �̸��� ����ؼ� �ҷ����� ��, @Autowired�� ���� ���
					
					//@Inject	: java���� �����Ǵ� ������̼�, @Autowired�� ������ ������̼�(Ÿ������ ��ü�� ������´�.)
					//@Resource	: java���� �����Ǵ� ������̼�, @Autowired + @Qualifier�� �ϳ��� �����. <== �̸����� ��ü�� �ĺ�
		
		//1. Spring �����̳ʸ� ����
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. Spring �����̳ʷ� ���� �ʿ��� ��ü�� Lookup �Ѵ�. : DI(��ü�� ����)
		TV tv=(TV) factory.getBean("tv3"); 
		
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumeDown();

	}

}
