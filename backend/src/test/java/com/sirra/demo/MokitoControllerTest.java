package com.sirra.demo;

import com.sirra.demo.controler.IndividuControler;
import com.sirra.demo.dao.IndividuDao;
import com.sirra.demo.model.Individu;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MokitoControllerTest {
        @InjectMocks
        private IndividuControler individuControler;
        @Mock
        private IndividuDao individuDao;
        @Autowired
        private MockMvc mvc;
        @Before("")
        public void init() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testGetIndividuById(){
            Individu individu = new Individu();
            individu.setId(1);
            when(individuDao.findById(1)).thenReturn(individu);

            Individu individu1 = individuControler.afficherUnIndividu(1);
            verify(individuDao).findById(1);

            assertEquals(1,individu1.getId());
        }
        @Test
        public void testFindAll() throws Exception{
            Individu individu = new Individu();
            Individu individu2 = new Individu();
            individu2.setId(3);
            individu.setId(2);
            List<Individu> allIndividu = Arrays.asList(individu,individu2);
            given(individuDao.findAll()).willReturn(allIndividu);
            List<Individu> individu1 = individuControler.listeEmployes();
            assertEquals(2,allIndividu.size());
        }


}
