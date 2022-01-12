package co.com.sofkau.ramdom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RandomRouter.class)
class RandomRouterTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    RandomRepository repository;


    @Test
    void get() {
        var random = new Random();
        random.setId("randomId");
        random.setDate(LocalDate.now().toString());
        random.setOrderedList("rrrr,www,qqq,eee");
        random.setRandomList("qqq,www,eee,rrrr");

        Mockito.when(repository.findAll()).thenReturn(Flux.just(random));

        webTestClient.get()
                .uri("/random")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].originalList")
                .isEqualTo("rrrr,www,qqq,eee")
                .consumeWith(randomEntinty -> {
                    var randonTest = randomEntinty.getResponseBody();
                    assert randonTest != null;
                });

        Mockito.verify(repository).findAll();

    }

    @Test
    void post() {
        var request = new RequestDTO();
        request.setList("rrrr,www,qqq,eee");

        var random = new Random();
        random.setId("randomId");
        random.setDate(LocalDate.now().toString());
        random.setOrderedList("rrrr,www,qqq,eee");
        random.setRandomList("qqq,www,eee,rrrr");

        when(repository.save(any())).thenReturn(Mono.just(random));

        webTestClient.post()
                .uri("/random")
                .body(Mono.just(request), RequestDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Random.class)
                .consumeWith(bod -> {
                    var body = bod.getResponseBody();
                    assert body != null;
                });

        Mockito.verify(repository).save(any());

    }


    @Test
    void put(){

        var request = new RequestDTO();
        request.setList("rrrr,www,qqq,eee");

        var random = new Random();
        random.setId("randomId");
        random.setDate(LocalDate.now().toString());
        random.setOrderedList("rrrr,www,qqq,eee");
        random.setRandomList("qqq,www,eee,rrrr");

        when(repository.findById(any(String.class))).thenReturn(Mono.just(random));
        when(repository.save(any())).thenReturn(Mono.just(random));

        webTestClient.put()
                .uri("/random/randomId")
                .body(Mono.just(request), RequestDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Random.class)
                .consumeWith(bod -> {
                    var body = bod.getResponseBody();
                    assert body != null;
                });

        Mockito.verify(repository).save(any());
        Mockito.verify(repository).findById("randomId");


    }

    @Test
    void delete(){
        var request = new RequestDTO();
        request.setList("rrrr,www,qqq,eee");

        when(repository.deleteById(any(String.class))).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/random/randomId")
                .exchange()
                .expectStatus().isOk();


        Mockito.verify(repository).deleteById("randomId");

    }

}