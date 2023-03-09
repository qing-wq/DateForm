package ink.whi.service;

import ink.whi.pojo.Data;

import java.util.Date;
import java.util.List;

public interface DataService {
    int addData(Data data);

    List<Data> selectData(long currentTime);
}
