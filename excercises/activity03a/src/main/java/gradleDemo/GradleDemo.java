package gradleDemo;

import org.apache.commons.validator.*;

public class GradleDemo{
	public static final String cardNum = "1111222233334444";


	public static void main(String[] args){
		System.out.println("Gradle!\nGradle!\nGradle!\nI made it out of clay");

		CreditCardValidator ccv = new CreditCardValidator();
		System.out.println(cardNum + " is " + ccv.isValid(cardNum));
	}
}
