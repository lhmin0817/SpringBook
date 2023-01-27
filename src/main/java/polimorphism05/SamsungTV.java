package polimorphism05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv3")	//SamsungTV tv3 = new SamsungTV(); tv3 : SamsungTV, TV ����
public class SamsungTV implements TV {
	
	@Autowired
	private Speaker speaker;//��ü ���� ����

	@Override
	public void powerOn() {
		System.out.println("SamsungTV - ������ �մϴ�.");
	}
	@Override
	public void powerOff() {
		System.out.println("SamsungTV - ������ ���ϴ�.");
	}
	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
}
