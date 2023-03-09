package ink.whi.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.whi.pojo.Data;
import ink.whi.service.Imp.DataServiceImp;
import ink.whi.utils.TimeUtil;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
public class formController {

    @Autowired
    private DataServiceImp dataService;

    /**
     * 将活动数据存入数据库(废弃)
     * @param ruleForm
     * @return
     * @throws ParseException
     */
    @RequestMapping("/dateForm")
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ResponseBody
    @CrossOrigin
    public String saveData(@RequestBody String ruleForm) throws ParseException {
        JSONObject jsonObject = JSONObject.fromObject(ruleForm);
        JSONObject data = jsonObject.getJSONObject("ruleForm");
        String beginDate = data.getString("beginDate");
        String beginTime = data.getString("beginTime");
        String endDate = data.getString("endDate");
        String endTime = data.getString("endTime");
        // 世界时间
        String startTimeStr = beginDate.substring(0, beginDate.indexOf('T')) + "T" + beginTime.substring(beginTime.indexOf("T") + 1);
        String overTimeStr = endDate.substring(0, endDate.indexOf('T'))+ "T" + endTime.substring(endTime.indexOf("T") + 1);
        // 中国标准时间
        long startTime = TimeUtil.transformTime(startTimeStr);
        long overTime = TimeUtil.transformTime(overTimeStr);
        Data myData = new Data(data.getString("title"), startTime, overTime, data.getString("desc"));
        dataService.addData(myData);
        return "success";
    }

    /**
     * 将数据存入数据库
     * @param ruleForm
     * @return
     * @throws ParseException
     */
    @RequestMapping("/toDateForm")
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ResponseBody
    @CrossOrigin
    public String save(@RequestBody String ruleForm) throws ParseException {
        JSONObject jsonObject = JSONObject.fromObject(ruleForm);
        JSONObject data = jsonObject.getJSONObject("ruleForm");
        String beginDate = data.getString("beginDate");
        String beginTime = data.getString("beginTime");
        String endDate = data.getString("endDate");
        String endTime = data.getString("endTime");
        String startTimeStr = beginDate + " " + beginTime;
        String overTimeStr = endDate + " " + endTime;
        long startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimeStr).getTime();
        long overTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(overTimeStr).getTime();
        Data myData = new Data("",startTime, overTime, data.getString("desc"));
        dataService.addData(myData);
        return "success";
    }

    /**
     * 返回当前时间后开始的活动
     *
     * @return active json
     * @throws JsonProcessingException
     */
    @RequestMapping("/getData")
    @ResponseBody
    public LinkedHashSet<String> getData() throws JsonProcessingException {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        long time = new Date().getTime();
        List<Data> myData = dataService.selectData(time);
        // 排序,从小到大
        myData.sort(new Comparator<Data>() {
            @Override
            public int compare(Data t1, Data t2) {
                return (t1.getBeginTime() - t2.getBeginTime() > 0) ? 1 : -1;
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        for (Data data : myData) {
            LinkedMap map = new LinkedMap();
            map.put("desc", data.getDesc());
            String beginTime = sdf.format(new Date(data.getBeginTime()));
            String endTime = sdf.format(new Date(data.getEndTime()));
            map.put("beginTime", beginTime);
            map.put("endTime", endTime);
            String jsonStr = mapper.writeValueAsString(map);
            set.add(jsonStr);
        }
        return set;
    }
}
