package polimorphism04;

public class SonyTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("SonyTV - ������ �Ŵϴ�.");
	}
	@Override
	public void powerOff() {
		System.out.println("SonyTV - ������ ���ϴ�.");
	}
	@Override
	public void volumeUp() {
		System.out.println("SonyTV - ������ ���Դϴ�.");
	}
	@Override
	public void volumeDown() {
		System.out.println("SonyTV - ������ ����ϴ�.");
	}
}
