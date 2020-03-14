package standardClass;

public class Task5 {

	public static void main(String[] args) {
		int cat = 0;
		int mouse = 0;
		String text = "cat_mouse_mouse_cat_cat";
		String[] textArray =text.split("_");
		for(String target : textArray) {
			if(target.equals("cat")) {
				cat++;
				continue;
			}else if(target.equals("mouse")){
				mouse++;
			}
		}
		if(cat > mouse) {
			System.out.println("catの方が多い");
		}else{
			System.out.println("mouseの方が多い");
		}

	}

}
