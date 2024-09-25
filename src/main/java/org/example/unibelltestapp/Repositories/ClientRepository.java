package org.example.unibelltestapp.Repositories;

import org.example.unibelltestapp.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author batal
 * @Date 25.09.2024
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}