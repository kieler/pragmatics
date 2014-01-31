package de.cau.klay.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.cau.klay.gwt.client.layout.KlayExampleLayouter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KlayGWT implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button layoutButton = new Button("Do Layout");

		// We can add style names to widgets
		layoutButton.addStyleName("sendButton");

		RootPanel.get("sendButtonContainer").add(layoutButton);

		class MyHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				KlayExampleLayouter.layoutExample();
			}
		}
		layoutButton.addClickHandler(new MyHandler());
	}
}
