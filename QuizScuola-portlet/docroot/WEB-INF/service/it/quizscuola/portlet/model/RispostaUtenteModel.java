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

package it.quizscuola.portlet.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the RispostaUtente service. Represents a row in the &quot;Quiz_RispostaUtente&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link it.quizscuola.portlet.model.impl.RispostaUtenteModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link it.quizscuola.portlet.model.impl.RispostaUtenteImpl}.
 * </p>
 *
 * @author Salvatore
 * @see RispostaUtente
 * @see it.quizscuola.portlet.model.impl.RispostaUtenteImpl
 * @see it.quizscuola.portlet.model.impl.RispostaUtenteModelImpl
 * @generated
 */
public interface RispostaUtenteModel extends BaseModel<RispostaUtente> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a risposta utente model instance should use the {@link RispostaUtente} interface instead.
	 */

	/**
	 * Returns the primary key of this risposta utente.
	 *
	 * @return the primary key of this risposta utente
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this risposta utente.
	 *
	 * @param primaryKey the primary key of this risposta utente
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this risposta utente.
	 *
	 * @return the uuid of this risposta utente
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this risposta utente.
	 *
	 * @param uuid the uuid of this risposta utente
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the id risposta utente of this risposta utente.
	 *
	 * @return the id risposta utente of this risposta utente
	 */
	public long getIdRispostaUtente();

	/**
	 * Sets the id risposta utente of this risposta utente.
	 *
	 * @param idRispostaUtente the id risposta utente of this risposta utente
	 */
	public void setIdRispostaUtente(long idRispostaUtente);

	/**
	 * Returns the id risposta of this risposta utente.
	 *
	 * @return the id risposta of this risposta utente
	 */
	public long getIdRisposta();

	/**
	 * Sets the id risposta of this risposta utente.
	 *
	 * @param idRisposta the id risposta of this risposta utente
	 */
	public void setIdRisposta(long idRisposta);

	/**
	 * Returns the id utente of this risposta utente.
	 *
	 * @return the id utente of this risposta utente
	 */
	public long getIdUtente();

	/**
	 * Sets the id utente of this risposta utente.
	 *
	 * @param idUtente the id utente of this risposta utente
	 */
	public void setIdUtente(long idUtente);

	/**
	 * Returns the data risposta of this risposta utente.
	 *
	 * @return the data risposta of this risposta utente
	 */
	public Date getDataRisposta();

	/**
	 * Sets the data risposta of this risposta utente.
	 *
	 * @param dataRisposta the data risposta of this risposta utente
	 */
	public void setDataRisposta(Date dataRisposta);

	/**
	 * Returns the id domanda of this risposta utente.
	 *
	 * @return the id domanda of this risposta utente
	 */
	public long getIdDomanda();

	/**
	 * Sets the id domanda of this risposta utente.
	 *
	 * @param idDomanda the id domanda of this risposta utente
	 */
	public void setIdDomanda(long idDomanda);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(RispostaUtente rispostaUtente);

	@Override
	public int hashCode();

	@Override
	public CacheModel<RispostaUtente> toCacheModel();

	@Override
	public RispostaUtente toEscapedModel();

	@Override
	public RispostaUtente toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}