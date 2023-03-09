package ink.whi.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class JsonData {
    String title;
    Date beginDate;
    Date beginTime;
    Date endDate;
    Date endTime;
    String desc;
}
