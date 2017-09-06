package com.java.academy.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Test
public class BookTest {


    @DataProvider
    public Object[][] booksValues(){
        return new Object[][] {
            {"Pan Tadeusz", "Mickiewicz", "-", "-30%", BigDecimal.valueOf(100.00),
                    new Bookstore("Unknown", "http://nic.pl"), "www.image.jpg", "www.link.pl"},
            {"Potop", "Sienkiewicz", "-", "-25%", BigDecimal.valueOf(25.99),
                    new Bookstore("Unknown", "http://cos.pl"), "www.image.jpg", "www.link.pl"}
        };
    }


    @Test(dataProvider = "booksValues")
    public void shouldCreateBookWithAppropriateValues(String title, String author, String category, String discount,
                                                      BigDecimal price, Bookstore bookstore, String image, String link) {

        //given
        Book book = new Book(title, author, category, discount, price, bookstore);

        //when
        book.setImgUrl(image);
        book.setUrl(link);

        //then
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(book.getTitle(), title);
        sa.assertEquals(book.getAuthor(), author);
        sa.assertEquals(book.getCategory(), category);
        sa.assertEquals(book.getPromoDetails(), discount);
        sa.assertEquals(book.getPrice(), price);
        sa.assertEquals(book.getBookstore(), bookstore);
        sa.assertEquals(book.getImgUrl(), image);
        sa.assertEquals(book.getUrl(), link);
        sa.assertEquals(book.getSubtitle(), null);
        sa.assertAll();
    }

    @Test(dataProvider = "booksValues")
    public void shouldCreateBookWithAppropriateBySetters(String title, String author, String category, String discount,
                                                      BigDecimal price, Bookstore bookstore, String image, String link) {
        //given
        Book book = new Book();
        Date start = new Date();
        List<CollectionTime> collectDates = new ArrayList<>();
        CollectionTime time;


        //when
        book.setBookstore(bookstore);
        book.setAuthor(author);
        book.setPrice(price);
        book.setCategory(category);
        book.setTitle(title);
        book.setPromoDetails(discount);
        book.setImgUrl(image);
        book.setUrl(link);
        book.setSubtitle("-");
        time = new CollectionTime(book, book.getPrice(), start);
        book.setCollectedDates(collectDates);
        book.addCollectedDates(time);

        //then
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(book.getTitle(), title);
        sa.assertEquals(book.getAuthor(), author);
        sa.assertEquals(book.getCategory(), category);
        sa.assertEquals(book.getPromoDetails(), discount);
        sa.assertEquals(book.getPrice(), price);
        sa.assertEquals(book.getBookstore(), bookstore);
        sa.assertEquals(book.getImgUrl(), image);
        sa.assertEquals(book.getUrl(), link);
        sa.assertEquals(book.getSubtitle(), "-");
        sa.assertEquals(book.getCollectedDates().size(), 1);
        sa.assertAll();
    }

    @Test (dataProvider = "booksValues")
    public void shouldReturnTrueIfEquals(String title, String author, String category, String discount,
                                         BigDecimal price, Bookstore bookstore, String image, String link) {

        //given
        Book book = new Book(title, author, category, discount, price, bookstore);
        Book book1 = new Book(title, author, category, discount, price, bookstore);

        //when
        book.setImgUrl(image);
        book.setUrl(link);

        //then
        assertTrue(book1.equals(book));
        assertTrue(book.equals(book1));

    }

    @Test (dataProvider = "booksValues")
    public void shouldReturnSameHashCodeIfEquals(String title, String author, String category, String discount,
                                         BigDecimal price, Bookstore bookstore, String image, String link) {

        //given
        Book book = new Book(title, author, category, discount, price, bookstore);
        Book book1 = new Book(title, author, category, discount, price, bookstore);

        //when
        book.setImgUrl(image);
        book.setUrl(link);

        //then
        assertEquals(book.hashCode(), book1.hashCode());
    }

    @Test (dataProvider = "booksValues")
    public void shouldRerunAppropriateString(String title, String author, String category, String discount,
                                             BigDecimal price, Bookstore bookstore, String image, String link) {

        //given
        Book book = new Book(title, author, category, discount, price, bookstore);

        //when
        book.setImgUrl(image);
        book.setUrl(link);

        //then
        assertEquals(book.toString(), ("Book{" +
                "title='" + title + '\'' +
                ", subtitle='" + null + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", promoDetails='" + discount + '\'' +
                ", imgUrl='" + image + '\'' +
                ", url='" + link + '\'' +
                ", bookstore=" + bookstore +
                '}'));
    }
}
