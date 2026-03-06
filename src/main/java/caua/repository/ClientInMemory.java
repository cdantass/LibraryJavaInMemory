package caua.repository;

import caua.model.Client;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ClientInMemory implements ClientRepository{

    private final Map<Long, Client> clients = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    @Override
    public Client save(Client client){
        if(client.getId() == null){
            client.setId(idGenerator.getAndIncrement());
        }
        clients.put(client.getId(), client);

        return client;
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
