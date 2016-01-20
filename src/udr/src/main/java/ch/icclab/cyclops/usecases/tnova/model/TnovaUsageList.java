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
package ch.icclab.cyclops.usecases.tnova.model;

import ch.icclab.cyclops.services.iaas.cloudstack.resource.dto.UsageList;
import ch.icclab.cyclops.services.iaas.cloudstack.resource.dto.UserUsage;
import org.influxdb.dto.QueryResult;

/**
 * @author Manu
 *         Created on 27.11.15.
 */
public class TnovaUsageList extends UsageList {

    @Override
    public void addUsageFromMeter(QueryResult queryResult) {

        // create object based on received result
        UserUsage userUsage = new UserUsage(queryResult);

        usage.addUsageToList(userUsage);

    }
}
