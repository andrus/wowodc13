package demo.rest.model;

import java.util.Date;

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
	
	@XmlElement
	public String getBody() {
		return delegate.getBody();
	}
	
	@XmlElement
	public Date getPublishedOn() {
		return delegate.getPublishedOn();
	}
}
