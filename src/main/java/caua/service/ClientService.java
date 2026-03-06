package caua.service;

import caua.model.Client;
import caua.repository.ClientRepository;

import java.util.List;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client createClient(Long id, String name){
        Client client = new Client(id, name);
        clientRepository.save(client);

        return client;
    }
    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        return clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Client not found"));
    }

    public Client findByNameClient(String name){
        return clientRepository.findByName(name).orElseThrow(()-> new RuntimeException("Client name not found"));
    }
}