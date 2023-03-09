package ink.whi.mapper;

import ink.whi.pojo.Data;

import java.util.Date;
import java.util.List;

public interface DataMapper {

    int addData(Data data);

    List<Data> selectData(long currentTime);
}
