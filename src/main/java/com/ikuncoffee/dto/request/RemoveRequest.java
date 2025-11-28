package com.ikuncoffee.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RemoveRequest {

    @NotNull
    @Schema(description = "ID")
    private Long id;

}
