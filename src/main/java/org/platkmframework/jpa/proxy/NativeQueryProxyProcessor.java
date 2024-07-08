package org.platkmframework.jpa.proxy;

import java.lang.reflect.Method;

import org.apache.commons.lang3.NotImplementedException;
import org.platkmframework.proxy.ProxyProcesorException;
import org.platkmframework.proxy.ProxyProcessor;

public class NativeQueryProxyProcessor implements ProxyProcessor{

	@Override
	public Object run(Object proxy, Class<?> classInterface, Method method, Object[] args)
			throws ProxyProcesorException {
		throw new NotImplementedException("NativeQueryProxyProcessor");
	}

	 
}
