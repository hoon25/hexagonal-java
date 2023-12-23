package dev.hoon25.domain.service;

import dev.hoon25.domain.entity.Router;
import dev.hoon25.domain.specification.CIDRSpecification;
import dev.hoon25.domain.specification.NetworkAmountSpecification;
import dev.hoon25.domain.specification.NetworkAvailabilitySpecification;
import dev.hoon25.domain.specification.RouterTypeSpecification;
import dev.hoon25.domain.specification.shared.Specification;
import dev.hoon25.domain.vo.Network;

public class NetworkOperation {
    public static Router createNetwork(Router router, Network network) {
        Specification<Router> availabilitySpec = new NetworkAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
        Specification<Integer> cidrSpec = new CIDRSpecification();
        Specification<Router> routerTypeSpec = new RouterTypeSpecification();
        Specification<Router> amountSpec = new NetworkAmountSpecification();

        //TODO Network의 CIDR 검증이 중복되는것같은데 확인해보자
        if (!cidrSpec.isSatisfiedBy(network.getCidr())) {
            throw new IllegalStateException("CIDR is below " + CIDRSpecification.MINIMUM_ALLOWED_CIDR);
        }

        if (!availabilitySpec.isSatisfiedBy(router)) {
            throw new IllegalStateException("Address already exist");
        }

        if (amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network newNetwork = router.createNetwork(network.getAddress(), network.getName(), network.getCidr());
            router.addNetworkToSwitch(newNetwork);
        }
        return router;
    }
}
