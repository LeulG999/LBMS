package edu.miu.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.miu.model.Book;
import edu.miu.model.BookCopy;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class JSONConvertor {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) JSONConvertor::serializeLocalDate)
            .registerTypeAdapter(Book.class, (JsonSerializer<Book>) JSONConvertor::serializeBook)
            .registerTypeAdapter(BookCopy.class, (JsonSerializer<BookCopy>) JSONConvertor::serializeBookCopy)
            .create();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    private static JsonPrimitive serializeLocalDate(LocalDate localDate, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(localDate.toString());
    }

    private static JsonObject serializeBook(Book book, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isbn", book.getIsbn());
        jsonObject.addProperty("title", book.getTitle());
        jsonObject.addProperty("author", book.getAuthor());
        jsonObject.addProperty("category", book.getCategory().name());
        jsonObject.addProperty("price", book.getPrice());
        jsonObject.addProperty("totalCopies", book.getTotalCopies());
        jsonObject.addProperty("availableCopies", book.getAvailableCopies());
        jsonObject.addProperty("inventoryValue", book.getInventoryValue());
        jsonObject.add("copies", context.serialize(book.getCopies()));
        return jsonObject;
    }

    private static JsonObject serializeBookCopy(BookCopy bookCopy, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("copyNumber", bookCopy.getCopyNumber());
        jsonObject.addProperty("available", bookCopy.isAvailable());
        jsonObject.add("acquiredDate", context.serialize(bookCopy.getAcquiredDate()));
        return jsonObject;
    }
}
