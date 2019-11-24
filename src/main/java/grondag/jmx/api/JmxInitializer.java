/*******************************************************************************
 * Copyright 2019 grondag
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package grondag.jmx.api;

import org.apiguardian.api.API;

/**
 * Use to make JMX an optional dependency. To do so, implement this interface
 * in a stand-alone class and declare a "jmx" end point in the mod's
 * fabric.mod.json that points to the implementation. <p>
 *
 * Every mod that implements this interface and declares and end point will receive
 * exactly one call to {@link #onInitalizeJmx()}.<p>
 *
 * To maintain an optional dependency, all calls to JMX methods must be isolated to
 * the JmxEntryPoint instance or to classes that are only loaded if {@link #onInitalizeJmx()}
 * is called.<p>
 *
 * Note that it is NOT necessary to implement this interface and register a
 * "jmx" end point for mods that nest the JMX library or have a hard dependency on JMX.
 * Such mods can safely handle JMX registration in their client initialize instance.
 */
@API(status = API.Status.MAINTAINED)
public interface JmxInitializer {
	/**
	 * Signals mods that maintain an optional dependency on JMX that JMX is
	 * loaded. Such mod should handle initialization activities that reference
	 * JMX classes during this call. <p>
	 *
	 * Will be called after mod initialization but before mode baking begins.
	 * It will be called exactly once per game start - subsequent resource or renderer
	 * reloads will not cause it to be called again.
	 */
	void onInitalizeJmx();
}
