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
package fr.putnami.pwt.core.serialization.ppc.shared.util;

public final class PpcUtils {
	public static final String SEPARATOR = "|";
	public static final String SEPARATOR_STRINGS = "--";
	public static final String SEPARATOR_TYPE_REF = "@";

	private PpcUtils() {
	}

	public static String encodeString(String value) {
		return value;
	}

	public static String decodeString(String value) {
		return value;
	}

	public static String extractClassFromRef(String ref) {
		int atIndex = ref.indexOf(SEPARATOR_TYPE_REF);
		if (atIndex != -1) {
			return ref.substring(0, atIndex);
		}
		return ref;
	}

	public static Integer extractInstanceIdFromRef(String ref) {
		int atIndex = ref.indexOf(SEPARATOR_TYPE_REF);
		if (atIndex != -1) {
			return Integer.parseInt(ref.substring(atIndex + 1));
		}
		return null;
	}

}