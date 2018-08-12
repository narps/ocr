package com.narp.ocr.speller;

import com.narp.template.Template;

public class SpellCorrector {
	
	public static String correct(String text, Template t) {
		for (String word : t.getSpellMap().keySet()) {
			text = text.replace(word, t.getSpellMap().get(word));
		}
		return text;
	}
	
}