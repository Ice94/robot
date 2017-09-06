package com.java.academy.controller;

import com.java.academy.model.Book;
import com.java.academy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/robot")
public class MainController {
	
	@Autowired
	BookService bookService;

	@RequestMapping()
	public String welcome(Model model) {
		return "start";
	}
	
	@RequestMapping("/books")
	public String books(Model model) {
		return "books";
	}

	@RequestMapping("/booksTiles")
	public String books2(Model model) {
		return "booksTiles";
	}

    @RequestMapping(value = "/rest/books/{filter}/{data}/{ByPriceRange}", method = RequestMethod.GET)
    public @ResponseBody Set<Book> read(@PathVariable String filter, @PathVariable String data,
				   @MatrixVariable(pathVar="ByPriceRange") Map<String, List<String>> filterParams) {
        Set<Book> books = bookService.getBooksByParams(filter, data, filterParams);

        return books;
    }

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Wewnętrzny błąd serwera.")
	public void handleServerErrors(Exception ex) {	}
}