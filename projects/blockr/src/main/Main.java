package main;

class Main {
    /**
     * main function of the program
     * @param {String[]} args list of arguments to pass on to the program
     *                   this is currently not used
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MyCanvasWindow("Blockr Group 5").show();
        });
    }
}
