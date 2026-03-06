package caua.repository;

import caua.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findById(Long id);

    Optional<Client> findByName(String name);

    List<Client> findAll();
}