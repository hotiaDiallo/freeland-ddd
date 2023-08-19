package tech.zerofiltre.freeland.collab.domain.client;

import tech.zerofiltre.freeland.collab.domain.client.model.Client;
import tech.zerofiltre.freeland.collab.domain.client.model.ClientId;

import java.util.Optional;

public interface ClientProvider {
    Optional<Client> clientOfId(ClientId clientId);

    Client registerClient(Client client);
}
