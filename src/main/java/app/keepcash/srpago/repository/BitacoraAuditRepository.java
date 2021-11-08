package app.keepcash.srpago.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.keepcash.srpago.model.entity.BitacoraAuditEntity;

@Repository
public interface BitacoraAuditRepository extends JpaRepository<BitacoraAuditEntity, BigInteger> {

}
