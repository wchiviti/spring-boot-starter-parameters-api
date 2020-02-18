package io.github.hobbstech.parametersapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateParameterCommand extends CreateParameterCommand {

    @JsonIgnore
    private long id;

    public void updateEntity(AbstractParameterDomainEntity parameterDomainEntity) {
        parameterDomainEntity.setDescription(this.getDescription());
        parameterDomainEntity.setName(this.getName());
    }

}
