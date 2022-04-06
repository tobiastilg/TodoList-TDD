import domain.*;
import exchange.DataImport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Alle Methoden der Taskliste sind mit maximaler Code-Coverage zu testen
 * Es sind an passenden Stellen parametrisierte Tests zu verwenden
 * Die importData-Methode muss mit Mock getestet werden.
 */

@DisplayName("Tests der Klasse TaskList")
@ExtendWith(MockitoExtension.class) //für automatische Initialisierung
public class TaskListTests {

    @Mock
    private DataImport dataImportMock;
    private Task task1;
    private Task task2;
    private TaskList taskList;

    @BeforeEach
    void setUp(){
        //given
        TaskTitle taskTitle = new TaskTitle("Test-Driven-Development");
        TaskDescription taskDescription = new TaskDescription("TDD mithilfe von Red-Green-Refactor");
        LocalDateTime deadline = LocalDateTime.of(2022, 4, 7, 15, 0);
        TaskPriority taskPriority = new TaskPriority(8);

        this.task1 = new Task(taskTitle, taskDescription, deadline, taskPriority);
        this.task2 = new Task(taskTitle, taskDescription, deadline, taskPriority);
        this.taskList = new TaskList();
    }

    @Test
    @DisplayName("Korrektes Zuweisen eines Tasks")
    void addTask_korrekteInputParameter_taskHinzugefuegt() {
        //When
        taskList.addTask(task1);

        //Then
        Assertions.assertEquals(taskList.getAllTasks().get(0), task1);
        Assertions.assertEquals(taskList.getAllTasks().size(), 1);
    }

    @Test
    @DisplayName("Korrektes Löschen eines Tasks aus der Liste")
    void deleteTask_korrekteInputParameter_taskGeloescht() {
        //When
        taskList.addTask(task1);
        taskList.deleteTaskAtIndex(0);

        //Then
        Assertions.assertEquals(taskList.getAllTasks().size(), 0);
    }

    @Test
    @DisplayName("Korrektes Pinnen eines Tasks aus der Liste")
    void pinTask_korrekteInputParameter_taskGepinnt() {
        //When
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.pinTaskWithIndex(0);

        //Then
        Assertions.assertEquals(taskList.getAllTasksPinnedInFront().get(0), task1);
        Assertions.assertEquals(taskList.getAllTasks().size(), 2);
    }

    @Test
    @DisplayName("Korrektes Importieren der Daten")
    void importData_korrekteInputParameter_datenImportiert() {
        //When
        List <Task> returnList = new ArrayList<>();
        returnList.add(task1);
        returnList.add(task2);
        Mockito.when(dataImportMock.importTasks()).thenReturn(returnList);

        taskList.importData(dataImportMock);

        //Then
        Assertions.assertEquals(taskList.getAllTasks().size(), 2);
        Assertions.assertEquals(taskList.getAllTasks().get(1), task2);
    }
}
