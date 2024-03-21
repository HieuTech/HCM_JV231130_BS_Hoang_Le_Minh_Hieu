package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");

        while (true){
            System.out.println("1: Add new book and input info per book");
            System.out.println("2: Display all book");
            System.out.println("3: Find book that import price is second highest");
            System.out.println("4: Delete Book By Id");
            System.out.println("5: Find Book By Name");
            System.out.println("6: Update Book By Id");
            System.out.println("7: Sign Out");

            byte choice = Byte.parseByte(book.inputFromUser(scanner ,"\\d"));

            switch (choice){
                case 1:
                    book.addBook(scanner);
                    break;

                case 2:
                    book.displayAllBook();
                    break;

                case 3:
                    book.inputData(scanner);
                    break;
                case 4:
                    book.deleteBookById(scanner);
                    break;
                case 5:
                    book.findBookByNameOrDesc(scanner);
                    break;
                case 6:
                    book.updateBookById(scanner);
                    break;

                case 7:

                    return;

                default:
                    System.out.println("Your choice is out of range");
                    break;
            }

        }
    }
}
