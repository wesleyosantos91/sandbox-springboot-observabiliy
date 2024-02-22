package io.github.wesleyosantos91.domain.entity;

import de.huxhorn.sulky.ulid.ULID;
import io.github.wesleyosantos91.domain.entity.type.UlidType;
import io.github.wesleyosantos91.utils.ULIDUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_person")
public class PersonEntity implements Persistable<ULID.Value> {

    @Id
    @Type(UlidType.class)
    @Column(name = "id", columnDefinition = "VARCHAR(26)")
    private ULID.Value id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Override
    public ULID.Value getId() {
        return id;
    }

    public void setId(ULID.Value id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isNew() {
        if (Objects.isNull(id)) {
            setId(ULID.parseULID(ULIDUtils.createUTCMinus3ULID()));
            return true;
        } else {
            return false;
        }

    }
}