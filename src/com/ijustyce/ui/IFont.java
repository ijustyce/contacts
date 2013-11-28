package com.ijustyce.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class IFont {

	/**
	 * create font from file
	 * @param fontFormat if font is trueType , fontFormat is Font.TRUETYPE_FONT
	 * else is Font.TYPE1_FONT
	 * @param path Font file path
	 * @param size font size
	 * @return font
	 */
	public Font getFont(int fontFormat, String path, float size) {

		Font font = null;
		try {
			font = Font.createFont(fontFormat, new File(path));
			font = font.deriveFont(size);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return font;
	}

	public Font getDefaultFont() {

		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("msyh.ttc"));
			font = font.deriveFont(12f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return font;
	}
}
