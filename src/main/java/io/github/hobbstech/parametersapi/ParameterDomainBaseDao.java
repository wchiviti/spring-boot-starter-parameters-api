package io.github.hobbstech.parametersapi;

import io.github.hobbstech.commons.utilities.jpa.BaseDao;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface ParameterDomainBaseDao<T extends AbstractParameterDomainEntity> extends BaseDao<T> {

    boolean existsByName(String name);

    Optional<T> findByName(String name);

}
