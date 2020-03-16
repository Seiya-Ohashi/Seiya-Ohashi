package method;

public class Task2 {
//50.5/10.6
	public static  void main(String[] args) {
		double  lengthA = 50.5;
		double  heightA = 10.1;
		double triangleArea = getTriangleArea(lengthA, heightA);
		System.out.println("三角形の面積");
		System.out.println(triangleArea);
	}
	public static double getTriangleArea(double lengthB, double heightB){
		double  area = lengthB * heightB / 2;
		area = Math.round(area);
		return area;
	}
}
