package com.minilab.googleguava;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;


/**
 * @Author huangyulu
 * @Date 2026/2/4 18:07
 * @Description
 */
public class GuavaTest {

    @Test
    public void testListContents() {

        List<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");

//        assert names.contains("A");
//        assert names.contains("E");



        assertThat(names).contains("A");
        assertThat(names).contains("D");




    }
}
