package com.vet.clinic.app.domain.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.util.StringUtils;

public class VspSortFilter
{
  public static enum SortDirection
  {
    ASC,
    DESC;
  }

  /**
   * Get SortDirection value from the given sort direction name. Making the input to uppercase and then matching for
   * some leniency.
   * 
   * <p>
   * Eg ascEnding/Ascending -> ASCENDING asc -> null.
   * </p>
   */
  public static SortDirection fromName(String givenSortDirection)
  {
    if (StringUtils.isEmpty(givenSortDirection))
    {
      return null;
    }

    for (SortDirection sortDirection : SortDirection.values())
    {
      if (sortDirection.name().equals(givenSortDirection.toUpperCase(Locale.getDefault())))
      {
        return sortDirection;
      }
    }

    return null;
  }

  List<SortOrder> sortOrder = new ArrayList<SortOrder>();

  /**
   * SortOrder class which defines the sort order field and direction for this particular field.
   * 
   * @author vaibhav_shah
   *
   */
  public static class SortOrder
  {
    SortDirection sortDirection;
    String fieldName;

    /**
     * Constructor.
     * 
     * @param fieldName
     *          - sortField
     * @param sortDirection
     *          - sortDirection
     */
    public SortOrder(String fieldName, SortDirection sortDirection)
    {
      this.fieldName = fieldName;
      this.sortDirection = sortDirection;
    }

    public SortDirection getSortDirection()
    {
      return sortDirection;
    }

    public String getFieldName()
    {
      return fieldName;
    }

  }

  /**
   * Adds given sortorder to the list.
   * 
   * @param sortOrder
   *          - sortorder
   */
  public void addSortOrder(SortOrder sortOrder)
  {
    this.sortOrder.add(sortOrder);
  }

  public List<SortOrder> getSortOrder()
  {
    return this.sortOrder;
  }
  
  
  public static VspSortFilter toSortFilter(String sortField, String sortDir)
  {
    VspSortFilter sortFilter = new VspSortFilter();
    SortOrder sortOrder = null;
    SortDirection sortDirection = VspSortFilter.fromName(sortDir);

    if (sortField != null)
    {

      if (sortDirection != null)
      {
        sortOrder = new SortOrder(sortField, sortDirection);
      }
      else
      {
        sortOrder = new SortOrder(sortField, VspSortFilter.SortDirection.DESC);
      }
    }
    else
    {
      sortOrder = new SortOrder("updateTime", VspSortFilter.SortDirection.DESC);
    }

    sortFilter.addSortOrder(sortOrder);
    return sortFilter;

  }

}