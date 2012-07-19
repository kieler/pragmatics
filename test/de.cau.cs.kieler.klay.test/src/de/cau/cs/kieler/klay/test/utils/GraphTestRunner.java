package de.cau.cs.kieler.klay.test.utils;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.junit.runners.model.FrameworkMethod;

import de.cau.cs.kieler.core.test.runners.KielerTestRunner;

/**
 * This test runner is used by the x. In the initialize() method it calls the
 * x()
 * 
 * @author wah
 * 
 */
public class GraphTestRunner extends KielerTestRunner {

	/**
	 * Instantiates a new test runner.
	 * 
	 * @param klass
	 *            the Class
	 * @throws Throwable
	 *             the throwable
	 */
	public GraphTestRunner(Class<?> klass) throws Throwable {
		super(klass);
	}

	@Override
	public void initialize(final Object object) {
		
		if (object instanceof GraphTestUtilTest) {
            // Do the actual initialization.
            ((GraphTestUtilTest) object).initialize();
        }

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRunnerName(List<Object[]> parameterObjectList,
			int parameterIndex) {
		Object[] objectArray = parameterObjectList.get(parameterIndex);
		IPath iPath = (IPath) objectArray[0];
		String name = iPath.toString();
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTestName(List<Object[]> parameterObjectList,
			int parameterIndex, FrameworkMethod method) {
		// TODO Auto-generated method stub
		return getRunnerName(parameterObjectList, parameterIndex);
	}

}
