package com.github.brunomndantas.tpl4j.task.helpers.when.whenAll;

import com.github.brunomndantas.tpl4j.context.IContext;
import com.github.brunomndantas.tpl4j.core.cancel.CancellationToken;
import com.github.brunomndantas.tpl4j.core.cancel.CancelledException;
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

public class WhenAllTaskTest {

    @Test
    public void getTasksTest() {
        String id = UUID.randomUUID().toString();
        Task<String> t1 = new Task<>(() -> "");
        Task<String> t2 = new Task<>(() -> "");
        Collection<Task<String>> tasks = Arrays.asList(t1, t2);
        CancellationToken cancellationToken = new CancellationToken();
        IScheduler scheduler = new DedicatedThreadScheduler();
        Option[] options = new Option[0];

        WhenAllTask<String> task = new WhenAllTask<>(id, tasks, cancellationToken, scheduler, options);

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

        WhenAllTask<String> task = new WhenAllTask<>(id, tasks, cancellationToken, scheduler, options);

        assertSame(id, task.getId());
        assertSame(tasks, task.getTasks());
        assertTrue(task.getContextExecutor() instanceof WhenAllContextExecutor);
        assertSame(tasks, ((WhenAllContextExecutor<?>)task.getContextExecutor()).getTasks());
        assertSame(cancellationToken, task.getCancellationToken());
        assertSame(scheduler, task.getScheduler());
        assertEquals(Arrays.asList(options), task.getOptions().getOptions());
        assertSame(tasks, task.getTasks());
    }

    @Test
    public void executionEndWithSuccessTest() throws Exception {
        Task<String> successTaskA = new Task<>(TestUtils.SUCCESS_ACTION);
        Task<String> successTaskB = new Task<>(TestUtils.SUCCESS_ACTION);
        Collection<Task<String>> tasks = Arrays.asList(successTaskA, successTaskB);

        successTaskA.start();
        successTaskB.start();

        WhenAllTask<String> task = new WhenAllTask<>("", tasks, new CancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS);
        task.start();

        IContext<Collection<String>> template = TaskTestUtils.createTemplate(task.getId(), new WhenAllAction<>(tasks), task.getCancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS, State.SUCCEEDED, Arrays.asList(TestUtils.SUCCESS_RESULT, TestUtils.SUCCESS_RESULT), null);
        TaskTestUtils.validateTask(task, template);
    }

    @Test
    public void executionEndWithCancelTest() throws Exception {
        Task<String> successTask = new Task<>(TestUtils.SUCCESS_ACTION);
        Task<String> cancelTask = new Task<>(TestUtils.CANCEL_ACTION);
        Collection<Task<String>> tasks = Arrays.asList(successTask, cancelTask);

        successTask.start();
        cancelTask.start();

        WhenAllTask<String> task = new WhenAllTask<>("", tasks, new CancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS);
        task.start();

        IContext<Collection<String>> template = TaskTestUtils.createTemplate(task.getId(), new WhenAllAction<>(tasks), task.getCancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS, State.CANCELED, null, new CancelledException(""));
        TaskTestUtils.validateTask(task, template);
    }

    @Test
    public void executionEndWithFailTest() throws Exception {
        Task<String> successTask = new Task<>(TestUtils.SUCCESS_ACTION);
        Task<String> cancelTask = new Task<>(TestUtils.CANCEL_ACTION);
        Task<String> failTask = new Task<>(TestUtils.FAIL_ACTION);
        Collection<Task<String>> tasks = Arrays.asList(successTask, cancelTask, failTask);

        successTask.start();
        cancelTask.start();
        failTask.start();

        WhenAllTask<String> task = new WhenAllTask<>("", tasks, new CancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS);
        task.start();

        IContext<Collection<String>> template = TaskTestUtils.createTemplate(task.getId(), new WhenAllAction<>(tasks), task.getCancellationToken(), Task.DEFAULT_SCHEDULER, Task.DEFAULT_OPTIONS, State.FAILED, null, TestUtils.FAIL_RESULT);
        TaskTestUtils.validateTask(task, template);
    }

}
