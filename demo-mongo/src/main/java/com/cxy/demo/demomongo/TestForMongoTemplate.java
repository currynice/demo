//package com.cxy.demo.demomongo;
//
//import com.cxy.demo.demomongo.converter.MoneyReadConverter;
//import com.cxy.demo.demomongo.entity.Coffee;
//import com.mongodb.client.result.UpdateResult;
//import lombok.extern.slf4j.Slf4j;
//import org.joda.money.CurrencyUnit;
//import org.joda.money.Money;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.springframework.data.mongodb.core.query.Criteria.where;
//import static org.springframework.data.mongodb.core.query.Query.query;
//
///**
// * Description:   <br>
// * Date: 2020/5/11 12:04  <br>
// *
// * @author :cxy <br>
// * @version : 1.0 <br>
// */
//@Component
//@Slf4j
//public class TestForMongoTemplate implements ApplicationRunner {
//    //类似JdbcTemplate
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//
//
//    @Bean
//    public MongoCustomConversions mongoCustomConversions() {
//        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
//    }
//
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Coffee espresso = Coffee.builder()
//                .name("espresso")
//                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
//                .createTime(new Date())
//                .updateTime(new Date()).build();
//        Coffee saved = mongoTemplate.save(espresso);
//        log.info("Coffee espresso {}", saved);
//
//        Coffee newWithoutMoney = Coffee.builder()
//                .name("latte")
//                .createTime(new Date())
//                .updateTime(new Date()).build();
//        Coffee saved2 = mongoTemplate.save(newWithoutMoney);
//        log.info("Coffee latte {}", saved2);
//
//
//        Coffee cappuccino = Coffee.builder()
//                .name("cappuccino")
//                .createTime(new Date())
//                .price(Money.of(CurrencyUnit.of("CNY"), 35.5))
//                .updateTime(new Date()).build();
//        Coffee saved3 = mongoTemplate.save(cappuccino);
//
//        //查询
//        List<Coffee> list = mongoTemplate.find(
//                Query.query(Criteria.where("name").is("espresso")), Coffee.class);
//        log.info("Find {} documents", list.size());
//        list.forEach(c -> log.info("Coffee {}", c));
//
//        Thread.sleep(3000); // 为了显示更新时间
//        UpdateResult result = mongoTemplate.updateFirst(query(where("name").is("latte")),
//                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
//                        .currentDate("updateTime"),
//                Coffee.class);
//        log.info("Update Result: {}", result.getModifiedCount());
//        Coffee updateOne = mongoTemplate.findById(saved2.getId(), Coffee.class);
//        log.info("Update Result: {}", updateOne);
//
//
////        Coffee remove = mongoTemplate.findById(saved3.getId(), Coffee.class);
////        mongoTemplate.remove(remove);
//    }
//}
