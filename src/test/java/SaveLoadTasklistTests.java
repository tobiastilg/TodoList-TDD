import domain.Task;
import domain.TaskList;
import exceptions.LoadTaskListFromFileException;
import exceptions.SaveTaskListToFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.FileManagement;
import persistence.FileManagementImplementation;

/**
 * Alle Methoden der File-Management-Klasse mit maximaler Code-Coverage sind zu testen
 */
@DisplayName("Tests der Klasse FileManagement")
public class SaveLoadTasklistTests {

    private FileManagement fileManagement;
    private TaskList taskList;

    @BeforeEach
    void setUp(){
        fileManagement = new FileManagementImplementation();
        taskList = new TaskList();
    }

    @Test
    @DisplayName("Korrektes Speichern der Taskliste")
    void tasklisteSpeichern_korrekteInputParameter_tasklisteGespeichert() {
        //When & Then
        Assertions.assertDoesNotThrow(()-> fileManagement.saveTaskListToFile(taskList));

        /*try {
            fileManagement.saveTaskListToFile(taskList);
        } catch (SaveTaskListToFileException e) {
            System.out.println(e.getMessage());
            //Assertions.assertThrows(SaveTaskListToFileException.class, ()-> fileManagement.saveTaskListToFile(taskList));
        }*/
    }

    /**
     * Dieser Test wird nicht funktionieren, da dafür etwas an der Klasse oder dem Interface
     * verändert werden müsste (zB der Dateiname).
     */
    @Test
    @DisplayName("Fehlerhaftes Speichern der Taskliste")
    void tasklisteSpeichern_fehlerhafteInputParameter_tasklisteNichtGespeichert() {
        //When & Then
        Assertions.assertThrows(SaveTaskListToFileException.class, ()-> fileManagement.saveTaskListToFile(taskList));
    }

    @Test
    @DisplayName("Korrektes Laden der Taskliste")
    void tasklisteLaden_korrekteInputParameter_tasklisteGeladen() {
        //When & Then
        Assertions.assertDoesNotThrow(()-> fileManagement.loadTaskListFromFile());
        Assertions.assertInstanceOf(TaskList.class, taskList);

        /*try {
            TaskList taskList = fileManagement.loadTaskListFromFile();
        } catch (LoadTaskListFromFileException e) {
            System.out.println(e.getMessage());
        }*/
    }

    /**
     * Dieser Test wird nicht funktionieren, da dafür etwas an der Klasse oder dem Interface
     * verändert werden müsste (zB der Dateiname).
     */
    @Test
    @DisplayName("Fehlerhaftes Laden der Taskliste")
    void tasklisteLaden_fehlerhafteInputParameter_tasklisteNichtGeladen() {
        //When & Then
        Assertions.assertThrows(LoadTaskListFromFileException.class, ()-> fileManagement.loadTaskListFromFile());
    }
}
