package polimorphism03;

public class AppleTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("AppleTV - ������ �մϴ�.");
	}
	@Override
	public void powerOff() {
		System.out.println("AppleTV - ������ ���ϴ�.");
	}
	@Override
	public void volumeUp() {
		System.out.println("AppleTV - ������ ���Դϴ�.");
	}
	@Override
	public void volumeDown() {
		System.out.println("AppleTV - ������ ����ϴ�.");
	}

}
