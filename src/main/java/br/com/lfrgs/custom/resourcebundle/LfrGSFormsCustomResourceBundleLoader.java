package br.com.lfrgs.custom.resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

/**
 * @author Jordana Morais, Rafael Oliveira
 */
@Component(
		immediate = true, 
		property = { 
			"bundle.symbolic.name=com.liferay.dynamic.data.mapping.lang",
			"resource.bundle.base.name=content.Language", 
			"servlet.context.name=dynamic-data-mapping-lang",
			"service.ranking:Integer=100" }, 
		service = ResourceBundleLoader.class)
public class LfrgGSFormsCustomResourceBundleLoader implements ResourceBundleLoader {

	private AggregateResourceBundleLoader resourceBundleLoader;

	@Override
	public ResourceBundle loadResourceBundle(Locale locale) {

		return resourceBundleLoader.loadResourceBundle(locale);
	}


	@Reference(target = "(bundle.symbolic.name=com.liferay.dynamic.data.mapping.lang)")
	public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {

		ClassLoader classLoader = this.getClass().getClassLoader();

		ClassResourceBundleLoader classResourceBundleLoader = new ClassResourceBundleLoader("content.Language",
				classLoader);
		CacheResourceBundleLoader cacheResourceBundleLoader = new CacheResourceBundleLoader(classResourceBundleLoader);

		this.resourceBundleLoader = new AggregateResourceBundleLoader(cacheResourceBundleLoader, resourceBundleLoader);
	}

}