package guru.springframework.spring6webapp.Bootstrap;

import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.PublisherRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstname("Eric");
        eric.setLastname("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        // Add book/author to repos
        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstname("Rod");
        rod.setLastname("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757485");


        // Add book/author to repos
        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        

        Publisher penbooks = new Publisher();
        penbooks.setPublishername("Penguin Books");
        penbooks.setAddress("21 Jump street");
        penbooks.setCity("New York");
        penbooks.setState("New York");
        penbooks.setZip("010101");

        // add publisher to repository
        Publisher penbooksSaved = publisherRepository.save(penbooks);

        // add book to author
        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        // add author to book
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        // Add publisher to book
        dddSaved.setPublisher(penbooksSaved);
        noEJBSaved.setPublisher(penbooksSaved);

        // persist(save) books
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        // persist(save) Authors
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());

        

        System.out.println("Publishers Count: "+ publisherRepository.count() );

    }

}
