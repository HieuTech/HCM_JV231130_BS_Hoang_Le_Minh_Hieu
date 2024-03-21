package ra.bussiness;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {

    private int bookId;
    private String bookName;
    private String descriptions;
    private String author;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus;

    private static int count = 0;
    private Book[] books = new Book[0];

    public Book() {

    }

    public Book(int bookId, String bookName, String descriptions, String author, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.descriptions = descriptions;
        this.author = author;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", author='" + author + '\'' +
                ", importPrice=" + importPrice +
                ", exportPrice=" + exportPrice +
                ", interest=" + interest +
                ", bookStatus=" + bookStatus +
                '}';
    }

    public void inputData(Scanner scanner) {
        byte arrLength = (byte) books.length;
        float maxValue = 0;
        for (Book book : books) {
                float interest = (float) (book.getExportPrice() - book.getImportPrice());
                book.setInterest(interest);
            if(book.getInterest() > maxValue){
                maxValue = book.getInterest();
            }
        }
        float secondMaxValue = (byte) books[0].getInterest(), currentValue;
        for (int i = 0; i < arrLength; i++) {
            currentValue = books[i].getInterest();
            if(secondMaxValue < currentValue){
                if(secondMaxValue < maxValue && currentValue != maxValue){
                    secondMaxValue = currentValue;
                }
            }
        }
        System.out.printf("So lon thu 2  la %f " ,secondMaxValue);

    }



    public void deleteBookById(Scanner scanner){
        System.out.println("Input ProductID you want to cal the delete");

        int bookId = Integer.parseInt(inputFromUser(scanner, "\\d*"));
        byte arrLength = (byte) books.length;
        Book[] newArr = new Book[arrLength - 1];
        boolean isValid = true;
        for (byte i = 0; i< arrLength; i ++) {
            newArr[i] = books[i];
            if (books[i].getBookId() == bookId) {
                isValid = false;
                continue;
            }
        }
        books = newArr;
        if(isValid){
            System.out.println("BookId not found");

        }

    }
    public void findBookByNameOrDesc(Scanner scanner){
        System.out.println("Input Product name or Product Desc you are looking for");

        String inputValue = inputFromUser(scanner, "\\w{0,10}");
        boolean isValid = true;
        for (Book book : books) {
            if (book.getBookName().equals(inputValue) || book.getDescriptions().equals(inputValue)) {
                System.out.println(book);
                isValid = false;
            }
        }
        if(isValid){
            System.out.println("BookName or Book Desc not found");
        }



    }
    public void updateBookById(Scanner scanner){
        System.out.println("Input ProductID you want to update");

        int bookId = Integer.parseInt(inputFromUser(scanner, "\\d*"));
        byte arrLength = (byte) books.length;
            boolean isValid = true;
        for (byte i = 0; i< arrLength; i ++) {
            if (books[i].getBookId() == bookId) {
                System.out.println("input the bookName");
                books[i].setBookName(getBookName(scanner));
                System.out.println("input the author");
                books[i].setAuthor(getBookAuthor(scanner));
                System.out.println("input the Description");
                books[i].setDescriptions(getBookDesc(scanner));
                System.out.println("input the importPrice");
                books[i].setImportPrice(getImportPrice(scanner));
                System.out.println("input the exportPrice");
                books[i].setExportPrice(getExportPrice(scanner, books[i]));

                System.out.println("input the book status");
                books[i].setBookStatus(getBookStatus(scanner));
                isValid = false;
            }

        }
        if(isValid){
            System.out.println("BookId not found");

        }


    }


    public void addBook(Scanner scanner) {


        System.out.println("How many book you want to add?");
        byte quantity = Byte.parseByte(inputFromUser(scanner, "\\b(?:[1-9]|[1-9][0-9]|100)\\b"));
        byte arrLength = (byte) books.length;
        Book[] newArr = new Book[arrLength + quantity];

        //copy oldArr to new Arr
        byte currentIndex = 0;
        for (int i = 0; i < arrLength; i++) {
            newArr[i] = books[i];
            currentIndex++;
        }

        for (int i = currentIndex; i < newArr.length; i++) {
            newArr[currentIndex] = addPerBook(scanner);
            currentIndex++;
        }

        books = newArr;

    }

    public Book addPerBook(Scanner scanner) {
        count ++;
        Book bookAdd = new Book();

        bookAdd.setBookId(count);
        System.out.println("input the bookName");
        bookAdd.setBookName(getBookName(scanner));

        System.out.println("input the author");
        bookAdd.setAuthor(getBookAuthor(scanner));
        System.out.println("input the Description");
        bookAdd.setDescriptions(getBookDesc(scanner));
        System.out.println("input the importPrice");
        bookAdd.setImportPrice(getImportPrice(scanner));
        System.out.println("input the exportPrice");
        bookAdd.setExportPrice(getExportPrice(scanner, bookAdd));

        System.out.println("input the book status");
        bookAdd.setBookStatus(getBookStatus(scanner));

        return bookAdd;
    }

    public String getBookName(Scanner scanner) {

        return inputFromUser(scanner, "\\w*");
    }

    public String getBookAuthor(Scanner scanner) {
        return inputFromUser(scanner, "\\w*");
    }

    public String getBookDesc(Scanner scanner) {
        return inputFromUser(scanner, "\\w*");
    }

    public boolean getBookStatus(Scanner scanner) {
        return Boolean.parseBoolean(inputFromUser(scanner, "(true|false)"));
    }
    public double getImportPrice(Scanner scanner) {


        double valueImportPrice = Double.parseDouble(inputFromUser(scanner, "\\d*"));

        if (valueImportPrice > 0) {
            return valueImportPrice;
        } else {
            System.out.println("Import price must greater than 0");
            return getImportPrice(scanner);
        }

    }

    public double getExportPrice(Scanner scanner, Book book) {


        double valueExportPrice = Double.parseDouble(inputFromUser(scanner, "\\d*"));

        if (valueExportPrice >= book.getImportPrice() * 1.2) {
            return valueExportPrice;
        } else {
            System.out.println("Import price must greater 20% than import Price");
            return getExportPrice(scanner, book);
        }

    }


    public void displayAllBook() {
        if (books.length == 0) {
            System.out.println("Your Book Array is empty");
        }
        for (Book book : books) {
            System.out.println(book.toString());
        }

    }


    public String inputFromUser(Scanner scanner, String regex) {

        while (true) {
            String inputValue = scanner.nextLine();
            boolean checkValid = isValid(regex, inputValue);
            if (checkValid) {
                return inputValue;
            }
            System.out.println("Input invalid format, please type again");
        }


    }


    public boolean isValid(String regex, String inputValue) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputValue);
        return matcher.matches();

    }


}
