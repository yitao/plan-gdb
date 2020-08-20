package com.simile.plan.arango.test;

import com.alibaba.fastjson.JSON;
import com.simile.plan.arango.api.model.*;
import com.simile.plan.arango.api.service.ArangoUserService;
import com.simile.plan.arango.ArangoSearchServiceImpl;
import com.simile.plan.arango.ArangoUserServiceImpl;
import com.simile.plan.arango.api.service.ArangoSearchService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by yitao on 2020/07/21
 */
public class ArangoSearchServiceTest {

    @Test
    public void testSearch() {
        ArangoDataSourceConfiguration dataSourceConfig = new ArangoDataSourceConfiguration("192.168.1.36", 8529);
        ArangoSearchService arangoSearchService = new ArangoSearchServiceImpl(dataSourceConfig);

        SearchData searchData = new SearchData("beijing_poc", "person_new", 1, 1);
        SearchResult searchResult = arangoSearchService.findByPage(searchData);
        System.out.println(JSON.toJSONString(searchResult));
        //{"hh":"336978626","csrq":"1999-12-30 00:00:00","object_key":"19376681","whcd":"本科","keys":"19376681","_rev":"_axNJB6e--A","_tags":["海致标签","9月18日","事件标签"],"zjxy":"萨满教","pic":"archive/person/19376681/pic/19376681.jpg","_key":"19376681","uuid":"38fdd20483c66c83bed79da5461bad0f","cjsj":"2019-08-29 00:00:00","hjdqh":"广西水口","@version":"1","zzmm":"群众","id":"38fdd20483c66c83bed79da5461bad0f","key":"420011199912301112","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","yhzgx":"二子","xb":"男性","mz":"蒙古族","version":"1569131400008","mzmc":"蒙古族","fwcs":"水光电子厂","@timestamp":"2020-06-23T18:29:25.009Z","hjxz":"广西水口","xm":"赵午光","hzxm":"张大大","xbmc":"男性","_id":"person_new/19376681","jgdq":"广西水口","jg":"广西水口","zy":"工人"}
        //{"rows":[{"hh":"336978626","csrq":"1999-12-30 00:00:00","object_key":"19376681","whcd":"本科","keys":"19376681","_rev":"_axNJB6e--A","_tags":["海致标签","9月18日","事件标签"],"zjxy":"萨满教","pic":"archive/person/19376681/pic/19376681.jpg","_key":"19376681","uuid":"38fdd20483c66c83bed79da5461bad0f","cjsj":"2019-08-29 00:00:00","hjdqh":"广西水口","@version":"1","zzmm":"群众","id":"38fdd20483c66c83bed79da5461bad0f","key":"420011199912301112","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","yhzgx":"二子","xb":"男性","mz":"蒙古族","version":"1569131400008","mzmc":"蒙古族","fwcs":"水光电子厂","@timestamp":"2020-06-23T18:29:25.009Z","hjxz":"广西水口","xm":"赵午光","hzxm":"张大大","xbmc":"男性","_id":"person_new/19376681","jgdq":"广西水口","jg":"广西水口","zy":"工人"},{"hh":"20182019","csrq":"1989-03-30 00:00:00","object_key":"331114189911111739","whcd":"专科","keys":"331114189911111739","_rev":"_axNJB9----","_tags":["涉毒人员"],"zjxy":"无","pic":"archive/person/331114189911111739/pic/331114189911111739.jpg","_key":"331114189911111739","uuid":"d51fa327ab22222767bfbcbec017f13f","cjsj":"2019-08-29 00:00:00","hjdqh":"湖北武汉","@version":"1","zzmm":"群众","id":"d51fa327ab22222767bfbcbec017f13f","key":"411521197811228913","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","xb":"男性","mz":"土家族","version":1569141600015,"mzmc":"土家族","@timestamp":"2020-06-23T18:29:25.791Z","hjxz":"武汉市青山区","xm":"赵晓珲","xbmc":"男性","_id":"person_new/331114189911111739","jgdq":"湖北武汉","jg":"湖北武汉","zy":"教师"},{"hh":"H09013","csrq":"1986-09-08","zxzt":"否","object_key":"421083198609083021","keys":"421083198609083021","_rev":"_axNJDfu--i","_tags":["近期频繁来京","昼伏夜出","频繁往返武汉","高危人员","刑事前科","汉族"],"zjxy":"","pic":"archive/person/421083198609083021/pic/421083198609083021.jpeg","_key":"421083198609083021","uuid":"af364507108e1dc59b0b4be7d9b941af","hjdqh":"广西水口","@version":"1","zzmm":"共产党","id":"af364507108e1dc59b0b4be7d9b941af","key":"473433189514181417","rksj":"2019-01-10","byzk":"未服兵役","yhzgx":"夫妻","swzt":"否","mz":"汉族","version":1583115852770,"mzmc":"汉族","fwcs":"","xjgajgmc":"湖北省洪湖市公安局","@timestamp":"2020-06-23T18:29:42.127Z","hyzk":"未婚","hjxz":"湖北省洪湖市曹市镇六合村2号","xm":"李惠丽","cym":"李惠丽","hzxm":"张小伟","xbmc":"女性","_id":"person_new/421083198609083021","jgdq":"湖北洪湖市","jg":"湖北洪湖市","zy":"无业"},{"hh":"20182022","csrq":"1980-12-30 00:00:00","object_key":"153541189818181616","whcd":"本科","keys":"153541189818181616","_rev":"_axNJB7a--A","_tags":["9月18日","事件标签","海致标签"],"zjxy":"无","pic":"archive/person/153541189818181616/pic/153541189818181616.jpg","_key":"153541189818181616","uuid":"7b9f87d7dbf9132ac42898ac0f102723","cjsj":"2019-08-29 00:00:00","hjdqh":"北京海淀","@version":"1","zzmm":"群众","id":"7b9f87d7dbf9132ac42898ac0f102723","key":"420101195004040011","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","xb":"男性","mz":"汉族","version":"1569131400008","mzmc":"汉族","@timestamp":"2020-06-23T18:29:25.013Z","hjxz":"768创意园","xm":"赵中锴","xbmc":"男性","_id":"person_new/153541189818181616","jgdq":"北京","jg":"北京","zy":"工人"},{"hh":"336978626","csrq":"1999-12-30 00:00:00","object_key":"153834189318143614","whcd":"本科","keys":"153834189318143614","_rev":"_axNJB8K--B","_tags":["事件标签","海致标签","9月18日"],"zjxy":"藏传佛教","pic":"archive/person/153834189318143614/pic/153834189318143614.jpg","_key":"153834189318143614","uuid":"f68317db507f9708726807eccb40cd1f","cjsj":"2019-08-29 00:00:00","hjdqh":"广西水口","@version":"1","zzmm":"民主党","id":"f68317db507f9708726807eccb40cd1f","key":"420011199912301113","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","yhzgx":"长女","xb":"女性","mz":"壮族","version":"1569131400008","mzmc":"壮族","fwcs":"朝阳机械厂","@timestamp":"2020-06-23T18:29:25.010Z","hjxz":"广西水口","xm":"赵山川","hzxm":"张大大","xbmc":"女性","_id":"person_new/153834189318143614","jgdq":"广西水口","jg":"广西水口","zy":"厨师"},{"hh":"20182021","csrq":"1952-03-15 00:00:00","object_key":"141115187819154847","whcd":"研究生","keys":"141115187819154847","_rev":"_axNJB8K--_","_tags":["事件标签","9月18日","海致标签"],"zjxy":"无","pic":"archive/person/141115187819154847/pic/141115187819154847.jpg","_key":"141115187819154847","uuid":"b2ebf91f12e7cfe83fb39217599956d1","cjsj":"2019-08-29 00:00:00","hjdqh":"北京海淀","@version":"1","zzmm":"群众","id":"b2ebf91f12e7cfe83fb39217599956d1","key":"422622195203150302","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","xb":"女性","mz":"汉族","version":"1569131400008","mzmc":"汉族","@timestamp":"2020-06-23T18:29:25.008Z","hjxz":"768创意园","xm":"赵威皓","xbmc":"女性","_id":"person_new/141115187819154847","jgdq":"北京","jg":"北京","zy":"助理"},{"hh":"336978626","csrq":"1988-04-12","zxzt":"否","object_key":"350624198209051010","whcd":"大学","keys":"350624198209051010","_rev":"_axNJDfu--F","_tags":["男性","汉族","身高一米七以上","涉毒人员","频繁入住酒店","近期频繁来京"],"zjxy":"","pic":"archive/person/350624198209051010/pic/350624198209051010.jpg","_key":"350624198209051010","uuid":"b21907e2c8077c8a4de9a676f363d872","hjdqh":"河南省郑州市金水区站前街20号","@version":"1","zzmm":"群众","id":"b21907e2c8077c8a4de9a676f363d872","key":"47331318871135411Y","rksj":"2019-01-11","byzk":"未服兵役","yhzgx":"户主","swzt":"否","xb":"男","mz":"汉族","version":1583115852770,"mzmc":"汉族","fwcs":"","xjgajgmc":"河南省郑州市公安局","@timestamp":"2020-06-23T18:29:42.126Z","hyzk":"已婚","hjxz":"湖北省洪湖市曹市镇六合村2号","xm":"刘志军","cym":"刘志军","hzxm":"刘志军","xbmc":"男","_id":"person_new/350624198209051010","jgdq":"河南省驻马店市上蔡县","jg":"河南省驻马店市上蔡县","zy":"教师"},{"hh":"20182020","csrq":"1981-01-19 00:00:00","object_key":"311113188517346438","whcd":"博士","keys":"311113188517346438","_rev":"_axNJB7G---","_tags":["事件标签","9月18日","海致标签"],"zjxy":"无","pic":"archive/person/311113188517346438/pic/311113188517346438.jpg","_key":"311113188517346438","uuid":"90b0a9b727507e77d09b4b257a817669","cjsj":"2019-08-29 00:00:00","hjdqh":"湖北武汉","@version":"1","zzmm":"群众","id":"90b0a9b727507e77d09b4b257a817669","key":"413029198101093815","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","xb":"男性","mz":"土家族","version":"1569131400008","mzmc":"土家族","@timestamp":"2020-06-23T18:29:25.012Z","hjxz":"武汉市青山区","xm":"赵绚海","xbmc":"男性","_id":"person_new/311113188517346438","jgdq":"湖北武汉","jg":"湖北武汉","zy":"学者"},{"hh":"20182026","csrq":"1980-07-23 00:00:00","object_key":"311534189111351153","whcd":"博士","keys":"311534189111351153","_rev":"_axNJB7G--A","_tags":["涉毒人员"],"zjxy":"无","pic":"archive/person/311534189111351153/pic/311534189111351153.jpg","_key":"311534189111351153","uuid":"b1666d8b456072ebc2ec414ce5868a1b","cjsj":"2019-08-29 00:00:00","hjdqh":"黑龙江哈尔滨","@version":"1","zzmm":"群众","id":"b1666d8b456072ebc2ec414ce5868a1b","key":"441502198007232111","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","xb":"男性","mz":"白族","version":1569141600015,"mzmc":"白族","@timestamp":"2020-06-23T18:29:25.795Z","hjxz":"道里区","xm":"赵吾行","xbmc":"男性","_id":"person_new/311534189111351153","jgdq":"黑龙江哈尔滨","jg":"黑龙江哈尔滨","zy":"企业家"},{"hh":"20182023","csrq":"1992-09-01 00:00:00","object_key":"331395188118156138","whcd":"博士","keys":"331395188118156138","_rev":"_axNJB7G--C","_tags":["涉毒人员"],"zjxy":"无","pic":"archive/person/331395188118156138/pic/331395188118156138.jpg","_key":"331395188118156138","uuid":"541b6ba43682e44e7ddc69d850701687","cjsj":"2019-08-29 00:00:00","hjdqh":"北京海淀","@version":"1","zzmm":"群众","id":"541b6ba43682e44e7ddc69d850701687","key":"421122199209016833","rksj":"2019-08-29 00:00:00","byzk":"未服兵役","xb":"男性","mz":"汉族","version":1569142800009,"mzmc":"汉族","@timestamp":"2020-06-23T18:29:26.998Z","hjxz":"768创意园","xm":"赵腾","xbmc":"男性","_id":"person_new/331395188118156138","jgdq":"北京","jg":"北京","zy":"司机"}],"total":20}
    }

