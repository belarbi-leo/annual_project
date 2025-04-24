package com.pa2aresgi.pa2a.tools;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResponseErrorV2 {

    private Map<String,List<String>> errors;

    public ResponseErrorV2(){
        this.errors = new HashMap<>();
    }

    public ResponseErrorV2(String error, String message){
        this.errors = new HashMap<>();
        this.errors.put(error,List.of(message));
    }

    public ResponseErrorV2(String error, List<String> errors) {
        this.errors = new HashMap<>();
        this.errors.put(error, errors);
    }

    public ResponseErrorV2(List<String> errorsNames, List<?> errors) {
        this.errors = new HashMap<>();
        Object obj = errors.getFirst();
        if (obj instanceof String){
            if (errorsNames.size() != errors.size()) throw new IllegalArgumentException("Number of errors' labels and errors do not match");
            for (int i = 0; i < errorsNames.size(); i++) {
                this.errors.put(errorsNames.get(i), List.of((String)errors.get(i)));
            }
        } else if (obj instanceof List<?> errorsList){
            if (errorsNames.size() != errors.size()) throw new IllegalArgumentException("Number of errors' labels and errors' list do not match");
            for (int i = 0; i < errorsNames.size(); i++) {
                for (Object ob : errorsList) {
                    if (!(ob instanceof String)) throw new IllegalArgumentException("Expected String inside list at index " + i);
                }
                @SuppressWarnings("unchecked")  //Warning apparent si non modifié, donc on check avant si les éléments sont bien des strings et on peut suppress le warning vu que c'est donc 100% safe maintenant (obligé de mettre l'annotation sur une affectation de variable, parce que sinon dans un bloc ca ne fonctionne pas)
                List<String> erList = (List<String>)errorsList.get(i);
                this.errors.put(errorsNames.get(i), erList);
            }
        }
    }

    public void add(String error, String errors) {
        this.errors.put(error, List.of(errors));
    }

    public void add(String error, List<String> errors) {
        this.errors.put(error, errors);
    }

}
