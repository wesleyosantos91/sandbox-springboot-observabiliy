package io.github.wesleyosantos91.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.huxhorn.sulky.ulid.ULID;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PersonResponse(
        @JsonSerialize(using = ToStringSerializer.class)
        ULID.Value id,
        String name,
        LocalDate dateOfBirth,
        String cpf,
        String email
) {
}
