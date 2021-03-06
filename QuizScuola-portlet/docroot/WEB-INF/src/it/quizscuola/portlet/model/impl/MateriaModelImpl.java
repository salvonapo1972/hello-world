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

import it.quizscuola.portlet.model.Materia;
import it.quizscuola.portlet.model.MateriaModel;
import it.quizscuola.portlet.model.MateriaSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Materia service. Represents a row in the &quot;Quiz_Materia&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link it.quizscuola.portlet.model.MateriaModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MateriaImpl}.
 * </p>
 *
 * @author Salvatore
 * @see MateriaImpl
 * @see it.quizscuola.portlet.model.Materia
 * @see it.quizscuola.portlet.model.MateriaModel
 * @generated
 */
@JSON(strict = true)
public class MateriaModelImpl extends BaseModelImpl<Materia>
	implements MateriaModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a materia model instance should use the {@link it.quizscuola.portlet.model.Materia} interface instead.
	 */
	public static final String TABLE_NAME = "Quiz_Materia";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "idMateria", Types.BIGINT },
			{ "descMateria", Types.VARCHAR },
			{ "idClasse", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table Quiz_Materia (uuid_ VARCHAR(75) null,idMateria LONG not null primary key,descMateria VARCHAR(75) null,idClasse LONG,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Quiz_Materia";
	public static final String ORDER_BY_JPQL = " ORDER BY materia.descMateria ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Quiz_Materia.descMateria ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.it.quizscuola.portlet.model.Materia"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.it.quizscuola.portlet.model.Materia"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.it.quizscuola.portlet.model.Materia"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static long GROUPID_COLUMN_BITMASK = 2L;
	public static long UUID_COLUMN_BITMASK = 4L;
	public static long DESCMATERIA_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Materia toModel(MateriaSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Materia model = new MateriaImpl();

		model.setUuid(soapModel.getUuid());
		model.setIdMateria(soapModel.getIdMateria());
		model.setDescMateria(soapModel.getDescMateria());
		model.setIdClasse(soapModel.getIdClasse());
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
	public static List<Materia> toModels(MateriaSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Materia> models = new ArrayList<Materia>(soapModels.length);

		for (MateriaSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.it.quizscuola.portlet.model.Materia"));

	public MateriaModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _idMateria;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setIdMateria(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _idMateria;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Materia.class;
	}

	@Override
	public String getModelClassName() {
		return Materia.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("idMateria", getIdMateria());
		attributes.put("descMateria", getDescMateria());
		attributes.put("idClasse", getIdClasse());
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

		Long idMateria = (Long)attributes.get("idMateria");

		if (idMateria != null) {
			setIdMateria(idMateria);
		}

		String descMateria = (String)attributes.get("descMateria");

		if (descMateria != null) {
			setDescMateria(descMateria);
		}

		Long idClasse = (Long)attributes.get("idClasse");

		if (idClasse != null) {
			setIdClasse(idClasse);
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
	public long getIdMateria() {
		return _idMateria;
	}

	@Override
	public void setIdMateria(long idMateria) {
		_idMateria = idMateria;
	}

	@JSON
	@Override
	public String getDescMateria() {
		if (_descMateria == null) {
			return StringPool.BLANK;
		}
		else {
			return _descMateria;
		}
	}

	@Override
	public void setDescMateria(String descMateria) {
		_columnBitmask = -1L;

		_descMateria = descMateria;
	}

	@JSON
	@Override
	public long getIdClasse() {
		return _idClasse;
	}

	@Override
	public void setIdClasse(long idClasse) {
		_idClasse = idClasse;
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
				Materia.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Materia.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Materia toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Materia)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MateriaImpl materiaImpl = new MateriaImpl();

		materiaImpl.setUuid(getUuid());
		materiaImpl.setIdMateria(getIdMateria());
		materiaImpl.setDescMateria(getDescMateria());
		materiaImpl.setIdClasse(getIdClasse());
		materiaImpl.setGroupId(getGroupId());
		materiaImpl.setCompanyId(getCompanyId());
		materiaImpl.setUserId(getUserId());
		materiaImpl.setUserName(getUserName());
		materiaImpl.setCreateDate(getCreateDate());
		materiaImpl.setModifiedDate(getModifiedDate());

		materiaImpl.resetOriginalValues();

		return materiaImpl;
	}

	@Override
	public int compareTo(Materia materia) {
		int value = 0;

		value = getDescMateria().compareTo(materia.getDescMateria());

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

		if (!(obj instanceof Materia)) {
			return false;
		}

		Materia materia = (Materia)obj;

		long primaryKey = materia.getPrimaryKey();

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
		MateriaModelImpl materiaModelImpl = this;

		materiaModelImpl._originalUuid = materiaModelImpl._uuid;

		materiaModelImpl._originalGroupId = materiaModelImpl._groupId;

		materiaModelImpl._setOriginalGroupId = false;

		materiaModelImpl._originalCompanyId = materiaModelImpl._companyId;

		materiaModelImpl._setOriginalCompanyId = false;

		materiaModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Materia> toCacheModel() {
		MateriaCacheModel materiaCacheModel = new MateriaCacheModel();

		materiaCacheModel.uuid = getUuid();

		String uuid = materiaCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			materiaCacheModel.uuid = null;
		}

		materiaCacheModel.idMateria = getIdMateria();

		materiaCacheModel.descMateria = getDescMateria();

		String descMateria = materiaCacheModel.descMateria;

		if ((descMateria != null) && (descMateria.length() == 0)) {
			materiaCacheModel.descMateria = null;
		}

		materiaCacheModel.idClasse = getIdClasse();

		materiaCacheModel.groupId = getGroupId();

		materiaCacheModel.companyId = getCompanyId();

		materiaCacheModel.userId = getUserId();

		materiaCacheModel.userName = getUserName();

		String userName = materiaCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			materiaCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			materiaCacheModel.createDate = createDate.getTime();
		}
		else {
			materiaCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			materiaCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			materiaCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return materiaCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", idMateria=");
		sb.append(getIdMateria());
		sb.append(", descMateria=");
		sb.append(getDescMateria());
		sb.append(", idClasse=");
		sb.append(getIdClasse());
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
		sb.append("it.quizscuola.portlet.model.Materia");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>idMateria</column-name><column-value><![CDATA[");
		sb.append(getIdMateria());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>descMateria</column-name><column-value><![CDATA[");
		sb.append(getDescMateria());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>idClasse</column-name><column-value><![CDATA[");
		sb.append(getIdClasse());
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

	private static ClassLoader _classLoader = Materia.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Materia.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _idMateria;
	private String _descMateria;
	private long _idClasse;
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
	private Materia _escapedModel;
}