package service;

import java.io.IOException;
import java.util.List;

public interface IService<T> {
    List<T> findALl() throws IOException;
}
