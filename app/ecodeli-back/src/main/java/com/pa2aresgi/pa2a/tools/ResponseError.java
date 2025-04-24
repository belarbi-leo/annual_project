package com.pa2aresgi.pa2a.tools;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseError {
    private List<String> errors;

    public ResponseError() {
        this.errors = new ArrayList<>();
    }

    public ResponseError(List<String> errors) {
        this.errors = errors;
    }

    public ResponseError(String error) {
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }
}
