package com.example.been;

import lombok.Data;

@Data
public class ResponseEntity<T> {
    int code;
    String info;
    T data;

    public ResponseEntity(int code,String info){
        this.code=code;
        this.info=info;
    }

    public ResponseEntity(int code,String info,T t){
        this.code=code;
        this.info=info;
        this.data=t;
    }
}
