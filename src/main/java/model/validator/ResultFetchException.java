package model.validator;

import java.util.List;
import java.util.stream.Collectors;

public class ResultFetchException extends RuntimeException{
    private final List<String> errors;
    public ResultFetchException(List<String> errors){
        super("Failde to fetch the result as operation encountered errors");
        this.errors=errors;
    }
    @Override
    public String toString(){
        //fiecare obiect din lista va fi mapat la toString-ul lui(vom apela toString pe fiecare)
        return errors.stream().map(Object::toString).collect(Collectors.joining(",")) + super.toString();
    }
}
