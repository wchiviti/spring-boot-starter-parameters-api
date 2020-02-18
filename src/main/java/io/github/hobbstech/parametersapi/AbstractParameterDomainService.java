package io.github.hobbstech.parametersapi;

import io.github.hobbstech.commons.utilities.service.DomainService;

public interface AbstractParameterDomainService<T extends AbstractParameterDomainEntity,
        X extends CreateParameterCommand, Y extends UpdateParameterCommand>
        extends DomainService<T, X, Y> {
}
