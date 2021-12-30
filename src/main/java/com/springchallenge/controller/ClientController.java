package com.springchallenge.controller;


import com.springchallenge.dto.ProductDTO;
import com.springchallenge.entity.Client;
import com.springchallenge.entity.Product;
import com.springchallenge.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    private List<Client> clients = new ArrayList<Client>();

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Client client) throws IOException {

        try {
            clientRepository.newClient(client);
        } catch (IOException e) {
            throw new RuntimeException("erro no io");
        }
        return ResponseEntity.status(201).body("Client has been created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClient() throws IOException {
        return ResponseEntity.status(200).body(clientRepository.getClients());
    }

}
