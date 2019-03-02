package com.dali.DripChain.controller;

import com.dali.DripChain.entity.Alarm;
import com.dali.DripChain.entity.Announcement;
import com.dali.DripChain.entity.Company;
import com.dali.DripChain.entity.Device;
import com.dali.DripChain.entity.DeviceGroup;
import com.dali.DripChain.service.AndService;
import com.dali.DripChain.tools.TransEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/AndUser")
class AndController {
    private static Logger log= LoggerFactory.getLogger(AndController.class);
    @Resource
    private AndService andService;

    @Autowired//自动装配
    private HttpServletRequest request;
    private RequestMethod produces;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String username, String password){
        log.debug("123456789");
        return "login";
    }
//    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
//    public String doLogin(String username, String password){
//        User user=new User();
//        user.setsUsername(username);
//        user.setsPassword(password);
//        AndUserService andUserService=new AndUserService();
//        User Loginer= andUserService.userLogin(user);
//        log.debug(Loginer.getsUsername());
//        log.debug(Loginer.getsEmail());
//        log.debug(Loginer.getsEmail());
//        return "login";
//    }

    @RequestMapping("/doLogin")
    @ResponseBody//注解返回字符串
    public String doLogin(String username, String password){


        log.debug("********");
        log.debug(request.getSession().getId());

        Company company=new Company();
        company.setsUserName(username);
        company.setsPassword(password);
        Company Loginer= andService.userLogin(company);
        if (Loginer==null) {
            return String.valueOf(-1);
        }
        request.getSession().setAttribute("User",Loginer);
        return String.valueOf(Loginer.getId());
    }

    @RequestMapping(value = "/getAlarm")
    @ResponseBody
    public String getAlarm() throws Exception
    {
        Company company;
        if (request.getSession().getAttribute("User")!=null){
            company= (Company) request.getSession().getAttribute("User");
        }
        else {
            return "-1";
        }
        List<Alarm> alarms=new ArrayList<>();
        alarms=andService.getAlarmlist(company);
        String json ="123";
        ObjectMapper mapper=new ObjectMapper();
        //使时间数据格式可以传输
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        //使Jackson适用于unicode编码
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        //使jackson适用于hibernate5
        mapper.registerModule(new Hibernate5Module());
        try {
            json= mapper.writeValueAsString(alarms);

        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(alarms.size());
        try {
            CollectionLikeType javaType= mapper.getTypeFactory().constructCollectionLikeType(List.class, Alarm.class);
            alarms=mapper.readValue(json, javaType);
            System.out.println("******");
            System.out.println(alarms.get(0).getdUpdateTime());
        }catch (Exception e) {
            e.printStackTrace();
        }
        TransEncoding transEncoding=new TransEncoding();
//        json=transEncoding.UnicodeToUtf(json);
//        System.out.println(json);
        return String.valueOf(json);

    }

    @RequestMapping(value="/getDevice")
    @ResponseBody
    public String getDevice() throws Exception{
        Company company=null;
        if(request.getSession().getAttribute("User")!=null){
            company= (Company) request.getSession().getAttribute("User");
        }else{
            return "-1";
        }
        List<Device> devices=new ArrayList<Device>();
        devices=andService.getDevicelist(company);
        String json ="123";
        ObjectMapper mapper=new ObjectMapper();
        //使时间数据格式可以传输
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        //使Jackson适用于unicode编码
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        //使jackson适用于hibernate5
        mapper.registerModule(new Hibernate5Module());
        try {
            json= mapper.writeValueAsString(devices);

        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            CollectionLikeType javaType= mapper.getTypeFactory().constructCollectionLikeType(List.class, Alarm.class);
            devices=mapper.readValue(json, javaType);
            System.out.println("******");
        }catch (Exception e) {
            e.printStackTrace();
        }
        TransEncoding transEncoding=new TransEncoding();
//        json=transEncoding.UnicodeToUtf(json);
//        System.out.println(json);
        return String.valueOf(json);
    }

    @RequestMapping("/doGetData")
    @ResponseBody//注解返回字符串
    public String doGetData(String DevId){
        Company company=null;
        if(request.getSession().getAttribute("User")!=null){
            company= (Company) request.getSession().getAttribute("User");
        }else{
            return "-1";
        }
        List<String> list=new ArrayList<String>();
        list=andService.getDataPrepare(Integer.parseInt(DevId),company);
        ObjectMapper mapper=new ObjectMapper();
        String json =null;
        try {
            json=mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("addDevice")
    @ResponseBody
    public String addDevice(String deviceName, BigDecimal deviceLon,BigDecimal deviceLat, String deviceAdd){
        Company company=null;
        if(request.getSession().getAttribute("User")!=null){
            company= (Company) request.getSession().getAttribute("User");
        }else{
            return "-1";
        }
        DeviceGroup deviceGroup=new DeviceGroup();
        deviceGroup=andService.getDeviceGroup(1);
        if(deviceGroup==null) {
            return "-2";
        }

        Device device=new Device();
        device.setCompany(company);
        device.setiDeviceStatus(0);
        device.setiDeviceType(0);
        device.setDeviceGroup(deviceGroup);
        device.setdDeviceLongitude(deviceLon);
        device.setdDeciceLatitude(deviceLat);
        device.setsDeviceAddress(deviceAdd);
        device.setsDeviceName(deviceName);
        System.out.println(device.getdDeviceLongitude());
        System.out.println("hellohello");
        int iReturn=andService.addDevice(device);
        return String.valueOf(iReturn);
    }

    /**
     * 安卓发来的请求头部解析
     * @param request
     * @param sessionId
     * @param postBody
     * @return
     */
    private @ResponseBody LinkedHashMap<String, Object> receiveHeaders(
            HttpServletRequest request,
            @CookieValue(value = "JSESSIONID", required = false)
                    String sessionId,@RequestBody String postBody) {
        LinkedHashMap<String, Object> result=new LinkedHashMap<String, Object>();
        Map<String, Object> header=new HashMap<String, Object>();
        Map<String, Object> params=new HashMap<String, Object>();
        result.put("postBody", postBody);
        @SuppressWarnings("rawtypes")
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            header.put(key, value);
        }
        result.put("headers", header);
        result.put("JSESSIONID", sessionId);
        log.debug("headers", header);
        log.debug("JSESSIONID", sessionId);
        System.out.println(result.toString());
        return result;
    }


    @RequestMapping("doRegister")
    @ResponseBody
    public int doRegister(String telephone,String userPassword){
        Company company=new Company();
        company.setsUserName(telephone);
        company.setsPassword(userPassword);
        company.setsGoc("671000");
        company.setsCompanyTelephone(telephone);
        return andService.addCompany(company);
    }

    @RequestMapping("getAnnouncement")
    @ResponseBody
    public String getAnnouncement(){
        List<Announcement> list=andService.getAnnouncements();
        String json="-2";
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        //使Jackson适用于unicode编码
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        //使jackson适用于hibernate5
        mapper.registerModule(new Hibernate5Module());
        try {
            json= mapper.writeValueAsString(list);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("getCompany")
    @ResponseBody
    public String getCompany() {
        Company company = null;
        if (request.getSession().getAttribute("User") != null) {
            company = (Company) request.getSession().getAttribute("User");
        } else {
            return "-1";
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        //使Jackson适用于unicode编码
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        //使jackson适用于hibernate5
        mapper.registerModule(new Hibernate5Module());
        try {
            return mapper.writeValueAsString(company);

        }catch (Exception e) {
            e.printStackTrace();
            return "-2";
        }
    }

    @RequestMapping("updateCompany")
    @ResponseBody
    public String updateCompany(String sCompanyTelephone,String sGoc,String sCompanyAddress,String sCompanyType,String sCompanyName,String sEmail){
        Company company = null;
        if (request.getSession().getAttribute("User") != null) {
            company = (Company) request.getSession().getAttribute("User");
        } else {
            return "-1";
        }

        if(!sCompanyTelephone.equals("")){
            company.setsCompanyTelephone(sCompanyTelephone);
        }
        if(!sGoc.equals("")){
            company.setsGoc(sGoc);
        }
        if(!sCompanyAddress.equals("")){
            company.setsCompanyAddress(sCompanyAddress);
        }
        if(!sCompanyType.equals("")){
            company.setsCompanyType(sCompanyType);
        }
        if(!sCompanyName.equals("")){
            company.setsCompanyName(sCompanyName);
        }
        if(!sEmail.equals("")){
            company.setsEmail(sEmail);
        }

        andService.updateCompany(company);

        return "1";
    }

}
