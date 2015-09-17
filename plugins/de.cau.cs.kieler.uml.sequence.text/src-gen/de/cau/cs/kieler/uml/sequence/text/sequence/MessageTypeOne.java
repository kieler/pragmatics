/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Message Type One</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#getMessageTypeOne()
 * @model
 * @generated
 */
public enum MessageTypeOne implements Enumerator
{
  /**
   * The '<em><b>Async</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ASYNC_VALUE
   * @generated
   * @ordered
   */
  ASYNC(0, "async", "async"),

  /**
   * The '<em><b>Response</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #RESPONSE_VALUE
   * @generated
   * @ordered
   */
  RESPONSE(1, "response", "response"),

  /**
   * The '<em><b>Sync</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SYNC_VALUE
   * @generated
   * @ordered
   */
  SYNC(2, "sync", "sync");

  /**
   * The '<em><b>Async</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Async</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ASYNC
   * @model name="async"
   * @generated
   * @ordered
   */
  public static final int ASYNC_VALUE = 0;

  /**
   * The '<em><b>Response</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Response</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #RESPONSE
   * @model name="response"
   * @generated
   * @ordered
   */
  public static final int RESPONSE_VALUE = 1;

  /**
   * The '<em><b>Sync</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Sync</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SYNC
   * @model name="sync"
   * @generated
   * @ordered
   */
  public static final int SYNC_VALUE = 2;

  /**
   * An array of all the '<em><b>Message Type One</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final MessageTypeOne[] VALUES_ARRAY =
    new MessageTypeOne[]
    {
      ASYNC,
      RESPONSE,
      SYNC,
    };

  /**
   * A public read-only list of all the '<em><b>Message Type One</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<MessageTypeOne> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Message Type One</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MessageTypeOne get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      MessageTypeOne result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Message Type One</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MessageTypeOne getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      MessageTypeOne result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Message Type One</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MessageTypeOne get(int value)
  {
    switch (value)
    {
      case ASYNC_VALUE: return ASYNC;
      case RESPONSE_VALUE: return RESPONSE;
      case SYNC_VALUE: return SYNC;
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
  private MessageTypeOne(int value, String name, String literal)
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
  
} //MessageTypeOne
