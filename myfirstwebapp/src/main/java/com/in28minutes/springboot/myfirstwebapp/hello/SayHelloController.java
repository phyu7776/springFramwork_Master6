package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! What are you learning today?";
    }

    // 무식 하게 HTML return 할 경우 매우 번거롭고 귀찮다.
    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append(  "<head>");
        sb.append(      "<title> My first HTML Page </title>");
        sb.append(  "</head>");
        sb.append(  "<body>");
        sb.append(      "My first html page with body");
        sb.append(  "</body>");
        sb.append("</html>");

        return sb.toString();
    }

    // 위 같은 문제를 해결하고자 View 를 return함 (Java Server Page)
    // src/main/resources/META-INF/resources/WEB-INF/jsp

    /**
     *  Client /say-hello-jsp
     *  Server SayHelloController - sayHelloJsp method
     *                                         ㄴ View Resolver (application.properties 에서 설정)
     *                                            접두어 = "/WEB-INF/jsp/"
     *                                            접미어 = ".jsp"
     *
     *                                           접두어 + sayHello (return) + 접미어
     *                                           (/WEB-INF/jsp/sayHello.jsp)
     *                                           ^ 해당 경로에 있는 jsp 파일 return
     *
     * @return
     */
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }
}
