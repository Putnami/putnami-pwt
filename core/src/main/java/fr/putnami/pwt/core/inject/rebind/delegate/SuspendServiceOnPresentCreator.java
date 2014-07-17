package fr.putnami.pwt.core.inject.rebind.delegate;

import java.util.List;

import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import fr.putnami.pwt.core.inject.rebind.InjectorCreatorDelegate;
import fr.putnami.pwt.core.service.client.CallbackAdapter;
import fr.putnami.pwt.core.service.client.CommandController;
import fr.putnami.pwt.core.service.shared.domain.CommandResponse;

public class SuspendServiceOnPresentCreator extends InjectorCreatorDelegate {

	private final String injectorName;

	public SuspendServiceOnPresentCreator(String injectorName) {
		this.injectorName = injectorName;

	}

	@Override
	public void initComposer(ClassSourceFileComposerFactory composerFactory) {
		composerFactory.addImport(CommandController.class.getName());
		composerFactory.addImport(CallbackAdapter.class.getName());
		composerFactory.addImport(List.class.getName());
		composerFactory.addImport(CommandResponse.class.getName());
	}

	@Override
	public void writeBeforePresent(SourceWriter srcWriter) {
		srcWriter.println("CommandController commandController = CommandController.get();");
		srcWriter.println("boolean isSuspended = commandController.isSuspended();");
		srcWriter.println("commandController.setSuspended(true);");

	}

	@Override
	public void writeAfterPresent(SourceWriter srcWriter) {
		srcWriter.println("commandController.flush(new CallbackAdapter<List<CommandResponse>>() {");
		srcWriter.println("@Override");
		srcWriter.println("public void onSuccess(List<CommandResponse> result) {");
		srcWriter.println("displayer.setWidget(%s.this);", injectorName);
		srcWriter.println("};");
		srcWriter.println("});");
		srcWriter.println("commandController.setSuspended(isSuspended);");
	}
}