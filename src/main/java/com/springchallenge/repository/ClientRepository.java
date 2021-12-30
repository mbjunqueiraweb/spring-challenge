package com.springchallenge.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springchallenge.entity.Client;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ClientRepository {
    public List<Client> clients = new ArrayList<Client>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "clients.json";

    public List<Client> getClients() throws IOException {
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        clients = Arrays.asList(objectMapper.readValue(is, Client[].class));
        return clients;
    }

    public void newClient(Client client) throws IOException {
        client.setId((long) clients.size()+1);
        clients.add(client);
        objectMapper.writeValue(new File(PATH), clients);
    }


}
