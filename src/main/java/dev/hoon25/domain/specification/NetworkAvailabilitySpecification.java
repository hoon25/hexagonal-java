package dev.hoon25.domain.specification;

import dev.hoon25.domain.entity.Router;
import dev.hoon25.domain.specification.shared.AbstractSpecification;
import dev.hoon25.domain.vo.IP;
import dev.hoon25.domain.vo.Network;

public class NetworkAvailabilitySpecification extends AbstractSpecification<Router> {
    private IP address;
    private String name;
    private int cidr;

    // TODO Network를 인자로
    public NetworkAvailabilitySpecification(IP address, String name, int cidr) {
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router != null && isNetworkAvailable(router);
    }

    private boolean isNetworkAvailable(Router router) {
        for (Network network : router.retrieveNetworks()) {
            if (network.getAddress().equals(address) && network.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
