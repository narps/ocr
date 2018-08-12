package com.narp.ocr.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.narp.abbyy.wrapper.AbbyyClient;
import com.narp.ocr.invoice.Invoice;
import com.narp.ocr.text.processor.TextProcessor;
import com.narp.ocr.utils.OCRUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/file")
public class FileController {

	Map<String, List<String>> headers = new HashMap<String, List<String>>();
	boolean isItem = false;

	public static void main(String[] args) {

		FileController fc = new FileController();
		fc.initHeaders();

		String uploadedFileLocation = "/mnt/home/naresh/projects/ocr/backend/uploads/" + "drug_mart_invoice.png";
		String[] input = { "recognize", "--lang=English", uploadedFileLocation };
		// /mnt/home/naresh/projects/ocr/backend/
		// AbbyyClient.ocrIt(input);

	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "/mnt/home/naresh/projects/ocr/backend/uploads/" + fileDetail.getFileName();

		writeToFile(uploadedInputStream, uploadedFileLocation);
		String[] input = { "recognize", "--lang=English", uploadedFileLocation };
		// String[] input = {"receipt",uploadedFileLocation};
		String result = null;

		result = AbbyyClient.ocrIt(input);
		Invoice invoice = null;
		try {
			invoice = TextProcessor.processText(result);
		} catch (FileNotFoundException e) {
			System.out.println(this.getClass().getSimpleName() + ".uploadFile " + e.getStackTrace());
		}
		String strRes = "Could not fetch valid invoice from " + result;
		if(invoice != null){
			try {
				strRes = OCRUtils.convertObjectToJSON(invoice);
			} catch (Exception e) {
				System.out.println(this.getClass().getSimpleName() + ".uploadFile, excepton " + e.getStackTrace());
			}
		}
		
		return Response.status(500).entity(strRes).build();
	}

	private void process(String result) {
		System.out.println("processing text ");
		String processedReceipt = "";
		boolean isItem = false;
		String[] lines = result.split(System.getProperty("line.separator"));
		for (String line : lines) {
			System.out.println(" " + line);
			if (isTotal(line)) {
				processedReceipt += line;
			}
			if (isHeader(line) || isItem) {
				processedReceipt += line;
			}
		}
		System.out.println("PtEXT : " + processedReceipt);
	}

	private boolean isTotal(String line) {
		if (line.contains("NET PAYABLE")) {

			isItem = false;
			return true;

		}
		return false;
	}

	private boolean isHeader(String line) {

		try {

			int counter = headers.get("d1").size();

			System.out.println(" counter " + counter + " , line " + line);

			for (String s : headers.get("d1")) {
				line.concat(s);
				counter--;
			}
			System.out.println(" counter " + counter);
			if (counter <= 2) {
				isItem = true;
				return true;
			}

		} catch (Exception e) {
			System.out.println(" " + e.getMessage());
		}
		return false;
	}

	private void initHeaders() {

		String[] s = { "MFG", "PRODUCT NAME", "HSNCODE", "PACK", "BATCH", "EXPIRY", "QTY", "M.R.P", "RATE", "AMOUNT",
				"GSTX" };
		List<String> d1Hdr = Arrays.asList(s);
		headers.put("d1", d1Hdr);
	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}