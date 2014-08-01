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
package fr.putnami.pwt.core.error.client;

public interface ErrorHandler {

	int DEFAULT_PRIORITY = 0;
	int LOWER_PRIORITY = Integer.MIN_VALUE;
	int LOW_PRIORITY = Integer.valueOf(-1000);
	int HIGH_PRIORITY = Integer.valueOf(1000);
	int HIGHER_PRIORITY = Integer.MAX_VALUE;

	boolean handle(Throwable error);

	int getPriority();

}
