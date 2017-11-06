package utils;

public class Toolbox {
	public static String redToGreen(float numb) {

		if (!Float.isNaN(numb)) {
			System.out.println("Input number: " + numb);
			int r, g, b;

			b = 0;

			if (numb < 50) {
				r = 255;
				float percentage = (numb / 50f);
				g = (int) (255 * percentage);
			} else {
				g = 255;
				float percentage = ((numb - 50) / 50f);
				r = 255 - (int) (255 * percentage);
			}

			System.out.println("RGB:" + r + "," + g + "," + b);

			return String.format("#%02x%02x%02x", r, g, b);
		}
		else
			return String.format("#%02x%02x%02x", 255, 255,255);
	}

}
