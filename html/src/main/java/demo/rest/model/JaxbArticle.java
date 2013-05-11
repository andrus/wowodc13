package demo.rest.model;

import javax.xml.bind.annotation.XmlElement;

import demo.cayenne.Article;

public class JaxbArticle {

	private Article delegate;

	public JaxbArticle() {
	}
	
	public JaxbArticle(Article delegate) {
		this.delegate = delegate;
	}

	@XmlElement
	public String getTitle() {
		return delegate.getTitle();
	}
}
