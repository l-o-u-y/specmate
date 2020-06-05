package com.specmate.emfrest.crud;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.specmate.auth.api.IAuthenticationService;
import com.specmate.common.exception.SpecmateException;
import com.specmate.emfrest.api.IRestService;
import com.specmate.emfrest.api.RestServiceBase;
import com.specmate.model.base.IContainer;
import com.specmate.model.base.IModel;
import com.specmate.model.processes.Process;
import com.specmate.model.requirements.CEGModel;
import com.specmate.rest.RestResult;

@Component(immediate = true, service = IRestService.class)
public class ImageService extends RestServiceBase {

	@Override
	public String getServiceName() {
		return "image";
	}

	@Override
	public boolean canGet(Object target) {
		return target instanceof CEGModel || target instanceof Process;
	}

	@Override
	public RestResult<String> get(Object target, MultivaluedMap<String, String> queryParams, String token)
			throws SpecmateException {
		int width = Integer.parseInt(queryParams.getFirst("width"));
		IContainer container = (IContainer) target;
		IModel model = (IModel) target;
		//String id = "image-" + container.getId();
		String id = "255";
		String script = 
				//"<div id='" + id + "'> " 
				//+ "</div> " 
				//+ "<script> " 
				//+ 
				"alert('Hi'); "
				+ "let image = atob('" + model.getImage() + "');"
				+ "document.getElementById('255').innerHTML = image; "
				+ "document.getElementById('255').children[0].style['min-width'] = 100px; "
		/*
		 * + "var image = atob('" + model.getImage() + "') " +
		 * "document.getElementById('" + id + "').innerHTML = image; " + "var width = "
		 * + width + "; " + "var maxWidthSVG = document.getElementById('" + id +
		 * "').children[0].style['min-width']; " +
		 * "maxWidthSVG = maxWidthSVG.substring(0, maxWidthSVG.length - 2); " +
		 * "var factor = 0.0 + width / maxWidthSVG; " + "document.getElementById('" + id
		 * + "').children[0].style.transform = 'scale(' + factor + ')'; " +
		 * "document.getElementById('" + id +
		 * "').children[0].style['transform-origin'] = 'left top'; " +
		 * "document.getElementById('" + id + "').style.width = width + 'px'; "
		 */
				//+ "</script> "
				;
		

		return new RestResult<>(Response.Status.OK, "{\"image\": \"" + model.getImage() + "\"}");
		//return new RestResult<>(Response.Status.OK, "{\"image\": \"" + script + "\"}");
	}
}
