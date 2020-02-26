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
package com.github.brunomndantas.tpl4j.task.helpers.unwrap;

import com.github.brunomndantas.tpl4j.core.action.IAction;
import com.github.brunomndantas.tpl4j.core.cancel.CancellationToken;
import com.github.brunomndantas.tpl4j.core.cancel.CancelledException;
import com.github.brunomndantas.tpl4j.task.Task;

public class UnwrapAction<T> implements IAction<T> {

    protected volatile Task<Task<T>> task;
    public Task<Task<T>> getTask() { return this.task; }



    public UnwrapAction(Task<Task<T>> task) {
        this.task = task;
    }



    @Override
    public T run(CancellationToken cancellationToken) throws Exception {
        if(this.task.getStatus().cancelledEvent.hasFired())
            throw new CancelledException();

        Task<T> task = this.task.getResult();

        if(task.getStatus().cancelledEvent.hasFired())
            throw new CancelledException();

        return task.getResult();
    }

}