package com.santi.curso.springboot.jpa.springboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
//import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.santi.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.santi.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.santi.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// list();
		// findOne();
		// create();
		// update();
		// delete();
		// delete2();
		// personalizedQueries();
		// personalizedQueries2();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperAndLowerCase();
		// personalizedQueriesBetween();
		// queriesFunctionAggregation();
		// subQueries();
		whereIn();

	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("============== consulta where in ==============");
		List<Person> persons = personRepository.getPersonsByIds(Arrays.asList(1L, 2L, 5L, 7L));
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("============== consulta por el nombre más corto y su largo ==============");
		List<Object[]> registers = personRepository.getShortestName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("============== consulta para obtener el último registro de persona ==============");
		Optional<Person> optionalPerson = personRepository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {

		System.out.println("============== consulta con el total de registros de la tabla personas ==============");
		Long count = personRepository.getTotalPerson();
		System.out.println(count);

		System.out.println("============== consulta con el valor mínimo del id ==============");
		Long min = personRepository.getMinId();
		System.out.println(min);

		System.out.println("============== consulta con el valor máximo del id ==============");
		Long max = personRepository.getMaxId();
		System.out.println(max);

		System.out.println("============== consulta con el nombre y su largo ==============");
		List<Object[]> regs = personRepository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("============== consulta con el nombre más corto ==============");
		Integer minLengthName = personRepository.getMinNameLength();
		System.out.println(minLengthName);

		System.out.println("============== consulta con el nombre más largo ==============");
		Integer maxLengthName = personRepository.getMaxNameLength();
		System.out.println(maxLengthName);

		System.out.println(
				"============== consultas resumen de funciones de agregacion min, max, sum, avg, count ==============");
		Object[] resumeReg = (Object[]) personRepository.getResumeAggregationFunction();
		System.out.println(
				"min=" + resumeReg[0] + ", max=" + resumeReg[1] + ", sum=" + resumeReg[2] + ", avg=" + resumeReg[3]
						+ ", count=" + resumeReg[4]);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out.println("============== consultas por rangos ==============");
		List<Person> persons = personRepository.findByIdBetweenOrderByNameAsc(2L, 5L);
		persons.forEach(System.out::println);

		persons = personRepository.findByNameBetweenOrderByNameDescLastnameAsc("J", "P");
		persons.forEach(System.out::println);

		persons = personRepository.findAllByOrderByNameDesc();
		persons.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("============== consulta nombres y apellidos de personas ==============");
		List<String> names = personRepository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("============== consulta nombres y apellidos mayúscula ==============");
		names = personRepository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);
		System.out.println("============== consulta nombres y apellidos minúscula ==============");
		names = personRepository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
		System.out.println("============== consulta personalizada persona upper y lower case ==============");
		List<Object[]> regs = personRepository.findAllPersonDataListCase();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido="
				+ reg[2] + ", lenguaje=" + reg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("============== consultas con nombres de personas ==============");
		List<String> names = personRepository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("============== consultas con nombres únicos de personas ==============");
		names = personRepository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("============== consulta con lenguajes de programación únicos ==============");
		List<String> languages = personRepository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("============== consulta con lenguajes de programación únicos ==============");
		Long totalLanguage = personRepository.findAllProgrammingLanguageDistinctCount();
		System.out.println("total de lenguajes de programación: " + totalLanguage);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println("============== consulta por objeto persona y lenguaje de programación ==============");
		List<Object[]> personsRegs = personRepository.findAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println(
				"============== consulta que puebla y devuelve objeto entity de una instancia personalizada ==============");
		List<Person> persons = personRepository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println(
				"============== consulta que puebla y devuelve objeto dto de una clase personalizada ==============");
		List<PersonDto> personsDto = personRepository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("============== consulta solo el nombre por el id ==============");
		System.out.println("Ingrese el id:");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("============== mostrando solo el nombre ==============");
		String name = personRepository.getNameById(id);
		System.out.println(name);

		System.out.println("============== mostrando solo el id ==============");
		Long idDb = personRepository.getIdById(id);
		System.out.println(idDb);

		System.out.println("============== mostrando nombre completo con concat ==============");
		String fullName = personRepository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("============== consulta por campos personalizados por el id ==============");
		// Object[] personReg = (Object[]) personRepository.obtenerPersonDataById(id);
		// System.out.println("id=" + personReg[0] + ", nombre=" + personReg[1] + ",
		// apellido=" + personReg[2]
		// + ", lenguaje=" + personReg[3]);
		Optional<Object> optionalReg = personRepository.obtenerPersonDataById(id);
		if (optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.get();
			System.out.println("id=" + personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2]
					+ ", lenguaje=" + personReg[3]);
		}

		System.out.println("============== consulta por campos personalizados lista ==============");
		List<Object[]> regs = personRepository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido="
				+ reg[2] + ", lenguaje=" + reg[3]));
	}

	@Transactional
	public void delete2() {

		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);

		optionalPerson.ifPresentOrElse(personRepository::delete,
				() -> System.out.println("No se encontró la persona con id: " + id));

		personRepository.findAll().forEach(System.out::println);

		scanner.close();
	}

	@Transactional
	public void delete() {

		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar:");
		Long id = scanner.nextLong();
		personRepository.deleteById(id);

		personRepository.findAll().forEach(System.out::println);

		scanner.close();
	}

	@Transactional
	public void update() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);

		if (optionalPerson.isPresent()) {
			Person personDB = optionalPerson.get();
			System.out.println(personDB);
			System.out.println("Ingrese el lenguaje de programación:");
			String programmingLanguage = scanner.next();
			personDB.setProgrammingLanguage(programmingLanguage);
			Person personUpdated = personRepository.save(personDB);
			System.out.println(personUpdated);
		} else {
			System.out.println("No se encontró la persona con id: " + id);
		}

		// optionalPerson.ifPresentOrElse(person -> {
		// System.out.println(person);
		// System.out.println("Ingrese el lenguaje de programación:");
		// String programmingLanguage = scanner.next();
		// person.setProgrammingLanguage(programmingLanguage);
		// Person personDb = personRepository.save(person);
		// System.out.println(personDb);
		// }, () -> System.out.println("No se encontro la persona con id: " + id));

		scanner.close();

	}

	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre:");
		String name = scanner.next();
		System.out.println("Ingrese el apellido:");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programación:");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		// Person person = new Person(null, "Lalo", "Thor", "Python");

		Person personNew = personRepository.save(person);
		System.out.println(personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne() {
		// Person person = personRepository.findById(1L).orElseThrow();
		// System.out.println(person);

		// Person person = null;
		// Optional<Person> optionalPerson = personRepository.findById(1L);
		// if (optionalPerson.isPresent()) {
		// person = optionalPerson.get();
		// }
		// System.out.println(person);

		// personRepository.findById(1L).ifPresent(person ->
		// System.out.println(person));
		// personRepository.findById(8L).ifPresent(System.out::println);

		personRepository.findOne(1L).ifPresent(System.out::println);

		personRepository.findOneName("Pepe").ifPresent(System.out::println);
		personRepository.findOneLikeName("Pe").ifPresent(System.out::println);

		personRepository.findByNameContaining("Se").ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void list() {

		// List<Person> persons = (List<Person>) personRepository.findAll();
		// List<Person> persons = (List<Person>)
		// personRepository.findByProgrammingLanguage("Java");
		// List<Person> persons = (List<Person>)
		// personRepository.buscarByProgrammingLanguage("Java", "Andres");
		List<Person> persons = personRepository.findByProgrammingLanguageAndName("Java", "Andres");

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValues = personRepository.obtenerPersonData("Maria");

		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
			System.out.println(Arrays.toString(person));

		});
	}

}
