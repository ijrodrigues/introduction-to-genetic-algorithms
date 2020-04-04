package com.br.ijrodrigues.introductiontogeneticalgorithms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRepository extends JpaRepository<String, String> {

}
