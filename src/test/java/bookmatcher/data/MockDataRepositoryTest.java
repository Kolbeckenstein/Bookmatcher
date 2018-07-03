package bookmatcher.data;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockDataRepositoryTest{
    public MockDataRepository testRepo = new MockDataRepository();

    @Test
    public void getUserJSONBlob(){
        assertTrue(testRepo.getUserJSONBlob().contains("\"username\":\"Testerino\""));
        assertTrue(testRepo.getUserJSONBlob().contains("\"books\":[\"Dune\",\"Flowers for Algernon\",\"Watership Down\"]"));
    }

    @Test
    public void getUsername(){
        assertTrue(testRepo.getUsername().equals("Testerino"));
    }
}