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
package com.github.brunomndantas.tpl4j.task.helpers.parallel.task;

import com.github.brunomndantas.tpl4j.core.cancel.ICancellationToken;
import com.github.brunomndantas.tpl4j.core.options.Option;
import com.github.brunomndantas.tpl4j.core.options.Options;
import com.github.brunomndantas.tpl4j.core.scheduler.IScheduler;
import com.github.brunomndantas.tpl4j.task.Task;
import com.github.brunomndantas.tpl4j.task.helpers.parallel.action.IParallelAction;
import com.github.brunomndantas.tpl4j.task.helpers.parallel.action.ParallelWorkerAction;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ParallelWorkerTask<T,K> extends Task<Collection<K>> {

    public ParallelWorkerTask(String taskId, IParallelAction<T,K> action, Iterator<T> iterator, ICancellationToken cancellationToken, IScheduler scheduler, Option... options) {
        super(
                Task.DEFAULT_CONTEXT_BUILDER.build(taskId, new ParallelWorkerAction<>(action, iterator), cancellationToken, scheduler, new Options(Arrays.asList(options))),
                Task.DEFAULT_CONTEXT_MANAGER,
                Task.DEFAULT_CONTEXT_BUILDER,
                Task.DEFAULT_CONTEXT_EXECUTOR
        );
    }

}
