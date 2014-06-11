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
package fr.putnami.pwt.core.widget.client;

import java.util.Date;

import com.google.gwt.user.client.ui.IsWidget;

import fr.putnami.pwt.core.widget.client.base.AbstractOutput;
import fr.putnami.pwt.core.widget.client.helper.DateRenderer;

public class OutputDate extends AbstractOutput<Date> {

	private String format;

	public OutputDate() {
		setRenderer(DateRenderer.get());
	}

	protected OutputDate(OutputDate source) {
		super(source);
		format = source.format;
	}

	@Override
	public IsWidget cloneWidget() {
		return new OutputDate(this);
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
		setRenderer(new DateRenderer(format));
	}

}