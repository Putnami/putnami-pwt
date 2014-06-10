/**
 * This file is part of pwt-doc.
 *
 * pwt-doc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * pwt-doc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with pwt-doc.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.putnami.pwt.doc.client.page.form;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.putnami.pwt.core.editor.client.helper.MessageHelper;
import fr.putnami.pwt.core.editor.shared.constant.ValidationConstants;
import fr.putnami.pwt.core.model.client.model.Model;
import fr.putnami.pwt.core.widget.client.Form;
import fr.putnami.pwt.core.widget.client.NavSpy;
import fr.putnami.pwt.core.widget.client.binder.UiBinderLocalized;
import fr.putnami.pwt.core.widget.client.helper.DateParser;

public class FormInputControlsView extends Composite {

	public enum Gender {
		MALE,
		FEMALE,
		UNKNOWN
	}

	public static class Bean {
		public Gender gender = Gender.MALE;
		public String name = "John Doe";
		public String password = "secret";
		public String state = "Michigan";
		public String email = "john.doe@gmail.com";
		public boolean major = true;
		public boolean notMajor = false;
		public int age = Random.nextInt(100);
		public double height = Random.nextInt(1000000) / 100D;
		public Date birthdate = new DateParser("dd/MM/yyyy").parseOrNull("02/03/1985");
		public String remarks = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce lobortis elementum vestibulum. Aliquam luctus semper congue. Fusce placerat tempus lectus, et pulvinar elit aliquam eget. Suspendisse placerat vitae risus vitae sagittis. Suspendisse dignissim orci urna, in aliquam lectus pharetra eu. Donec velit elit, tincidunt semper mollis et, adipiscing vel dui. Morbi rhoncus dui sit amet libero gravida sagittis. Duis tincidunt luctus elit, ac cursus nisi tempus in. Fusce quis quam quam. Suspendisse hendrerit lobortis metus, non fermentum nibh tincidunt gravida.";

		public String mainGroup = "Friends";
		public List<String> groups = Lists.newArrayList("Friends", "Colleague");
	}

	public interface Constants extends ConstantsWithLookup, ValidationConstants {
		@DefaultStringValue("Mr.")
		String genderMaleEnum();

		@DefaultStringValue("Mrs.")
		String genderFemaleEnum();
	}

	public interface BeanModel extends Model<Bean> {

		Model<Bean> MODEL = GWT.create(BeanModel.class);
	}

	interface Binder extends UiBinderLocalized<Widget, FormInputControlsView> {

		Binder BINDER = GWT.create(Binder.class);
	}

	@UiField(provided = true)
	final NavSpy navSpy;

	@UiField(provided = true)
	final List<String> stateSuggestions = Lists.newArrayList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
			"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
			"Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
			"New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
			"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
	@UiField(provided = true)
	final List<Integer> ageList = Lists.newArrayList();
	@UiField(provided = true)
	final List<String> groups = Lists.newArrayList("Familly", "Friends", "Colleague", "Other");

	@UiField
	Form<Bean> editor;

	@UiConstructor
	public FormInputControlsView(NavSpy navSpy) {
		this.navSpy = navSpy;

		for (int i = 0; i < 100; i++) {
			ageList.add(Integer.valueOf(i));
		}

		initWidget(Binder.BINDER.createAndBindUi(this));
		navSpy.redraw();

		MessageHelper messageHelper = new MessageHelper((ConstantsWithLookup) GWT.create(Constants.class));
		editor.setMessageHelper(messageHelper);
		editor.initialize(BeanModel.MODEL);
		editor.edit(new Bean());
		editor.getDriver().setAutoFlush(true);

	}

}
