package cz.muni.fi.pv168.project.data;

import cz.muni.fi.pv168.project.model.Department;
import cz.muni.fi.pv168.project.model.Employee;
import cz.muni.fi.pv168.project.model.Gender;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Month.*;
import static java.time.temporal.ChronoUnit.DAYS;

public class TestDataGenerator {

    private static final Map<Gender, List<String>> FIRST_NAMES = Map.of(
            Gender.MALE, List.of("Petr", "Pavel", "Tomáš", "Jan", "Lukáš", "Patrik", "Jiří", "Karel", "Albert", "František"),
            Gender.FEMALE, List.of("Petra", "Pavla", "Tamara", "Jana", "Lucie", "Patricie", "Jiřina", "Karla", "Alena", "Františka")
    );

    private static final Map<Gender, List<String>> LAST_NAMES = Map.of(
            Gender.MALE, List.of("Novák", "Novotný", "Adámek", "Černý", "Hrdina", "Šťastný", "Žužlík", "Kvasnička", "Vopička", "Řezníček"),
            Gender.FEMALE, List.of("Nováková", "Novotná", "Adámková", "Černá", "Hrdinová", "Šťastná", "Žužlíková", "Kvasničková", "Vopičková", "Řezníčková")
    );

    private static final List<Department> DEPARTMENTS = List.of(
            new Department("IT", "007"),
            new Department("Sales", "666"),
            new Department("HR", "112")
    );

    private static final LocalDate MIN_BIRTH_DATE = LocalDate.of(1950, JANUARY, 1);
    private static final LocalDate MAX_BIRTH_DATE = LocalDate.of(2002, DECEMBER, 31);

    private final Random random = new Random(129863358486L);

    public Employee createTestEmployee() {
        Gender gender = selectRandom(Arrays.asList(Gender.values()));
        String firstName = selectRandom(FIRST_NAMES.get(gender));
        String lastName = selectRandom(LAST_NAMES.get(gender));
        LocalDate birthDate = selectRandomLocalDate(MIN_BIRTH_DATE, MAX_BIRTH_DATE);
        Department department = selectRandom(DEPARTMENTS);
        return new Employee(firstName, lastName, gender, birthDate, department);
    }

    public List<Employee> createTestEmployees(int count) {
        return Stream
                .generate(this::createTestEmployee)
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Department> getDepartments() {
        return DEPARTMENTS;
    }

    private <T> T selectRandom(List<T> data) {
        int index = random.nextInt(data.size());
        return data.get(index);
    }

    private LocalDate selectRandomLocalDate(LocalDate min, LocalDate max) {
        int maxDays = Math.toIntExact(DAYS.between(min, max) + 1);
        int days = random.nextInt(maxDays);
        return min.plusDays(days);
    }
}
