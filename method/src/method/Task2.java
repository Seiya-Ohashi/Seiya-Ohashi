package method;

public class Task2 {

	public static  void main(String[] args) {
		double  lengthA = 50.5;
		double  heightA = 10.6;
		getTriangleArea(lengthA,heightA);
		System.out.println("三角形の面積");
		double triangleArea = getTriangleArea(lengthA,heightA);
		System.out.println(triangleArea);
	}
	public static double getTriangleArea(double lengthB , double heightB){
		double  Area = lengthB * heightB / 2;
		Area = Math.ceil(Area);
		return Area;
	}
}
