package org.example.SpringMVCLesson.controllers;

import org.example.SpringMVCLesson.model.MyPojo;
import org.example.SpringMVCLesson.model.MyPojoChild;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.Date;

@RestController
public class ThirdController {
    @PostMapping("/rest")
    public Mono<ResponseEntity<MyPojo>> handleRest() throws InterruptedException {
        System.out.println("Post Accepted...");
        MyPojoChild child = new MyPojoChild(new Date());
        MyPojo pojo = new MyPojo(1,"child",child);
        System.out.println("Pojo Created");
        return null;
    }

    private String superLongMethod() throws InterruptedException {
        System.out.println("Super Long Method");
        Thread.sleep(3000);
        return "ok";
    }
}
