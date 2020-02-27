package com.github.brunomndantas.tpl4j.context;

import com.github.brunomndantas.tpl4j.core.action.IAction;
import com.github.brunomndantas.tpl4j.core.cancel.ICancellationToken;
import com.github.brunomndantas.tpl4j.core.options.IOptions;
import com.github.brunomndantas.tpl4j.core.scheduler.IScheduler;
import com.github.brunomndantas.tpl4j.core.status.IStatus;

import java.util.Collection;

public class Context<T> implements IContext<T> {

    protected String taskId;
    @Override public synchronized String getTaskId() { return this.taskId; }

    protected IAction<T> action;
    @Override public synchronized IAction<T> getAction() { return this.action; }

    protected ICancellationToken cancellationToken;
    @Override public synchronized ICancellationToken getCancellationToken() { return this.cancellationToken; }

    protected IScheduler scheduler;
    @Override public synchronized IScheduler getScheduler() { return this.scheduler; }

    protected IOptions options;
    @Override public synchronized IOptions getOptions() { return this.options; }

    protected IStatus status;
    @Override public synchronized IStatus getStatus() { return this.status; }

    protected String parentTaskId;
    @Override public synchronized String getParentTaskId() { return this.parentTaskId; }
    @Override public synchronized void setParentTaskId(String parentTaskId) { this.parentTaskId = parentTaskId; }

    protected Collection<String> childrenTasksIds;
    @Override public synchronized Collection<String> getChildrenTasksIds() { return this.childrenTasksIds; }
    @Override public synchronized void setChildrenTasksIds(Collection<String> childrenTasksIds) { this.childrenTasksIds = childrenTasksIds; }

    protected long creatorThreadId;
    @Override public synchronized long getCreatorThreadId() { return this.creatorThreadId; }
    @Override public synchronized void setCreatorThreadId(long creatorThreadId) { this.creatorThreadId = creatorThreadId; }

    protected long executorThreadId;
    @Override public synchronized long getExecutorThreadId() { return this.executorThreadId; }
    @Override public synchronized void setExecutorThreadId(long executorThreadId) { this.executorThreadId = executorThreadId; }

    protected T resultValue;
    @Override public synchronized T getResultValue() { return this.resultValue; }
    @Override public synchronized void setResultValue(T resultValue) { this.resultValue = resultValue; }

    protected Exception resultException;
    @Override public synchronized Exception getResultException() { return this.resultException; }
    @Override public synchronized void setResultException(Exception resultException) { this.resultException = resultException; }



    public Context(String taskId,
                   IAction<T> action, ICancellationToken cancellationToken, IScheduler scheduler, IOptions options, IStatus status,
                   String parentTaskId, Collection<String> childrenTasksIds, long creatorThreadId, long executorThreadId,
                   T resultValue, Exception resultException) {
        this.taskId = taskId;
        this.action = action;
        this.cancellationToken = cancellationToken;
        this.scheduler = scheduler;
        this.options = options;
        this.status = status;
        this.parentTaskId = parentTaskId;
        this.childrenTasksIds = childrenTasksIds;
        this.creatorThreadId = creatorThreadId;
        this.executorThreadId = executorThreadId;
        this.resultValue = resultValue;
        this.resultException = resultException;
    }



    @Override public synchronized boolean hasChild(String childTaskId) {
        return this.childrenTasksIds.contains(childTaskId);
    }

    @Override public synchronized void addChild(String childTaskId) {
        this.childrenTasksIds.add(childTaskId);
    }

}
