/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package it.quizscuola.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for RispostaUtente. This utility wraps
 * {@link it.quizscuola.portlet.service.impl.RispostaUtenteServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Salvatore
 * @see RispostaUtenteService
 * @see it.quizscuola.portlet.service.base.RispostaUtenteServiceBaseImpl
 * @see it.quizscuola.portlet.service.impl.RispostaUtenteServiceImpl
 * @generated
 */
public class RispostaUtenteServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link it.quizscuola.portlet.service.impl.RispostaUtenteServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static it.quizscuola.portlet.model.RispostaUtente addRispostaUtente(
		com.liferay.portal.service.ServiceContext ctx, long idRisposta,
		long idDomanda) {
		return getService().addRispostaUtente(ctx, idRisposta, idDomanda);
	}

	public static com.liferay.portal.kernel.json.JSONObject addJSONRispostaUtente(
		long idUtente, long idRisposta, long idDomanda) {
		return getService()
				   .addJSONRispostaUtente(idUtente, idRisposta, idDomanda);
	}

	public static com.liferay.portal.kernel.json.JSONObject removeRispostaUtente(
		long idUtente)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().removeRispostaUtente(idUtente);
	}

	public static it.quizscuola.portlet.model.RispostaUtente getRispostaUtente(
		long idDomanda, long idUtente) {
		return getService().getRispostaUtente(idDomanda, idUtente);
	}

	public static void clearService() {
		_service = null;
	}

	public static RispostaUtenteService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					RispostaUtenteService.class.getName());

			if (invokableService instanceof RispostaUtenteService) {
				_service = (RispostaUtenteService)invokableService;
			}
			else {
				_service = new RispostaUtenteServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(RispostaUtenteServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(RispostaUtenteService service) {
	}

	private static RispostaUtenteService _service;
}