/**
 * This file is part of pwt.
 *
 * pwt is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * pwt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with pwt.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.putnami.pwt.core.widget.client.event;

import java.util.Collections;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class SelectionEvent extends GwtEvent<SelectionEvent.Handler> {

	public interface Handler extends EventHandler {

		void onSelectionChange(SelectionEvent event);
	}

	public interface HasSelectionHandlers extends HasHandlers {

		HandlerRegistration addSelectionHandler(Handler handler);
	}

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final List<?> selection;

	public SelectionEvent(List<?> selection) {
		super();
		this.selection = selection;
	}

	@Override
	public GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onSelectionChange(this);
	}

	public <A> List<A> getSelection() {
		return Collections.unmodifiableList((List<? extends A>) selection);
	}
}
