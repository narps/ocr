package com.narp.ocr.text.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.narp.ocr.invoice.Invoice;
import com.narp.ocr.speller.SpellCorrector;
import com.narp.template.Template;
import com.narp.template.TemplateCache;

public class TextProcessor {
	
	static {
		TemplateCache tCache = new TemplateCache();
	}
	
	public static String totalLine;
	

	public static void main(String[] args) throws IOException {
		// processCSV("/mnt/home/naresh/projects/ocr/backend/1531572944917.xlsx");
		// String text = doOcr();
		// processCSV("/mnt/home/naresh/projects/ocr/backend/results/nares.xlsx");
		
		
		String text = getTextFromFile("/mnt/home/naresh/projects/ocr/backend/1531133653950.txt");
		processText(text);
		
		
	}

	private static String getTextFromFile(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(fileName));
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		StringBuilder out = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			out.append(line + "\n");
		}
		reader.close();
		return out.toString();
	}

	public static Invoice processText(String text) throws FileNotFoundException {
		// System.out.println( " Text " + text);
		
		Template t = findTemplate(text);
		Invoice inv = getInvoice(text, t);
		return inv;
	}

	private static Invoice getInvoice(String text, Template t) {
		System.out.println(TextProcessor.class.getSimpleName()+".getInvoice :: processing invoice using template " + t);
		text = SpellCorrector.correct(text, t);
		Invoice invoice = new Invoice();
		
		String[] lines = text.substring(text.indexOf(t.getHeaderIdentifier()), text.indexOf(t.getSummaryIdentifier()))
				.split("\n");
		List<String> lineItems = new ArrayList<String>();
		
		String[] allLines = text.split(System.lineSeparator());
		for (String line : allLines) {
			if (line.toLowerCase().contains(t.getTotalIdentifier().toLowerCase())) {
				line = line.trim().replaceAll("\\s{2}+", "");
				totalLine = line;
			}
		}

		for (String l : lines) {
			if (!l.trim().isEmpty()){
				l = l.trim().replaceAll("\\s+", ",");
				lineItems.add(l);
			}
		}
		
		invoice.setLineItems(lineItems);
		invoice.setTotalLine(totalLine);
		System.out.println(TextProcessor.class.getSimpleName()+".getInvoice :: processed invoice : " + invoice);
		return invoice;
	}

	private static Template findTemplate(String text) {
		System.out.println("finding template from text content");
		Collection<Template> templates = TemplateCache.getAllTemplates();
		for (Template t : templates) {
			for (String idntfr : t.getTemplateUniqueIdentifiers()) {
				if (text.contains(idntfr)) {
					System.out.println("template found !!!");
					return t;
				}
			}
		}
		System.out.println("could not find any template !!! \n \n");
		return null;
	}
}
