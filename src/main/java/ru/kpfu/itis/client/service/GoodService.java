package ru.kpfu.itis.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Good;

import java.util.List;

/**
 * Created by etovladislav on 16.04.16.
 */
@RemoteServiceRelativePath("springGwtServices/goodService")
public interface GoodService extends RemoteService {

    List<Category> getAllCategories();

    Good getProduct(Long id);

    List<Good> getProducts();

}
