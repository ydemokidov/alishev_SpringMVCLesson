import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.SpringMVCLesson.config.SpringConfig;
import org.example.SpringMVCLesson.controllers.ThirdController;
import org.example.SpringMVCLesson.model.MyPojo;
import org.example.SpringMVCLesson.model.MyPojoChild;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Mono;

import java.util.Date;

@RunWith(SpringRunner.class)
@WebMvcTest(ThirdController.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestClass {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testController() throws Exception {
        resultHandler handler = new resultHandler();
        for(int i=0;i<1000;i++) {
            mvc.perform(MockMvcRequestBuilders.post("/rest"))
                    .andDo(handler);
        }
    }

    class resultHandler implements ResultHandler {
        @Override
        public void handle(MvcResult mvcResult) throws Exception {
            System.out.println(mvcResult.getResponse().getContentAsString());
        }
    }

    @Test
    public void testJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MyPojoChild child = new MyPojoChild(new Date());
        MyPojo pojo = new MyPojo(1,"child",child);
        System.out.println(mapper.writeValueAsString(pojo));
    }
}
