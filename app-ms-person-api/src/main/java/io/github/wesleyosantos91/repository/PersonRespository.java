package io.github.wesleyosantos91.repository;

import de.huxhorn.sulky.ulid.ULID;
import io.github.wesleyosantos91.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRespository extends JpaRepository<PersonEntity, ULID.Value> {
}
