package com.narp.ocr.invoice;

import java.util.List;

public class Invoice {

	private List<String> lineItems;

	private String totalLine;

	private String distributorName;

	private String distGstNumber;

	public List<String> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<String> lineItems) {
		this.lineItems = lineItems;
	}

	public String getTotalLine() {
		return totalLine;
	}

	public void setTotalLine(String totalLine) {
		this.totalLine = totalLine;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getDistGstNumber() {
		return distGstNumber;
	}

	public void setDistGstNumber(String distGstNumber) {
		this.distGstNumber = distGstNumber;
	}
	
	private static void printInvoice(Invoice inv) {
		for (String l : inv.getLineItems()) {
			System.out.println(l.trim());
		}
		System.out.println(inv.getTotalLine().trim());
	}

	@Override
	public String toString() {
		return "Invoice [lineItems=" + lineItems + ", totalLine=" + totalLine + ", distributorName=" + distributorName
				+ ", distGstNumber=" + distGstNumber + "]";
	}

}