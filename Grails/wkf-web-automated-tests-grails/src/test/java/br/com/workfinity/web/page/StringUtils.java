package br.com.workfinity.web.page;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
	
	public static String myJoin(String textToInsirtInArray, String[] stringArray) {
		
		return myJoin(textToInsirtInArray, Arrays.asList(stringArray));
	}
	
	public static String myJoin(String textToInsirtInArray, List<String> stringArray) {
		String concatenar = "";
		for (String s : stringArray) {
			concatenar += s + textToInsirtInArray;
		}

		return concatenar;
	}

	public static String removeSpecialChar(String charcter) {
		return charcter.replaceAll("\\W", "");
	}

	public static String replaceAll(String originalString, List<String> oldStrings, List<String> newStrings) {
		if (oldStrings.size() == newStrings.size()) {
			for (int i = 0; i < oldStrings.size(); i++) {
				originalString = replace(originalString, oldStrings.get(i), newStrings.get(i));
			}
			return originalString;
		}
		return null;
	}

	public static String replace(String originalString, String oldString, String newString) {
		String originalStringWithReplace = originalString.replace(oldString, newString);
		return originalStringWithReplace.replaceAll("\t", "");
	}

	public static String randomString(Integer size) {
		return null != size ? RandomStringUtils.random(size, true, true) : "";
	}

	public static String returnShortPackageName(String param) {

		String newString = "";
		if (!param.contains(".")) {
			return param;
		} else {
			int count = 0;
			for (int i = 0; i < param.length(); i++) {
				if (param.charAt(i) == '.')
					count++;
			}
			if (count < 2) {
				return param;
			} else {

				String[] paramSplit = param.split(Pattern.quote("."));

				int a = 0;
				while (a < paramSplit.length - 2) {
					newString += paramSplit[a].charAt(0) + ".";
					a++;
				}

				newString += paramSplit[paramSplit.length - 2] + ".";
				newString += paramSplit[paramSplit.length - 1];

				return newString;
			}
		}
	}

	public static String returnStringsConcatenada(List<String> stringsSeparadas) {
		String newString = "";
		for (int i = 0; i < stringsSeparadas.size() - 1; i++) {
			newString += stringsSeparadas.get(i) + ", ";
		}
		return newString + stringsSeparadas.get(stringsSeparadas.size() - 1);
	}
}
