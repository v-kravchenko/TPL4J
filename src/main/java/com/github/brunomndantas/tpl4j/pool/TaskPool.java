/*-
* Copyright (c) 2019, Bruno Dantas <bmndantas@gmail.com>
* All rights reserved.
*
* This program is free software: you can redistribute it and/or modify it
* under the terms of the GNU General Public License as published by the
* Free Software Foundation, either version 3 of the License, or (at your
* option) any later version.
*
* This program is distributed in the hope that it will be useful, but
* WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
* or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
* for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program.  If not, see <http://www.gnu.org/licenses/>.  */
package com.github.brunomndantas.tpl4j.pool;

import com.github.brunomndantas.tpl4j.task.parallel.action.IParallelAction;
import com.github.brunomndantas.tpl4j.task.parallel.action.IParallelUninterruptibleAction;
import com.github.brunomndantas.tpl4j.task.parallel.action.IParallelUninterruptibleVoidAction;
import com.github.brunomndantas.tpl4j.task.parallel.action.IParallelVoidAction;
import com.github.brunomndantas.tpl4j.task.parallel.task.ParallelTask;
import com.github.brunomndantas.tpl4j.task.Task;
import com.github.brunomndantas.tpl4j.task.core.TaskOption;
import com.github.brunomndantas.tpl4j.task.core.action.IAction;
import com.github.brunomndantas.tpl4j.task.core.action.IEmptyAction;
import com.github.brunomndantas.tpl4j.task.core.action.IEmptyVoidAction;
import com.github.brunomndantas.tpl4j.task.core.action.IVoidAction;
import com.github.brunomndantas.tpl4j.task.unwrap.UnwrapTask;
import com.github.brunomndantas.tpl4j.task.when.whenAll.WhenAllTask;
import com.github.brunomndantas.tpl4j.task.when.whenAny.WhenAnyTask;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class TaskPool {

    private static final TaskPool INSTANCE = new TaskPool();



    public static Consumer<Runnable> getTaskScheduler() {
        return INSTANCE.getScheduler();
    }


    public static <T> Task<T> createTask(String taskId, IAction<T> action, TaskOption... options) {
        return INSTANCE.create(taskId, action, options);
    }

    public static <T> Task<T> createTask(String taskId, IEmptyAction<T> action, TaskOption... options) {
        return INSTANCE.create(taskId, action, options);
    }

    public static Task<Void> createTask(String taskId, IVoidAction action, TaskOption... options) {
        return INSTANCE.create(taskId, action, options);
    }

    public static Task<Void> createTask(String taskId, IEmptyVoidAction action, TaskOption... options) {
        return INSTANCE.create(taskId, action, options);
    }


    public static <T> Task<T> createTask(IAction<T> action, TaskOption... options) {
        return INSTANCE.create(action, options);
    }

    public static <T> Task<T> createTask(IEmptyAction<T> action, TaskOption... options) {
        return INSTANCE.create(action, options);
    }

    public static Task<Void> createTask(IVoidAction action, TaskOption... options) {
        return INSTANCE.create(action, options);
    }

    public static Task<Void> createTask(IEmptyVoidAction action, TaskOption... options) {
        return INSTANCE.create(action, options);
    }


    public static <T> Task<T> createTask(String taskId, IAction<T> action) {
        return INSTANCE.create(taskId, action);
    }

    public static <T> Task<T> createTask(String taskId, IEmptyAction<T> action) {
        return INSTANCE.create(taskId, action);
    }

    public static Task<Void> createTask(String taskId, IVoidAction action) {
        return INSTANCE.create(taskId, action);
    }

    public static Task<Void> createTask(String taskId, IEmptyVoidAction action) {
        return INSTANCE.create(taskId, action);
    }


    public static <T> Task<T> createTask(IAction<T> action) {
        return INSTANCE.create(action);
    }

    public static <T> Task<T> createTask(IEmptyAction<T> action) {
        return INSTANCE.create(action);
    }

    public static Task<Void> createTask(IVoidAction action) {
        return INSTANCE.create(action);
    }

    public static Task<Void> createTask(IEmptyVoidAction action) {
        return INSTANCE.create(action);
    }


    public static <T> Task<T> createAndStartTask(String taskId, IAction<T> action, TaskOption... options) {
        return INSTANCE.createAndStart(taskId, action, options);
    }

    public static <T> Task<T> createAndStartTask(String taskId, IEmptyAction<T> action, TaskOption... options) {
        return INSTANCE.createAndStart(taskId, action, options);
    }

    public static Task<Void> createAndStartTask(String taskId, IVoidAction action, TaskOption... options) {
        return INSTANCE.createAndStart(taskId, action, options);
    }

    public static Task<Void> createAndStartTask(String taskId, IEmptyVoidAction action, TaskOption... options) {
        return INSTANCE.createAndStart(taskId, action, options);
    }


    public static <T> Task<T> createAndStartTask(IAction<T> action, TaskOption... options) {
        return INSTANCE.createAndStart(action, options);
    }

    public static <T> Task<T> createAndStartTask(IEmptyAction<T> action, TaskOption... options) {
        return INSTANCE.createAndStart(action, options);
    }

    public static Task<Void> createAndStartTask(IVoidAction action, TaskOption... options) {
        return INSTANCE.createAndStart(action, options);
    }

    public static Task<Void> createAndStartTask(IEmptyVoidAction action, TaskOption... options) {
        return INSTANCE.createAndStart(action, options);
    }


    public static <T> Task<T> createAndStartTask(String taskId, IAction<T> action) {
        return INSTANCE.createAndStart(taskId, action);
    }

    public static <T> Task<T> createAndStartTask(String taskId, IEmptyAction<T> action) {
        return INSTANCE.createAndStart(taskId, action);
    }

    public static Task<Void> createAndStartTask(String taskId, IVoidAction action) {
        return INSTANCE.createAndStart(taskId, action);
    }

    public static Task<Void> createAndStartTask(String taskId, IEmptyVoidAction action) {
        return INSTANCE.createAndStart(taskId, action);
    }


    public static <T> Task<T> createAndStartTask(IAction<T> action) {
        return INSTANCE.createAndStart(action);
    }

    public static <T> Task<T> createAndStartTask(IEmptyAction<T> action) {
        return INSTANCE.createAndStart(action);
    }

    public static Task<Void> createAndStartTask(IVoidAction action) {
        return INSTANCE.createAndStart(action);
    }

    public static Task<Void> createAndStartTask(IEmptyVoidAction action) {
        return INSTANCE.createAndStart(action);
    }


    public static <T> WhenAllTask<T> whenAllTask(String taskId, Collection<Task<T>> tasks, TaskOption... options) {
        return INSTANCE.whenAll(taskId, tasks, options);
    }

    public static <T> WhenAllTask<T> whenAllTask(String taskId, Collection<Task<T>> tasks) {
        return INSTANCE.whenAll(taskId, tasks);
    }


    public static <T> WhenAllTask<T> whenAllTask(Collection<Task<T>> tasks, TaskOption... options) {
        return INSTANCE.whenAll(tasks, options);
    }

    public static <T> WhenAllTask<T> whenAllTask(Collection<Task<T>> tasks) {
        return INSTANCE.whenAll(tasks);
    }


    public static <T> WhenAnyTask<T> whenAnyTask(String taskId, Collection<Task<T>> tasks, TaskOption... options) {
        return INSTANCE.whenAny(taskId, tasks, options);
    }

    public static <T> WhenAnyTask<T> whenAnyTask(String taskId, Collection<Task<T>> tasks) {
        return INSTANCE.whenAny(taskId, tasks);
    }


    public static <T> WhenAnyTask<T> whenAnyTask(Collection<Task<T>> tasks, TaskOption... options) {
       return INSTANCE.whenAny(tasks, options);
    }

    public static <T> WhenAnyTask<T> whenAnyTask(Collection<Task<T>> tasks) {
        return INSTANCE.whenAny(tasks);
    }


    public static <T> UnwrapTask<T> unwrapTask(String taskId, Task<Task<T>> taskToUnwrap, TaskOption... options) {
        return INSTANCE.unwrap(taskId, taskToUnwrap, options);
    }

    public static <T> UnwrapTask<T> unwrapTask(String taskId, Task<Task<T>> taskToUnwrap) {
        return INSTANCE.unwrap(taskId, taskToUnwrap);
    }


    public static <T> UnwrapTask<T> unwrapTask(Task<Task<T>> taskToUnwrap, TaskOption... options) {
        return INSTANCE.unwrap(taskToUnwrap, options);
    }

    public static <T> UnwrapTask<T> unwrapTask(Task<Task<T>> taskToUnwrap) {
        return INSTANCE.unwrap(taskToUnwrap);
    }


    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(taskId, action, elements, options);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(taskId, action, elements, options);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelUninterruptibleAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(taskId, action, elements, options);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(taskId, action, elements, options);
    }


    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelAction<T,K> action, Iterable<T> elements) {
        return INSTANCE.forEach(taskId, action, elements);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelVoidAction<T> action, Iterable<T> elements) {
        return INSTANCE.forEach(taskId, action, elements);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelUninterruptibleAction<T,K> action, Iterable<T> elements) {
        return INSTANCE.forEach(taskId, action, elements);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(String taskId, IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements) {
        return INSTANCE.forEach(taskId, action, elements);
    }


    public static <T,K> ParallelTask<T,K> forEachTask(IParallelAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(action, elements, options);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(IParallelVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(action, elements, options);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(IParallelUninterruptibleAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(action, elements, options);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        return INSTANCE.forEach(action, elements, options);
    }


    public static <T,K> ParallelTask<T,K> forEachTask(IParallelAction<T,K> action, Iterable<T> elements) {
        return INSTANCE.forEach(action, elements);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(IParallelVoidAction<T> action, Iterable<T> elements) {
        return INSTANCE.forEach(action, elements);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(IParallelUninterruptibleAction<T,K> action, Iterable<T> elements) {
        return INSTANCE.forEach(action, elements);
    }

    public static <T,K> ParallelTask<T,K> forEachTask(IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements) {
        return INSTANCE.forEach(action, elements);

    }



    public static void dispose() {
        INSTANCE.close();
    }



    protected volatile ExecutorService pool;

    protected volatile Consumer<Runnable> scheduler;
    public Consumer<Runnable> getScheduler() { return this.scheduler; }



    public TaskPool(int nThreads) {
        this.pool = Executors.newFixedThreadPool(nThreads);
        this.scheduler = pool::submit;
    }

    public TaskPool() {
        this(Runtime.getRuntime().availableProcessors());
    }



    public <T> Task<T> create(String taskId, IAction<T> action, TaskOption... options) {
        return new Task<>(taskId, action, this.scheduler, options);
    }

    public <T> Task<T> create(String taskId, IEmptyAction<T> action, TaskOption... options) {
        return new Task<>(taskId, action, this.scheduler, options);
    }

    public Task<Void> create(String taskId, IVoidAction action, TaskOption... options) {
        return new Task<>(taskId, action, this.scheduler, options);
    }

    public Task<Void> create(String taskId, IEmptyVoidAction action, TaskOption... options) {
        return new Task<>(taskId, action, this.scheduler, options);
    }


    public <T> Task<T> create(IAction<T> action, TaskOption... options) {
        return new Task<>(action, this.scheduler, options);
    }

    public <T> Task<T> create(IEmptyAction<T> action, TaskOption... options) {
        return new Task<>(action, this.scheduler, options);
    }

    public Task<Void> create(IVoidAction action, TaskOption... options) {
        return new Task<>(action, this.scheduler, options);
    }

    public Task<Void> create(IEmptyVoidAction action, TaskOption... options) {
        return new Task<>(action, this.scheduler, options);
    }


    public <T> Task<T> create(String taskId, IAction<T> action) {
        return new Task<>(taskId, action, this.scheduler);
    }

    public <T> Task<T> create(String taskId, IEmptyAction<T> action) {
        return new Task<>(taskId, action, this.scheduler);
    }

    public Task<Void> create(String taskId, IVoidAction action) {
        return new Task<>(taskId, action, this.scheduler);
    }

    public Task<Void> create(String taskId, IEmptyVoidAction action) {
        return new Task<>(taskId, action, this.scheduler);
    }


    public <T> Task<T> create(IAction<T> action) {
        return new Task<>(action, this.scheduler);
    }

    public <T> Task<T> create(IEmptyAction<T> action) {
        return new Task<>(action, this.scheduler);
    }

    public Task<Void> create(IVoidAction action) {
        return new Task<>(action, this.scheduler);
    }

    public Task<Void> create(IEmptyVoidAction action) {
        return new Task<>(action, this.scheduler);
    }


    public <T> Task<T> createAndStart(String taskId, IAction<T> action, TaskOption... options) {
        Task<T> task = create(taskId, action, options);

        task.start();

        return task;
    }

    public <T> Task<T> createAndStart(String taskId, IEmptyAction<T> action, TaskOption... options) {
        Task<T> task = create(taskId, action, options);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(String taskId, IVoidAction action, TaskOption... options) {
        Task<Void> task = create(taskId, action, options);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(String taskId, IEmptyVoidAction action, TaskOption... options) {
        Task<Void> task = create(taskId, action, options);

        task.start();

        return task;
    }


    public <T> Task<T> createAndStart(IAction<T> action, TaskOption... options) {
        Task<T> task = create(action, options);

        task.start();

        return task;
    }

    public <T> Task<T> createAndStart(IEmptyAction<T> action, TaskOption... options) {
        Task<T> task = create(action, options);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(IVoidAction action, TaskOption... options) {
        Task<Void> task = create(action, options);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(IEmptyVoidAction action, TaskOption... options) {
        Task<Void> task = create(action, options);

        task.start();

        return task;
    }


    public <T> Task<T> createAndStart(String taskId, IAction<T> action) {
        Task<T> task = create(taskId, action);

        task.start();

        return task;
    }

    public <T> Task<T> createAndStart(String taskId, IEmptyAction<T> action) {
        Task<T> task = create(taskId, action);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(String taskId, IVoidAction action) {
        Task<Void> task = create(taskId, action);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(String taskId, IEmptyVoidAction action) {
        Task<Void> task = create(taskId, action);

        task.start();

        return task;
    }


    public <T> Task<T> createAndStart(IAction<T> action) {
        Task<T> task = create(action);

        task.start();

        return task;
    }

    public <T> Task<T> createAndStart(IEmptyAction<T> action) {
        Task<T> task = create(action);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(IVoidAction action) {
        Task<Void> task = create(action);

        task.start();

        return task;
    }

    public Task<Void> createAndStart(IEmptyVoidAction action) {
        Task<Void> task = create(action);

        task.start();

        return task;
    }


    public <T> WhenAllTask<T> whenAll(String taskId, Collection<Task<T>> tasks, TaskOption... options) {
        WhenAllTask<T> task = new WhenAllTask<>(taskId, tasks, this.scheduler, options);

        task.start();

        return task;
    }

    public <T> WhenAllTask<T> whenAll(String taskId, Collection<Task<T>> tasks) {
        WhenAllTask<T> task = new WhenAllTask<>(taskId, tasks, this.scheduler);

        task.start();

        return task;
    }


    public <T> WhenAllTask<T> whenAll(Collection<Task<T>> tasks, TaskOption... options) {
        WhenAllTask<T> task = new WhenAllTask<>(tasks, this.scheduler, options);

        task.start();

        return task;
    }

    public <T> WhenAllTask<T> whenAll(Collection<Task<T>> tasks) {
        WhenAllTask<T> task = new WhenAllTask<>(tasks, this.scheduler);

        task.start();

        return task;
    }


    public <T> WhenAnyTask<T> whenAny(String taskId, Collection<Task<T>> tasks, TaskOption... options) {
        WhenAnyTask<T> task = new WhenAnyTask<>(taskId, tasks, this.scheduler, options);

        task.start();

        return task;
    }

    public <T> WhenAnyTask<T> whenAny(String taskId, Collection<Task<T>> tasks) {
        WhenAnyTask<T> task = new WhenAnyTask<>(taskId, tasks, this.scheduler);

        task.start();

        return task;
    }


    public <T> WhenAnyTask<T> whenAny(Collection<Task<T>> tasks, TaskOption... options) {
        WhenAnyTask<T> task = new WhenAnyTask<>(tasks, this.scheduler, options);

        task.start();

        return task;
    }

    public <T> WhenAnyTask<T> whenAny(Collection<Task<T>> tasks) {
        WhenAnyTask<T> task = new WhenAnyTask<>(tasks, this.scheduler);

        task.start();

        return task;
    }


    public <T> UnwrapTask<T> unwrap(String taskId, Task<Task<T>> taskToUnwrap, TaskOption... options) {
        UnwrapTask<T> task = new UnwrapTask<>(taskId, taskToUnwrap, this.scheduler, options);

        task.start();

        return task;
    }

    public <T> UnwrapTask<T> unwrap(String taskId, Task<Task<T>> taskToUnwrap) {
        UnwrapTask<T> task = new UnwrapTask<>(taskId, taskToUnwrap, this.scheduler);

        task.start();

        return task;
    }


    public <T> UnwrapTask<T> unwrap(Task<Task<T>> taskToUnwrap, TaskOption... options) {
        UnwrapTask<T> task = new UnwrapTask<>(taskToUnwrap, this.scheduler, options);

        task.start();

        return task;
    }

    public <T> UnwrapTask<T> unwrap(Task<Task<T>> taskToUnwrap) {
        UnwrapTask<T> task = new UnwrapTask<>(taskToUnwrap, this.scheduler);

        task.start();

        return task;
    }


    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelUninterruptibleAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }


    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelAction<T,K> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelVoidAction<T> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelUninterruptibleAction<T,K> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(String taskId, IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(taskId, action, elements.iterator(), this.scheduler);
        task.start();
        return task;
    }


    public <T,K> ParallelTask<T,K> forEach(IParallelAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(IParallelVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(IParallelUninterruptibleAction<T,K> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements, TaskOption... options) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler, options);
        task.start();
        return task;
    }


    public <T,K> ParallelTask<T,K> forEach(IParallelAction<T,K> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(IParallelVoidAction<T> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(IParallelUninterruptibleAction<T,K> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler);
        task.start();
        return task;
    }

    public <T,K> ParallelTask<T,K> forEach(IParallelUninterruptibleVoidAction<T> action, Iterable<T> elements) {
        ParallelTask<T,K> task = new ParallelTask<>(action, elements.iterator(), this.scheduler);
        task.start();
        return task;

    }


    public void close() {
        pool.shutdown();
    }

}
