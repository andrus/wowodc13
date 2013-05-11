package demo.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "news")
public class JaxbArticles {

	@XmlElement(name = "articles")
	public List<JaxbArticle> articles = new ArrayList<JaxbArticle>();

	public void addArticle(JaxbArticle article) {
		articles.add(article);
	}

}
