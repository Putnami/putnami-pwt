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
package fr.putnami.pwt.core.inject.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fr.putnami.pwt.core.mvp.client.ViewPlace;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MvpDescritpion {
	Class<? extends AcceptsOneWidget> display() default AcceptsOneWidget.class;

	Class<? extends ViewPlace> defaultPlace() default ViewPlace.class;

	Class<? extends ViewPlace>[] activities() default {};
}
