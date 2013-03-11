package examples.object.clone;

/**
 *
 * @author blecherl
 */
public class CloneMain {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student goodStudent =
                new Student("Urkel", 123, new int[]{100, 100, 100, 100});

        Student badStudent = goodStudent.clone();
        badStudent.setName("Bart");

        System.out.println("Start of semester:");
        System.out.println(goodStudent);
        System.out.println(badStudent);

        int[] badStudentsGrades = badStudent.getGrades();
        for (int i = 0; i < badStudentsGrades.length; i++) {
            badStudentsGrades[i] = 50;
        }

        System.out.println();
        System.out.println("End of semester:");
        System.out.println(goodStudent);
        System.out.println(badStudent);
    }
}
