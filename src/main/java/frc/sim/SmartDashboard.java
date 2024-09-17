package frc.sim;

public class SmartDashboard {
    /**
     * Put a number in the table.
     *
     * @param key   the key to be assigned to
     * @param value the value that will be assigned
     * @return False if the table key already exists with a different type
     */
    public static void putNumber(String key, double value) {
        System.out.println(key + ": " + value);
    }

    /**
     * Put a boolean in the table.
     *
     * @param key   the key to be assigned to
     * @param value the value that will be assigned
     * @return False if the table key already exists with a different type
     */
    public static void putBoolean(String key, boolean value) {
        System.out.println(key + ": " + value);
    }

    /**
     * Put a string in the table.
     *
     * @param key   the key to be assigned to
     * @param value the value that will be assigned
     * @return False if the table key already exists with a different type
     */
    public static void putString(String key, String value) {
        System.out.println(key + ": " + value);
    }
}
