package br.com.lucianoneves.tarefas.controllers;

import br.com.lucianoneves.tarefas.models.TarefasModel;
import br.com.lucianoneves.tarefas.notfoundexception.TarefaNotFoundException;
import br.com.lucianoneves.tarefas.services.TarefasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TarefasController {
    @Autowired
    private TarefasServices tarefasServices;

    @GetMapping("/tarefas/nova-tarefa")
    public String novoFormTarefa(Model model) {
        model.addAttribute("tarefa", new TarefasModel());
        model.addAttribute("pageTitle", "Adicionar Tarefa");
        return "tarefa_form";
    }

    @PostMapping("/tarefas/salvar-tarefa")
    public String salvarTarefa(TarefasModel tarefasModel, RedirectAttributes redirectAttributes) {
        tarefasServices.save(tarefasModel);
        redirectAttributes.addFlashAttribute("message", "Tarefa salva com sucesso!");
        return "redirect:/tarefas";
    }

    @GetMapping("/tarefas")
    public String listarTarefas (Model model) {
        List<TarefasModel> listaTarefa = tarefasServices.findAll();
        model.addAttribute("listaTarefa", listaTarefa);
        return "tarefas";
    }

    @GetMapping("/tarefas/editar-tarefa/{id}")
    public String editarFormTarefa(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            TarefasModel tarefasModel = tarefasServices.get(id);
            model.addAttribute("tarefa", tarefasModel);
            model.addAttribute("pageTitle", "Editando Tarefa (ID: " + id + ")");
            return "tarefa_form_edit";
        } catch (TarefaNotFoundException tarefaNotFoundException) {
            redirectAttributes.addFlashAttribute("message", tarefaNotFoundException.getMessage());
            return "redirect:/tarefas";
        }
    }

    @GetMapping("tarefas/editar-status/{id}")
    public String alterarStatus(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            TarefasModel tarefasModel = tarefasServices.get(id);
            tarefasModel.setStatus(!tarefasModel.isStatus());
            tarefasServices.save(tarefasModel);
            model.addAttribute("message", "Status do Id: " + id + " alterado com sucesso");
            return "redirect:/tarefas";
        } catch (TarefaNotFoundException tarefaNotFoundException) {
            redirectAttributes.addFlashAttribute("message", tarefaNotFoundException.getMessage());
            return "redirect:/tarefas";
        }
    }

    @GetMapping("/tarefas/excluir/{id}")
    public String deletarTarefa(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            tarefasServices.delete(id);
            redirectAttributes.addFlashAttribute("message", "Tarefa Id: " + id +
                    " foi exluído com sucesso!");
        } catch (TarefaNotFoundException tarefaNotFoundException) {
            redirectAttributes.addFlashAttribute("message", tarefaNotFoundException.getMessage());
        }
        return "redirect:/tarefas";
    }
}
