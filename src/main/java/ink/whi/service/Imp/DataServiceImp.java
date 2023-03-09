package ink.whi.service.Imp;

import ink.whi.mapper.DataMapper;
import ink.whi.pojo.Data;
import ink.whi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DataServiceImp implements DataService {

    @Autowired
    private DataMapper dataMapper;

    public void setDataMapper(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public int addData(Data data) {
        return dataMapper.addData(data);
    }

    @Override
    public List<Data> selectData(long currentTime) {
        return dataMapper.selectData(currentTime);
    }
}
