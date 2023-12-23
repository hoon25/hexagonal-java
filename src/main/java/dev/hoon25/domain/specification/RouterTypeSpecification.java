package dev.hoon25.domain.specification;

import dev.hoon25.domain.entity.Router;
import dev.hoon25.domain.specification.shared.AbstractSpecification;
import dev.hoon25.domain.vo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {
    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
