package com.vet.clinic.app.domain.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import com.vet.clinic.app.domain.common.VspSortFilter;
import com.vet.clinic.app.domain.common.VspSortFilter.SortOrder;
import com.vet.clinic.app.domain.pet.Pet;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;

@Repository
@Validated
public class AppointmentSearchRepository
{
  @Autowired
  EntityManager entityManager;

  public List<Appointment>
      search(Properties searchProperties,
          VspSortFilter commpSortFilter, int start, int perPage)
  {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
    final Root<Appointment> root = criteriaQuery.from(Appointment.class);

    criteriaQuery.select(root);

    if (searchProperties != null)
    {
      criteriaQuery.where(getCriteria(searchProperties, criteriaBuilder, root));
    }
    if (commpSortFilter != null)
    {
      final List<SortOrder> sortList = commpSortFilter.getSortOrder();
      if (!CollectionUtils.isEmpty(sortList))
      {
        for (final SortOrder sortOrder : sortList)
        {
          if (sortOrder.getSortDirection() == VspSortFilter.SortDirection.ASC)
          {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortOrder.getFieldName())));
          }
          else
          {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortOrder.getFieldName())));
          }
        }
      }
    }

    final TypedQuery<Appointment> typedQuery = entityManager.createQuery(criteriaQuery);
    addQueryParameters(searchProperties, typedQuery);
    typedQuery.setFirstResult(start);
    typedQuery.setMaxResults(perPage);

    return typedQuery.getResultList();
  }

  public Integer count(Properties searchProperties)
  {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
    final Root<Appointment> root = criteriaQuery.from(Appointment.class);
    criteriaQuery.select(criteriaBuilder.count(root))
        .where(getCriteria(searchProperties, criteriaBuilder, root));

    final TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
    addQueryParameters(searchProperties, typedQuery);
    return typedQuery.getSingleResult().intValue();
  }

  private void addQueryParameters(Properties properties, TypedQuery<?> typedQuery)
  {
    if (properties != null && typedQuery != null)
    {
      for (String key : properties.stringPropertyNames())
      {
        if (key.equals("vetId") || key.equals("petId"))
        {
          typedQuery.setParameter(key, Long.parseLong(properties.getProperty(key)));
        }
        else
        {
          typedQuery.setParameter(key, properties.getProperty(key));
        }
      }
    }

  }

  private Predicate getCriteria(Properties properties,
      CriteriaBuilder criteriaBuilder, Root<Appointment> root)
  {
    List<Predicate> criteria = new ArrayList<Predicate>();
    if (properties != null)
    {
      for (String key : properties.stringPropertyNames())
      {
        ParameterExpression<String> parameterExpression = criteriaBuilder.parameter(String.class, key);
        if (key.equals("fromTs"))
        {
          criteria.add(
              criteriaBuilder.greaterThanOrEqualTo(root.<String> get("startTime"),
                  criteriaBuilder.lower(parameterExpression)));
        }
        else if (key.equals("toTs"))
        {
          criteria.add(
              criteriaBuilder.lessThanOrEqualTo(root.<String> get("startTime"),
                  criteriaBuilder.lower(parameterExpression)));
        }
        else if (key.equals("vetId"))
        {
          Join<Appointment, Veterinarian> veterinarian = root.join("veterinarian");
          ParameterExpression<Long> vetIdparamExp = criteriaBuilder.parameter(Long.class, key);

          criteria.add(criteriaBuilder.equal(
              veterinarian.<Long> get("id"), vetIdparamExp));
        }
        else if (key.equals("petId"))
        {
          ParameterExpression<Long> petIdparamExp = criteriaBuilder.parameter(Long.class, key);
          Join<Appointment, Pet> pet = root.join("pet");

          criteria.add(criteriaBuilder.equal(
              pet.<Long> get("id"), petIdparamExp));

        }
      }
    }

    if (criteria.size() == 1)
    {
      return criteria.get(0);
    }

    return criteriaBuilder.and(criteria.toArray(new Predicate[0]));
  }

}
