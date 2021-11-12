package app.keepcash.srpago.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.keepcash.srpago.model.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, BigInteger> {
	
	@Query("SELECT c FROM CustomerEntity c WHERE c.idUser = ?1")
	public Optional<CustomerEntity> geCustomerByIdUser(String idUser);
	
}
