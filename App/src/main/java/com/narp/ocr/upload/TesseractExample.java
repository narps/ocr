/*package com.narp.ocr.upload;

import java.io.File;

import org.xeustechnologies.googleapi.spelling.SpellChecker;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractExample
{
    public static void main(String[] args) throws Exception{
        System.out.println("asfd");
    }
    public static void main(String[] args) {
    	System.out.println("bging");
        File imageFile = new File("/mnt/home/naresh/projects/ocr/samples-img/12points.png");
        Tesseract instance = new Tesseract(); // JNA Interface Mapping
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    	
    	SpellChecker sc = new SpellChecker();
    	SpellResponse check = sc.check("teyt");
    	
    	for (int i = 0; i < check.getCorrections().length; i++) {
			for (String string : check.getCorrections()[i].getWords()) {
				System.out.println(string);
			}
		}
    	System.out.println("teyst");
    }
}
*/