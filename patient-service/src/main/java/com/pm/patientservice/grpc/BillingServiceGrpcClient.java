package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingServiceGrpcClient {

    // wait for response to come from billing service to continue
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    // env var
    public BillingServiceGrpcClient (
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grcp.port:9001}") int serverPort ){

        log.info("Connecting to Billing Service to GRPC server on {}:{}", serverAddress, serverPort);

        // create a managed connection channel, disable encryption
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext().build();

        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    // generating request for billing service based on patient service
    // response based on protofile
    public BillingResponse createBillingAccount(String patientId, String name , String email){

        BillingRequest request = BillingRequest.newBuilder()
                .setName(patientId)
                .setName(name)
                .setEmail(email)
                .build();

        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Response from billing service via GRPC server is {}", response);
        return response;
    }
}
