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
 * Description: POJO object for Network Usage Data, both type 4 (Sent) and 5 (Received)
 */
public class NetworkUsageData extends UsageData {

    // Device type (domain router, external load balancer, etc.)
    private String type;

    // Its network ID
    private String networkid;

    /////////////////////////////
    // Getters and Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNetworkid() {
        return networkid;
    }

    public void setNetworkid(String networkid) {
        this.networkid = networkid;
    }

    @Override
    protected Map<String, String> getObjectTags() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("type", type);
        map.put("networkid", networkid);

        return map;
    }

    @Override
    protected Map<String, Object> getObjectFields() {
        return new HashMap<String, Object>();
    }

    @Override
    protected String getMeterName() {
        return getUsagetype() == 4 ? getMeterNameForOutgoing() : getMeterNameForIncoming();
    }

    public String getMeterNameForOutgoing() {
        return "cloudstack.network.outgoing.bytes";
    }

    public String getMeterNameForIncoming() {
        return "cloudstack.network.incoming.bytes";
    }
}
