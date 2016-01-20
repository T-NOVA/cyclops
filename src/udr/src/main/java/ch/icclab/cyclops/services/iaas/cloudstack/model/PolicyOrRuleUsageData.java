/*
 * Copyright (c) 2015. Zuercher Hochschule fuer Angewandte Wissenschaften
 *  All Rights Reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License"); you may
 *     not use this file except in compliance with the License. You may obtain
 *     a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *     WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *     License for the specific language governing permissions and limitations
 *     under the License.
 */
package ch.icclab.cyclops.services.iaas.cloudstack.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Martin Skoviera
 * Created on: 15-Oct-15
 * Description: POJO object for Load Balancer Policy (type 11) or Port Forwarding Rule Usage Data (type 12)
 */
public class PolicyOrRuleUsageData extends UsageData {

    @Override
    protected Map<String, String> getObjectTags() {
        return new HashMap<String, String>();
    }

    @Override
    protected Map<String, Object> getObjectFields() {
        return new HashMap<String, Object>();
    }

    @Override
    protected String getMeterName() {
        return getUsagetype() == 11 ? getMeterNameBalancer() : getMeterNameForwarder();
    }

    public String getMeterNameBalancer() {
        return "cloudstack.load.balancer.hours";
    }

    public String getMeterNameForwarder() {
        return "cloudstack.port.forwarding.hours";
    }
}
