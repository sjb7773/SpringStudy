package JB.JBspring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        //html로 넘기는 key와 value
        model.addAttribute("data", "hello!!");
        //대응(mapping)되는 templates/hello.html로 가서 렌더링해라
        return "hello";
    }

    @GetMapping("hello-mvc")
    //외부에서 파라미터 받는다. ->Model에 담으면 view에서 렌더링할때 씀
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    //api 방식
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //요거 때문에 api방식 많이씀(객체반환방식->JSON)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        //JSON으로 return (key-value구조로 되어있음)
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
