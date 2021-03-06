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
package com.github.brunomndantas.tpl4j.task.helpers.when.whenAll;

import com.github.brunomndantas.tpl4j.core.action.IAction;
import com.github.brunomndantas.tpl4j.core.cancel.ICancellationToken;
import com.github.brunomndantas.tpl4j.core.status.State;
import com.github.brunomndantas.tpl4j.task.Task;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class WhenAllAction<T> implements IAction<Collection<T>> {

    protected volatile Collection<Task<T>> tasks;
    public Collection<Task<T>> getTasks() { return this.tasks; }



    public WhenAllAction(Collection<Task<T>> tasks) {
        this.tasks = tasks;
    }



    @Override
    public Collection<T> run(ICancellationToken cancellationToken) throws Exception {
        if(this.anyFailed())
            throw this.collectErrors();

        if(this.anyCancelled())
            throw this.collectCancellations();

        return this.collectResults(cancellationToken);
    }

    protected boolean anyFailed() {
        return this.tasks
                .stream()
                .anyMatch((task) -> task.getFailedEvent().hasFired());
    }

    protected Exception collectErrors() {
        return this.combineExceptions(
                this.tasks
                    .stream()
                    .filter(t -> t.getState().equals(State.FAILED))
                    .map(Task::getResultException)
                    .collect(Collectors.toList())
        );
    }

    protected boolean anyCancelled() {
        return this.tasks
                .stream()
                .anyMatch((task) -> task.getCancelledEvent().hasFired());
    }

    protected Exception collectCancellations() {
        return this.combineExceptions(
                this.tasks
                    .stream()
                    .filter(t -> t.getState().equals(State.CANCELED))
                    .map(Task::getResultException)
                    .collect(Collectors.toList())
        );
    }

    protected Exception combineExceptions(Collection<Exception> exceptions) {
        Exception exception = null;

        for(Exception e : exceptions) {
            if(exception == null)
                exception = e;
            else if(e != exception)
                exception.addSuppressed(e);
        }

        return exception;
    }

    protected Collection<T> collectResults(ICancellationToken token) throws Exception {
        Collection<T> results = new LinkedList<>();

        for(Task<T> task : this.tasks)
            results.add(task.getResult());

        return results;
    }

}
