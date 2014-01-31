package de.cau.klay.gwt.client;

import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.Exportable;
import org.timepedia.exporter.client.ExporterUtil;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
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

		// for the gwt exporter
		ExporterUtil.exportAll();

		onLoad();
	}

	/*
	 * Have a look at http://code.google.com/p/gwt-exporter/
	 */
	public static class Klay implements Exportable {
		@Export("$wnd.layout")
		public static void layout(String foo) {
			Window.alert("Performed layout" + foo);
		}
	}

	private native void onLoad() /*-{
									if ($wnd.myInit) $wnd.myInit();
									}-*/;
}
