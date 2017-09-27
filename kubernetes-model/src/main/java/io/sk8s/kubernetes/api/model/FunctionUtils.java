/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.sk8s.kubernetes.api.model;

/**
 * Provides utility methods for dealing with XFunction and related model classes.
 *
 * @author Eric Bottard
 */
public class FunctionUtils {

	/**
	 * Return a HandlerReference for the given function. If the handler namespace is not set, use the namespace from
	 * the function.
	 */
	public static HandlerReference adjustHandlerReference(XFunction function) {
		HandlerReference handlerRef = function.getSpec().getHandlerRef();
		if (handlerRef.getNamespace() == null) {
			handlerRef = new io.sk8s.kubernetes.api.model.HandlerReferenceBuilder(handlerRef)
					.withNamespace(function.getMetadata().getNamespace())
					.build();
		}
		return handlerRef;
	}

	public static String param(String name, XFunction function) {
		for (FunctionParam functionParam : function.getSpec().getParams()) {
			if (functionParam.getName().equals(name)) {
				return functionParam.getValue();
			}
		}
		return null;
	}


}
