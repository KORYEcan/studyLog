package me.shinsunyoung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller //컨트롤럴라는 것을 명시적으로 표시
public class ExampleController {


    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {  //Model-> 뷰로 데이터를 넘겨주는 모델 객체
        Person examplePerson= new Person();
        examplePerson.setId(1L);
        examplePerson.setName("김도연");
        examplePerson.setAge(28);
        examplePerson.setHobbies(List.of("곱창먹기","대창먹기","막창먹기","예병창괴롭히기","에병창짜증폭발시키기"));

        model.addAttribute("person", examplePerson); // Person 객체 저장
        model.addAttribute("today", LocalDate.now());

        return "example";


    }



    @Setter
    @Getter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
