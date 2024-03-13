package com.example.memorydb.book.service;

import com.example.memorydb.book.db.BookRepository;
import com.example.memorydb.book.model.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// 생성자와 같은 꼴 Service 어노테이션으로 Spring Context에 저장될 때,
// Constructor로 작성하면 Spring에서 Bean 영역에서 context를 찾아와서 주입함.
// RequiredArgsConstructor는 'final'로 선언된 필드에 대한 생성자 작성을 줄여 가독성을 올림 (Lombok)
// Autowired와 같은 어노테이션으로 봐도되나 차이점이 있음.
// Autowired는 final이 굳이 필요없음. 자동으로 Spring이 직접 bean을 찾음.
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository = new BookRepository();
/*
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
*/

    public BookEntity create(BookEntity book) {
        return bookRepository.save(book);
    }

    public List<BookEntity> findALl() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void delete(Long id) {
        bookRepository.delete(id);
    }
}
