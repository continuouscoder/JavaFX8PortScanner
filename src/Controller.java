import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.net.Socket;

public class Controller {
    @FXML
    public TextField hostValue;
    @FXML
    public TextField startingPortValue;
    @FXML
    public TextField endingPortValue;
    @FXML
    public TextArea scanResultsValues;

    @FXML
    public void startScan(Event e) {
        System.out.println("Button Clicked");
        System.out.println(hostValue.getText());
        System.out.println(startingPortValue.getText());
        System.out.println(endingPortValue.getText());
        System.out.println("Scanning host: " + hostValue.getText() + " starting at port: " + startingPortValue.getText() + " and ending at port: " + endingPortValue.getText());

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scanResultsValues.clear();
                for (int i = Integer.parseInt(startingPortValue.getText()); i <= Integer.parseInt(endingPortValue.getText()); i++) {
                    try {
                        Socket socket = new Socket(hostValue.getText(), i);
                        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                        dout.writeUTF("\n\n\n");
                        dout.flush();
                        dout.close();
                        socket.close();
                        System.out.println("Port " + i + " is open...");
                        scanResultsValues.appendText("Port open: " + Integer.toString(i )+"\n");
                    } catch (Exception err) {

                    }
                }
                scanResultsValues.appendText("\n## Scan Completed ##");
            }
        });
//        Runnable task = new Runnable() {  // Concurrency solution provided by: https://examples.javacodegeeks.com/desktop-java/javafx/javafx-concurrency-example/
//            @Override
//            public void run() {  // Part of the Runnable:
//                // My scanner code gets dropped in the run method of the runnable...
//                scanResultsValues.clear();
//                for (int i = Integer.parseInt(startingPortValue.getText()); i <= Integer.parseInt(endingPortValue.getText()); i++) {
//                    try {
//                        Socket socket = new Socket(hostValue.getText(), i);
//                        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
//                        dout.writeUTF("\n\n\n");
//                        dout.flush();
//                        dout.close();
//                        socket.close();
//                        System.out.println("Port " + i + " is open...");
//                        scanResultsValues.appendText("Port open: " + Integer.toString(i )+"\n");
//                    } catch (Exception err) {
//
//                    }
//                }
//                scanResultsValues.appendText("\n## Scan Completed ##");
//            }
//        };
//
//        Thread backgroundThread = new Thread(task); // Concurrency solution: defining a new background thread
//        backgroundThread.setDaemon(true);           // Concurrency solution: thread will use a daemon
//        backgroundThread.start();                   // Concurrency solution: starting thread
    }

}
