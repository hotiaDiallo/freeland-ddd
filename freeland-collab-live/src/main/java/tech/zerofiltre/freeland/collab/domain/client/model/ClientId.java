package tech.zerofiltre.freeland.collab.domain.client.model;

import tech.zerofiltre.freeland.collab.domain.CompanyId;

public class ClientId extends CompanyId {
    public ClientId(String siren, String name) {
        super(siren, name);
    }
}
