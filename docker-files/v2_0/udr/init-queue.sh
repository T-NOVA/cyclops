#!/bin/bash
# Copyright (c) 2016. Zuercher Hochschule fuer Angewandte Wissenschaften
# All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may
# not use this file except in compliance with the License. You may obtain
# a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations
# under the License.
#
# Author: Martin Skoviera
# Modified by: Piyush Harsh

wget http://rabbitmq:15672/cli/rabbitmqadmin >/dev/null 2>&1

if [[ $? != 0 ]]; then
    echo "RabbitMQ Management Plugin was not found, make sure it's installed"
    exit 2
fi

chmod +x rabbitmqadmin

# Create necessary exchanges
./rabbitmqadmin declare exchange --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.udr.broadcast" type=fanout
./rabbitmqadmin declare exchange --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.rate.broadcast" type=fanout
./rabbitmqadmin declare exchange --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.cdr.broadcast" type=fanout
./rabbitmqadmin declare exchange --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.billing.broadcast" type=fanout
./rabbitmqadmin declare exchange --host=rabbitmq --port=15672 --vhost="/" name="tnova" type=direct
./rabbitmqadmin declare exchange --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.scheduler.broadcast" type=fanout

# Create necessary queues
./rabbitmqadmin declare queue --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.udr.consume" durable=true
./rabbitmqadmin declare queue --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.rate.consume" durable=true
./rabbitmqadmin declare queue --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.cdr.consume" durable=true
./rabbitmqadmin declare queue --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.udr.commands" durable=true
./rabbitmqadmin declare queue --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.cdr.commands" durable=true
./rabbitmqadmin declare queue --host=rabbitmq --port=15672 --vhost="/" name="cyclops.tnova.rate.commands" durable=true
./rabbitmqadmin declare queue --host=rabbitmq --port=15672 --vhost="/" name="cyclops" durable=true

# Bind UDR to Rule engine (pushing UDR records)
./rabbitmqadmin declare binding --host=rabbitmq --port=15672 --vhost="/" source="cyclops.tnova.udr.broadcast" destination_type="queue" destination="cyclops.tnova.rate.consume"
./rabbitmqadmin declare binding --host=rabbitmq --port=15672 --vhost="/" source="cyclops.tnova.rate.broadcast" destination_type="queue" destination="cyclops.tnova.cdr.consume"
./rabbitmqadmin declare binding --host=rabbitmq --port=15672 --vhost="/" source="tnova" destination_type="queue" destination="cyclops" routing_key="event"
./rabbitmqadmin declare binding --host=rabbitmq --port=15672 --vhost="/" source="cyclops.tnova.scheduler.broadcast" destination_type="queue" destination="cyclops.tnova.udr.commands"


rm rabbitmqadmin