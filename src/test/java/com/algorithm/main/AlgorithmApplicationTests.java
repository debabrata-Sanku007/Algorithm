package com.algorithm.main;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.algorithm.exception.NegativeSkillSetException;
import com.algorithm.model.Player;
import com.algorithm.service.AlgorithmApplicationService;

@SpringBootTest
class AlgorithmApplicationTests {

	private MockMvc mockMvc;
	@Spy
	AlgorithmApplicationService service = new AlgorithmApplication();
	@InjectMocks
	AlgorithmApplication algorithmApplication;
	List<Player> forSum = new LinkedList<>();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(algorithmApplication).build();
		forSum.add(new Player(4, false));
		forSum.add(new Player(6, false));
		forSum.add(new Player(-6, false));
	}

	@Test
	public void remainingPlayerSkillSum_Test() {
		when(service.remainingPlayerSkillSum(forSum)).thenReturn(10);
		assertEquals(10, service.remainingPlayerSkillSum(forSum));
	}

	@Test
	public void takingInputFromUser_exceptionTest() throws NegativeSkillSetException {
		Mockito.doThrow(new NegativeSkillSetException("You Can not set a skill number with (-) negative value..")).when(service).takingInputFromUser();
        assertThrows(NegativeSkillSetException.class,()->service.takingInputFromUser());
	}
	@Test
	public void takingInputFromUser_scannerClassTest() throws NegativeSkillSetException {
		when(service.takingInputFromUser()).thenReturn(forSum); 
        assertEquals(forSum, service.takingInputFromUser());
     
	}
	
}
