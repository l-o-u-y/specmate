/**
 */
package com.specmate.model.requirements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>RG Connection Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.specmate.model.requirements.RequirementsPackage#getRGConnectionType()
 * @model
 * @generated
 */
public enum RGConnectionType implements Enumerator {
	/**
	 * The '<em><b>Inheritance</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INHERITANCE_VALUE
	 * @generated
	 * @ordered
	 */
	INHERITANCE(0, "Inheritance", "Inheritance"),

	/**
	 * The '<em><b>Composition</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPOSITION_VALUE
	 * @generated
	 * @ordered
	 */
	COMPOSITION(1, "Composition", "Composition"),

	/**
	 * The '<em><b>Target</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TARGET_VALUE
	 * @generated
	 * @ordered
	 */
	TARGET(2, "Target", "Target"),

	/**
	 * The '<em><b>Source</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOURCE_VALUE
	 * @generated
	 * @ordered
	 */
	SOURCE(3, "Source", "Source");

	/**
	 * The '<em><b>Inheritance</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INHERITANCE
	 * @model name="Inheritance"
	 * @generated
	 * @ordered
	 */
	public static final int INHERITANCE_VALUE = 0;

	/**
	 * The '<em><b>Composition</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPOSITION
	 * @model name="Composition"
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITION_VALUE = 1;

	/**
	 * The '<em><b>Target</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TARGET
	 * @model name="Target"
	 * @generated
	 * @ordered
	 */
	public static final int TARGET_VALUE = 2;

	/**
	 * The '<em><b>Source</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SOURCE
	 * @model name="Source"
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE_VALUE = 3;

	/**
	 * An array of all the '<em><b>RG Connection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RGConnectionType[] VALUES_ARRAY =
		new RGConnectionType[] {
			INHERITANCE,
			COMPOSITION,
			TARGET,
			SOURCE,
		};

	/**
	 * A public read-only list of all the '<em><b>RG Connection Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RGConnectionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>RG Connection Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RGConnectionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RGConnectionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>RG Connection Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RGConnectionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RGConnectionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>RG Connection Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RGConnectionType get(int value) {
		switch (value) {
			case INHERITANCE_VALUE: return INHERITANCE;
			case COMPOSITION_VALUE: return COMPOSITION;
			case TARGET_VALUE: return TARGET;
			case SOURCE_VALUE: return SOURCE;
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
	private RGConnectionType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //RGConnectionType