    @Test
    public void testUpdate() {
        Map<String, Object> row = JSON.parseObject("{\"hh\":\"336978626\",\"csrq\":\"1999-12-30 00:00:00\",\"object_key\":\"193766811\",\"whcd\":\"本科\",\"keys\":\"193766811\",\"_rev\":\"_axNJB6e--A\",\"_tags\":[\"YT海致标签\",\"9月18日\",\"事件标签\"],\"zjxy\":\"萨满教\",\"pic\":\"archive/person/19376681/pic/19376681.jpg\",\"_key\":\"193766811\",\"uuid\":\"38fdd20483c66c83bed79da5461bad0f\",\"cjsj\":\"2019-08-29 00:00:00\",\"hjdqh\":\"YT广西水口\",\"@version\":\"1\",\"zzmm\":\"群众\",\"id\":\"38fdd20483c66c83bed79da5461bad0f\",\"key\":\"420011199912301112\",\"rksj\":\"2019-08-29 00:00:00\",\"byzk\":\"未服兵役\",\"yhzgx\":\"二子\",\"xb\":\"男性\",\"mz\":\"蒙古族\",\"version\":\"1569131400008\",\"mzmc\":\"蒙古族\",\"fwcs\":\"水光电子厂\",\"@timestamp\":\"2020-06-23T18:29:25.009Z\",\"hjxz\":\"广西水口\",\"xm\":\"赵午光\",\"hzxm\":\"张大大\",\"xbmc\":\"男性\",\"_id\":\"person_new/193766811\",\"jgdq\":\"广西水口\",\"jg\":\"广西水口\",\"zy\":\"工人\"}").getInnerMap();
        row.remove("_rev");
        row.remove("_id");
        List<Map<String, Object>> rows = new ArrayList<>();
        rows.add(row);

        ArangoDataSourceConfiguration dataSourceConfig = new ArangoDataSourceConfiguration("192.168.1.36", 8529);
        ArangoUserService arangoUserService = new ArangoUserServiceImpl(dataSourceConfig);

        WriteData<Map<String, Object>> writeData = new WriteData<>("beijing_poc", "person_new", rows);
        WriteResult wr = arangoUserService.save(writeData);
        System.out.println(JSON.toJSONString(wr));

        ArangoSearchService arangoSearchService = new ArangoSearchServiceImpl(dataSourceConfig);
        SearchData searchData = new SearchData("beijing_poc", "person_new", 1, 1);
        SearchResult searchResult = arangoSearchService.findByPage(searchData);
        System.out.println(JSON.toJSONString(searchResult));
    }

    @Test
    public void testDelete(){
        ArangoDataSourceConfiguration dataSourceConfig = new ArangoDataSourceConfiguration("192.168.1.36", 8529);
        ArangoUserService arangoUserService = new ArangoUserServiceImpl(dataSourceConfig);
        List<String> keys = new ArrayList<>();
        keys.add("193766811");
        DeleteData deleteData = new DeleteData("beijing_poc", "person_new", keys);
        DeleteResult dr = arangoUserService.delete(deleteData);
        System.out.println(JSON.toJSONString(dr));
    }
}
