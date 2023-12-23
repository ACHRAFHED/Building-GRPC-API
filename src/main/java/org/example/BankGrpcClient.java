package org.example;

import java.io.IOException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.stubs.Bank;
import org.example.stubs.BankServiceGrpc;

public class BankGrpcClient {
    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9999)
                .usePlaintext()
                .build();
        BankServiceGrpc.BankServiceBlockingStub blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setAmount(8600)
                .setCurrencyFrom("MAD")
                .setCurrencyTo("EUR")
                .build();
        Bank.ConvertCurrencyResponse response = blockingStub.convert(request);
        System.out.println(response);
    }
}
