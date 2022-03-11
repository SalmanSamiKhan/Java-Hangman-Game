
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static String get_word() { // get word from word list
		words w = new words();
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		int random_num = random.nextInt(2465);
		String secret_word = w.word[random_num].toUpperCase();
		// System.out.println(secret_word);
		return secret_word;
	}
	
//	public static void 
	Hangman() {
		String s = get_word();
		// System.out.println(s);
		char[] word = s.toCharArray(); // words --> char array
		int len = word.length;
		int life = 5;
		ArrayList<Character> used_letters = new ArrayList<Character>();
		char[] display = display_initial(len).toCharArray(); // char array for displaying output
		print(used_letters, life, display);
		boolean Flag = true;
		while (Flag) {

			char letter = input(used_letters); // valid word
			boolean flag = false;

			// match user input with secret word
			for (int i = 0; i < len; i++) {
				if (letter == word[i]) {
					display[i] = letter;
					word[i] = '*';
					flag = true;
				}
			}

			if (flag == false) {
				life--;
				System.out.println("Sorry! This letter is not in the word");
			}
			;

			// print
			print(used_letters, life, display);

			// while loop control
			if (life == 0 || (!String.valueOf(display).contains("-"))) {
				Flag = false;
			}

		}

		// after finishing while its result time
		result(display, s);
	}

	// display "-----" depending on the word length
	public static String display_initial(int len) {
		String string = "";
		while (len > 0) {
			string += "-";
			len--;
		}

		// System.out.println("Word: " + string);
		return string;
	}

	// Input, check if valid, again take input
	public static char input(ArrayList used_letters) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		char c = ' ';
		while (flag) {

			System.out.print("\nGuess a letter: ");
			String s = sc.next().toUpperCase(); // upper case A B C
			int input_length = s.length();
			c = s.charAt(0);
			
			int asci = (int) c;
			// asci<65 || asci>122 || (asci>90 && asci<97)
			if (asci < 65 || asci > 90 ) { // A =65, B=90
				System.out.println("Invalid character! Please use a letter\n");
				
			} else {
				if(input_length>1) {
					System.out.println("Please input only 1 letter at a time!");
				}
				else {
					boolean b = check_repeat(used_letters, c); // if repeated
					if (b) {
						System.out.println("You have already used it! Please use another letter\n");

					}

					else {
						used_letters.add(c);// Valid letter
						flag = false;
					// return c;
					}
				}
				// return c;
			}
		}
		return c;
	}

	// if repeated letter is entered. String can be used in place of ArrayList
	public static boolean check_repeat(ArrayList used_letter, char letter) {
		if (used_letter.contains(letter)) {

			return true;
		}

		else {
			return false;
		}
	}

	// Print the text to beshowed on the display
	public static void print(ArrayList used_letter, int life, char[] display) {
		System.out.println("Word: " + new String(display));

		System.out.print("Used letters: ");
		for (int i = 0; i < used_letter.size(); i++) {
			System.out.print(used_letter.get(i) + " ");
		}

		// used_letter.forEach(System.out::print); // Also includes [ ] -_-
		System.out.println("\nLives: " + life);

	}

	// calculate result
	public static void result(char[] display, String s) {

		if (String.valueOf(display).contains("-")) {
			System.out.println("Sorry! You could not guess the word!\n" + "The word was: " + s);
		}

		else {
			System.out.println("Yeee! You guessed the word right!\n" + "The word is: " + s);
		}
	}

}