package br.com.lucianoneves.tarefas.repositories;

import br.com.lucianoneves.tarefas.models.TarefasModel;
import org.springframework.data.repository.CrudRepository;

public interface TarefasRepository extends CrudRepository<TarefasModel, Integer> {
    Long countById(Integer id);
}
