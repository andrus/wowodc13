package demo.editor.components;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Parameter;

import demo.services.domain.IDomainService;

public class PageWrapper {
	@Parameter
	private String title;

	@Inject
	private IDomainService domainService;

	public String getFullTitle() {
		return title + " - " + domainService.currentDomain().getName()
				+ " - editor";
	}
}
