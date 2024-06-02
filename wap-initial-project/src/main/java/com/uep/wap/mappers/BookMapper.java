package com.uep.wap.mappers;

import com.uep.wap.dto.BookDto;
import com.uep.wap.model.Book;
import java.util.List;

public interface BookMapper {

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto bookDto);

    List<BookDto> bookListToBookDtoList(List<Book> books);
}
