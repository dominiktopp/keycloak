/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.operator;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.Map;

/**
 * @author Vaclav Muzikar <vmuzikar@redhat.com>
 */
@ConfigMapping(prefix = "operator")
public interface Config {
    Keycloak keycloak();

    interface Keycloak {
        String image();
        String imagePullPolicy();
        boolean startOptimized();
        int pollIntervalSeconds();

        Map<String, String> podLabels();
    }

    // workarounds for OLM env values
    // to be removed after https://github.com/keycloak/keycloak/issues/12352

    @WithDefault("keycloak-operator")
    String name();

    interface Condition {
        @WithDefault("keycloak-operator.v999-SNAPSHOT")
        String name();
    }

    Condition condition();
}
