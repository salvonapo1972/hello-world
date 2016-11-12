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

package it.quizscuola.portlet.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import it.quizscuola.portlet.model.Domanda;
import it.quizscuola.portlet.model.DomandaModel;
import it.quizscuola.portlet.model.DomandaSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Domanda service. Represents a row in the &quot;Quiz_Domanda&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link it.quizscuola.portlet.model.DomandaModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DomandaImpl}.
 * </p>
 *
 * @author Salvatore
 * @see DomandaImpl
 * @see it.quizscuola.portlet.model.Domanda
 * @see it.quizscuola.portlet.model.DomandaModel
 * @generated
 */
@JSON(strict = true)
public class DomandaModelImpl extends BaseModelImpl<Domanda>
	implements DomandaModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a domanda model instance should use the {@link it.quizscuola.portlet.model.Domanda} interface instead.
	 */
	public static final String TABLE_NAME = "Quiz_Domanda";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "idDomanda", Types.BIGINT },
			{ "descDomanda", Types.VARCHAR },
			{ "idArgomento", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table Quiz_Domanda (uuid_ VARCHAR(75) null,idDomanda LONG not null primary key,descDomanda VARCHAR(75) null,idArgomento LONG,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Quiz_Domanda";
	public static final String ORDER_BY_JPQL = " ORDER BY domanda.descDomanda ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Quiz_Domanda.descDomanda ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.it.quizscuola.portlet.model.Domanda"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.it.quizscuola.portlet.model.Domanda"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.it.quizscuola.portlet.model.Domanda"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static long GROUPID_COLUMN_BITMASK = 2L;
	public static long UUID_COLUMN_BITMASK = 4L;
	public static long DESCDOMANDA_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Domanda toModel(DomandaSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Domanda model = new DomandaImpl();

		model.setUuid(soapModel.getUuid());
		model.setIdDomanda(soapModel.getIdDomanda());
		model.setDescDomanda(soapModel.getDescDomanda());
		model.setIdArgomento(soapModel.getIdArgomento());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Domanda> toModels(DomandaSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Domanda> models = new ArrayList<Domanda>(soapModels.length);

		for (DomandaSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.it.quizscuola.portlet.model.Domanda"));

	public DomandaModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _idDomanda;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setIdDomanda(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _idDomanda;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Domanda.class;
	}

	@Override
	public String getModelClassName() {
		return Domanda.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("idDomanda", getIdDomanda());
		attributes.put("descDomanda", getDescDomanda());
		attributes.put("idArgomento", getIdArgomento());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long idDomanda = (Long)attributes.get("idDomanda");

		if (idDomanda != null) {
			setIdDomanda(idDomanda);
		}

		String descDomanda = (String)attributes.get("descDomanda");

		if (descDomanda != null) {
			setDescDomanda(descDomanda);
		}

		Long idArgomento = (Long)attributes.get("idArgomento");

		if (idArgomento != null) {
			setIdArgomento(idArgomento);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getIdDomanda() {
		return _idDomanda;
	}

	@Override
	public void setIdDomanda(long idDomanda) {
		_idDomanda = idDomanda;
	}

	@JSON
	@Override
	public String getDescDomanda() {
		if (_descDomanda == null) {
			return StringPool.BLANK;
		}
		else {
			return _descDomanda;
		}
	}

	@Override
	public void setDescDomanda(String descDomanda) {
		_columnBitmask = -1L;

		_descDomanda = descDomanda;
	}

	@JSON
	@Override
	public long getIdArgomento() {
		return _idArgomento;
	}

	@Override
	public void setIdArgomento(long idArgomento) {
		_idArgomento = idArgomento;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Domanda.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Domanda.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Domanda toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Domanda)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DomandaImpl domandaImpl = new DomandaImpl();

		domandaImpl.setUuid(getUuid());
		domandaImpl.setIdDomanda(getIdDomanda());
		domandaImpl.setDescDomanda(getDescDomanda());
		domandaImpl.setIdArgomento(getIdArgomento());
		domandaImpl.setGroupId(getGroupId());
		domandaImpl.setCompanyId(getCompanyId());
		domandaImpl.setUserId(getUserId());
		domandaImpl.setUserName(getUserName());
		domandaImpl.setCreateDate(getCreateDate());
		domandaImpl.setModifiedDate(getModifiedDate());

		domandaImpl.resetOriginalValues();

		return domandaImpl;
	}

	@Override
	public int compareTo(Domanda domanda) {
		int value = 0;

		value = getDescDomanda().compareTo(domanda.getDescDomanda());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Domanda)) {
			return false;
		}

		Domanda domanda = (Domanda)obj;

		long primaryKey = domanda.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		DomandaModelImpl domandaModelImpl = this;

		domandaModelImpl._originalUuid = domandaModelImpl._uuid;

		domandaModelImpl._originalGroupId = domandaModelImpl._groupId;

		domandaModelImpl._setOriginalGroupId = false;

		domandaModelImpl._originalCompanyId = domandaModelImpl._companyId;

		domandaModelImpl._setOriginalCompanyId = false;

		domandaModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Domanda> toCacheModel() {
		DomandaCacheModel domandaCacheModel = new DomandaCacheModel();

		domandaCacheModel.uuid = getUuid();

		String uuid = domandaCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			domandaCacheModel.uuid = null;
		}

		domandaCacheModel.idDomanda = getIdDomanda();

		domandaCacheModel.descDomanda = getDescDomanda();

		String descDomanda = domandaCacheModel.descDomanda;

		if ((descDomanda != null) && (descDomanda.length() == 0)) {
			domandaCacheModel.descDomanda = null;
		}

		domandaCacheModel.idArgomento = getIdArgomento();

		domandaCacheModel.groupId = getGroupId();

		domandaCacheModel.companyId = getCompanyId();

		domandaCacheModel.userId = getUserId();

		domandaCacheModel.userName = getUserName();

		String userName = domandaCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			domandaCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			domandaCacheModel.createDate = createDate.getTime();
		}
		else {
			domandaCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			domandaCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			domandaCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return domandaCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", idDomanda=");
		sb.append(getIdDomanda());
		sb.append(", descDomanda=");
		sb.append(getDescDomanda());
		sb.append(", idArgomento=");
		sb.append(getIdArgomento());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("it.quizscuola.portlet.model.Domanda");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>idDomanda</column-name><column-value><![CDATA[");
		sb.append(getIdDomanda());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>descDomanda</column-name><column-value><![CDATA[");
		sb.append(getDescDomanda());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>idArgomento</column-name><column-value><![CDATA[");
		sb.append(getIdArgomento());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Domanda.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Domanda.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _idDomanda;
	private String _descDomanda;
	private long _idArgomento;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _columnBitmask;
	private Domanda _escapedModel;
}