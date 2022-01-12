package co.com.sofkau.ramdom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
public class RandomRouter {

    @Autowired
    private RandomRepository repository;

    public RandomRouter(RandomRepository randomRepository) {
        repository = randomRepository;
    }

    @PostMapping("/random/post-list")
    public Mono<Random> post(@RequestBody RequestDTO request) {
        return Mono.just(new Random())
                .map(entity -> {
                    entity.setDate(LocalDate.now().toString());
                    entity.setOrderedList(request.getList());
                    return entity;
                })
                .map(entity -> {
                    var list = Stream.of(request.getList().split(","))
                            .map(p -> p.trim())
                            .collect(Collectors.toList());
                    Collections.shuffle(list);

                    var randomList = list.stream()
                            .collect(Collectors.joining(","));
                    entity.setRandomList(randomList);
                    return entity;
                })
                .flatMap(repository::save);

    }

    @GetMapping("/random/getAll")
    public Flux<Random> get() {
        return repository.findAll();
    }

    @PutMapping("/random/update-list/{idList}")
    public Mono<Random> update(@PathVariable("idList") String id, @RequestBody RequestDTO request) {
        return repository.findById(id).flatMap(random -> {
            var list = Stream.of(request.getList().split(","))
                    .map(p -> p.trim())
                    .collect(Collectors.toList());
            Collections.shuffle(list);

            var randomList = list.stream()
                    .collect(Collectors.joining(","));
            random.setRandomList(randomList);
            return repository.save(random);
        });
    }

    @DeleteMapping("/random/delete-list/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return repository.deleteById(id);
    }


}
