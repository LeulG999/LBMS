package edu.miu.presentation;

import edu.miu.model.Book;
import edu.miu.model.BookCopy;
import edu.miu.repository.LibraryRepository;
import edu.miu.service.LibraryService;
import edu.miu.utility.JSONConvertor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        LibraryRepository libraryRepository = new LibraryRepository();
        LibraryService libraryService = new LibraryService(libraryRepository);

        loadSampleBooks(libraryService);

        Map<String, Object> output = new LinkedHashMap<>();
        output.put("allBooks", libraryService.getAllBooks());
        output.put("rareBooks", libraryService.getRareBooks());
        output.put("booksSortedByPriceDescending", libraryService.getBooksSortedByPriceDescending());
        output.put("totalInventoryValue", libraryService.calculateInventoryValue());

        System.out.println(JSONConvertor.toJson(output));
    }

    private static void loadSampleBooks(LibraryService libraryService) {
        Book effectiveJava = new Book(
                "978-0134685991",
                "Effective Java",
                "Joshua Bloch",
                45.50,
                Arrays.asList(
                        new BookCopy("EJ-001", true, LocalDate.of(2024, 1, 10)),
                        new BookCopy("EJ-002", false, LocalDate.of(2024, 1, 15)),
                        new BookCopy("EJ-003", true, LocalDate.of(2024, 2, 1))
                )
        );

        Book cleanCode = new Book(
                "978-0132350884",
                "Clean Code",
                "Robert C. Martin",
                39.99,
                Arrays.asList(
                        new BookCopy("CC-001", true, LocalDate.of(2023, 9, 5)),
                        new BookCopy("CC-002", true, LocalDate.of(2023, 9, 8))
                )
        );

        Book domainDrivenDesign = new Book(
                "978-0321125217",
                "Domain-Driven Design",
                "Eric Evans",
                59.95,
                Arrays.asList(
                        new BookCopy("DDD-001", false, LocalDate.of(2022, 6, 20))
                )
        );

        libraryService.addBook(effectiveJava);
        libraryService.addBook(cleanCode);
        libraryService.addBook(domainDrivenDesign);
    }
}
