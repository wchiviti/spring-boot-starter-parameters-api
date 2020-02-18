package io.github.hobbstech.parametersapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateParameterCommand {

    @NotBlank(message = "Name should be provided")
    @Size(max = 255, message = "Name should not be greater than 255 Characters")
    @JsonProperty(required = true)
    private String name;

    private String description;



}
