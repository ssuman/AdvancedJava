/* 
 * Grep.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
package rit.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program implements the functionality of Grep
 * @author      Kanth
 * @author      Suman
 * 
 */
public class Grep {

	// Program execution begins here
	public static void main(String[] args) throws IOException {

		// Ignore duplicates
		Set<String> setValues = new HashSet<String>();
		File file = new File(args[0]);
		InputStream is = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = "";

		// Read a line at a time
		while ((line = br.readLine()) != null) {
			
			Pattern pattern = Pattern.compile(args[1], Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(line);

			// Find the match and add it to the Set interface.
			while (matcher.find()) {
				setValues.add(line);
			}
		}

		// Print all matched strings
		for (String str : setValues) {
			System.out.println(str);
		}

	}

}
