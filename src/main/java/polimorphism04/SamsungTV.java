package polimorphism04;

public class SamsungTV implements TV {

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
		System.out.println("SamsungTV - ������ ���Դϴ�.");
	}
	@Override
	public void volumeDown() {
		System.out.println("SamsungTV - ������ ����ϴ�.");
	}
}
