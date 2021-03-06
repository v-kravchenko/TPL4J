package com.github.brunomndantas.tpl4j.task.helpers.when.whenAny;

import com.github.brunomndantas.tpl4j.context.IContext;
import com.github.brunomndantas.tpl4j.core.cancel.CancellationToken;
import com.github.brunomndantas.tpl4j.core.options.Option;
import com.github.brunomndantas.tpl4j.core.scheduler.DedicatedThreadScheduler;
import com.github.brunomndantas.tpl4j.core.scheduler.IScheduler;
import com.github.brunomndantas.tpl4j.core.status.State;
import com.github.brunomndantas.tpl4j.task.Task;
import com.github.brunomndantas.tpl4j.transversal.TaskTestUtils;
import com.github.brunomndantas.tpl4j.transversal.TestUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.*;

public class WhenAnyTaskTest {

    @Test
    public void getTasksTest() {
        String id = UUID.randomUUID().toString();
        Task<String> t1 = new Task<>(() -> "");
        Task<String> t2 = new Task<>(() -> "");
        Collection<Task<String>> tasks = Arrays.asList(t1, t2);
        CancellationToken cancellationToken = new CancellationToken();
        IScheduler scheduler = new DedicatedThreadScheduler();
        Option[] options = new Option[0];

        WhenAnyTask<String> task = new WhenAnyTask<>(id, tasks, cancellationToken, scheduler, options);

        assertSame(tasks, task.getTasks());
    }

    @Test
    public void constructorsTest() {
        String id = UUID.randomUUID().toString();
        Task<String> t1 = new Task<>(() -> "");
        Task<String> t2 = new Task<>(() -> "");
        Collection<Task<String>> tasks = Arrays.asList(t1, t2);
        CancellationToken cancellationToken = new CancellationToken();
        IScheduler scheduler = new DedicatedThreadScheduler();
        Option[] options = new Option[0];

        WhenAnyTask<String> task = new WhenAnyTask<>(id, tasks, cancellationToken, scheduler, options);

        assertSame(id, task.getId());
        assertTrue(task.getContextExecutor() instanceof WhenAnyContextExecutor);
        assertEquals(tasks, ((WhenAnyContextExecutor<?>)task.getContextExecutor()).getTasks());
        assertSame(cancellationToken, task.getCancellationToken());
        assertSame(scheduler, task.getScheduler());
        assertEquals(Arrays.asList(options), task.getOptions().getOptions());
        assertSame(tasks, task.getTasks());
    }

    @Test
    public void executionEndThroughSuccessTest() throws Exception {
        Task<String> successTaskA = new Task<>(TestUtils.SUCCESS_ACTION);
        Task<String> successTaskB = new Task<>(TestUtils.SUCCESS_ACTION);
        Collection<Task<String>> tasks = Arrays.asList(successTaskA, successTaskB);

        successTaskA.start();

        WhenAnyTask<String> task = new WhenAnyTask<>("", tasks, new CancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS);
        task.start();

        IContext<Task<String>> template = TaskTestUtils.createTemplate(task.getId(), new WhenAnyAction<>(tasks), task.getCancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS, State.SUCCEEDED, successTaskA, null);
        TaskTestUtils.validateTask(task, template);
    }

    @Test
    public void executionEndThroughCancelTest() throws Exception {
        Task<String> successTask = new Task<>(TestUtils.SUCCESS_ACTION);
        Task<String> cancelTask = new Task<>(TestUtils.CANCEL_ACTION);
        Collection<Task<String>> tasks = Arrays.asList(successTask, cancelTask);

        cancelTask.start();

        WhenAnyTask<String> task = new WhenAnyTask<>("", tasks, new CancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS);
        task.start();

        IContext<Task<String>> template = TaskTestUtils.createTemplate(task.getId(), new WhenAnyAction<>(tasks), task.getCancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS, State.SUCCEEDED, cancelTask, null);
        TaskTestUtils.validateTask(task, template);
    }

    @Test
    public void executionEndThroughFailTest() throws Exception {
        Task<String> successTask = new Task<>(TestUtils.SUCCESS_ACTION);
        Task<String> cancelTask = new Task<>(TestUtils.CANCEL_ACTION);
        Task<String> failTask = new Task<>(TestUtils.FAIL_ACTION);
        Collection<Task<String>> tasks = Arrays.asList(successTask, cancelTask, failTask);

        failTask.start();

        WhenAnyTask<String> task = new WhenAnyTask<>("", tasks, new CancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS);
        task.start();

        IContext<Task<String>> template = TaskTestUtils.createTemplate(task.getId(), new WhenAnyAction<>(tasks), task.getCancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS, State.SUCCEEDED, failTask, null);
        TaskTestUtils.validateTask(task, template);
    }

}
