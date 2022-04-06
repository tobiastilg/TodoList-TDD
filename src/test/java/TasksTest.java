import domain.*;
import exceptions.TaskTitleNotValidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Alle Methoden der Taskklasse sind mit maximaler Code-Coverage zu testen
 * Es sind an passenden Stellen parametrisierte Tests zu verwenden
 */
@DisplayName("Tests der Klasse Task")
public class TasksTest {

    private TaskTitle taskTitle;
    private TaskDescription taskDescription;
    private LocalDateTime deadline;
    private TaskPriority taskPriority;
    private Task taskTDD;

    @BeforeEach
    void setUp(){
        //Given
        this.taskTitle = new TaskTitle("Test-Driven-Development");
        this.taskDescription = new TaskDescription("TDD mithilfe von Red-Green-Refactor");
        this.deadline = LocalDateTime.of(2022, 4, 7, 15, 0);
        this.taskPriority = new TaskPriority(8);

        this.taskTDD = new Task(taskTitle, taskDescription, deadline, taskPriority);
    }

    @Test
    @DisplayName("Korrektes Anlegen eines Tasks")
    void taskAnlegen_korrekteInputParameter_taskAngelegt() {
        //When
        Task task = new Task(taskTitle, taskDescription, deadline, taskPriority);

        //Then
        Assertions.assertInstanceOf(Task.class, task);
        Assertions.assertEquals(task.getTaskTitle(), taskTitle);
        Assertions.assertEquals(task.getTaskDescription(), taskDescription);
        Assertions.assertEquals(task.getDeadline(), deadline);
        Assertions.assertEquals(task.getTaskPriority(), taskPriority);
        Assertions.assertEquals(task.getTaskStatus(), TaskStatus.open);
        Assertions.assertFalse(task.isPinned());

        //Assertions.assertEquals(task.getCreationTimestamp(), LocalDateTime.now());
        //Assertions.assertInstanceOf(HashSet.class, task.getTagList());
    }

    @Test
    @DisplayName("Fehlerhaftes Anlegen eines Tasks aufgrund des Tasktitles")
    void taskAnlegen_falscheInputParameter_taskTitleNotValidException() {
        //When & Then
        Assertions.assertThrows(TaskTitleNotValidException.class, ()->
                new Task(null, taskDescription, deadline, taskPriority));
    }

    @Test
    @DisplayName("Korrektes Zuweisen eines Tags")
    void addTag_korrekteInputParameter_tagHinzugefuegt() {
        //When
        taskTDD.addTag(new Tag(new Tagname("Testing")));

        Assertions.assertEquals(taskTDD.getTagList().size(), 1);
    }

    @ParameterizedTest(name = "tag: {0}")
    @DisplayName("Korrektes Zuweisen mehrerer Tags")
    @CsvSource({"TDD", "UML", "MVC", "JDBC", "GIT"})
    void addTags_korrekteInputParameter_tagsHinzugefuegt(Tagname tagname) {
        //When
        Tag tag = new Tag(tagname);
        taskTDD.addTag(tag);

        Assertions.assertEquals(taskTDD.getTagList().size(), 1);
        Assertions.assertTrue(taskTDD.getTagList().contains(tag));
        Assertions.assertEquals(tag.getTagname(), tagname);
    }

    @Test
    @DisplayName("Korrektes Pinnen eines Tasks")
    void taskPinnen_korrekteInputParameter_taskGepinnt() {
        //When
        taskTDD.pinTask();

        //Then
        Assertions.assertTrue(taskTDD.isPinned());
    }

    @Test
    @DisplayName("Korrektes Entpinnen eines Tasks")
    void taskEntpinnen_korrekteInputParameter_taskEntpinnt() {
        //When
        taskTDD.unpinTask();

        //Then
        Assertions.assertFalse(taskTDD.isPinned());
    }
}
