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

package it.quizscuola.portlet.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import it.quizscuola.portlet.NoSuchRispostaException;
import it.quizscuola.portlet.model.Risposta;
import it.quizscuola.portlet.model.impl.RispostaImpl;
import it.quizscuola.portlet.model.impl.RispostaModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the risposta service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Salvatore
 * @see RispostaPersistence
 * @see RispostaUtil
 * @generated
 */
public class RispostaPersistenceImpl extends BasePersistenceImpl<Risposta>
	implements RispostaPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RispostaUtil} to access the risposta persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RispostaImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			RispostaModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the rispostas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rispostas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @return the range of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rispostas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Risposta> list = (List<Risposta>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Risposta risposta : list) {
				if (!Validator.equals(uuid, risposta.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RISPOSTA_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RispostaModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Risposta>(list);
				}
				else {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first risposta in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByUuid_First(uuid, orderByComparator);

		if (risposta != null) {
			return risposta;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRispostaException(msg.toString());
	}

	/**
	 * Returns the first risposta in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<Risposta> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last risposta in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByUuid_Last(uuid, orderByComparator);

		if (risposta != null) {
			return risposta;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRispostaException(msg.toString());
	}

	/**
	 * Returns the last risposta in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Risposta> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rispostas before and after the current risposta in the ordered set where uuid = &#63;.
	 *
	 * @param idRisposta the primary key of the current risposta
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta[] findByUuid_PrevAndNext(long idRisposta, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = findByPrimaryKey(idRisposta);

		Session session = null;

		try {
			session = openSession();

			Risposta[] array = new RispostaImpl[3];

			array[0] = getByUuid_PrevAndNext(session, risposta, uuid,
					orderByComparator, true);

			array[1] = risposta;

			array[2] = getByUuid_PrevAndNext(session, risposta, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Risposta getByUuid_PrevAndNext(Session session,
		Risposta risposta, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RISPOSTA_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RispostaModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(risposta);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Risposta> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rispostas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (Risposta risposta : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(risposta);
		}
	}

	/**
	 * Returns the number of rispostas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RISPOSTA_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "risposta.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "risposta.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(risposta.uuid IS NULL OR risposta.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			RispostaModelImpl.UUID_COLUMN_BITMASK |
			RispostaModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the risposta where uuid = &#63; and groupId = &#63; or throws a {@link it.quizscuola.portlet.NoSuchRispostaException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByUUID_G(String uuid, long groupId)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByUUID_G(uuid, groupId);

		if (risposta == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRispostaException(msg.toString());
		}

		return risposta;
	}

	/**
	 * Returns the risposta where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the risposta where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Risposta) {
			Risposta risposta = (Risposta)result;

			if (!Validator.equals(uuid, risposta.getUuid()) ||
					(groupId != risposta.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_RISPOSTA_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Risposta> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Risposta risposta = list.get(0);

					result = risposta;

					cacheResult(risposta);

					if ((risposta.getUuid() == null) ||
							!risposta.getUuid().equals(uuid) ||
							(risposta.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, risposta);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Risposta)result;
		}
	}

	/**
	 * Removes the risposta where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the risposta that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta removeByUUID_G(String uuid, long groupId)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = findByUUID_G(uuid, groupId);

		return remove(risposta);
	}

	/**
	 * Returns the number of rispostas where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RISPOSTA_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "risposta.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "risposta.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(risposta.uuid IS NULL OR risposta.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "risposta.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			RispostaModelImpl.UUID_COLUMN_BITMASK |
			RispostaModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the rispostas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rispostas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @return the range of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByUuid_C(String uuid, long companyId, int start,
		int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rispostas where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<Risposta> list = (List<Risposta>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Risposta risposta : list) {
				if (!Validator.equals(uuid, risposta.getUuid()) ||
						(companyId != risposta.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RISPOSTA_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RispostaModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Risposta>(list);
				}
				else {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first risposta in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (risposta != null) {
			return risposta;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRispostaException(msg.toString());
	}

	/**
	 * Returns the first risposta in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Risposta> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last risposta in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (risposta != null) {
			return risposta;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRispostaException(msg.toString());
	}

	/**
	 * Returns the last risposta in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Risposta> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rispostas before and after the current risposta in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param idRisposta the primary key of the current risposta
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta[] findByUuid_C_PrevAndNext(long idRisposta, String uuid,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = findByPrimaryKey(idRisposta);

		Session session = null;

		try {
			session = openSession();

			Risposta[] array = new RispostaImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, risposta, uuid,
					companyId, orderByComparator, true);

			array[1] = risposta;

			array[2] = getByUuid_C_PrevAndNext(session, risposta, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Risposta getByUuid_C_PrevAndNext(Session session,
		Risposta risposta, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RISPOSTA_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RispostaModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(risposta);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Risposta> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rispostas where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (Risposta risposta : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(risposta);
		}
	}

	/**
	 * Returns the number of rispostas where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RISPOSTA_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "risposta.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "risposta.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(risposta.uuid IS NULL OR risposta.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "risposta.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DOMANDA = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDomanda",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOMANDA =
		new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDomanda",
			new String[] { Long.class.getName() },
			RispostaModelImpl.IDDOMANDA_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOMANDA = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDomanda",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the rispostas where idDomanda = &#63;.
	 *
	 * @param idDomanda the id domanda
	 * @return the matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByDomanda(long idDomanda)
		throws SystemException {
		return findByDomanda(idDomanda, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the rispostas where idDomanda = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param idDomanda the id domanda
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @return the range of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByDomanda(long idDomanda, int start, int end)
		throws SystemException {
		return findByDomanda(idDomanda, start, end, null);
	}

	/**
	 * Returns an ordered range of all the rispostas where idDomanda = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param idDomanda the id domanda
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findByDomanda(long idDomanda, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOMANDA;
			finderArgs = new Object[] { idDomanda };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DOMANDA;
			finderArgs = new Object[] { idDomanda, start, end, orderByComparator };
		}

		List<Risposta> list = (List<Risposta>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Risposta risposta : list) {
				if ((idDomanda != risposta.getIdDomanda())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RISPOSTA_WHERE);

			query.append(_FINDER_COLUMN_DOMANDA_IDDOMANDA_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RispostaModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(idDomanda);

				if (!pagination) {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Risposta>(list);
				}
				else {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first risposta in the ordered set where idDomanda = &#63;.
	 *
	 * @param idDomanda the id domanda
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByDomanda_First(long idDomanda,
		OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByDomanda_First(idDomanda, orderByComparator);

		if (risposta != null) {
			return risposta;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("idDomanda=");
		msg.append(idDomanda);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRispostaException(msg.toString());
	}

	/**
	 * Returns the first risposta in the ordered set where idDomanda = &#63;.
	 *
	 * @param idDomanda the id domanda
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByDomanda_First(long idDomanda,
		OrderByComparator orderByComparator) throws SystemException {
		List<Risposta> list = findByDomanda(idDomanda, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last risposta in the ordered set where idDomanda = &#63;.
	 *
	 * @param idDomanda the id domanda
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByDomanda_Last(long idDomanda,
		OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByDomanda_Last(idDomanda, orderByComparator);

		if (risposta != null) {
			return risposta;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("idDomanda=");
		msg.append(idDomanda);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRispostaException(msg.toString());
	}

	/**
	 * Returns the last risposta in the ordered set where idDomanda = &#63;.
	 *
	 * @param idDomanda the id domanda
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByDomanda_Last(long idDomanda,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDomanda(idDomanda);

		if (count == 0) {
			return null;
		}

		List<Risposta> list = findByDomanda(idDomanda, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rispostas before and after the current risposta in the ordered set where idDomanda = &#63;.
	 *
	 * @param idRisposta the primary key of the current risposta
	 * @param idDomanda the id domanda
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta[] findByDomanda_PrevAndNext(long idRisposta,
		long idDomanda, OrderByComparator orderByComparator)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = findByPrimaryKey(idRisposta);

		Session session = null;

		try {
			session = openSession();

			Risposta[] array = new RispostaImpl[3];

			array[0] = getByDomanda_PrevAndNext(session, risposta, idDomanda,
					orderByComparator, true);

			array[1] = risposta;

			array[2] = getByDomanda_PrevAndNext(session, risposta, idDomanda,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Risposta getByDomanda_PrevAndNext(Session session,
		Risposta risposta, long idDomanda, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RISPOSTA_WHERE);

		query.append(_FINDER_COLUMN_DOMANDA_IDDOMANDA_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(RispostaModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(idDomanda);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(risposta);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Risposta> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rispostas where idDomanda = &#63; from the database.
	 *
	 * @param idDomanda the id domanda
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByDomanda(long idDomanda) throws SystemException {
		for (Risposta risposta : findByDomanda(idDomanda, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(risposta);
		}
	}

	/**
	 * Returns the number of rispostas where idDomanda = &#63;.
	 *
	 * @param idDomanda the id domanda
	 * @return the number of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByDomanda(long idDomanda) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DOMANDA;

		Object[] finderArgs = new Object[] { idDomanda };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RISPOSTA_WHERE);

			query.append(_FINDER_COLUMN_DOMANDA_IDDOMANDA_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(idDomanda);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_DOMANDA_IDDOMANDA_2 = "risposta.idDomanda = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, RispostaImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDomanda_RispEsatta",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			RispostaModelImpl.IDDOMANDA_COLUMN_BITMASK |
			RispostaModelImpl.ESATTA_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DOMANDA_RISPESATTA = new FinderPath(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByDomanda_RispEsatta",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns the risposta where idDomanda = &#63; and esatta = &#63; or throws a {@link it.quizscuola.portlet.NoSuchRispostaException} if it could not be found.
	 *
	 * @param idDomanda the id domanda
	 * @param esatta the esatta
	 * @return the matching risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByDomanda_RispEsatta(long idDomanda, boolean esatta)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByDomanda_RispEsatta(idDomanda, esatta);

		if (risposta == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("idDomanda=");
			msg.append(idDomanda);

			msg.append(", esatta=");
			msg.append(esatta);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRispostaException(msg.toString());
		}

		return risposta;
	}

	/**
	 * Returns the risposta where idDomanda = &#63; and esatta = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param idDomanda the id domanda
	 * @param esatta the esatta
	 * @return the matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByDomanda_RispEsatta(long idDomanda, boolean esatta)
		throws SystemException {
		return fetchByDomanda_RispEsatta(idDomanda, esatta, true);
	}

	/**
	 * Returns the risposta where idDomanda = &#63; and esatta = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param idDomanda the id domanda
	 * @param esatta the esatta
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching risposta, or <code>null</code> if a matching risposta could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByDomanda_RispEsatta(long idDomanda, boolean esatta,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { idDomanda, esatta };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
					finderArgs, this);
		}

		if (result instanceof Risposta) {
			Risposta risposta = (Risposta)result;

			if ((idDomanda != risposta.getIdDomanda()) ||
					(esatta != risposta.getEsatta())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_RISPOSTA_WHERE);

			query.append(_FINDER_COLUMN_DOMANDA_RISPESATTA_IDDOMANDA_2);

			query.append(_FINDER_COLUMN_DOMANDA_RISPESATTA_ESATTA_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(idDomanda);

				qPos.add(esatta);

				List<Risposta> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"RispostaPersistenceImpl.fetchByDomanda_RispEsatta(long, boolean, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Risposta risposta = list.get(0);

					result = risposta;

					cacheResult(risposta);

					if ((risposta.getIdDomanda() != idDomanda) ||
							(risposta.getEsatta() != esatta)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
							finderArgs, risposta);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Risposta)result;
		}
	}

	/**
	 * Removes the risposta where idDomanda = &#63; and esatta = &#63; from the database.
	 *
	 * @param idDomanda the id domanda
	 * @param esatta the esatta
	 * @return the risposta that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta removeByDomanda_RispEsatta(long idDomanda, boolean esatta)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = findByDomanda_RispEsatta(idDomanda, esatta);

		return remove(risposta);
	}

	/**
	 * Returns the number of rispostas where idDomanda = &#63; and esatta = &#63;.
	 *
	 * @param idDomanda the id domanda
	 * @param esatta the esatta
	 * @return the number of matching rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByDomanda_RispEsatta(long idDomanda, boolean esatta)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DOMANDA_RISPESATTA;

		Object[] finderArgs = new Object[] { idDomanda, esatta };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RISPOSTA_WHERE);

			query.append(_FINDER_COLUMN_DOMANDA_RISPESATTA_IDDOMANDA_2);

			query.append(_FINDER_COLUMN_DOMANDA_RISPESATTA_ESATTA_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(idDomanda);

				qPos.add(esatta);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_DOMANDA_RISPESATTA_IDDOMANDA_2 = "risposta.idDomanda = ? AND ";
	private static final String _FINDER_COLUMN_DOMANDA_RISPESATTA_ESATTA_2 = "risposta.esatta = ?";

	public RispostaPersistenceImpl() {
		setModelClass(Risposta.class);
	}

	/**
	 * Caches the risposta in the entity cache if it is enabled.
	 *
	 * @param risposta the risposta
	 */
	@Override
	public void cacheResult(Risposta risposta) {
		EntityCacheUtil.putResult(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaImpl.class, risposta.getPrimaryKey(), risposta);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { risposta.getUuid(), risposta.getGroupId() }, risposta);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
			new Object[] { risposta.getIdDomanda(), risposta.getEsatta() },
			risposta);

		risposta.resetOriginalValues();
	}

	/**
	 * Caches the rispostas in the entity cache if it is enabled.
	 *
	 * @param rispostas the rispostas
	 */
	@Override
	public void cacheResult(List<Risposta> rispostas) {
		for (Risposta risposta : rispostas) {
			if (EntityCacheUtil.getResult(
						RispostaModelImpl.ENTITY_CACHE_ENABLED,
						RispostaImpl.class, risposta.getPrimaryKey()) == null) {
				cacheResult(risposta);
			}
			else {
				risposta.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rispostas.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(RispostaImpl.class.getName());
		}

		EntityCacheUtil.clearCache(RispostaImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the risposta.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Risposta risposta) {
		EntityCacheUtil.removeResult(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaImpl.class, risposta.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(risposta);
	}

	@Override
	public void clearCache(List<Risposta> rispostas) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Risposta risposta : rispostas) {
			EntityCacheUtil.removeResult(RispostaModelImpl.ENTITY_CACHE_ENABLED,
				RispostaImpl.class, risposta.getPrimaryKey());

			clearUniqueFindersCache(risposta);
		}
	}

	protected void cacheUniqueFindersCache(Risposta risposta) {
		if (risposta.isNew()) {
			Object[] args = new Object[] {
					risposta.getUuid(), risposta.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				risposta);

			args = new Object[] { risposta.getIdDomanda(), risposta.getEsatta() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOMANDA_RISPESATTA,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
				args, risposta);
		}
		else {
			RispostaModelImpl rispostaModelImpl = (RispostaModelImpl)risposta;

			if ((rispostaModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						risposta.getUuid(), risposta.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					risposta);
			}

			if ((rispostaModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						risposta.getIdDomanda(), risposta.getEsatta()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DOMANDA_RISPESATTA,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
					args, risposta);
			}
		}
	}

	protected void clearUniqueFindersCache(Risposta risposta) {
		RispostaModelImpl rispostaModelImpl = (RispostaModelImpl)risposta;

		Object[] args = new Object[] { risposta.getUuid(), risposta.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((rispostaModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					rispostaModelImpl.getOriginalUuid(),
					rispostaModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] { risposta.getIdDomanda(), risposta.getEsatta() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOMANDA_RISPESATTA,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
			args);

		if ((rispostaModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA.getColumnBitmask()) != 0) {
			args = new Object[] {
					rispostaModelImpl.getOriginalIdDomanda(),
					rispostaModelImpl.getOriginalEsatta()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOMANDA_RISPESATTA,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DOMANDA_RISPESATTA,
				args);
		}
	}

	/**
	 * Creates a new risposta with the primary key. Does not add the risposta to the database.
	 *
	 * @param idRisposta the primary key for the new risposta
	 * @return the new risposta
	 */
	@Override
	public Risposta create(long idRisposta) {
		Risposta risposta = new RispostaImpl();

		risposta.setNew(true);
		risposta.setPrimaryKey(idRisposta);

		String uuid = PortalUUIDUtil.generate();

		risposta.setUuid(uuid);

		return risposta;
	}

	/**
	 * Removes the risposta with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param idRisposta the primary key of the risposta
	 * @return the risposta that was removed
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta remove(long idRisposta)
		throws NoSuchRispostaException, SystemException {
		return remove((Serializable)idRisposta);
	}

	/**
	 * Removes the risposta with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the risposta
	 * @return the risposta that was removed
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta remove(Serializable primaryKey)
		throws NoSuchRispostaException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Risposta risposta = (Risposta)session.get(RispostaImpl.class,
					primaryKey);

			if (risposta == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRispostaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(risposta);
		}
		catch (NoSuchRispostaException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Risposta removeImpl(Risposta risposta) throws SystemException {
		risposta = toUnwrappedModel(risposta);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(risposta)) {
				risposta = (Risposta)session.get(RispostaImpl.class,
						risposta.getPrimaryKeyObj());
			}

			if (risposta != null) {
				session.delete(risposta);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (risposta != null) {
			clearCache(risposta);
		}

		return risposta;
	}

	@Override
	public Risposta updateImpl(it.quizscuola.portlet.model.Risposta risposta)
		throws SystemException {
		risposta = toUnwrappedModel(risposta);

		boolean isNew = risposta.isNew();

		RispostaModelImpl rispostaModelImpl = (RispostaModelImpl)risposta;

		if (Validator.isNull(risposta.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			risposta.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (risposta.isNew()) {
				session.save(risposta);

				risposta.setNew(false);
			}
			else {
				session.merge(risposta);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RispostaModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((rispostaModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { rispostaModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { rispostaModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((rispostaModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						rispostaModelImpl.getOriginalUuid(),
						rispostaModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						rispostaModelImpl.getUuid(),
						rispostaModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((rispostaModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOMANDA.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						rispostaModelImpl.getOriginalIdDomanda()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOMANDA, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOMANDA,
					args);

				args = new Object[] { rispostaModelImpl.getIdDomanda() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DOMANDA, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DOMANDA,
					args);
			}
		}

		EntityCacheUtil.putResult(RispostaModelImpl.ENTITY_CACHE_ENABLED,
			RispostaImpl.class, risposta.getPrimaryKey(), risposta);

		clearUniqueFindersCache(risposta);
		cacheUniqueFindersCache(risposta);

		return risposta;
	}

	protected Risposta toUnwrappedModel(Risposta risposta) {
		if (risposta instanceof RispostaImpl) {
			return risposta;
		}

		RispostaImpl rispostaImpl = new RispostaImpl();

		rispostaImpl.setNew(risposta.isNew());
		rispostaImpl.setPrimaryKey(risposta.getPrimaryKey());

		rispostaImpl.setUuid(risposta.getUuid());
		rispostaImpl.setIdRisposta(risposta.getIdRisposta());
		rispostaImpl.setDescRisposta(risposta.getDescRisposta());
		rispostaImpl.setEsatta(risposta.isEsatta());
		rispostaImpl.setIdDomanda(risposta.getIdDomanda());
		rispostaImpl.setGroupId(risposta.getGroupId());
		rispostaImpl.setCompanyId(risposta.getCompanyId());
		rispostaImpl.setUserId(risposta.getUserId());
		rispostaImpl.setUserName(risposta.getUserName());
		rispostaImpl.setCreateDate(risposta.getCreateDate());
		rispostaImpl.setModifiedDate(risposta.getModifiedDate());

		return rispostaImpl;
	}

	/**
	 * Returns the risposta with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the risposta
	 * @return the risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRispostaException, SystemException {
		Risposta risposta = fetchByPrimaryKey(primaryKey);

		if (risposta == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRispostaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return risposta;
	}

	/**
	 * Returns the risposta with the primary key or throws a {@link it.quizscuola.portlet.NoSuchRispostaException} if it could not be found.
	 *
	 * @param idRisposta the primary key of the risposta
	 * @return the risposta
	 * @throws it.quizscuola.portlet.NoSuchRispostaException if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta findByPrimaryKey(long idRisposta)
		throws NoSuchRispostaException, SystemException {
		return findByPrimaryKey((Serializable)idRisposta);
	}

	/**
	 * Returns the risposta with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the risposta
	 * @return the risposta, or <code>null</code> if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Risposta risposta = (Risposta)EntityCacheUtil.getResult(RispostaModelImpl.ENTITY_CACHE_ENABLED,
				RispostaImpl.class, primaryKey);

		if (risposta == _nullRisposta) {
			return null;
		}

		if (risposta == null) {
			Session session = null;

			try {
				session = openSession();

				risposta = (Risposta)session.get(RispostaImpl.class, primaryKey);

				if (risposta != null) {
					cacheResult(risposta);
				}
				else {
					EntityCacheUtil.putResult(RispostaModelImpl.ENTITY_CACHE_ENABLED,
						RispostaImpl.class, primaryKey, _nullRisposta);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(RispostaModelImpl.ENTITY_CACHE_ENABLED,
					RispostaImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return risposta;
	}

	/**
	 * Returns the risposta with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param idRisposta the primary key of the risposta
	 * @return the risposta, or <code>null</code> if a risposta with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Risposta fetchByPrimaryKey(long idRisposta)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)idRisposta);
	}

	/**
	 * Returns all the rispostas.
	 *
	 * @return the rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rispostas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @return the range of rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rispostas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.quizscuola.portlet.model.impl.RispostaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rispostas
	 * @param end the upper bound of the range of rispostas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Risposta> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Risposta> list = (List<Risposta>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RISPOSTA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RISPOSTA;

				if (pagination) {
					sql = sql.concat(RispostaModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Risposta>(list);
				}
				else {
					list = (List<Risposta>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the rispostas from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Risposta risposta : findAll()) {
			remove(risposta);
		}
	}

	/**
	 * Returns the number of rispostas.
	 *
	 * @return the number of rispostas
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RISPOSTA);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the risposta persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.it.quizscuola.portlet.model.Risposta")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Risposta>> listenersList = new ArrayList<ModelListener<Risposta>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Risposta>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(RispostaImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_RISPOSTA = "SELECT risposta FROM Risposta risposta";
	private static final String _SQL_SELECT_RISPOSTA_WHERE = "SELECT risposta FROM Risposta risposta WHERE ";
	private static final String _SQL_COUNT_RISPOSTA = "SELECT COUNT(risposta) FROM Risposta risposta";
	private static final String _SQL_COUNT_RISPOSTA_WHERE = "SELECT COUNT(risposta) FROM Risposta risposta WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "risposta.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Risposta exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Risposta exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(RispostaPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static Risposta _nullRisposta = new RispostaImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Risposta> toCacheModel() {
				return _nullRispostaCacheModel;
			}
		};

	private static CacheModel<Risposta> _nullRispostaCacheModel = new CacheModel<Risposta>() {
			@Override
			public Risposta toEntityModel() {
				return _nullRisposta;
			}
		};
}