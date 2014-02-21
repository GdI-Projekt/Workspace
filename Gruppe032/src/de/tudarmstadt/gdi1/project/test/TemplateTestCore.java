package de.tudarmstadt.gdi1.project.test;

import de.tudarmstadt.gdi1.project.Factory;

public class TemplateTestCore {

	public static String FACTORYPATH = "de.tudarmstadt.gdi1.project.FactoryIM";

	private static Factory f = null;

	public static Factory getFactory() {
		if (f == null) {
			try {
				f = ((Factory) Class.forName(FACTORYPATH).newInstance());
			} catch (ClassNotFoundException e) {
				System.err.println("Factory instance not found.");
			} catch (IllegalAccessException e) {
				System.err.println("Factory instance could not be accessed.");
			} catch (InstantiationException e) {
				System.err.println("Factory could not be instantiated.");
			}
		}

		return f;
	}

}
