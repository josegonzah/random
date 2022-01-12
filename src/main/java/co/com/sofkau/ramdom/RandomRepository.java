package co.com.sofkau.ramdom;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomRepository extends ReactiveCrudRepository<Random, String>{
    
}
