package souza.marlon.worstmovie.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import souza.marlon.worstmovie.dto.IntervalAward;
import souza.marlon.worstmovie.dto.TopIntervalAwards;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ActiveProfiles("test")
class IntervalAwardsControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testGetTopInterval() throws Exception {
        var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/interval-awards"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var response = mvcResult.getResponse();

        var topIntervalAwards = objectMapper.readValue(response.getContentAsString(), TopIntervalAwards.class);

        var expectedTopIntervalAwards = getExpectedTotalAwards();

        assertEquals("application/json", response.getContentType());
        assertEquals(expectedTopIntervalAwards.max().getFirst(), topIntervalAwards.max().getFirst());
        assertEquals(expectedTopIntervalAwards.max().getLast(), topIntervalAwards.max().getLast());
        assertEquals(expectedTopIntervalAwards.min().getFirst(), topIntervalAwards.min().getFirst());
        assertEquals(expectedTopIntervalAwards.min().getLast(), topIntervalAwards.min().getLast());
    }

    private TopIntervalAwards getExpectedTotalAwards() {
        return new TopIntervalAwards(
                List.of(
                        new IntervalAward("Joel Silver", 1L, 1990L, 1991L),
                        new IntervalAward("Bo Derek", 6L, 1984L, 1990L)
                ),
                List.of(
                        new IntervalAward("Matthew Vaughn", 13L, 2002L, 2015L),
                        new IntervalAward("Buzz Feitshans", 9L, 1985L, 1994L)
                )
        );
    }
}