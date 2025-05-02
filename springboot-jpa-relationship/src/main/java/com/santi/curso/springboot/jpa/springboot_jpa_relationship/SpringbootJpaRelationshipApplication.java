package com.santi.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.santi.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.santi.curso.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// manyToOne();
		// manyToOneFindByIdClient();
		// oneToMany();
		// oneToManyFindById();
		// removeAddress();
		// removeAddressFindById();
		// oneToManyInvoiceBidireccional();
		// oneToManyInvoiceBidireccionalFindById();
		// removeInvoiceBidireccionalFindById();
		// removeInvoiceBidireccional();
		// oneToOne();
		// oneToOneFindById();
		// oneToOneBidireccional();
		// oneToOneBidireccionalFindById();
		// manyToMany();
		// manyToManyFind();
		// manyToManyFind2();
		// manyToManyRemoveFind();
		// manyToManyRemove();
		// manyToManyBidireccional();
		// manyToManyBidireccionalRemove();
		// manyToManyBidireccionalFind();
		manyToManyRemoveBidireccionalFind();

	}

	@Transactional
	public void manyToManyRemoveBidireccionalFind() {

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));
		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}

	}

	@Transactional
	public void manyToManyBidireccionalFind() {

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));
		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyBidireccionalRemove() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de Spring Boot", "Andres");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}

	}

	@Transactional
	public void manyToManyBidireccional() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de Spring Boot", "Andres");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToManyRemove() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de Spring Boot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}

	}

	public void manyToManyRemoveFind() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {

			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}

	}

	public void manyToManyFind2() {

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	// @Transactional
	// public void manyToManyFind() {
	// // Este metodo no genera registros duplicados en la version 3.4.5 pero hay
	// que
	// // desactivar el cascade
	// // 1. Guardás los cursos primero (si no existen todavía)
	// Course course1 = new Course("Curso de Java Master", "Andres");
	// Course course2 = new Course("Curso de Spring Boot", "Andres");
	// courseRepository.saveAll(List.of(course1, course2));

	// // 2. Los traés ya attachados
	// Course course1Db = courseRepository.findByName("Curso de Java
	// Master").orElseThrow();
	// Course course2Db = courseRepository.findByName("Curso de Spring
	// Boot").orElseThrow();

	// // 3. Creás estudiantes
	// Student student1 = new Student("Jano", "Pura");
	// Student student2 = new Student("Erba", "Doe");

	// // 4. Asignás cursos reales persistidos
	// student1.setCourses(Set.of(course1Db, course2Db));
	// student2.setCourses(Set.of(course2Db));

	// // 5. Guardás estudiantes
	// studentRepository.saveAll(List.of(student1, student2));

	// System.out.println(student1);
	// System.out.println(student2);
	// }

	@Transactional
	public void manyToManyFind() { // duplica registros

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de Spring Boot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void manyToMany() {

		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de Spring Boot", "Andres");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

	}

	@Transactional
	public void oneToOneBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(2L);
		optionalClient.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);

			client.setClientDetails(clientDetails);
			clientRepository.save(client);

			System.out.println(client);

		});

	}

	@Transactional
	public void oneToOneBidireccional() {

		Client client = new Client("Erba", "Pura");

		ClientDetails clientDetails = new ClientDetails(true, 5000);

		client.setClientDetails(clientDetails);
		// clientDetails.setClient(client);
		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void oneToOneFindById() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);

			System.out.println(client);

		});

	}

	@Transactional
	public void oneToOne() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void removeInvoiceBidireccional() {

		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClientDb = clientRepository.findOne(3L);

		optionalClientDb.ifPresent(clientDb -> {
			Invoice invoice3 = new Invoice("compras de la casa", 5000L);
			invoice3.setId(1L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);// invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				clientDb.removeInvoice(invoice);
				// client.getInvoices().remove(invoice);
				// invoice.setClient(null);
				clientRepository.save(clientDb);
				System.out.println(clientDb);
			});
		});

	}

	@Transactional
	public void removeInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOne(1L);

		optionalClientDb.ifPresent(client -> {
			Invoice invoice3 = new Invoice("compras de la casa", 5000L);
			invoice3.setId(1L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);// invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				// client.getInvoices().remove(invoice);
				// invoice.setClient(null);
				clientRepository.save(client);
				System.out.println(client);
			});
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {

		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de oficina", 8000L);

		// List<Invoice> invoices = new ArrayList<>();
		// invoices.add(invoice1);
		// invoices.add(invoice2);
		// client.setInvoices(invoices);

		// invoice1.setClient(client);
		// invoice2.setClient(client);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById() {

		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("El vergel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				Address addressToRemove = c.getAddresses()
						.stream()
						.filter(a -> "Vasco de Gama".equals(a.getStreet()))
						.findFirst()
						.orElseThrow(() -> new RuntimeException("Address not found"));
				c.getAddresses().remove(addressToRemove);
				clientRepository.save(c);
				System.out.println(c);
			});

		});

	}

	@Transactional
	public void removeAddress() {

		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El vergel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});

	}

	@Transactional
	public void oneToManyFindById() {

		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {

			Address address1 = new Address("El vergel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);

		});

	}

	@Transactional
	public void oneToMany() {

		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El vergel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();

			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}

	}

}
