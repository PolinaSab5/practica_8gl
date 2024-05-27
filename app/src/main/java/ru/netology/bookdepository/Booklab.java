package ru.netology.bookdepository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booklab {
    private static Booklab sBooklab;
    private List<Book> mBooks;
    public static Booklab get(Context context){
        if (sBooklab == null){
            sBooklab = new Booklab(context);
        }
        return sBooklab;
    }
    private Booklab(Context context){
      mBooks = new ArrayList<>();
      for (int i = 0; i < 100; i++){
          Book book = new Book();
          book.setTitle("Book #"+ i);
          book.setReaded(i % 2 ==0);// Для каждого второго объекта
          mBooks.add(book);
      }
    }
    public List<Book> getBooks(){
        return mBooks;
    }
    public Book getBook(UUID id){
        for (Book book : mBooks){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }
}
