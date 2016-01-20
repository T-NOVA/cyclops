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
 * Description: POJO object for VPN User Usage Data (type 14)
 */
public class VPNUserUsageData extends UsageData {

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
        return "cloudstack.vpn.user.hours";
    }
}
