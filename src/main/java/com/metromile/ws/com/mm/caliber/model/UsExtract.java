package com.metromile.ws.com.mm.caliber.model;


import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.StaticCredentials;
import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_extract.Address;
import com.smartystreets.api.us_extract.*;
import com.smartystreets.api.us_street.Candidate;

import java.io.IOException;

public class UsExtract {

    public Address[] getAddress(String text) throws IOException, SmartyException {
        // We recommend storing your secret keys in environment variables.
        //StaticCredentials credentials = new StaticCredentials("39fa7c2d-b3c6-539e-4926-6a1603f440a5","0altG27Ne4fL7wcxitGJ");
        StaticCredentials credentials = new StaticCredentials("0a7f29de-788a-edf9-be86-85b4b5dfdd34","fnQUzicNDolc58GBJKQW");
        Client client = new ClientBuilder(credentials).buildUsExtractApiClient();
        /*String text = "Here is some text.\r\nMy address is 3785 Las Vegs Av." +
                "\r\nLos Vegas, Nevada." +
                "\r\nMeet me at 1 Rosedale Baltimore Maryland, not at 123 Phony Street, Boise Idaho.";*/
        Lookup lookup = new Lookup(text);

        Result result = client.send(lookup);

        Metadata metadata = result.getMetadata();
        System.out.println("Found " + metadata.getAddressCount() + " addresses.");
        System.out.println(metadata.getVerifiedCount() + " of them were valid.");
        System.out.println();

        Address[] addresses = result.getAddresses();

        System.out.println("Addresses: \r\n**********************\r\n");
        for (Address address : addresses) {
            System.out.println("\"" + address.getText() + "\"\n");
            System.out.println("Verified? " + address.isVerified());
            if (address.getCandidates().length > 0) {
                System.out.println("\nMatches:");

                for (Candidate candidate : address.getCandidates()) {
                    System.out.println(candidate.getDeliveryLine1());
                    System.out.println(candidate.getDeliveryLine2());
                    System.out.println(candidate.getComponents().getCityName());
                    System.out.println(candidate.getComponents().getState());
                    System.out.println(candidate.getComponents().getZipCode());

                    System.out.println();
                }
            } else System.out.println();

            System.out.println("**********************\n");

        }
        return addresses;
    }
}
