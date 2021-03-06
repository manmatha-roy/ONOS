/*
 * Copyright 2018-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.openstacktelemetry.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.onosproject.openstacktelemetry.api.config.RestTelemetryConfig;
import org.onosproject.openstacktelemetry.api.config.TelemetryConfig;

import java.util.Map;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A configuration file contains REST telemetry parameters.
 */
public final class DefaultRestTelemetryConfig implements RestTelemetryConfig {

    private final String address;
    private final int port;
    private final String endpoint;
    private final String method;
    private final String requestMediaType;
    private final String responseMediaType;
    private final Map<String, Object> configMap;

    private DefaultRestTelemetryConfig(String address, int port, String endpoint,
                                       String method, String requestMediaType,
                                       String responseMediaType,
                                       Map<String, Object> configMap) {
        this.address = address;
        this.port = port;
        this.endpoint = endpoint;
        this.method = method;
        this.requestMediaType = requestMediaType;
        this.responseMediaType = responseMediaType;
        this.configMap = configMap;
    }

    @Override
    public String address() {
        return address;
    }

    @Override
    public int port() {
        return port;
    }

    @Override
    public String endpoint() {
        return endpoint;
    }

    @Override
    public String method() {
        return method;
    }

    @Override
    public String requestMediaType() {
        return requestMediaType;
    }

    @Override
    public String responseMediaType() {
        return responseMediaType;
    }

    @Override
    public Map<String, Object> configMap() {
        if (configMap != null) {
            return ImmutableMap.copyOf(configMap);
        } else {
            return Maps.newConcurrentMap();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof DefaultRestTelemetryConfig) {
            final DefaultRestTelemetryConfig other = (DefaultRestTelemetryConfig) obj;
            return Objects.equals(this.address, other.address) &&
                    Objects.equals(this.port, other.port) &&
                    Objects.equals(this.endpoint, other.endpoint) &&
                    Objects.equals(this.method, other.method) &&
                    Objects.equals(this.requestMediaType, other.requestMediaType) &&
                    Objects.equals(this.responseMediaType, other.responseMediaType) &&
                    Objects.equals(this.configMap, other.configMap);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port, endpoint, method, requestMediaType,
                responseMediaType, configMap);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("address", address)
                .add("port", port)
                .add("endpoint", endpoint)
                .add("method", method)
                .add("requestMediaType", requestMediaType)
                .add("responseMediaType", responseMediaType)
                .add("configMap", configMap)
                .toString();
    }

    @Override
    public TelemetryConfig.Builder createBuilder() {
        return new DefaultBuilder();
    }

    /**
     * Builder class of DefaultRestTelemetryConfig.
     */
    public static final class DefaultBuilder implements Builder {

        private String address;
        private int port;
        private String endpoint;
        private String method;
        private String requestMediaType;
        private String responseMediaType;
        private Map<String, Object> configMap;

        @Override
        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public Builder withPort(int port) {
            this.port = port;
            return this;
        }

        @Override
        public Builder withEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        @Override
        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        @Override
        public Builder withRequestMediaType(String mediaType) {
            this.requestMediaType = mediaType;
            return this;
        }

        @Override
        public Builder withResponseMediaType(String mediaType) {
            this.responseMediaType = mediaType;
            return this;
        }

        @Override
        public Builder withConfigMap(Map<String, Object> configMap) {
            this.configMap = configMap;
            return this;
        }

        @Override
        public RestTelemetryConfig build() {
            checkNotNull(address, "REST server address cannot be null");
            checkNotNull(endpoint, "REST server endpoint cannot be null");

            return new DefaultRestTelemetryConfig(address, port, endpoint,
                    method, requestMediaType, responseMediaType, configMap);
        }
    }
}
