package com.lab.clientservice.client.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientResponseModel {

        private final String clientId;
        private final String firstName;
        private final String lastName;
        private final String emailAddress;
        private final String streetAddress;
        private final String city;
        private final String province;
        private final String country;
        private final String postalCode;
}
