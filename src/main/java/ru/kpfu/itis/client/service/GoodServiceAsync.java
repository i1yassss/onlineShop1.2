package ru.kpfu.itis.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Good;

import java.util.List;

/**
 * Created by etovladislav on 16.04.16.
 */
public interface GoodServiceAsync {

    void getAllCategories(AsyncCallback<List<Category>> asyncCallback);

    void getProduct(Long id, AsyncCallback<Good> productAsyncCallback);

    void getProducts(AsyncCallback<List<Good>> asyncCallback);
}
