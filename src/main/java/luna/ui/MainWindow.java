package luna.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import luna.Luna;
import luna.command.CommandResult;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {

    private final Image userImage = new Image(this.getClass()
                                                  .getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass()
                                                  .getResourceAsStream("/images/luna.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Luna luna;

    /**
     * Performs initialization tasks
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty()
                  .bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Luna instance
     */
    public void setLuna(Luna l) {
        luna = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Luna's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        CommandResult response = luna.getResponse(input);
        dialogContainer.getChildren()
                       .addAll(DialogBox.getUserDialog(input, userImage),
                               DialogBox.getLunaDialog(response.getOutput(), dukeImage));
        userInput.clear();

        if (response.isExit()) {
            luna.close();
            System.exit(0);
        }
    }

}
