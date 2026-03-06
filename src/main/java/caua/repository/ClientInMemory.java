package caua.repository;

import caua.model.Client;

import java.util.*;

public class ClientInMemory implements ClientRepository{

    private final Map<Long, Client> clients = new HashMap<>();

    @Override
    public void save(Client client){
        clients.put(client.getId(), client);
    }

    @Override
    public List<Client> findAll(){
        return new ArrayList<>(clients.values());
    }

    @Override
    public Optional<Client> findById(Long id){
        return clients.values().stream().filter(client -> client.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Client>findByName(String name){
        return clients.values().stream().filter(client -> client.getName().equalsIgnoreCase(name)).findFirst();
    }
}
