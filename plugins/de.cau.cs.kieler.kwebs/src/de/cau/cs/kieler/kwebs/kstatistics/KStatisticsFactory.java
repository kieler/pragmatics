/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.kstatistics;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage
 * @generated
 */
public interface KStatisticsFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KStatisticsFactory eINSTANCE = de.cau.cs.kieler.kwebs.kstatistics.impl.KStatisticsFactoryImpl.init();

    /**
     * Returns a new object of class '<em>KStatistics</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>KStatistics</em>'.
     * @generated
     */
    KStatistics createKStatistics();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    KStatisticsPackage getKStatisticsPackage();

} //KStatisticsFactory
