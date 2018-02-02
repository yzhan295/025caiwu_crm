package com.ifinance.tool;

public class Poet {
	private String author;
	@Override
	public String toString() {
		return "Poet [author=" + author + ", title=" + title + ", strain=" + strain + ", paragraph=" + paragraph + "]";
	}
	private String title;
	private String strain;
	private String paragraph;
	
	public String getAuthor() {
		return author;
	}
	public String getParagraph() {
		return paragraph;
	}
	public String getStrain() {
		return strain;
	}
	public String getTitle() {
		return title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
