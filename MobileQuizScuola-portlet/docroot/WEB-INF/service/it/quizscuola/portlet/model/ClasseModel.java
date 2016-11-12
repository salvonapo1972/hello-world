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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Classe service. Represents a row in the &quot;quiz_Classe&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link it.quizscuola.portlet.model.impl.ClasseModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link it.quizscuola.portlet.model.impl.ClasseImpl}.
 * </p>
 *
 * @author Salvatore
 * @see Classe
 * @see it.quizscuola.portlet.model.impl.ClasseImpl
 * @see it.quizscuola.portlet.model.impl.ClasseModelImpl
 * @generated
 */
public interface ClasseModel extends BaseModel<Classe>, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a classe model instance should use the {@link Classe} interface instead.
	 */

	/**
	 * Returns the primary key of this classe.
	 *
	 * @return the primary key of this classe
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this classe.
	 *
	 * @param primaryKey the primary key of this classe
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this classe.
	 *
	 * @return the uuid of this classe
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this classe.
	 *
	 * @param uuid the uuid of this classe
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the ID of this classe.
	 *
	 * @return the ID of this classe
	 */
	public long getId();

	/**
	 * Sets the ID of this classe.
	 *
	 * @param id the ID of this classe
	 */
	public void setId(long id);

	/**
	 * Returns the desc classe of this classe.
	 *
	 * @return the desc classe of this classe
	 */
	@AutoEscape
	public String getDescClasse();

	/**
	 * Sets the desc classe of this classe.
	 *
	 * @param descClasse the desc classe of this classe
	 */
	public void setDescClasse(String descClasse);

	/**
	 * Returns the group ID of this classe.
	 *
	 * @return the group ID of this classe
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this classe.
	 *
	 * @param groupId the group ID of this classe
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this classe.
	 *
	 * @return the company ID of this classe
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this classe.
	 *
	 * @param companyId the company ID of this classe
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this classe.
	 *
	 * @return the user ID of this classe
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this classe.
	 *
	 * @param userId the user ID of this classe
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this classe.
	 *
	 * @return the user uuid of this classe
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this classe.
	 *
	 * @param userUuid the user uuid of this classe
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this classe.
	 *
	 * @return the user name of this classe
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this classe.
	 *
	 * @param userName the user name of this classe
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this classe.
	 *
	 * @return the create date of this classe
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this classe.
	 *
	 * @param createDate the create date of this classe
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this classe.
	 *
	 * @return the modified date of this classe
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this classe.
	 *
	 * @param modifiedDate the modified date of this classe
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

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
	public int compareTo(Classe classe);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Classe> toCacheModel();

	@Override
	public Classe toEscapedModel();

	@Override
	public Classe toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}