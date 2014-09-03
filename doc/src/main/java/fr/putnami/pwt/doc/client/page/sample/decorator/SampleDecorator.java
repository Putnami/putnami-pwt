package fr.putnami.pwt.doc.client.page.sample.decorator;

import com.google.common.collect.Multimap;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;

import fr.putnami.pwt.core.inject.client.annotation.Templated;
import fr.putnami.pwt.core.mvp.client.View;
import fr.putnami.pwt.core.mvp.client.ViewDecorator;
import fr.putnami.pwt.core.widget.client.Anchor;
import fr.putnami.pwt.core.widget.client.Header;
import fr.putnami.pwt.core.widget.client.List;
import fr.putnami.pwt.core.widget.client.List.Type;
import fr.putnami.pwt.core.widget.client.ListItem;
import fr.putnami.pwt.core.widget.client.OneWidgetPanel;
import fr.putnami.pwt.core.widget.client.Panel;
import fr.putnami.pwt.core.widget.client.PanelAccordion;
import fr.putnami.pwt.plugin.code.client.StaticCode;
import fr.putnami.pwt.plugin.code.client.configuration.java.JavaConfiguration;
import fr.putnami.pwt.plugin.code.client.configuration.xml.XmlConfiguration;

@Templated
public class SampleDecorator extends ViewDecorator implements View {

	private class SourceItem extends ListItem implements ClickHandler {

		private final String fileName;

		public SourceItem(String fileName) {
			super();
			this.fileName = fileName;
			String simpleFileName = fileName;
			if (simpleFileName.lastIndexOf("/") != -1) {
				simpleFileName = simpleFileName.substring(simpleFileName.lastIndexOf("/") + 1);
			}
			Anchor<?> anchor = new Anchor(simpleFileName);
			anchor.addClickHandler(this);
			add(anchor);
		}

		@Override
		public void onClick(ClickEvent event) {
			requestFile(fileName);
		}
	}

	public static SampleDecorator get() {
		return GWT.create(SampleDecorator.class);
	}

	@UiField
	OneWidgetPanel sampleContent;
	@UiField
	PanelAccordion sourceAccordion;
	@UiField
	StaticCode sourceCode;


	@Override
	public void setWidget(IsWidget w) {
		sampleContent.setWidget(w);
		if (w instanceof HasSources) {
			addSources(((HasSources) w).getSourcesMap());
		}
	}

	protected void addSources(Multimap<String, String> sources) {
		Panel panelToOpen = null;
		String sourceToOpen = null;
		sourceAccordion.clear();
		for (String panelName : sources.keySet()) {

			List sourceList = new List();
			sourceList.setType(Type.LIST);
			for (String source : sources.get(panelName)) {
				if (sourceToOpen == null) {
					sourceToOpen = source;
				}
				sourceList.add(new SourceItem(source));
			}
			Panel sourcePanel = new Panel();
			if (panelToOpen == null) {
				panelToOpen = sourcePanel;
			}
			sourcePanel.add(new Header(panelName));
			sourcePanel.add(sourceList);
			sourceAccordion.add(sourcePanel);
		}
		requestFile(sourceToOpen);
		final Panel toOpen = panelToOpen;

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				toOpen.setCollapse(false);
			}
		});
	}

	private void requestFile(final String fileName) {
		sourceCode.asWidget().setVisible(false);
		sourceCode.setText("");

		RequestCallback callBack = new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				if (fileName.endsWith("xml")) {
					sourceCode.setConfiguration(XmlConfiguration.XML_CONFIGURATION);
				}
				else if (fileName.endsWith("java")) {
					sourceCode.setConfiguration(JavaConfiguration.JAVA_CONFIGURATION);
				}
				else {
					displayError(new RuntimeException("Unknow file type"));
				}
				sourceCode.setText(response.getText());
				sourceCode.asWidget().setVisible(true);
			}

			@Override
			public void onError(Request request, Throwable exception) {
				displayError(exception);
			}
		};
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, GWT.getModuleBaseURL() + "sample/" + fileName);
		builder.setCallback(callBack);
		try {
			builder.send();
		}
		catch (RequestException e) {
			callBack.onError(null, e);
		}
	}

	private void displayError(Throwable exception) {
		GWT.reportUncaughtException(exception);
	}

}