package br.com.lucianoneves.tarefas.notfoundexception;

public class TarefaNotFoundException extends Throwable {
    public TarefaNotFoundException(String message) {
        super(message);
    }
}
