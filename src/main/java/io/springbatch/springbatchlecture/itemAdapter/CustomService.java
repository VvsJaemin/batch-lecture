package io.springbatch.springbatchlecture.itemAdapter;

public class CustomService <T>{

    public void customWrite(T item) {
        System.out.println("item = " + item);
    }

}
