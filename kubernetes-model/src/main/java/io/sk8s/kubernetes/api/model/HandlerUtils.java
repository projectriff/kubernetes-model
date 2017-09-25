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
 * Provides utility methods for dealing with Handler and related model classes.
 *
 * @author Eric Bottard
 */
public class HandlerUtils {

	/**
	 * Create a new builder initialized with the components of the reference of the handler.
	 */
	public static io.sk8s.kubernetes.api.model.HandlerReferenceBuilder refBuilderOf(Handler handler) {
		return new io.sk8s.kubernetes.api.model.HandlerReferenceBuilder()
				.withName(handler.getMetadata().getName())
				.withNamespace(handler.getMetadata().getNamespace());
	}

	/**
	 * Return a new HandlerReference initialized with the components of the reference of the handler.
	 */
	public static HandlerReference refOf(Handler handler) {
		return refBuilderOf(handler).build();
	}

}
