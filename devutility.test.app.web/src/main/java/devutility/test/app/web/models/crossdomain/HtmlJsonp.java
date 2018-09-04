package devutility.test.app.web.models.crossdomain;

import java.util.List;

public class HtmlJsonp {
	private String Html;
	private List<String> Styles;
	private List<String> Scripts;

	public String getHtml() {
		return Html;
	}

	public void setHtml(String html) {
		Html = html;
	}

	public List<String> getStyles() {
		return Styles;
	}

	public void setStyles(List<String> styles) {
		Styles = styles;
	}

	public List<String> getScripts() {
		return Scripts;
	}

	public void setScripts(List<String> scripts) {
		Scripts = scripts;
	}
}