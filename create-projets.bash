#!/usr/bin/env bash

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=clients-service \
--package-name=com.lab.clientservice \
--groupId=com.lab.clientservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
clients-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=employees-service \
--package-name=com.lab.employeeservice \
--groupId=com.lab.employeeservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
employees-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=inventory-service \
--package-name=com.lab.inventoryservice \
--groupId=com.lab.inventoryservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
inventory-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=store-service \
--package-name=com.lab.storeservice \
--groupId=com.lab.storeservice \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
store-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=api-gateway \
--package-name=com.lab.apigateway \
--groupId=com.lab.apigateway \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
api-gateway

