package com.narp.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateCache {
	
	private static Map<String, Template> templateMap = new  HashMap<String, Template>();
	
	public TemplateCache() {
		loadTemplates();
	}
	
	private void loadTemplates(){
		Map<String, String> spellMap = new HashMap<String, String>();
		spellMap.put("PROOUCT", "PRODUCT");
		spellMap.put("6STX", "GST%");
		spellMap.put("OTY", "QTY");
		spellMap.put("COLOSTORAGE", "COLDSTORAGE");
		
		List<String> identifiers = new ArrayList<String>();
		identifiers.add("36ANHPK3492N1ZF");
		identifiers.add("RUG MART");
		
		
		Template t = new Template("drugMart", "MFG", "GSTX", spellMap);
		t.setTemplateUniqueIdentifiers(identifiers);
		t.setTotalIdentifier("ITEMS CANNOT BE");
		templateMap.put("drugMart", t);
		
	}
		
	public static Template getTemplate(String name){
		return templateMap.get(name);
	}
	
	public static Collection<Template> getAllTemplates(){
		return templateMap.values();
	}
}