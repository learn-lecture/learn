package com.example.restapi.contoller;

import com.example.restapi.Model.BookQueryParam;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController // RestAPI를 처리하는 컨트롤러
@RequestMapping("/api") // .../api 로 시작하는 주소를 처리함.
public class RestApiController {
    // Get처리를 위한 어노테이션
    // ../api/hello 를 요청하면 hello() 컨트롤러 실행
    // http://localhost:8080/api/hello
    @GetMapping(path = "/hello")
    public String hello() {
        var html = "<html><body> <h1> Hello Spring Boot</h1></body></html>";
        return html;
    }

    //PATH Valiable을 받는 방법 {}로 묶어서 parameter로 받음
    // http://localhost:8080/api/echo/hi/age/10/is-man/true
    // {parameter}와 method의 parameter의 이름이 다를 경우
    // @PathVariable(name = "parameter") var param 으로 Method param 수정
    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            @PathVariable String message,
            // PDT가 아닌 UDDT의 INTEGER를 사용하면 NULL을 입력받을 수 있음.
            // PathVariable이 NULL 이면 404 NOT FOUND 이므로 NULL은 입력 받을 수 없음.
            // 그러므로 PDT를 사용해서 입력받는게 적절. 근데 message는?
            @PathVariable int age,
            @PathVariable boolean isMan
    ) {
        System.out.println("echo message : " + message);
        System.out.println("echo message : " + age);
        System.out.println("echo message : " + isMan);
        // TODO 대문자로 변환해서 RETURN
        return message.toUpperCase();
    }

    @GetMapping(path = "/book")
    public void queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear, // 추천
            @RequestParam(name=  "issued-month") String issuedMonth, // 추천
            @RequestParam String issued_day // 비추하는 방법
    ){
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issued_day);
    }
    // 객체로 받을 때는 카멜표기로 통일해도 좋음.
    // http://localhost:8080/api/book2?category=IT&issuedYear=2023&issuedMonth=01&issuedDay=31
    @GetMapping(path = "/book2")
    public void queryParam2(BookQueryParam bookQueryParam){
        System.out.println(bookQueryParam);
    }

    // TODO Parameter 2가지를 int형태로 받아서 두 수의 덧셈 곱셈을 출력
    // http://localhost:8080/api/product?a=10&b=20
    @GetMapping(path = "/product")
    public String prudouct(@RequestParam int a, @RequestParam int b) {
        return Integer.toString(a*b);
    }

    // TODO String 타입 외에 boolean 타입도 받아보기
    // http://localhost:8080/api/booleantest?a=True
    @GetMapping(path = "/booleantest")
    public String booleantest(@RequestParam boolean a) {
        if(a) return "True";
        return "False";
    }
}
