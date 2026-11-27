package mn.edu.must.sqat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    ScholarshipCalculator calc;

    @BeforeEach
    void setUp() { calc = new ScholarshipCalculator(); }

    // ---- letterGrade ----

    @Test
    void letterGrade_returnsSomethingForAllRanges() {
        assertEquals("A", calc.letterGrade(95));
        assertEquals("B", calc.letterGrade(85));
        assertEquals("C", calc.letterGrade(75));
        assertEquals("D", calc.letterGrade(65));
        assertEquals("F", calc.letterGrade(30));
    }

    @Test
    void letterGrade_exactBoundaries() {
        assertAll(
            () -> assertEquals("A", calc.letterGrade(90.0)),
            () -> assertEquals("A", calc.letterGrade(100.0)),
            () -> assertEquals("B", calc.letterGrade(80.0)),
            () -> assertEquals("B", calc.letterGrade(89.99)),
            () -> assertEquals("C", calc.letterGrade(70.0)),
            () -> assertEquals("C", calc.letterGrade(79.99)),
            () -> assertEquals("D", calc.letterGrade(60.0)),
            () -> assertEquals("D", calc.letterGrade(69.99)),
            () -> assertEquals("F", calc.letterGrade(0.0)),
            () -> assertEquals("F", calc.letterGrade(59.99))
        );
    }

    @Test
    void letterGrade_invalidInputs_throw() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    // ---- gpa ----

    @Test
    void gpa_isAlwaysInRange() {
        double g1 = calc.gpa(new double[]{95, 85});
        double g2 = calc.gpa(new double[]{75, 65});
        double g3 = calc.gpa(new double[]{40});
        assertEquals(3.5, g1, 1e-6);
        assertEquals(1.5, g2, 1e-6);
        assertEquals(0.0, g3, 1e-6);
    }

    @Test
    void gpa_singleGrade_eachLetter() {
        assertAll(
            () -> assertEquals(4.0, calc.gpa(new double[]{95.0}), 1e-6),
            () -> assertEquals(3.0, calc.gpa(new double[]{85.0}), 1e-6),
            () -> assertEquals(2.0, calc.gpa(new double[]{75.0}), 1e-6),
            () -> assertEquals(1.0, calc.gpa(new double[]{65.0}), 1e-6),
            () -> assertEquals(0.0, calc.gpa(new double[]{50.0}), 1e-6)
        );
    }

    @Test
    void gpa_invalidInputs_throw() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    // ---- scholarship ----

    @Test
    void scholarship_isAlwaysValidPercent() {
        int s1 = calc.scholarship(3.9, 16, false);
        int s2 = calc.scholarship(3.5, 13, false);
        int s3 = calc.scholarship(3.1, 5, false);
        int s4 = calc.scholarship(2.0, 1, false);
        int s5 = calc.scholarship(4.0, 20, true);
        assertEquals(100, s1);
        assertEquals(70, s2);
        assertEquals(50, s3);
        assertEquals(0, s4);
        assertEquals(0, s5);
    }

    @Test
    void scholarship_boundaries_and_tiers() {
        assertAll(
            () -> assertEquals(0, calc.scholarship(0.0, 0, false)),
            () -> assertEquals(50, calc.scholarship(4.0, 0, false)),
            () -> assertEquals(100, calc.scholarship(3.8, 15, false)),
            () -> assertEquals(70, calc.scholarship(3.8, 14, false)),
            () -> assertEquals(70, calc.scholarship(3.4, 12, false)),
            () -> assertEquals(50, calc.scholarship(3.4, 11, false)),
            () -> assertEquals(50, calc.scholarship(3.0, 10, false)),
            () -> assertEquals(0, calc.scholarship(2.9, 15, false))
        );
    }

    @Test
    void scholarship_invalidInputs_throw() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false));
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false));
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false));
    }
}
