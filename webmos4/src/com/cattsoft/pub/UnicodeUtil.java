package com.cattsoft.pub;

public class UnicodeUtil {
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}
	public static int[] string2ASCII(String s) {// ×Ö·û´®×ª»»ÎªASCIIÂë   
        if (s == null || "".equals(s)) {   
            return null;   
        }   
  
        char[] chars = s.toCharArray();   
        int[] asciiArray = new int[chars.length];   
  
        for (int i = 0; i < chars.length; i++) {   
            asciiArray[i] = char2ASCII(chars[i]);   
        }   
        return asciiArray;   
    }   


	private static int char2ASCII(char c) {   
	        return (int) c;   
	    }   
	/**
	 * ½«×Ö·û´®±àÂë³É Unicode
	 * 
	 * @param theString
	 *            ´ý×ª»»³ÉUnicode±àÂëµÄ×Ö·û´®
	 * @param escapeSpace
	 *            ÊÇ·ñºöÂÔ¿Õ¸ñ
	 * @return ·µ»Ø×ª»»ºóUnicode±àÂëµÄ×Ö·û´®
	 */
	public static String stringtoUnicode(String theString, boolean escapeSpace) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace)
					outBuffer.append('\\');
				outBuffer.append(' ');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=': // Fall through
			case ':': // Fall through
			case '#': // Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}
}
