package br.com.lucianoneves.tarefas.services;

import br.com.lucianoneves.tarefas.models.TarefasModel;
import br.com.lucianoneves.tarefas.notfoundexception.TarefaNotFoundException;
import br.com.lucianoneves.tarefas.repositories.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefasServices {
    @Autowired
    private TarefasRepository tarefasRepository;

    public void save(TarefasModel tarefasModel) {
        tarefasRepository.save(tarefasModel);
    }

    public List<TarefasModel> findAll() {
        return (List<TarefasModel>) tarefasRepository.findAll();
    }

    public TarefasModel get(Integer id) throws TarefaNotFoundException {
        Optional<TarefasModel> optionalTarefasModel = tarefasRepository.findById(id);
        if (optionalTarefasModel.isPresent()) {
            return optionalTarefasModel.get();
        }
        throw new TarefaNotFoundException("Nenhuma tarefa encontrada com o id: " + id);
    }

    public void delete(Integer id) throws TarefaNotFoundException {
        Long count = tarefasRepository.countById(id);
        if (count == null || count == 0) {
            throw new TarefaNotFoundException("Não foi localizado tarefa com Id: " + id);
        }
        tarefasRepository.deleteById(id);
    }

}
