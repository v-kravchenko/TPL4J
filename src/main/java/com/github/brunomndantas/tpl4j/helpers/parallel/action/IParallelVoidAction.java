package com.github.brunomndantas.tpl4j.helpers.parallel.action;

import com.github.brunomndantas.tpl4j.core.context.cancel.CancellationToken;

public interface IParallelVoidAction<T> {

    void run(T value, CancellationToken cancellationToken) throws Exception;

}
