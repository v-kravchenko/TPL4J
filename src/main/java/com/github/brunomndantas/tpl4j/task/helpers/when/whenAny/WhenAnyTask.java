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
package com.github.brunomndantas.tpl4j.task.helpers.when.whenAny;

import com.github.brunomndantas.tpl4j.core.cancel.ICancellationToken;
import com.github.brunomndantas.tpl4j.core.options.Option;
import com.github.brunomndantas.tpl4j.core.options.Options;
import com.github.brunomndantas.tpl4j.core.scheduler.IScheduler;
import com.github.brunomndantas.tpl4j.task.Task;

import java.util.Arrays;
import java.util.Collection;

public class WhenAnyTask<T> extends Task<Task<T>> {

    protected volatile Collection<Task<T>> tasks;
    public Collection<Task<T>> getTasks() { return this.tasks; }



    public WhenAnyTask(String taskId, Collection<Task<T>> tasks, ICancellationToken cancellationToken, IScheduler scheduler, Option... options) {
        super(
                Task.DEFAULT_CONTEXT_BUILDER.build(taskId, new WhenAnyAction<>(tasks), cancellationToken, scheduler, new Options(Arrays.asList(options))),
                Task.DEFAULT_CONTEXT_MANAGER,
                Task.DEFAULT_CONTEXT_BUILDER,
                new WhenAnyContextExecutor<>(Task.DEFAULT_CONTEXT_MANAGER, tasks)
        );

        this.tasks = tasks;
    }

}
