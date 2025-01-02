package org.platkmframework.jpa.proxy;

import java.lang.reflect.Method;
import org.apache.commons.lang3.NotImplementedException;
import org.platkmframework.proxy.ProxyProcesorException;
import org.platkmframework.proxy.ProxyProcessor;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class NativeQueryProxyProcessor implements ProxyProcessor {

	
	/**
	 * NativeQueryProxyProcessor
	 */
    public NativeQueryProxyProcessor() {
		super();
	}

	/**
     * run
     * @param proxy proxy
     * @param classInterface classInterface
     * @param method method
     * @param args args
     * @return Object
     * @throws ProxyProcesorException ProxyProcesorException
     */
    @Override
    public Object run(Object proxy, Class<?> classInterface, Method method, Object[] args) throws ProxyProcesorException {
        throw new NotImplementedException("NativeQueryProxyProcessor");
    }
}
