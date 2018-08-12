package com.narp.template;

import java.util.List;
import java.util.Map;

public class Template {
	
	private String templateName;
	
	private String headerIdentifier;
	
	private String summaryIdentifier;
	
	private String totalIdentifier;
	
	private Map<String, String> spellMap;
	
	private List<String> templateUniqueIdentifiers ;

	public Template(String templateName, String headerIdentifier, String summaryIdentifier,
			Map<String, String> spellMap) {
		super();
		this.templateName = templateName;
		this.headerIdentifier = headerIdentifier;
		this.summaryIdentifier = summaryIdentifier;
		this.spellMap = spellMap;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getHeaderIdentifier() {
		return headerIdentifier;
	}

	public void setHeaderIdentifier(String headerIdentifier) {
		this.headerIdentifier = headerIdentifier;
	}

	public String getSummaryIdentifier() {
		return summaryIdentifier;
	}

	public void setSummaryIdentifier(String summaryIdentifier) {
		this.summaryIdentifier = summaryIdentifier;
	}

	public Map<String, String> getSpellMap() {
		return spellMap;
	}

	public void setSpellMap(Map<String, String> spellMap) {
		this.spellMap = spellMap;
	}
	
	public List<String> getTemplateUniqueIdentifiers() {
		return templateUniqueIdentifiers;
	}
	
	public void setTemplateUniqueIdentifiers(List<String> templateUniqueIdentifiers) {
		this.templateUniqueIdentifiers = templateUniqueIdentifiers;
	}

	public String getTotalIdentifier() {
		return totalIdentifier;
	}
	
	public void setTotalIdentifier(String totalIdentifier) {
		this.totalIdentifier = totalIdentifier;
	}

	@Override
	public String toString() {
		return "Template [templateName=" + templateName + ", headerIdentifier=" + headerIdentifier
				+ ", summaryIdentifier=" + summaryIdentifier + ", totalIdentifier=" + totalIdentifier + ", spellMap="
				+ spellMap + ", templateUniqueIdentifiers=" + templateUniqueIdentifiers + "]";
	}
	
}