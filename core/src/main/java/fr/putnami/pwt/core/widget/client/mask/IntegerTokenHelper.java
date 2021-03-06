/**
 * This file is part of pwt.
 *
 * pwt is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * pwt is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with pwt. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package fr.putnami.pwt.core.widget.client.mask;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.i18n.client.NumberFormat;

import java.util.List;

import fr.putnami.pwt.core.widget.client.mask.MaskValueBoxHelper.TokenHelper;

public class IntegerTokenHelper extends TokenHelper {

	protected final List<Character> characters = Lists.newArrayList();

	private int defaultValue = 0;
	private int min = Integer.MIN_VALUE;
	private int max = Integer.MAX_VALUE;
	private int maxLenght = String.valueOf(Integer.MAX_VALUE).length();

	private int value;

	private NumberFormat formater = NumberFormat.getFormat("#");

	public IntegerTokenHelper() {
	}

	public IntegerTokenHelper(int defaultValue, int min, int max, int maxLenght, String format) {
		this.defaultValue = defaultValue;
		this.min = min;
		this.max = max;
		if (maxLenght != -1) {
			this.maxLenght = maxLenght;
		}
		this.setFormater(format);
	}

	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMaxLenght() {
		return this.maxLenght;
	}

	public void setMaxLenght(int maxLenght) {
		this.maxLenght = maxLenght;
	}

	public void setFormater(String format) {
		this.formater = NumberFormat.getFormat(format);
	}

	@Override
	public void reset() {
		super.reset();
		this.characters.clear();
	}

	@Override
	public void setToken(String token) {
		super.setToken(token);
		if (!Strings.isNullOrEmpty(token)) {
			try {
				this.value = Integer.valueOf(token);
			} catch (NumberFormatException e) {
				this.value = this.defaultValue;
			}
		} else {
			this.value = this.defaultValue;
		}
	}

	@Override
	protected String flush() {
		if (this.token == null) {
			return this.placeHolder;
		}
		return this.token;
	}

	private void setValue(int value) {
		this.value = value;
		if (this.value > this.max) {
			this.value = this.max;
		} else if (this.value < this.min) {
			this.value = this.min;
		}
		this.token = this.formater.format(this.value);
	}

	@Override
	protected void focus(boolean forward) {
		this.characters.clear();
	}

	@Override
	protected boolean handleKeyDown(int keyDown) {
		switch (keyDown) {
			case KeyCodes.KEY_DOWN:
				if (this.value > 0) {
					this.setValue(--this.value);
				}
				break;
			case KeyCodes.KEY_UP:
				this.setValue(++this.value);
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	protected boolean handleKeyPress(char charPressed) {
		if (this.characters.size() >= this.maxLenght) {
			return false;
		}
		switch (charPressed) {
			case '0':
				if (this.characters.isEmpty()) {
					return true;
				}
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				this.characters.add(charPressed);
				StringBuilder sb = new StringBuilder();
				for (char c : this.characters) {
					sb.append(c);
				}
				int val = Integer.valueOf(sb.toString());
				if (val <= this.max && val >= this.min) {
					this.setValue(val);
				} else {
					this.characters.remove(this.characters.size() - 1);
					return false;
				}
				return true;
			default:
				return false;
		}
	}
}