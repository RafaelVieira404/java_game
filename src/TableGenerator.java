
    public class TableGenerator {
        public static final int maxScreenCol = 200;
        public static final int maxScreenRol = 200;

        public static void main(String[] args) {
            int[][] table = new int[maxScreenRol][maxScreenCol];
            // Print the table
            for (int i = 0; i < maxScreenRol; i++) {
                for (int j = 0; j < maxScreenCol; j++) {
                    System.out.print(table[i][j] + " ");
                }
                System.out.println(); // New line after each row
            }
        }
}
