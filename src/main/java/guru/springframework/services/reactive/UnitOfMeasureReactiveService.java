package guru.springframework.services.reactive;

import guru.springframework.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureReactiveService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
