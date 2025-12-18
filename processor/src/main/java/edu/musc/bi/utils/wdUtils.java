/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.musc.bi.utils;

import com.google.common.primitives.Ints;

import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.logging.Logger;

import java.util.Optional;

public class wdUtils {
    private static final Logger LOGGER = Logger.getLogger(wdUtils.class);

    public static final String getConfigValueString(
            final String itemName, final String defaultValue) {
        // check if wiremock URL exists
        String rst = defaultValue;
        try {
            final Optional<String> osItemValue =
                    ConfigProvider.getConfig().getOptionalValue(itemName, String.class);

            // Set URL depending if mock is enabled
            if (osItemValue.isPresent()) {
                rst = osItemValue.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.errorv(
                    "wdUtils::getConfigValueString has an Exception issue, the config"
                            + " item: {0} is missing!The error message: {1}",
                    itemName, e);
        }
        return rst;
    }

    public static Integer safeStringToInt(String param) {
        return Optional.ofNullable(param).map(Ints::tryParse).orElse(null);
    }
}
