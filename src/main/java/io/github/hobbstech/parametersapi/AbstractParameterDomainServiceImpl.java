package io.github.hobbstech.parametersapi;

import io.github.hobbstech.commons.utilities.exceptions.InvalidRequestException;
import io.github.hobbstech.commons.utilities.service.DomainServiceImpl;
import lombok.val;

import static io.github.hobbstech.validations.Validations.validate;

public abstract class AbstractParameterDomainServiceImpl<T extends AbstractParameterDomainEntity,
        X extends CreateParameterCommand, Y extends UpdateParameterCommand> extends DomainServiceImpl<T, X, Y>
        implements AbstractParameterDomainService<T, X, Y> {

    private final ParameterDomainBaseDao<T> parameterDomainBaseDao;

    public AbstractParameterDomainServiceImpl(ParameterDomainBaseDao<T> parameterDomainBaseDao) {
        super(parameterDomainBaseDao);
        this.parameterDomainBaseDao = parameterDomainBaseDao;
    }

    @Override
    public T create(X x) {

        validate(x);

        val nameUsed = parameterDomainBaseDao.existsByName(x.getName());

        if (nameUsed) {
            throw new InvalidRequestException("Name provided is already used");
        }

        val parameterDomainEntity = buildEntity(x);

        return parameterDomainBaseDao.save(parameterDomainEntity);
    }

    protected abstract T buildEntity(X x);

    protected abstract void updateEntity(T t, Y y);

    @Override
    public T update(Y y) {

        validate(y);

        val parameterDomainEntity = findById(y.getId());

        parameterDomainBaseDao.findByName(y.getName())
                .filter(entity -> entity.getId() != parameterDomainEntity.getId())
                .ifPresent(entity -> {
                    throw new InvalidRequestException("The provided name is already used by another record.");
                });

        updateEntity(parameterDomainEntity, y);

        return parameterDomainBaseDao.save(parameterDomainEntity);
    }
}
