package ru.job4j.ttd;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.ttd.SimpleGenerator;
import ru.job4j.ttd.Template;


import java.util.HashMap;
import java.util.Map;


import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleGeneratorTest {


        @Test
        public void whenTakeTextWithDataShouldReplaceParamsToData2() {
            //assign
            Template template = new SimpleGenerator();
            String text = "Hello, ${name} ${surname}";
            Map<String, String> data = new HashMap<>();
            data.put("${name}", "Ivan");
            data.put("${surname}", "Pupkin");
            String expect = "Hello, Ivan Pupkin";
            //act
            String result = template.generate(text, data);

            //action
            Assert.assertThat(result, is(expect));
           // assertEquals(checked, result);
        }
        @Test
        public void whenTakeTextWithDataShouldReplaceParamsToData1() {
            //assign
            Template template = new SimpleGenerator();
            String text = "Hello, ${name}";
            Map<String, String> data = new HashMap<>();
            data.put("${name}", "Dima");
            String expect = "Hello, Dima";
            //act
            String result = template.generate(text, data);

            //action
            Assert.assertThat(result, is(expect));
        }
        @Test
        public void whenHaveUnnecessaryParamThenException() throws Exception {
            String text = "${hello}, ${name}!";
            Map<String, String> params = new HashMap<>();
            params.put("${name}", "World");
            params.put("${hello}", "Hello");
            params.put("${param}", "Param");
            String expect = "Hello, World!";
            Template template = new SimpleGenerator();
            try {
                String result = template.generate(text, params);
                Assert.assertThat(result, is(expect));
            } catch (Exception thrown) {
                System.out.println(thrown.getMessage());
                assertNotEquals("", thrown.getMessage());
            }
        }

        @Test(expected = Exception.class)
        public void whenTakeTextWithDataShouldReplaceParamsToData3Error() throws Exception {
            //assign
            Template template = new SimpleGenerator();
            String text = "Hello, ${name} ${surname}";
            Map<String, String> data = new HashMap<>();
            data.put("${name}", "Oleg");
            String expect = "Hello, Oleg";
            //act
            String result = template.generate(text, data);

            //action
            Assert.assertThat(result, is(expect));
        }

        @Test
        public void whenTakeTextWithDataShouldReplaceParamsToDataDuplicateStringName() {
            //assign
            Template template = new SimpleGenerator();
            String text = "Hello, my name ${name}";
            Map<String, String> data = new HashMap<>();
            data.put("${name}", "Petr");
            String expect = "Hello, my name Petr";
            //act
            String result = template.generate(text, data);

            //action
            Assert.assertThat(result, is(expect));
        }

    }
