
package com.rayzr522.colournames;

public class Regex {

	public static int numMatches(String text, String regex) {

		text = " " + text + " ";

		if (text == null || regex == null) { return 0; }
		if (text.matches(regex)) { return 1; }
		if (text.length() < 1) { return 0; }
		if (text.split(regex).length < 1) { return 1; }
		if (text.split(regex)[0].equals(text)) { return 0; }
		String[] split = text.split(regex);
		int count = split.length;

		if (split.length > 1 && split[0].trim().equals("")) {
			count--;
		}
		
		if (split.length > 1 && split[split.length - 1].trim().equals("")) {
			count--;
		}

		return count;

	}

}
