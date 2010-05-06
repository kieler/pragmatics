/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.kiml.graphviz.dot.dot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Compass Point</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kiml.graphviz.dot.dot.DotPackage#getCompassPoint()
 * @model
 * @generated
 */
public enum CompassPoint implements Enumerator
{
  /**
   * The '<em><b>North</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NORTH_VALUE
   * @generated
   * @ordered
   */
  NORTH(0, "north", "n"),

  /**
   * The '<em><b>North East</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NORTH_EAST_VALUE
   * @generated
   * @ordered
   */
  NORTH_EAST(1, "northEast", "ne"),

  /**
   * The '<em><b>East</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EAST_VALUE
   * @generated
   * @ordered
   */
  EAST(2, "east", "e"),

  /**
   * The '<em><b>South East</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SOUTH_EAST_VALUE
   * @generated
   * @ordered
   */
  SOUTH_EAST(3, "southEast", "se"),

  /**
   * The '<em><b>South</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SOUTH_VALUE
   * @generated
   * @ordered
   */
  SOUTH(4, "south", "s"),

  /**
   * The '<em><b>South West</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SOUTH_WEST_VALUE
   * @generated
   * @ordered
   */
  SOUTH_WEST(5, "southWest", "sw"),

  /**
   * The '<em><b>West</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #WEST_VALUE
   * @generated
   * @ordered
   */
  WEST(6, "west", "w"),

  /**
   * The '<em><b>North West</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NORTH_WEST_VALUE
   * @generated
   * @ordered
   */
  NORTH_WEST(7, "northWest", "nw"),

  /**
   * The '<em><b>Center</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CENTER_VALUE
   * @generated
   * @ordered
   */
  CENTER(8, "center", "c"),

  /**
   * The '<em><b>Blank</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BLANK_VALUE
   * @generated
   * @ordered
   */
  BLANK(9, "blank", "_");

  /**
   * The '<em><b>North</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>North</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NORTH
   * @model name="north" literal="n"
   * @generated
   * @ordered
   */
  public static final int NORTH_VALUE = 0;

  /**
   * The '<em><b>North East</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>North East</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NORTH_EAST
   * @model name="northEast" literal="ne"
   * @generated
   * @ordered
   */
  public static final int NORTH_EAST_VALUE = 1;

  /**
   * The '<em><b>East</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>East</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EAST
   * @model name="east" literal="e"
   * @generated
   * @ordered
   */
  public static final int EAST_VALUE = 2;

  /**
   * The '<em><b>South East</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>South East</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SOUTH_EAST
   * @model name="southEast" literal="se"
   * @generated
   * @ordered
   */
  public static final int SOUTH_EAST_VALUE = 3;

  /**
   * The '<em><b>South</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>South</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SOUTH
   * @model name="south" literal="s"
   * @generated
   * @ordered
   */
  public static final int SOUTH_VALUE = 4;

  /**
   * The '<em><b>South West</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>South West</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SOUTH_WEST
   * @model name="southWest" literal="sw"
   * @generated
   * @ordered
   */
  public static final int SOUTH_WEST_VALUE = 5;

  /**
   * The '<em><b>West</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>West</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #WEST
   * @model name="west" literal="w"
   * @generated
   * @ordered
   */
  public static final int WEST_VALUE = 6;

  /**
   * The '<em><b>North West</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>North West</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NORTH_WEST
   * @model name="northWest" literal="nw"
   * @generated
   * @ordered
   */
  public static final int NORTH_WEST_VALUE = 7;

  /**
   * The '<em><b>Center</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Center</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CENTER
   * @model name="center" literal="c"
   * @generated
   * @ordered
   */
  public static final int CENTER_VALUE = 8;

  /**
   * The '<em><b>Blank</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Blank</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BLANK
   * @model name="blank" literal="_"
   * @generated
   * @ordered
   */
  public static final int BLANK_VALUE = 9;

  /**
   * An array of all the '<em><b>Compass Point</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final CompassPoint[] VALUES_ARRAY =
    new CompassPoint[]
    {
      NORTH,
      NORTH_EAST,
      EAST,
      SOUTH_EAST,
      SOUTH,
      SOUTH_WEST,
      WEST,
      NORTH_WEST,
      CENTER,
      BLANK,
    };

  /**
   * A public read-only list of all the '<em><b>Compass Point</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<CompassPoint> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Compass Point</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CompassPoint get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      CompassPoint result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Compass Point</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CompassPoint getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      CompassPoint result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Compass Point</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CompassPoint get(int value)
  {
    switch (value)
    {
      case NORTH_VALUE: return NORTH;
      case NORTH_EAST_VALUE: return NORTH_EAST;
      case EAST_VALUE: return EAST;
      case SOUTH_EAST_VALUE: return SOUTH_EAST;
      case SOUTH_VALUE: return SOUTH;
      case SOUTH_WEST_VALUE: return SOUTH_WEST;
      case WEST_VALUE: return WEST;
      case NORTH_WEST_VALUE: return NORTH_WEST;
      case CENTER_VALUE: return CENTER;
      case BLANK_VALUE: return BLANK;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private CompassPoint(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //CompassPoint
