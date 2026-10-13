import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.*;
import org.junit.Test;

public class FindClassmatesTest {

    // null username throws IllegalArgumentException
    @Test
    public void nullUser() {
        ClassesDataSource cds = student -> Collections.emptyList();
        StudentsDataSource sds = course -> Collections.emptyList();
        FriendFinder finder = new FriendFinder(cds, sds);

        assertThrows(IllegalArgumentException.class,
                () -> finder.findClassmates(null));
    }

    // empty username returns an empty set
    @Test
    public void emptyUser() {
        ClassesDataSource cds = student -> Collections.emptyList();
        StudentsDataSource sds = course -> Collections.emptyList();
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.emptySet(), finder.findClassmates(""));
    }

    // null classes data source throws IllegalStateException
    @Test
    public void nullClassesSource() {
        StudentsDataSource sds = course -> Collections.emptyList();
        FriendFinder finder = new FriendFinder(null, sds);

        assertThrows(IllegalStateException.class,
                () -> finder.findClassmates("me"));
    }

    // null students data source throws IllegalStateException
    @Test
    public void nullStudentsSource() {
        ClassesDataSource cds = student -> Collections.singletonList("3107");
        FriendFinder finder = new FriendFinder(cds, null);

        assertThrows(IllegalStateException.class,
                () -> finder.findClassmates("me"));
    }

    // null user class list returns an empty set
    @Test
    public void nullUserClasses() {
        ClassesDataSource cds = student -> null;
        StudentsDataSource sds = course -> Collections.emptyList();
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.emptySet(), finder.findClassmates("me"));
    }

    // empty user class list returns an empty set
    @Test
    public void emptyUserClasses() {
        ClassesDataSource cds = student -> Collections.emptyList();
        StudentsDataSource sds = course -> Collections.emptyList();
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.emptySet(), finder.findClassmates("me"));
    }

    // null class names are ignored while matching valid classes
    @Test
    public void nullClassName() {
        ClassesDataSource cds = student -> {
            if ("me".equals(student)) {
                return Arrays.asList(null, "3107");
            }
            return Collections.singletonList("3107");
        };
        StudentsDataSource sds = course -> Arrays.asList("me", "Anya");
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.singleton("Anya"), finder.findClassmates("me"));
    }

    // null student list returns an empty set
    @Test
    public void nullStudentList() {
        ClassesDataSource cds = student -> Collections.singletonList("3107");
        StudentsDataSource sds = course -> null;
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.emptySet(), finder.findClassmates("me"));
    }

    // empty student list returns an empty set
    @Test
    public void emptyStudentList() {
        ClassesDataSource cds = student -> Collections.singletonList("3107");
        StudentsDataSource sds = course -> Collections.emptyList();
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.emptySet(), finder.findClassmates("me"));
    }

    // null student names are skipped without losing valid matches
    @Test
    public void nullStudentName() {
        ClassesDataSource cds = student -> Collections.singletonList("3107");
        StudentsDataSource sds = course -> Arrays.asList(null, "me", "Anya");
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.singleton("Anya"), finder.findClassmates("me"));
    }

    // student with a null class list is skipped
    @Test
    public void nullOtherClasses() {
        ClassesDataSource cds = student -> {
            if ("Anya".equals(student)) {
                return null;
            }
            return Collections.singletonList("3107");
        };
        StudentsDataSource sds = course -> Arrays.asList("me", "Anya", "Mana");
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(Collections.singleton("Mana"), finder.findClassmates("me"));
    }

    // only other students taking all of my classes are returned
    @Test
    public void matchingClassmates() {
        ClassesDataSource cds = student -> {
            if (student.equals("me") || student.equals("Mana")) {
                return Arrays.asList("3107", "3203");
            }
            if (student.equals("Anya")) {
                return Arrays.asList("3107", "3827");
            }
            if (student.equals("Valeria")) {
                return Arrays.asList("3203");
            }
            if (student.equals("Zoe")) {
                return Arrays.asList("3107", "3157", "3203");
            }
            if (student.equals("Samantha")) {
                return Arrays.asList("3157");
            }
            return Collections.emptyList();
        };
        StudentsDataSource sds = course -> {
            if (course.equals("3107")) {
                return Arrays.asList("me", "Anya", "Mana", "Zoe");
            }
            if (course.equals("3203")) {
                return Arrays.asList("me", "Mana", "Valeria", "Zoe");
            }
            return Collections.emptyList();
        };
        FriendFinder finder = new FriendFinder(cds, sds);

        assertEquals(new HashSet<>(Arrays.asList("Mana", "Zoe")),
                finder.findClassmates("me"));
    }
}